package com.resac.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.res.model.*;
import com.resac.model.*;

@WebServlet("/resAc.do")
@MultipartConfig(
fileSizeThreshold = 1024 * 1024,
  maxFileSize = 1024 * 1024 * 5, 
  maxRequestSize = 1024 * 1024 * 5 * 5)
public class ResAcServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException{
		doGet(req,res);
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if("getOneResAc_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("resac_no");
				if(str == null || (str.trim()).length() == 0)
					errorMsgs.add("請輸入員工編號");
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/resAc/resAc.jsp");
					failureView.forward(req, res);
					return;
				}
				String resac_no = null;
				try {
					resac_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/resAc/resAc.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				ResAcService resAcSvc = new ResAcService();

				ResVO resVO = (ResVO)session.getAttribute("resVO");
				String resac_resno = resVO.getRes_no();
				ResAcVO resAcVO = resAcSvc.getOneResAc(resac_no, resac_resno);
				if(resAcVO == null)
					errorMsgs.add("查無資料");
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/resAc/resAc.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("resAcVO", resAcVO);
				String url = "/front-end/resAc/listOneResAc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:"+ e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/resAc/resAc.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				ResVO resVO = (ResVO)session.getAttribute("resVO");
				String resac_resno = resVO.getRes_no();
				String resac_no = req.getParameter("resac_no");
				String resac_noReg = "^[0-9]{6}$";
				if(resac_no == null ||resac_no.trim().length()==0) {
					errorMsgs.add("員工編號請勿空白");
				}else if(!resac_no.trim().matches(resac_noReg)){
					errorMsgs.add("員工編號為六位數字");
				}
				
				String resac_name = req.getParameter("resac_name");
				String resac_nameReg = "[(\\u4e00-\\u9fa5)]{2,10}";
				if(resac_name == null ||resac_name.trim().length()==0) {
					errorMsgs.add("員工姓名請勿空白");
				}else if(!resac_name.trim().matches(resac_nameReg)) {
					errorMsgs.add("員工姓名請輸入中文姓名");
				}
				
				String resac_pass = req.getParameter("resac_pass");
				String resac_pass2 = req.getParameter("resac_pass2");
				String resac_passReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,10}$";
				if(resac_pass == null || resac_pass.trim().length() == 0) {
					errorMsgs.add("請設定密碼");
				}else if(!resac_pass.trim().matches(resac_passReg)){
					errorMsgs.add("請輸入正確的密碼格式，必須包含至少一個英文大小寫及一個數字，至少8碼至多10碼");
				}else if(!resac_pass.trim().equals(resac_pass2.trim())) {
					errorMsgs.add("密碼與密碼確認須一致");
				}
				String resac_phone = req.getParameter("resac_phone");
				if(resac_phone == null || resac_phone.trim().length() == 0) {
					errorMsgs.add("電話號碼請勿空白");
				}
				
				Part part = req.getPart("resac_pic");
				InputStream in = part.getInputStream();
				byte[] resac_pic = null;
				if(in.available()==0) {
					errorMsgs.add("請上傳圖片");
				}else{
					in = part.getInputStream();
					resac_pic = new byte[in.available()];
					in.read(resac_pic);
				} in.close();
				
				String resac_intro = req.getParameter("resac_intro");
				if(resac_intro == null || resac_intro.trim().length() == 0 ) {
					errorMsgs.add("請輸入員工介紹");
				}
				
				String resac_status = "resac1"; //員工狀態預設resac1
				
				
				ResAcVO  resAcVO = new ResAcVO();
				
				resAcVO.setResac_no(resac_no);
				resAcVO.setResac_resno(resac_resno);
				resAcVO.setResac_pass(resac_pass);
				resAcVO.setResac_name(resac_name);
				resAcVO.setResac_phone(resac_phone);
				resAcVO.setResac_pic(resac_pic);
				resAcVO.setResac_intro(resac_intro);
				resAcVO.setResac_status(resac_status);
				
				
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("resAcVO", resAcVO);
					session.setAttribute("addResAc", "false");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/resAc/addResAc.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				ResAcService resAcSvc = new ResAcService();
				resAcVO = resAcSvc.addResAc(resac_no, resac_resno, resac_pass, resac_name, resac_phone, resac_pic, resac_intro, resac_status);
				req.setAttribute("resAcVO", resAcVO);
				session.setAttribute("addResAc", "true");
				String url = "/front-end/resAc/listAllResAc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/resAc/addResAc.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOneResAc_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String resac_no = req.getParameter("resac_no").trim();

