package com.restaurantac.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.resac.model.ResAcService;
import com.resac.model.ResAcVO;



/**
 * Servlet implementation class restaurantacLogin
 */
@WebServlet("/ShopLogin")
public class ResAcLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	protected ResAcVO allowShop(String resac_resno, String resac_no,String resac_pass) {
		ResAcService resAcSrv = new ResAcService();
		ResAcVO resAcVO = resAcSrv.getOneResAc(resac_no, resac_resno);
		if (resAcVO != null && resac_pass.equals(resAcVO.getResac_pass())) {
				
			return resAcVO;
		}else {
			System.out.println("null");
			return null;
		}
	}
		
		
    public ResAcLogin() {
        super();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		System.out.println(11111);
		//將請求內容存入字串
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		//將字串轉成json或gson格式，然後用key取值
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);//這個不知能否更快
		// 【取得使用者 帳號(account) 密碼(password)】
		String resac_resno = jsonObject.get("resac_resno").getAsString();
		String resac_no = jsonObject.get("resac_no").getAsString();
		String resac_pass = jsonObject.get("resac_pass").getAsString();

		// 【檢查該帳號 , 密碼是否有效】
		ResAcVO resAcVO = allowShop(resac_resno, resac_no,resac_pass);
		PrintWriter out = res.getWriter();
		if ( allowShop(resac_resno, resac_no,resac_pass) == null) { // 【帳號 , 密碼無效時】
			
			out.print("LoginFail");
			
		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			//將物件轉成json再用字串格式印出
			
			System.out.println("12123");
			System.out.println(gson.toJson(resAcVO));
			out.print(gson.toJson(resAcVO));
		}
	}

}
