package com.friendList.controller;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.myfeast.model.MyFeastService;

/**
 * Servlet implementation class FriendServlet
 */
@WebServlet("/FriendServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public FriendServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
	
		//將請求內容存入字串
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
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
		String action = reqjsonObject.get("action").getAsString();
		
		PrintWriter out = res.getWriter();
//將物件轉成json再用字串格式印出
			FeastInfoService feasInfoSrv=new FeastInfoService();
			if("getAll".equals(action)) {//取得所有飯局
				List<FeastInfoVO> FeastList=new ArrayList<FeastInfoVO>();	
				List<FeastInfoVO> result=new ArrayList<FeastInfoVO>();
				FeastList=feasInfoSrv.getAll();
				for(FeastInfoVO feastInfoVO:FeastList) {//報名中才顯示
					if("FEA1".equals(feastInfoVO.getFea_status())) {
						result.add(feastInfoVO);
					}
				}
				System.out.println("有幾個返局可加入"+result.size());
				out.print(gson.toJson(result));
				
			}else if("joinFeast".equals(action)) {
				String mem_no = reqjsonObject.get("mem_no").getAsString();
				String fea_no = reqjsonObject.get("fea_no").getAsString();
				System.out.println("joinFeast"+mem_no+"加入飯局"+fea_no);
				MyFeastService myFeastSrv=new MyFeastService();
//				if(myFeastSrv.getByMem(mem_no)==null) {//檢查是否已加入飯局 還是全體撈出來的時候就要檢查
					System.out.println(mem_no+"加入飯局"+fea_no+"同時取消關注");
					myFeastSrv.addMyFeast(fea_no, mem_no);
					FeastTrackService feastTrackSrv=new FeastTrackService();
					feastTrackSrv.deleteFeastTrack(fea_no, mem_no);
//				}
				
			}else if("trackFeastAdd".equals(action)) {
				String mem_no = reqjsonObject.get("mem_no").getAsString();
				String fea_no = reqjsonObject.get("fea_no").getAsString();
				System.out.println("會員"+mem_no+"關注"+fea_no);
				FeastTrackService feastTrackSrv=new FeastTrackService();
				feastTrackSrv.addFeastTrack(fea_no, mem_no);
			}else if("trackFeastDelete".equals(action)) {
				String mem_no = reqjsonObject.get("mem_no").getAsString();
				String fea_no = reqjsonObject.get("fea_no").getAsString();
				FeastTrackService feastTrackSrv=new FeastTrackService();
				feastTrackSrv.deleteFeastTrack(fea_no, mem_no);
			}

	}

}
