package com.ord.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.ord.model.OrdByFeastVO;
import com.ord.model.OrdFooditemVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.ord_details.model.Ord_detailsVO;
import com.res.model.ResService;
import com.res.model.ResVO;
import com.feastinfo.model.*;
import com.fooditem.model.FooditemService;



/**
 * Servlet implementation class OrdServlet
 */
@WebServlet("/OrdChangeStatus")
public class OrdChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	 //當餐廳點數異動，設置餐廳紀錄
	 protected int setResPoint(String res_no,int res_point) {

		   ResService resSrv=new ResService();
		   ResVO resVO=resSrv.getOneRes(res_no);
		   int point=resVO.getRes_point()+res_point;
		   resVO.setRes_point(point);
		   resSrv.updateRes(resVO.getRes_no(), resVO.getRes_adrs(), resVO.getRes_name(), resVO.getRes_ph(), resVO.getRes_point(), resVO.getRes_ac(), resVO.getRes_pass(), resVO.getRes_intro(), resVO.getRes_start(), resVO.getRes_end(), resVO.getRes_lat(), resVO.getRes_lot(), resVO.getRes_score(), resVO.getRes_cost(), resVO.getRes_comcount(), resVO.getRes_type(), resVO.getRes_status());
		   
		   return point;
	   }
    public OrdChangeStatus() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input2: " + jsonIn);
		
		OrdService ordSrv = new OrdService();
		FeastInfoService feastInfoSrv=new FeastInfoService();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),JsonObject.class);
		String action = jsonObject.get("action").getAsString();//改成什麼
		String feastId = jsonObject.get("feastId").getAsString();//改那個飯局的單號
		
		if (action!=null && feastId != null) {
			if(ordSrv.getByFeast(feastId)!=null) {
			List<OrdVO> ordVOList=ordSrv.getByFeast(feastId);
			int sum=0;
				for(OrdVO ordVO:ordVOList) {
					ordVO.setOrd_status(action);
					ordSrv.updateOrd(ordVO.getOrd_no(), ordVO.getOrd_fea_no(), ordVO.getOrd_memno(), ordVO.getOrd_resno(), ordVO.getOrd_price(), ordVO.getOrd_date(), ordVO.getOrd_status(), ordVO.getOrd_type());
					sum+=ordVO.getOrd_price();
					System.out.println("修改訂單:"+ordVO.getOrd_no()+"狀態為:"+action);
				}
				if("ords6".equalsIgnoreCase(action)) {
    				FeastInfoVO feastInfoVO =new FeastInfoVO();
    				feastInfoVO=feastInfoSrv.getOneFeastInfo(feastId);
    				feastInfoVO.setFea_status("fea3");
    				feastInfoSrv.updateFeastInfo(feastInfoVO.getFea_no(), feastInfoVO.getFea_resNo(), feastInfoVO.getFea_memNo(), feastInfoVO.getFea_title(),feastInfoVO.getFea_text(), feastInfoVO.getFea_number(),feastInfoVO.getFea_upLim(), feastInfoVO.getFea_upLim(), feastInfoVO.getFea_date(), feastInfoVO.getFea_startDate(), feastInfoVO.getFea_endDate(), feastInfoVO.getFea_type(), feastInfoVO.getFea_loc(), feastInfoVO.getFea_status());
				}
				}			
		}else {
			out.print("null");
		}
	}
}
