package com.leavemessage.controller;

import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;


import com.leavemessage.model.*;
import com.mem.model.MemVO;


public class LeaveMessageServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest req,HttpServletResponse res)
            throws ServletException, IOException{
        doPost(req,res);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)
            throws ServletException,IOException{
        
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        
        if("getAll".equals(action)) {
            //�d��
            LeaveMessageDAO dao = new LeaveMessageDAO();
            List<LeaveMessageVO> list = dao.getAll();
            
            session.setAttribute("list", list);
            String url = "/leavemessage/listAllLeaveMessage2_getFromSession.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���listAllEmp2_getFromSession.jsp
            successView.forward(req, res);
            return;
        }
        if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

                List<String> errorMsgs = new LinkedList<String>();
                // Store this set in the request scope, in case we need to
                // send the ErrorPage view.
                req.setAttribute("errorMsgs", errorMsgs);

                try {
                    /*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
                    String str = req.getParameter("lm_no");
                    if (str == null || (str.trim()).length() == 0) {
                        errorMsgs.add("�п�J�d���s��");
                    }
                    // Send the use back to the form, if there were errors
                    if (!errorMsgs.isEmpty()) {
                        RequestDispatcher failureView = req
                                .getRequestDispatcher("/leavemessage/leavemessage_select_page.jsp");
                        failureView.forward(req, res);
                        return;// �{�����_
                    }

                    String lm_no = null;
                    try {
                        lm_no = new String(str);
                    } catch (Exception e) {
                        errorMsgs.add("�d���s���榡�����T");
                    }
                    // Send the use back to the form, if there were errors
                    if (!errorMsgs.isEmpty()) {
                        RequestDispatcher failureView = req
                                .getRequestDispatcher("/leavemessage/leavemessage_select_page.jsp");
                        failureView.forward(req, res);
                        return;//�{�����_
                    }
                    
                    /***************************2.�}�l�d�߸��*****************************************/
                    LeaveMessageService LeaveMessageService = new LeaveMessageService();
                    LeaveMessageVO LeaveMessageVO = LeaveMessageService.getOneLeaveMessage(lm_no);
                    if (LeaveMessageVO == null) {
                        errorMsgs.add("�d�L���");
                    }
                    // Send the use back to the form, if there were errors
                    if (!errorMsgs.isEmpty()) {
                        RequestDispatcher failureView = req
                                .getRequestDispatcher("/leavemessage/leavemessage_select_page.jsp");
                        failureView.forward(req, res);
                        return;//�{�����_
                    }
                    
                    /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
                    req.setAttribute("LeaveMessageVO", LeaveMessageVO); // ��Ʈw���X��empVO����,�s�Jreq
                    String url = "/leavemessage/listOne_leavemessage.jsp";
                    RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
                    successView.forward(req, res);

                    /***************************��L�i�઺���~�B�z*************************************/
                } catch (Exception e) {
                    errorMsgs.add("�L�k���o���:" + e.getMessage());
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/leavemessage/leavemessage_select_page.jsp");
                    failureView.forward(req, res);
                }
            }
        

        if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            
            try {
                /***************************1.�����ШD�Ѽ�****************************************/
                String lm_no = new String(req.getParameter("lm_no"));
                
                /***************************2.�}�l�d�߸��****************************************/
                LeaveMessageService LeaveMessageService = new LeaveMessageService();
                LeaveMessageVO LeaveMessageVO = LeaveMessageService.getOneLeaveMessage(lm_no);
                                
                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
                req.setAttribute("LeaveMessageVO", LeaveMessageVO);         // ��Ʈw���X��empVO����,�s�Jreq
                String url = "/leavemessage/update_leavemessage_input.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z**********************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/leavemessage/listAllLeaveMessage.jsp");
                failureView.forward(req, res);
            }
        }
        
        
        if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
            
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
        
            try {
                /***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
                String lm_no = new String(req.getParameter("lm_no").trim());
                
                String lm_postno = req.getParameter("lm_postno");
                if (lm_postno == null || lm_postno.trim().length() == 0) {
                    errorMsgs.add("�d���s��: �ФŪť�");
                }
                

                
                String lm_memno = req.getParameter("lm_memno").trim();
                if (lm_memno == null || lm_memno.trim().length() == 0) {
                    errorMsgs.add("�|���s���ФŪť�");
                }
                
                String lm_text = req.getParameter("lm_text").trim();
                if (lm_text == null || lm_text.trim().length() == 0) {
                    errorMsgs.add("�d�����e�ФŪť�");
                }
                
                String lm_status = req.getParameter("lm_status").trim();
                if (lm_status == null || lm_status.trim().length() == 0) {
                    errorMsgs.add("�d�����A�ФŪť�");
                }
                

                LeaveMessageVO LeaveMessageVO = new LeaveMessageVO();
                LeaveMessageVO.setLm_no(lm_no);
                LeaveMessageVO.setLm_postno(lm_postno);
                LeaveMessageVO.setLm_memno(lm_memno);
                LeaveMessageVO.setLm_text(lm_text);
                LeaveMessageVO.setLm_status(lm_status);


                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("LeaveMessageVO", LeaveMessageVO); // �t����J�榡���~��empVO����,�]�s�Jreq
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/leavemessage/update_leavemessage_input.jsp");
                    failureView.forward(req, res);
                    return; //�{�����_
                }
                
                /***************************2.�}�l�ק���*****************************************/
                LeaveMessageService LeaveMessageService = new LeaveMessageService();
                LeaveMessageVO = LeaveMessageService.updateLeaveMessage(lm_no,lm_postno, lm_memno,lm_text,lm_status);
                
                /***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
                req.setAttribute("LeaveMessageVO", LeaveMessageVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
                String url = "/leavemessage/listOne_leavemessage.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z*************************************/
            } catch (Exception e) {
                errorMsgs.add("�ק��ƥ���:"+e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/leavemessage/update_leavemessage_input.jsp");
                failureView.forward(req, res);
            }
        }

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
            
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
                String lm_postno = req.getParameter("lm_postno");
                
                MemVO memVO = (MemVO) session.getAttribute("memberVO");
                 String lm_memno = memVO.getMem_no();
                
                String lm_text = req.getParameter("lm_text").trim();
                if (lm_text == null || lm_text.trim().length() == 0) {
                    errorMsgs.add("留言");
                }
                
                String lm_status = "lm1";
                

                LeaveMessageVO LeaveMessageVO = new LeaveMessageVO();
                LeaveMessageVO.setLm_postno(lm_postno);
                LeaveMessageVO.setLm_memno(lm_memno);
                LeaveMessageVO.setLm_text(lm_text);
                LeaveMessageVO.setLm_status(lm_status);


                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("LeaveMessageVO", LeaveMessageVO); // �t����J�榡���~��empVO����,�]�s�Jreq
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/front-end/post/post.jsp");
                    failureView.forward(req, res);
                    return;
                }
                
                /***************************2.�}�l�s�W���***************************************/
                LeaveMessageService LeaveMessageService = new LeaveMessageService();
                LeaveMessageVO = LeaveMessageService.addLeaveMessage(lm_postno, lm_memno, lm_text, lm_status);
                
                /***************************3.�s�W����,�ǳ����(Send the Success view)***********/
                String url = "/front-end/post/post.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
                successView.forward(req, res);              
                
                /***************************��L�i�઺���~�B�z**********************************/
            } catch (Exception e) {
                errorMsgs.add(e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/leavemessage/addLeaveMessage.jsp");
                failureView.forward(req, res);
            }
        }
        
        
        if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
    
            try {
                /***************************1.�����ШD�Ѽ�***************************************/
                String lm_no = new String(req.getParameter("lm_no"));
                
                /***************************2.�}�l�R�����***************************************/
                LeaveMessageService LeaveMessageService = new LeaveMessageService();
                LeaveMessageService.deleteLeaveMessage(lm_no);
                
                /***************************3.�R������,�ǳ����(Send the Success view)***********/                                
                String url = "/leavemessage/listAllLeaveMessage.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
                successView.forward(req, res);
                
                /***************************��L�i�઺���~�B�z**********************************/
            } catch (Exception e) {
                errorMsgs.add("�R����ƥ���:"+e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/leavemessage/listAllLeaveMessage.jsp");
                failureView.forward(req, res);
            }
        }

    }

}