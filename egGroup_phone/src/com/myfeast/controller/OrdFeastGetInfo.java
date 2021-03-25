package com.myfeast.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feastinfo.model.FeastInfoService;
import com.feastinfo.model.FeastInfoVO;
import com.feasttrack.model.FeastTrackService;
import com.feasttrack.model.FeastTrackVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.myfeast.model.MyFeastService;
import com.myfeast.model.MyFeastVO;

/**
 * Servlet implementation class MyFeastServlet
 */
@WebServlet("/OrdFeastServlet")
public class OrdFeastGetInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public OrdFeastGetInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
	
		//將請求內容存入字串
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input這個是訂單請求的餐團資訊: " + jsonIn);
		//將字串轉成json或gson格式，然後用key取值
		JsonObject reqjsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		JsonObject resjsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		// 【取得編號】
		String fea_no = reqjsonObject.get("fea_no").getAsString();
		
		// 【取得餐團資料】		
		FeastInfoVO Feast =new FeastInfoVO(); 
		FeastInfoService FeastSrv=new FeastInfoService();
		Feast=FeastSrv.getOneFeastInfo(fea_no);
		PrintWriter out = res.getWriter();								
		
		out.print(gson.toJson(Feast));	
		
		
	}
	

}
