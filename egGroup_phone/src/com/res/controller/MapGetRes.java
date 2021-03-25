package com.res.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.main.ImageUtil;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.res.model.ResService;
import com.res.model.ResVO;

/**
 * Servlet implementation class MapGetRes
 */
@WebServlet("/MapGetRes")
public class MapGetRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public MapGetRes() {
        super();
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.getWriter().append("Served at: ").append(req.getContextPath());
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input2: " + jsonIn);
		System.out.println("收到餐廳地圖資訊的請求");
		ResService resSrv = new ResService();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),JsonObject.class);
		String action = jsonObject.get("action").getAsString();//改成什麼
		//getList
		if (action.equals("getList")) {	
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			//已有篩選僅上線商家會取出
			out.print(gson.toJson(resSrv.getAll()));
			System.out.println(gson.toJson(resSrv.getAll()));
		}else if(action.equals("image")) {
			OutputStream os = res.getOutputStream();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			String res_no=jsonObject.get("res_no").getAsString();
			byte[] image = resSrv.getImgByRsNoRes(res_no);
			System.out.println("取圖res_no:"+res_no);
			if (image != null) {
				// 縮圖 in server side
				System.out.println("圖片");
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
				os.write(image);
				os.flush();
				System.out.println(image.length);
				os.close();
			}
			
		}else if(action.equals("getOne")) {
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			String res_no=jsonObject.get("res_no").getAsString();
			System.out.println(resSrv.getOneRes(res_no));
			out.print(gson.toJson(resSrv.getOneRes(res_no)));
		}
	}
	
}


