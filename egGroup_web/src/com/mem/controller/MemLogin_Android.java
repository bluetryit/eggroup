package com.mem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mem.model.MemService;
import com.mem.model.MemVO;



/**
 * Servlet implementation class MemLogin
 */
//@WebServlet("/MemLogin")
public class MemLogin_Android extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
		// 【實際上應至資料庫搜尋比對】
		protected MemVO allowUser(String mem_ac, String mem_pass) {
			MemService memSrv = new MemService();
			MemVO memVO = memSrv.memFindByAC(mem_ac);
			if (memVO != null && mem_pass.equals(memVO.getMem_pass())) {
				
				return memVO;
			}else {
				System.out.println("null");
				return null;}
		}
	
    public MemLogin_Android() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
//    	Gson gson = new Gson();
//		String mem_json = gson.toJson(areas);
//		res.setContentType("text/html; charset=UTF-8");
    	try {
			doPost(req,res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
		String mem_ac = jsonObject.get("mem_ac").getAsString();
		String mem_pass = jsonObject.get("mem_pass").getAsString();

		// 【檢查該帳號 , 密碼是否有效】
		MemVO memVO = allowUser(mem_ac, mem_pass);
		PrintWriter out = res.getWriter();
		if ((memVO = allowUser(mem_ac, mem_pass)) == null) { // 【帳號 , 密碼無效時】
			
			out.print("LoginFail");
			
		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			//將物件轉成json再用字串格式印出
			
			System.out.println("12123");
		
			out.print(gson.toJson(memVO));
		}
	}
}
