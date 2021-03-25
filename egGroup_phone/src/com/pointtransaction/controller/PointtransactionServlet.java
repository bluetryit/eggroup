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
import javax.servlet.http.Part;

import com.fooditem.model.FooditemService;
import com.fooditem.model.FooditemVO;
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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("pt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入點數編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pointtransaction/pointtransaction_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String pt_no = null;
				try {
					pt_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("點數編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pointtransaction/pointtransaction_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PointtransactionService pointtransactionSvc = new PointtransactionService();
				PointtransactionVO pointtransactionVO = pointtransactionSvc.getOnePointtransaction(pt_no);
				if (pointtransactionVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/fooditem/fooditem_select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("pointtransactionVO", pointtransactionVO); // 資料庫取出的empVO物件,存入req
				String url = "/pointtransactionVO/listOnePointtransactionVO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/fooditem/listOneFooditem.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllFooditem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String pt_no = new String(req.getParameter("pt_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				PointtransactionService pointtransactionSvc = new PointtransactionService();
				PointtransactionVO pointtransactionVO = pointtransactionSvc.getOnePointtransaction(pt_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("pointtransactionVO", pointtransactionVO); // 資料庫取出的fooditemVO物件,存入req
				String url = "/pointtransaction/update_pointtransaction_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_fooditem_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/pointtransaction/listAllpointtransaction.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_fooditem_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String pt_no = req.getParameter("pt_no").trim();
				String pt_memno = req.getParameter("pt_memno").trim();
				String pt_resno = req.getParameter("pt_memno").trim();

				Double pt_nt = null;
				try {
					pt_nt = new Double(req.getParameter("pt_nt").trim());
				} catch (NumberFormatException e) {
					pt_nt = 0.0;
					errorMsgs.add("請填數字.");
				}

				PointtransactionVO pointtransactionVO = new PointtransactionVO();

				pointtransactionVO.setPt_no(pt_no);
				pointtransactionVO.setPt_memno(pt_memno);
				pointtransactionVO.setPt_resno(pt_resno);
				pointtransactionVO.setPt_nt(pt_nt);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pointtransactionVO", pointtransactionVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/pointtransaction/update_pointtransaction_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				PointtransactionService pointtransactionSvc = new PointtransactionService();
				pointtransactionVO = pointtransactionSvc.updatePointtransaction(pt_no, pt_memno, pt_resno, pt_nt);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("pointtransactionVO", pointtransactionVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/pointtransactionVO/listOnepointtransactionVO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/fooditem/update_fooditem_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addFooditem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String pt_no = req.getParameter("pt_no").trim();	
				String pt_memno = req.getParameter("fo_resno").trim();
				String pt_resno = req.getParameter("fo_name");

				Double pt_nt = null;
				try {
					pt_nt = new Double(req.getParameter("pt_nt").trim());
				} catch (NumberFormatException e) {
					pt_nt = 0.0;
					errorMsgs.add("請填數字.");
				}
				
			
				PointtransactionVO pointtransactionVO = new PointtransactionVO();
				pointtransactionVO.setPt_memno(pt_no);
				pointtransactionVO.setPt_memno(pt_memno);
				pointtransactionVO.setPt_resno(pt_resno);
				pointtransactionVO.setPt_nt(pt_nt);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pointtransactionVO", pointtransactionVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/pointtransaction/addPointtransaction.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				PointtransactionService pointtransactionSvc = new PointtransactionService();
				pointtransactionVO = pointtransactionSvc.addPointtransaction(pt_no, pt_memno, pt_resno, pt_nt);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/pointtransaction/listAllpointtransaction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFooditem.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/pointtransaction/addPointtransaction.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllFooditem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String pt_no = (req.getParameter("pt_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				PointtransactionService pointtransactionSvc = new PointtransactionService();
				pointtransactionSvc.deletePointtransaction(pt_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/pointtransaction/listAllPointtransaction.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/pointtransaction/listAllPointtransaction.jsp");
				failureView.forward(req, res);
			}
		}
	}
}