				ResVO resVO = (ResVO)session.getAttribute("resVO");
				String resac_resno = resVO.getRes_no();
				ResAcService resAcSvc = new ResAcService();
				ResAcVO resAcVO = resAcSvc.getOneResAc(resac_no, resac_resno);
				
				req.setAttribute("resAcVO", resAcVO);
				String url = "/front-end/resAc/update_resAc_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher FailureView = req
						.getRequestDispatcher("/front-end/resAc/listAllResAc.jsp");
				FailureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				ResAcService resAcSvc = new ResAcService();
				ResVO resVO = (ResVO)session.getAttribute("resVO");
				ResAcVO resAcVO = (ResAcVO)session.getAttribute("resAcVO");
				String resac_resno = resVO.getRes_no();
				String resac_no = req.getParameter("resac_no");
				String resac_noReg = "^[0-9]{6}$";
				if(resac_no == null ||resac_no.trim().length()==0) {
					errorMsgs.add("員工編號請勿空白");
				}else if(!resac_no.trim().matches(resac_noReg)){
					errorMsgs.add("員工編號為六位數字");
				}
				
				String resac_name = req.getParameter("resac_name");
				String resac_nameReg = "[(\\u4e00-\\u9fa5)]{2,10}";
				if(resac_name == null ||resac_name.trim().length()==0) {
					errorMsgs.add("員工姓名請勿空白");
				}else if(!resac_name.trim().matches(resac_nameReg)) {
					errorMsgs.add("員工姓名請輸入中文姓名");
				}
				
				String resac_pass = req.getParameter("resac_pass");
				String resac_pass2 = req.getParameter("resac_pass2");
				String resac_passReg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,10}$";
				if(resac_pass == null || resac_pass.trim().length() == 0) {
					errorMsgs.add("請設定密碼");
				}else if(!resac_pass.trim().matches(resac_passReg)){
					errorMsgs.add("請輸入正確的密碼格式，必須包含至少一個英文大小寫及一個數字，至少8碼至多10碼");
				}else if(!resac_pass.trim().equals(resac_pass2.trim())) {
					errorMsgs.add("密碼與密碼確認須一致");
				}
				String resac_phone = req.getParameter("resac_phone");
				if(resac_phone == null || resac_phone.trim().length() == 0) {
					errorMsgs.add("電話號碼請勿空白");
				}
				
				Part part = req.getPart("resac_pic");
				InputStream in = part.getInputStream();
				byte[] resac_pic = null;
				if(in.available() == 0) {
					resac_pic = resAcVO.getResac_pic();
				}else{
					in = part.getInputStream();
					resac_pic = new byte[in.available()];
					in.read(resac_pic);
				} in.close();
				
				String resac_intro = req.getParameter("resac_intro");
				if(resac_intro == null || resac_intro.trim().length() == 0 ) {
					errorMsgs.add("請輸入員工介紹");
				}
				
				String resac_status = req.getParameter("resac_status");
				
				
				
				
				resAcVO.setResac_no(resac_no);
				resAcVO.setResac_resno(resac_resno);
				resAcVO.setResac_pass(resac_pass);
				resAcVO.setResac_name(resac_name);
				resAcVO.setResac_phone(resac_phone);
				resAcVO.setResac_pic(resac_pic);
				resAcVO.setResac_intro(resac_intro);
				resAcVO.setResac_status(resac_status);
				
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("resAcVO", resAcVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/resAc/update_resAc_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				resAcVO = resAcSvc.updateResAc(resac_no, resac_resno, resac_pass, resac_name, resac_phone, resac_pic, resac_intro, resac_status);
				
				req.setAttribute("resAcVO", resAcVO);
				String url = "/front-end/resAc/listAllResAc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/resAc/update_resAc_input.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
}
