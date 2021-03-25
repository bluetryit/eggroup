package com.reportlm.controller;

import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;

import com.leavemessage.model.LeaveMessageService;
import com.leavemessage.model.LeaveMessageVO;
import com.mem.model.MemVO;
import com.reportlm.model.*;

public class ReportLmServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws ServletException, IOException{
		System.out.println("22");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		if("getAll".equals(action)) {
			//查詢
			ReportLmDAO dao = new ReportLmDAO();
			List<ReportLmVO> list = dao.getAll();
			
			HttpSession session = req.getSession();
			session.setAttribute("list", list);
			String url = "/reportlm/listAllReportLm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
					String str = req.getParameter("repolm_no");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入檢舉留言編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/reportlm/reportlm_select_page.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					String repolm_no = null;
					try {
						repolm_no = new String(str);
					} catch (Exception e) {
						errorMsgs.add("檢舉留言編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/reportlm/reportlm_select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************2.開始查詢資料*****************************************/
					ReportLmService ReportLmService = new ReportLmService();
					ReportLmVO ReportLmVO = ReportLmService.getOneReportLm(repolm_no);
					if (ReportLmVO == null) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/reportlm/reportlm_select_page.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("ReportLmVO", ReportLmVO); 
					String url = "/reportlm/listOneReportLm.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reportlm/reportlm_select_page.jsp");
					failureView.forward(req, res);
				}
			}
		

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String repolm_no = new String(req.getParameter("repolm_no"));
				
				
				/***************************2.開始查詢資料****************************************/
				ReportLmService ReportLmService = new ReportLmService();
				ReportLmVO ReportLmVO = ReportLmService.getOneReportLm(repolm_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("ReportLmVO", ReportLmVO);         // 資料庫取出的empVO物件,存入req
				String url = "/reportlm/update_reportlm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/reportlm/listAllReportLm.jsp");
				failureView.forward(req, res);
			}
		}
if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String repolm_no = new String(req.getParameter("repolm_no").trim());
		System.out.println("repolm_no" + repolm_no);
				String repolm_status = req.getParameter("repolm_status").trim();
		System.out.println("repolm_status" + repolm_status);
		
				String repolm_lmano = new String(req.getParameter("repolm_lmano").trim());

				// Send the use back to the form, if there were errors
				
				/***************************2.開始修改資料*****************************************/
				ReportLmService ReportLmService = new ReportLmService();
				ReportLmService.updateRepoLm(repolm_no,repolm_status);
				if (repolm_status.equals("repolm1")) {
					LeaveMessageService leaveMessageService = new LeaveMessageService();
					LeaveMessageVO leaveMessageVO = leaveMessageService.getOneLeaveMessage(repolm_lmano);
					leaveMessageVO.setLm_status("lm2");
					leaveMessageService.updateLeaveMessage(leaveMessageVO.getLm_no(),leaveMessageVO.getLm_postno(), leaveMessageVO.getLm_memno(), leaveMessageVO.getLm_text(), leaveMessageVO.getLm_status());		
				}
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("update", "true");

				String url = "/back-end/reportlm/reportLm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				req.setAttribute("update", "true");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/reportlm/reportLm.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
        	System.out.println("197");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				//接收參數 處理錯誤	
				String leaveMessageRe = null;
				String LeaveMessage_lmno = null;
				String memno = null;
				MemVO memberVO = new MemVO();
							
				LeaveMessage_lmno = req.getParameter("LeaveMessage_lmno");
				System.out.println(LeaveMessage_lmno);
				System.out.println("213");
				memberVO = (MemVO)req.getSession().getAttribute("memberVO");
				System.out.println(memberVO);
				System.out.println("215");
				memno = memberVO.getMem_no();
				System.out.println("219");
				if(req.getParameter("leaveMessageRe").trim().length()==0) {
					errorMsgs.put("leaveMessageRe","請勿輸入空白");
				}else {
					leaveMessageRe = req.getParameter("leaveMessageRe");
				}
				System.out.println("225");
				if(!errorMsgs.isEmpty()) {
					
					req.getRequestDispatcher("/front-end/post/post.jsp").forward(req, res);
					return;
				}
				System.out.println("231");
				//DB處理
				ReportLmService rlmSvc = new ReportLmService();
				rlmSvc.addRepLm(LeaveMessage_lmno, memno, leaveMessageRe);
				
				//轉回頁面
				req.getRequestDispatcher("/front-end/post/post.jsp").forward(req, res);
				return;

				
				} catch (Exception e) {
					errorMsgs.put("leaveMessageRe", "刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/post/post.jsp");
					failureView.forward(req, res);
				}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	System.out.println("252");
			try {
				/***************************1.接收請求參數***************************************/
				String repolm_no = new String(req.getParameter("repolm_no"));
				
				/***************************2.開始刪除資料***************************************/
				ReportLmService ReportLmService = new ReportLmService();
				ReportLmService.deleteRepoLm(repolm_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/reportlm/listAllReportLm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/reportlm/listAllReportLm.jsp");
				failureView.forward(req, res);
			}
		}

	
		
	

		}
}