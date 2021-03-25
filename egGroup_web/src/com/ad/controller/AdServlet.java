package com.ad.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.res.model.*;
import com.ad.model.*;

@WebServlet("/ad.do")
@MultipartConfig()
public class AdServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if("getOneAd_For_Display_By_Admin".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String str = req.getParameter("ad_no");
				if(str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/ad/ad_BE.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String ad_no = null;
				try {
					ad_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/ad/ad_BE.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAd(ad_no);
				if(adVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/ad/ad_BE.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("adVO", adVO);
				String url = "/back-end/ad/listOneAd_BE.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/ad/ad_BE.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOneAd_For_Display_By_Res".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				String str = req.getParameter("ad_no");
				if(str == null || str.trim().length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ad/ad_FE.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String ad_no = null;
				try {
					ad_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ad/ad_FE.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				AdService adSvc = new AdService();
				
				ResVO resVO = (ResVO)session.getAttribute("resVO");
				String ad_resno = resVO.getRes_no();
				AdVO adVO = adSvc.getOneAdbyRes(ad_no,ad_resno);
				if(adVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ad/ad_FE.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("adVO", adVO);
				String url = "/front-end/ad/listOneAd_FE.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ad/ad_FE.jsp");
				failureView.forward(req, res);
			}
		}
		if("showAdInfo".equals(action)) {
				String ad_no = req.getParameter("ad_no");

				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAd(ad_no);

				req.setAttribute("adVO", adVO);
				System.out.println(adVO);
				String url = "/front-end/ad/showAdInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

		}
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			try {
				
				ResVO resVO = (ResVO)session.getAttribute("resVO");
				String ad_resno = resVO.getRes_no();
				String ad_text = req.getParameter("ad_text");
				
				String ad_title = req.getParameter("ad_title");
				
				Part part = req.getPart("ad_img");
				InputStream in = part.getInputStream();
				byte[] ad_img = null;
				if(in.available()==0) {
					errorMsgs.add("請上傳廣告圖片");
				}else{
					in = part.getInputStream();
					ad_img = new byte[in.available()];
					in.read(ad_img);
				} in.close();
				
				String ad_status = "ads1"; //新增廣告時預設狀態ads1 = 未審核
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Timestamp ad_start = null;
				try {
					ad_start = new java.sql.Timestamp(dateFormat.parse(req.getParameter("ad_start").trim()).getTime());
				} catch (IllegalArgumentException | ParseException e) {
					ad_start = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告結束刊登時間");
				}
				
				java.sql.Timestamp ad_end = null;
				try {
					ad_end = new java.sql.Timestamp(dateFormat.parse(req.getParameter("ad_end").trim()).getTime());
				} catch (IllegalArgumentException | ParseException e) {
					ad_end = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入廣告結束刊登時間");
				}
				
					
				AdVO adVO = new AdVO();
				
				adVO.setAd_resno(ad_resno);
				adVO.setAd_title(ad_title);
				adVO.setAd_text(ad_text);
				adVO.setAd_img(ad_img);
				adVO.setAd_status(ad_status);
				adVO.setAd_start(ad_start);
				adVO.setAd_end(ad_end);
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ad/addAd.jsp");
					failureView.forward(req, res);
					return;
				}
				AdService adSvc = new AdService();
				adVO = adSvc.addAd(ad_resno, ad_text, ad_img, ad_start, ad_end, ad_title, ad_status);
				req.setAttribute("adVO", adVO);
				String url = "/front-end/ad/listAllAd_FE.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ad/addAd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/front-end/ad/listAllAd_FE.jsp】  或 【/back-end/ad/listAllAd_BE.jsp】		
			
			try {
				String ad_no = req.getParameter("ad_no");
				
				AdService AdSvc = new AdService();
				AdVO adVO = AdSvc.getOneAd(ad_no);

				req.setAttribute("adVO", adVO); 
				
				//根據不同的請求來源forward至不同的修改畫面 前台餐廳業者可以修改內文...等 後台管理員只能修改 狀態
				String url = "";
				if(requestURL.equals("/front-end/ad/listAllAd_FE.jsp")) {
					url = "/front-end/ad/update_ad_input_FE.jsp";
				}else if (requestURL.equals("/back-end/ad/listAllAd_BE.jsp")) {
					url = "/back-end/ad/update_ad_input_BE.jsp";
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			
			try {
				AdVO adVO = (AdVO)session.getAttribute("adVO");
				
				//餐廳業者與後台管理員均不能修改的內容 
				String ad_no = adVO.getAd_no();
				String ad_resno = adVO.getAd_resno();
				Timestamp ad_end = adVO.getAd_end();
				Timestamp ad_start = adVO.getAd_start();
				
				//餐廳業者可以修改的內容 
				String ad_text = adVO.getAd_text();
				System.out.println(ad_text);
				String ad_title = adVO.getAd_title();
				byte[] ad_img = adVO.getAd_img();
				if(requestURL.equals("/front-end/ad/listAllAd_FE.jsp")) {
					String ad_textReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)(\\s)(\\S)]{30,500}$";
					ad_text = req.getParameter("ad_text");
					
					if(ad_text == null || ad_text.trim().length() == 0) {
						errorMsgs.add("請輸入廣告內文");
					}else if(!ad_text.trim().matches(ad_textReg)) { 
						errorMsgs.add("廣告內文: 只能是中、英文字母、數字和標點符號 ! 且長度必需在30到500之間");
		            }
					ad_title = req.getParameter("ad_title");
					String ad_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
					if(ad_title == null || ad_title.trim().length() == 0) {
						errorMsgs.add("請輸入廣告標題");
					}else if(!ad_title.trim().matches(ad_titleReg)) { 
						errorMsgs.add("廣告標題: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
		            }
					Part part = req.getPart("ad_img");
					InputStream in = part.getInputStream();
					if(in.available() == 0) {
						ad_img = adVO.getAd_img();
					}else{
						in = part.getInputStream();
						ad_img = new byte[in.available()];
						in.read(ad_img);
					} in.close();			
				}

				
				//後台管理員可以修改的內容 以及若餐廳業者修改 內容 狀態必須變更回未審核 
				String ad_status ="ads1"; 
				if(requestURL.equals("/back-end/ad/listAllAd_BE.jsp")) {
					ad_status = req.getParameter("ad_status");
				}
				


				
				adVO.setAd_no(ad_no);
				adVO.setAd_resno(ad_resno);
				adVO.setAd_title(ad_title);
				adVO.setAd_text(ad_text);
				adVO.setAd_img(ad_img);
				adVO.setAd_status(ad_status);
				adVO.setAd_start(ad_start);
				adVO.setAd_end(ad_end);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO);
					RequestDispatcher failureView = null;
					if(requestURL.equals("/front-end/ad/listAllAd_FE.jsp")) {
						failureView = req
								.getRequestDispatcher("/front-end/ad/update_ad_input_FE.jsp");
					}else if (requestURL.equals("/back-end/ad/listAllAd_BE.jsp")) {
						failureView = req
								.getRequestDispatcher("/back-end/ad/update_ad_input_BE.jsp");
					}
					failureView.forward(req, res);
					return;
				}
				
				AdService adSvc = new AdService();
				adVO = adSvc.updateAd(ad_no,ad_resno, ad_text, ad_img, ad_start, ad_end, ad_title, ad_status);
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = null;
				if(requestURL.equals("/front-end/ad/listAllAd_FE.jsp")) {
					failureView = req
							.getRequestDispatcher("/front-end/ad/update_ad_input_FE.jsp");
				}else if (requestURL.equals("/back-end/ad/listAllAd_BE.jsp")) {
					failureView = req
							.getRequestDispatcher("/back-end/ad/update_ad_input_BE.jsp");
				}
				failureView.forward(req, res);
			}
		}
	}
}
