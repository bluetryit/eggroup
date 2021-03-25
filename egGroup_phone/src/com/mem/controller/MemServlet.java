package com.mem.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mem.model.MemService;
import com.mem.model.MemVO;

@MultipartConfig(fileSizeThreshold=1024*1024,maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class MemServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/MEM-INF/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String mem_no = null;
				try {
					mem_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/MEM-INF/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.memFindByPrimaryKey(mem_no);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/MEM-INF/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/MEM-INF/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/MEM-INF/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_no = new String(req.getParameter("mem_no"));
				
				/***************************2.開始查詢資料****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.memFindByPrimaryKey(mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/MEM-INF/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/MEM-INF/listAllMem.jsp");
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
				String mem_no = req.getParameter("mem_no").trim();
				
				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String mem_adrs= req.getParameter("mem_adrs").trim();
				if (mem_adrs == null || mem_adrs.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				String mem_sex= req.getParameter("mem_sex").trim();
				if (mem_sex == null || mem_sex.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				java.sql.Date mem_bd = null;
				try {
					mem_bd = java.sql.Date.valueOf(req.getParameter("mem_bd").trim());
				} catch (IllegalArgumentException e) {
					mem_bd=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String mem_ph= req.getParameter("mem_ph").trim();
				if (mem_ph == null || mem_ph.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				String mem_email= req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				
				Integer mem_point= new Integer(req.getParameter("mem_point").trim());
				
				Part mem_img= req.getPart("mem_img");
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				//inputStream轉byte[]
				byte[] data = new byte[mem_img.getInputStream().available()];
				BufferedInputStream buffer=new BufferedInputStream(mem_img.getInputStream());
				mem_img.getInputStream().read(data, 0, data.length);
				
				
				String mem_pass= req.getParameter("mem_pass").trim();
				if (mem_pass == null || mem_pass.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				String mem_ac= req.getParameter("mem_ac").trim();
				if (mem_ac == null || mem_ac.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String mem_intro= req.getParameter("mem_intro").trim();
				if (mem_intro == null || mem_intro.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String mem_status= req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}

				

//				String mem_no = req.getParameter("mem_no");

				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_adrs(mem_adrs);
				memVO.setMem_sex(mem_sex);
				memVO.setMem_bd(mem_bd);
				memVO.setMem_ph(mem_ph);
				memVO.setMem_email(mem_email);
				memVO.setMem_point(mem_point);
				memVO.setMem_img(data);
				memVO.setMem_pass(mem_pass);
				memVO.setMem_ac(mem_ac);
				memVO.setMem_intro(mem_intro);
				memVO.setMem_status(mem_status);
				memVO.setMem_no(mem_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/MEM-INF/update_mem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemService memSvc = new MemService();
				//memVO = memSvc.memUpdate();
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emp/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/MEM-INF/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addMem.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//String mem_no = req.getParameter("mem_no");
				
				String mem_name = req.getParameter("mem_name");
				String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (mem_name == null || mem_name.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!mem_name.trim().matches(mem_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }

				System.out.println(mem_name);
				
				String mem_adrs= req.getParameter("mem_adrs").trim();
				if (mem_adrs == null || mem_adrs.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				
				
				String mem_sex= req.getParameter("mem_sex").trim();
				if (mem_sex == null || mem_sex.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				java.sql.Date mem_bd = null;
				try {
					mem_bd = java.sql.Date.valueOf(req.getParameter("mem_bd").trim());
				} catch (IllegalArgumentException e) {
					mem_bd=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String mem_ph= req.getParameter("mem_ph").trim();
				if (mem_ph == null || mem_ph.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				String mem_email= req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				
				Integer mem_point= new Integer(req.getParameter("mem_point").trim());
				
				Part mem_img= req.getPart("mem_img");
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				
				System.out.println(1);
				//inputStream轉byte[]
				byte[] data = new byte[mem_img.getInputStream().available()];
				BufferedInputStream buffer=new BufferedInputStream(mem_img.getInputStream());
				mem_img.getInputStream().read(data, 0, data.length);
				
				System.out.println(2);
				String mem_pass= req.getParameter("mem_pass").trim();
				if (mem_pass == null || mem_pass.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				String mem_ac= req.getParameter("mem_ac").trim();
				if (mem_ac == null || mem_ac.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String mem_intro= req.getParameter("mem_intro").trim();
				if (mem_intro == null || mem_intro.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				String mem_status= req.getParameter("mem_status").trim();
				if (mem_status == null || mem_status.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}


				String mem_no = req.getParameter("mem_no");
				MemVO memVO = new MemVO();
				memVO.setMem_name(mem_name);
				memVO.setMem_adrs(mem_adrs);
				memVO.setMem_sex(mem_sex);
				memVO.setMem_bd(mem_bd);
				memVO.setMem_ph(mem_ph);
				memVO.setMem_email(mem_email);
				memVO.setMem_point(mem_point);
				memVO.setMem_img(data);
				memVO.setMem_pass(mem_pass);
				memVO.setMem_ac(mem_ac);
				memVO.setMem_intro(mem_intro);
				memVO.setMem_status(mem_status);
				
				MemService memSrv=new MemService();
				memSrv.memInsert(mem_name, mem_adrs, mem_sex, mem_bd, mem_ph, mem_email, mem_point, data, mem_pass, mem_ac, mem_intro, mem_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/MEM-INF/addMem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemService memSvc = new MemService();
				//memVO = memSvc.memInsert();
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/MEM-INF/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMem.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/MEM-INF/addMem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllMem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mem_no = req.getParameter("mem_no");
				
				/***************************2.開始刪除資料***************************************/
				MemService memSvc = new MemService();
				memSvc.memDelete(mem_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/MEM-INF/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/MEM-INF/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
