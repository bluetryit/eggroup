package com.pointtransaction.controller;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ord.model.OrdFooditemVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.ord_details.model.Ord_detailsVO;
import com.pointtransaction.model.PointtransactionService;
import com.pointtransaction.model.PointtransactionVO;
import com.res.model.ResService;
import com.res.model.ResVO;
import com.mem.model.*;

/**
 * Servlet implementation class PointTrans
 */
@WebServlet("/PointTrans")
public class PointTrans extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       //當會員點數異動，設置會員紀錄
   protected int setMemPoint(String mem_no,int mem_point) {
	   MemService memSrv=new MemService();
	   MemVO memVO=memSrv.memFindByPrimaryKey(mem_no);
	   int point=memVO.getMem_point()+mem_point;
	   memVO.setMem_point(point);
	   memSrv.memUpdate(memVO.getMem_no(), memVO.getMem_name(), memVO.getMem_adrs(), memVO.getMem_sex(), memVO.getMem_bd(), memVO.getMem_ph(), memVO.getMem_email(), memVO.getMem_point(), memVO.getMem_img(), memVO.getMem_pass(), memVO.getMem_ac(), memVO.getMem_intro(), memVO.getMem_status());
	   
	   return point;
   }
   //當餐廳點數異動，設置餐廳紀錄
 protected int setResPoint(String res_no,int res_point) {

	   ResService resSrv=new ResService();
	   ResVO resVO=resSrv.getOneRes(res_no);
	   int point=resVO.getRes_point()+res_point;
	   resVO.setRes_point(point);
	   resSrv.updateRes(resVO.getRes_no(), resVO.getRes_adrs(), resVO.getRes_name(), resVO.getRes_ph(), resVO.getRes_point(), resVO.getRes_ac(), resVO.getRes_pass(), resVO.getRes_intro(), resVO.getRes_start(), resVO.getRes_end(), resVO.getRes_lat(), resVO.getRes_lot(), resVO.getRes_score(), resVO.getRes_cost(), resVO.getRes_comcount(), resVO.getRes_type(), resVO.getRes_status());
	   
	   return point;
   }
   //新增會員點數"購入"紀錄
   protected void addMemMoneyChange(String mem_no,int mem_point) {
	   PointtransactionService pttSrv=new PointtransactionService();
	   PointtransactionVO pttVO=new PointtransactionVO();
	   pttVO.setPt_memno(mem_no);
	   pttVO.setPt_nt((double)(mem_point));
	   pttSrv.addPtt(pttVO);
	
   }
   //新增餐廳點數"提領"紀錄
   protected void addResMoneyChange(String res_no,int res_point) {
	   PointtransactionService pttSrv=new PointtransactionService();
	   PointtransactionVO pttVO=new PointtransactionVO();
	   pttVO.setPt_resno(res_no);
	   pttVO.setPt_nt((double)(res_point));
	   pttSrv.addPtt(pttVO);
   }
   //新增會員"訂餐"紀錄
   protected void addOrdTrans(String mem_no,int mem_point,String res_no) {
	   PointtransactionService pttSrv=new PointtransactionService();
	   PointtransactionVO pttVO=new PointtransactionVO();
	   pttVO.setPt_memno(mem_no);
	   pttVO.setPt_resno(res_no);
	   pttVO.setPt_nt((double)(mem_point));
	   pttSrv.addPtt(pttVO);
   }
   
	
	
    public PointTrans() {
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
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		
		if(action.equals("ord")) {//訂餐交易
			String mem_no = jsonObject.get("mem_no").getAsString();
			String res_no=jsonObject.get("res_no").getAsString();
			int mem_point = jsonObject.get("mem_point").getAsInt();
			
			int mempoint=setMemPoint(mem_no,mem_point);
		//	int respoint=setResPoint(res_no,-mem_point);//設置餐廳資料，由於傳進來的 mem_point是負值所以這裡是負的
			JsonObject reJson=new JsonObject();
			addOrdTrans(mem_no,mem_point,res_no);
			reJson.addProperty("mempoint", mempoint);
		//	reJson.addProperty("respoint", respoint);
			
			System.out.println(gson.fromJson(reJson, String.class));
			out.print(gson.fromJson(reJson, String.class));
			
		}else if(action.equals("mem")) {//會員儲值
			String mem_no = jsonObject.get("mem_no").getAsString();
			int mem_point = jsonObject.get("mem_point").getAsInt();
			addMemMoneyChange(mem_no,mem_point);
			int point=setMemPoint(mem_no,mem_point);
			System.out.println(gson.toJson(point));
			out.print(gson.toJson(point));
			
		}else if(action.equals("getList")){
			System.out.println("這邊請求有金來");
			String mem_no = jsonObject.get("mem_no").getAsString();
			 PointtransactionService pttSrv=new PointtransactionService();
			System.out.println(gson.toJson(pttSrv.getByMem(mem_no)));
			out.print(gson.toJson(pttSrv.getByMem(mem_no)));
		
		}else {//餐廳提款
			String res_no = jsonObject.get("res_no").getAsString();
			int res_point = jsonObject.get("res_point").getAsInt();
			addResMoneyChange(res_no,res_point);
			int point=setResPoint(res_no,res_point);
			System.out.println(gson.toJson(point));
			out.print(gson.toJson(point));
		}
					
	} 
				
}


