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
@WebServlet("/MyFeastServlet")
public class MyFeastGetInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 取得使用者餐團清單
			protected List<MyFeastVO> findMyFeast(String mye_memNo){
				MyFeastService myFeastSrv = new MyFeastService();
				List<MyFeastVO> myFeastList = myFeastSrv.getByMem(mye_memNo);
				if (myFeastList != null) {
					
					return myFeastList;
				}else {
					System.out.println("myFeastList null");
					return null;}
			}
			protected List<FeastTrackVO> findNoticeFeast(String mye_memNo){
				FeastTrackService noticeFeastSrv = new FeastTrackService();
				List<FeastTrackVO> noticeFeastList =noticeFeastSrv.getByMemFeastTrack(mye_memNo);
				if (noticeFeastList != null) {
					
					return noticeFeastList;
				}else {
					System.out.println("noticeFeastList null");
					return null;}
				
			}
    public MyFeastGetInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		req.setCharacterEncoding("UTF-8");
//		res.setCharacterEncoding("UTF-8");
//		res.setContentType("text/html; charset=UTF-8");
//	
//		//將請求內容存入字串
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm").create();
//		BufferedReader br = req.getReader();
//		StringBuilder jsonIn = new StringBuilder();
//		String line = null;
//		while ((line = br.readLine()) != null) {
//			jsonIn.append(line);
//		}
//		System.out.println("input: " + jsonIn);
//		//將字串轉成json或gson格式，然後用key取值
//		JsonObject reqjsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
//		JsonObject resjsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
//		// 【取得編號】
//		String fea_no = reqjsonObject.get("fea_no").getAsString();
//		
//		// 【取得餐團資料】		
//		FeastInfoVO Feast =new FeastInfoVO(); 
//		FeastInfoService FeastSrv=new FeastInfoService();
//		Feast=FeastSrv.getOneFeastInfo(fea_no);
//		PrintWriter out = res.getWriter();								
//		
//		out.print(gson.toJson(Feast));	
//			
		
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
		System.out.println("input: " + jsonIn);
		//將字串轉成json或gson格式，然後用key取值
		JsonObject reqjsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		JsonObject resjsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		// 【取得使用者編號】
		String mye_memNo = reqjsonObject.get("mye_memNo").getAsString();
		

		// 【取得餐團資料】
		List<MyFeastVO> myFeastList =new ArrayList<MyFeastVO>(); //已參與
		List<FeastTrackVO> noticeFeastList =new ArrayList<FeastTrackVO>(); //純關注
		myFeastList=findMyFeast(mye_memNo);
		noticeFeastList=findNoticeFeast(mye_memNo);
		PrintWriter out = res.getWriter();
		if (myFeastList == null) { // 【尚未有餐團紀錄】
			
			resjsonObject.addProperty("now","");
			resjsonObject.addProperty("his","");
			
		} else { // 【有資料時】
			//將物件轉成json再用字串格式印出
			FeastInfoService feasInfoSrv=new FeastInfoService();
			List<FeastInfoVO> now_FeastInfoList=new ArrayList<FeastInfoVO>();
			List<FeastInfoVO> his_FeastInfoList=new ArrayList<FeastInfoVO>();
			List<FeastInfoVO> not_FeastInfoList=new ArrayList<FeastInfoVO>();
			for(MyFeastVO feast : myFeastList) {
				System.out.println(feasInfoSrv.getOneFeastInfo(feast.getMye_feaNo()).getFea_status());
				if(feasInfoSrv.getOneFeastInfo(feast.getMye_feaNo()).getFea_status().equalsIgnoreCase("FEA1")||feasInfoSrv.getOneFeastInfo(feast.getMye_feaNo()).getFea_status().equalsIgnoreCase("FEA2")) {
					now_FeastInfoList.add(feasInfoSrv.getOneFeastInfo(feast.getMye_feaNo()));
				 }else {
					his_FeastInfoList.add(feasInfoSrv.getOneFeastInfo(feast.getMye_feaNo()));
					
				 }
			}
			
			System.out.println("now&his");
			System.out.println("now:"+gson.toJson(now_FeastInfoList));
			System.out.println("his:"+gson.toJson(his_FeastInfoList));
			System.out.println("----------------------------------------");
			
			resjsonObject.addProperty("now",gson.toJson(now_FeastInfoList));
			resjsonObject.addProperty("his",gson.toJson(his_FeastInfoList));			
			
		}
		
		if (noticeFeastList == null) { // 【尚未有關注餐團】
			
			resjsonObject.addProperty("not","");
			
		} else { // 【有資料時】
			//將物件轉成json再用字串格式印出
			FeastInfoService feasInfoSrv=new FeastInfoService();
			List<FeastInfoVO> not_FeastInfoList=new ArrayList<FeastInfoVO>();
			for(FeastTrackVO feast : noticeFeastList) {
				//檢查為可加入飯局狀態才顯示，要刪除不可加入的嗎?
				if(feasInfoSrv.getOneFeastInfo(feast.getTra_feaNo()).getFea_status().equalsIgnoreCase("FEA1")) {
					not_FeastInfoList.add(feasInfoSrv.getOneFeastInfo(feast.getTra_feaNo()));
				 }
			}
			
			System.out.println("notice");
			System.out.println(gson.toJson(not_FeastInfoList));
	
			System.out.println("----------------------------------------");
		
	
			resjsonObject.addProperty("not",gson.toJson(not_FeastInfoList));
			
		}
		out.print(resjsonObject);
		
		
	}
	

}
