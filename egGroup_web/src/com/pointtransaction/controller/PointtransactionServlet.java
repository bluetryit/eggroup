package com.pointtransaction.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fooditem.model.FooditemService;
import com.fooditem.model.FooditemVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.pointtransaction.model.PointtransactionService;
import com.pointtransaction.model.PointtransactionVO;

/**
 * Servlet implementation class PointtransactionServlet
 */
@WebServlet("/PointtransactionServlet")
public class PointtransactionServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		HttpSession session = req.getSession();
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("pt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�I�ƽs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pointtransaction/pointtransaction_select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String pt_no = null;
				try {
					pt_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�I�ƽs���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pointtransaction/pointtransaction_select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				PointtransactionService pointtransactionSvc = new PointtransactionService();
				PointtransactionVO pointtransactionVO = pointtransactionSvc.getOnePointtransaction(pt_no);
				if (pointtransactionVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/fooditem/fooditem_select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("pointtransactionVO", pointtransactionVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/pointtransactionVO/listOnePointtransactionVO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/fooditem/listOneFooditem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllFooditem.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String pt_no = new String(req.getParameter("pt_no"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PointtransactionService pointtransactionSvc = new PointtransactionService();
				PointtransactionVO pointtransactionVO = pointtransactionSvc.getOnePointtransaction(pt_no);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("pointtransactionVO", pointtransactionVO); // ��Ʈw���X��fooditemVO����,�s�Jreq
				String url = "/pointtransaction/update_pointtransaction_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_fooditem_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pointtransaction/listAllpointtransaction.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_fooditem_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String pt_no = req.getParameter("pt_no").trim();
				String pt_memno = req.getParameter("pt_memno").trim();
				String pt_resno = req.getParameter("pt_memno").trim();

				Double pt_nt = null;
				try {
					pt_nt = new Double(req.getParameter("pt_nt").trim());
				} catch (NumberFormatException e) {
					pt_nt = 0.0;
					errorMsgs.add("�ж�Ʀr.");
				}

				PointtransactionVO pointtransactionVO = new PointtransactionVO();

				pointtransactionVO.setPt_no(pt_no);
				pointtransactionVO.setPt_memno(pt_memno);
				pointtransactionVO.setPt_resno(pt_resno);
				pointtransactionVO.setPt_nt(pt_nt);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pointtransactionVO", pointtransactionVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pointtransaction/update_pointtransaction_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				PointtransactionService pointtransactionSvc = new PointtransactionService();
				pointtransactionVO = pointtransactionSvc.updatePointtransaction(pt_no, pt_memno, pt_resno, pt_nt);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("pointtransactionVO", pointtransactionVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/pointtransactionVO/listOnepointtransactionVO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/fooditem/update_fooditem_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addFooditem.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String pt_no = req.getParameter("pt_no").trim();	
				String pt_memno = req.getParameter("fo_resno").trim();
				String pt_resno = req.getParameter("fo_name");

				Double pt_nt = null;
				try {
					pt_nt = new Double(req.getParameter("pt_nt").trim());
				} catch (NumberFormatException e) {
					pt_nt = 0.0;
					errorMsgs.add("�ж�Ʀr.");
				}
				
			
				PointtransactionVO pointtransactionVO = new PointtransactionVO();
				pointtransactionVO.setPt_memno(pt_no);
				pointtransactionVO.setPt_memno(pt_memno);
				pointtransactionVO.setPt_resno(pt_resno);
				pointtransactionVO.setPt_nt(pt_nt);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pointtransactionVO", pointtransactionVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/pointtransaction/addPointtransaction.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				PointtransactionService pointtransactionSvc = new PointtransactionService();
				pointtransactionVO = pointtransactionSvc.addPointtransaction(pt_memno, pt_resno, pt_nt);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/pointtransaction/listAllpointtransaction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllFooditem.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/pointtransaction/addPointtransaction.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllFooditem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String pt_no = (req.getParameter("pt_no"));

				/*************************** 2.�}�l�R����� ***************************************/
				PointtransactionService pointtransactionSvc = new PointtransactionService();
				pointtransactionSvc.deletePointtransaction(pt_no);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/pointtransaction/listAllPointtransaction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/pointtransaction/listAllPointtransaction.jsp");
				failureView.forward(req, res);
			}
		}
if ("addpoint".equals(action)) { // 老子在jsp的223行，寫了value="addpoint" name="action"
			
			try {
				String mem_no = req.getParameter("mem_no"); // 老子從jsp的222行 取到mem_no
				
				Double point = Double.parseDouble(req.getParameter("point"));
				
				PointtransactionService pointtransactionService = new PointtransactionService();
				pointtransactionService.addPointtransaction(mem_no, null, point);
				
				MemService memsvc = new MemService();
				MemVO memVO = memsvc.memFindByPrimaryKey(mem_no);
				
				memVO = memsvc.memUpdate(mem_no, memVO.getMem_name(), memVO.getMem_adrs(), memVO.getMem_sex(),
							memVO.getMem_bd(), memVO.getMem_ph(), memVO.getMem_email(), (memVO.getMem_point() + point.intValue()), memVO.getMem_img(),
							memVO.getMem_pass(), memVO.getMem_ac(), memVO.getMem_intro(), memVO.getMem_status());
				/*************************** 3.準備轉交(Send the Success view) ***********/
				
				session.setAttribute("memberVO", memVO);
				
				String url = req.getContextPath() + "/front-end/mem/mem.jsp";
				res.sendRedirect(url);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				String url = req.getContextPath() + "/front-end/mem/mem.jsp";
				res.sendRedirect(url);
			}
		}
	}
}