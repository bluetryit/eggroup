package com.friendlist.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feastinfo.model.FeastInfoService;
import com.feastinfo.model.FeastInfoVO;
import com.friendlist.model.FriendListService;
import com.friendlist.model.FriendListVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

public class FriendListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(request, res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 if (session.getAttribute("memberVO") == null)
	     {
	         session.setAttribute("location", request.getHeader("referer") );		
	     }
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		System.out.println("action" + action);
		


		if("insert".equals(action)) {					
			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				 //MemVO memVO = (MemVO) session.getAttribute("memberVO");
				 FeastInfoService feastInfoService = new FeastInfoService();
				 
				 MemVO memVO=(MemVO) session.getAttribute("memberVO");
				 FriendListService friSvc=new FriendListService();
				 
				 
				 
				 
				 String f_memno = (String) request.getParameter("f_memno");		
				 System.out.println("f_memno" + f_memno);
				 
				 String f_no =memVO.getMem_no();
				 System.out.println("f_no" + f_no);
				 
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/post/post.jsp");
					failureView.forward(request, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				 friSvc.findByIds(f_no,f_memno);	//???

				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = request.getContextPath() + "/front-end/post/post.jsp";			
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, res);				
				
//				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/post/post.jsp");
				failureView.forward(request, res);
			}
		}
		
		 if ("getInvite".equals(action)) {
		try {
			FriendListService friSvc = new FriendListService();
			
			String f_no = (String) request.getParameter("f_no");
			System.out.println("f_no" + f_no);
			
			String f_memno = (String) request.getParameter("f_memno");
			System.out.println("f_memno" + f_memno);
			
			friSvc.findByIds2(f_no,f_memno);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/front-end/friendlist/getInviteFriendList.jsp";	
			System.out.println(url);
			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(request, res);				
			System.out.println(123);
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			RequestDispatcher failureView = request
					.getRequestDispatcher("/front-end/friendlist/getInviteFriendList.jsp");
			failureView.forward(request, res);
		}
	}
		
		 if ("noInvite".equals(action)) {
			try {
				FriendListService friSvc = new FriendListService();
				
				String f_no = (String) request.getParameter("f_no");
				System.out.println("f_no:" + f_no);
				
				String f_memno = (String) request.getParameter("f_memno");
				System.out.println("f_memno:" + f_memno);
				
				friSvc.rejectFriendList(f_no,f_memno);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url ="/front-end/friendlist/getInviteFriendList.jsp";	
				System.out.println(url);
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, res);
				System.out.println(456);
				
//				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/friendlist/getInviteFriendList.jsp");
				failureView.forward(request, res);
			}
		} 
		
//        if ("insert".equals(action)) { 	
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
////			request.setAttribute("errorMsgs", errorMsgs);
//
////			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				 //MemVO memVO = (MemVO) session.getAttribute("memberVO");
//				 FeastInfoService feastInfoService = new FeastInfoService();
//				 
//				 MemVO memVO=(MemVO) session.getAttribute("memberVO");
//				 FriendListService friSvc=new FriendListService();
//				 
//				 
//				 String mye_feano = (String) request.getParameter("mye_feano");
//				 System.out.println("mye_feano" + mye_feano);
//				 
//				 FeastInfoVO feastInfoVO = feastInfoService.getOneFeastInfo(mye_feano);
//				 System.out.println(feastInfoVO);
//				 
//				 String f_memno = (String) request.getParameter("f_memno");
//				 System.out.println("f_memno" + f_memno);
//				 
//				 String f_no =memVO.getMem_no();
//				 System.out.println("f_no" + f_no);
//				 
//				
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					request.setAttribute("feastInfoVO", feastInfoVO);
//					RequestDispatcher failureView = request
//							.getRequestDispatcher("/front-end/feast/listOneFeast.jsp");
//					failureView.forward(request, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				 friSvc.addFriendList(f_no,f_memno);	//???
//
//				 request.setAttribute("feastInfoVO", feastInfoVO);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = request.getContextPath() + "/front-end/feast/listOneFeast.jsp";
//				
//				
//				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(request, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
////			} catch (Exception e) {
////				errorMsgs.add(e.getMessage());
////				RequestDispatcher failureView = request
////						.getRequestDispatcher("/front-end/feast/listOneFeast.jsp");
////				failureView.forward(request, res);
////			}
//		}
		
		
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			request.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				//MemVO memVO = (MemVO) session.getAttribute("memberVO");
//				 MemService memSv = new MemService();
//				 MemVO memVO=memSv.memFindByPrimaryKey("ME000001");
//				 FriendListService friSvc=new FriendListService();
//				 
//				 String f_no = memVO.getMem_no();
//				 String f_memno=memVO.getMem_no(); 								//???
//				
//				/***************************2.開始刪除資料***************************************/
//				friSvc.rejectFriendList(f_no,f_memno);							//???
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/friendlist/listAllFriendList.jsp";
//				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(request, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = request
//						.getRequestDispatcher("/friendlist/listAllFriendList.jsp");
//				failureView.forward(request, res);
//			}
//		}
	}
}
