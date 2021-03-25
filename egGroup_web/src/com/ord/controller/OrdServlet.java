package com.ord.controller;

import java.io.*;
import java.util.*;
import java.util.function.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.res.model.*;
import com.mem.model.*;
import com.ord.model.*;
import com.fooditem.model.*;
import com.ord_details.model.*;
import com.pointtransaction.model.*;


public class OrdServlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if("getOneOrd_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String ord_no = req.getParameter("ord_no");
				if(ord_no == null || (ord_no.trim()).length() == 0 ) {
					errorMsgs.add("請輸入訂單編號");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord/ord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.getOneOrd(ord_no);
				
				if(ordVO == null) {
					errorMsgs.add("查無此訂單");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord/ord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("ordVO", ordVO);
				String url = "/front-end/ord/listOneOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord/ord.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if("insert".equals(action)) {
			//選擇餐點的listAllFooditem.jsp -> checkout到確認餐點的ordConfirm.jsp(inster) -> 顯示訂單新增成功後的ordFinal.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				
				
				MemVO memVO = (MemVO)session.getAttribute("memberVO"); // 會員登入時會員實體有存session
				System.out.println(memVO);
				String ord_memno = memVO.getMem_no();
				System.out.println(ord_memno);
				String ord_fea_no = req.getParameter("ord_fea_no");
				System.out.println(ord_fea_no);
				ResVO resVO = (ResVO)session.getAttribute("ResVO"); 
				String ord_resno = resVO.getRes_no();
				System.out.println(ord_resno);
				java.sql.Timestamp ord_date = new java.sql.Timestamp(System.currentTimeMillis());
				System.out.println(ord_date);
				String ord_status = "ords1"; //預設新增訂單時狀態為 ords1 = 未處理
				
				String ord_type = ""; 
				if(req.getParameter("ord_type").equals("內用")) {
					ord_type ="ordt3";
				}else if (req.getParameter("ord_type").equals("外帶")) {
					ord_type = "ordt1";
				}else {
					ord_type = "ordt2";
				}
				
				
				
				@SuppressWarnings("unchecked")
				Vector<FooditemVO> foodList = (Vector<FooditemVO>)session.getAttribute("shoppingCart");
				List<Ord_detailsVO> detailList = new ArrayList<Ord_detailsVO>();
				Integer ord_price = new Integer((int)session.getAttribute("total"));
				if(memVO.getMem_point()<ord_price) {
					session.setAttribute("noMoney", "true");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ord/ordConfirm.jsp");
					failureView.forward(req, res);
					return;
				}
				for(FooditemVO fooditemVO : foodList) {
					Ord_detailsVO ord_detailsVO = new Ord_detailsVO();
					ord_detailsVO.setDet_fono(fooditemVO.getFo_no());
					ord_detailsVO.setDet_price(fooditemVO.getFo_price());
					ord_detailsVO.setDet_quantity(fooditemVO.getFo_quantity());
					detailList.add(ord_detailsVO);
				};
				
				
				OrdVO ordVO = new OrdVO();
				ordVO.setOrd_fea_no(ord_fea_no);
				ordVO.setOrd_memno(ord_memno);
				ordVO.setOrd_resno(ord_resno);
				ordVO.setOrd_date(ord_date);
				ordVO.setOrd_status(ord_status);
				ordVO.setOrd_type(ord_type);
				ordVO.setOrd_price(ord_price);
				
				
//				if(!errorMsgs.isEmpty()) {
//					req.setAttribute("ordVO", ordVO);
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/ord/ordConfirm.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				
				//新增訂單與訂單明細
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.addWithDetails(ord_fea_no, ord_memno, ord_resno, ord_price, ord_date, ord_status, ord_type, detailList);
				
				//扣點數
				String mem_name = memVO.getMem_name();
				String mem_adrs = memVO.getMem_adrs();
				String mem_sex = memVO.getMem_sex();
				java.sql.Date mem_bd = memVO.getMem_bd();
				String mem_ph = memVO.getMem_ph();
				String mem_email = memVO.getMem_email();
				byte[] mem_img = memVO.getMem_img();
				String mem_pass = memVO.getMem_pass();
				String mem_ac = memVO.getMem_ac();
				String mem_intro = memVO.getMem_intro();
				String mem_status = memVO.getMem_status();

				memVO.setMem_name(mem_name);
				memVO.setMem_adrs(mem_adrs);
				memVO.setMem_sex(mem_sex);
				memVO.setMem_bd(mem_bd);
				memVO.setMem_ph(mem_ph);
				memVO.setMem_email(mem_email);
				memVO.setMem_img(mem_img);
				memVO.setMem_pass(mem_pass);
				memVO.setMem_ac(mem_ac);
				memVO.setMem_intro(mem_intro);
				memVO.setMem_status(mem_status);
				
				Integer point = memVO.getMem_point();
				Integer mem_point = point - ord_price;
				memVO.setMem_point(mem_point);
				MemService memSvc = new MemService();
				memSvc.memUpdate(ord_memno, mem_name, mem_adrs, mem_sex, mem_bd, mem_ph, mem_email, mem_point, mem_img, mem_pass, mem_ac, mem_intro, mem_status);
				//新增一筆點數交易
				PointtransactionVO pointtransactionVO = new PointtransactionVO();
				PointtransactionService pointSvc = new PointtransactionService();
				String pt_memno = ord_memno;
				System.out.println(pt_memno);
				String pt_resno = null;
				Double pt_nt = new Double(0-ord_price);
				
				pointtransactionVO.setPt_memno(pt_memno);
				pointtransactionVO.setPt_resno(pt_resno);
				pointtransactionVO.setPt_nt(pt_nt);
				
				System.out.println(pointtransactionVO);
				pointSvc.addPointtransaction(pt_memno, pt_resno, pt_nt);
				System.out.println("184");
				req.setAttribute("ordVO", ordVO);
				System.out.println(ordVO);
				String url = "/front-end/ord/ordFinal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("結帳失敗"+e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/ord/listAllFooditem.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if("showFoodsInfo".equals(action)) {
			
			String res_no = req.getParameter("res_no");
			ResService resSvc = new ResService();
			ResVO resVO = resSvc.getOneRes(res_no);


			session.setAttribute("ResVO", resVO);
			String url = "/front-end/ord/listAllFooditem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

	}
		
		if("accept".equals(action)) {
			String fea_no = req.getParameter("fea_no");
			
			OrdService ordSvc = new OrdService();
			List<OrdVO> list = ordSvc.getAllUnOrdByFea(fea_no);
			
			for (OrdVO ordVO : list) {
				String ord_status = "ords4"; //將該飯局的所有未處理訂單改成已接單
				//其他的部分都取原值存回
				String ord_no = ordVO.getOrd_no();
				String ord_fea_no = ordVO.getOrd_fea_no();
				String ord_memno = ordVO.getOrd_memno();
				String ord_resno = ordVO.getOrd_resno();
				Integer ord_price = ordVO.getOrd_price();
				java.sql.Timestamp ord_date = ordVO.getOrd_date();
				String ord_type = ordVO.getOrd_type();
				
				
				ordSvc.updateOrd(ord_no, ord_fea_no, ord_memno, ord_resno, ord_price, ord_date, ord_status, ord_type);
			}
			
			session.setAttribute("AcceptSuccess", "true");
			String url = "/front-end/ord/ordMgByResUn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("reject".equals(action)) {
			String fea_no = req.getParameter("fea_no");
			
			OrdService ordSvc = new OrdService();
			List<OrdVO> list = ordSvc.getAllUnOrdByFea(fea_no);
			
			for (OrdVO ordVO : list) {
				String ord_status = "ords5"; //將該飯局的所有未處理訂單改成拒絕接單
				//其他的部分都取原值存回
				String ord_no = ordVO.getOrd_no();
				String ord_fea_no = ordVO.getOrd_fea_no();
				String ord_memno = ordVO.getOrd_memno();
				String ord_resno = ordVO.getOrd_resno();
				Integer ord_price = ordVO.getOrd_price();
				java.sql.Timestamp ord_date = ordVO.getOrd_date();
				String ord_type = ordVO.getOrd_type();
				
				
				ordSvc.updateOrd(ord_no, ord_fea_no, ord_memno, ord_resno, ord_price, ord_date, ord_status, ord_type);
			}
			
			session.setAttribute("RejectSuccess", "true");
			String url = "/front-end/ord/ordMgByResUn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		@SuppressWarnings("unchecked")
		Vector<FooditemVO> buylist = (Vector<FooditemVO>)session.getAttribute("shoppingCart");
		Boolean checkout = false;
		
		if (!"checkout".equals(action)) {
			if (("remove").equals(action)) {
				checkout = true;
				String del = req.getParameter("remove");
				Predicate<FooditemVO> condition = fooditemVO -> fooditemVO.getFo_no().equals(del);
				buylist.removeIf(condition);
			} 
			else if (("add").equals(action)) {
			System.out.println(288);
				checkout = true;	
				String fo_no = req.getParameter("fo_no");
				String fo_name = req.getParameter("fo_name");
				Integer fo_quantity = new Integer(req.getParameter("fo_quantity"));
				Integer fo_price = new Integer(req.getParameter("fo_price"));

				FooditemVO fooditemVO = new FooditemVO();

				fooditemVO.setFo_no(fo_no);
				fooditemVO.setFo_name(fo_name);
				fooditemVO.setFo_quantity(fo_quantity);
				fooditemVO.setFo_price(fo_price);

				if (buylist == null) {
					System.out.println(303);
					buylist = new Vector<FooditemVO>();
					buylist.add(fooditemVO);
				} else {
					if(buylist.contains(fooditemVO)) {
						System.out.println(308);
						FooditemVO innerFood = buylist.get(buylist.indexOf(fooditemVO));
						innerFood.setFo_quantity(innerFood.getFo_quantity()+fooditemVO.getFo_quantity());
					}else {
						System.out.println(312);
						buylist.add(fooditemVO);
					}
				}
			}
			else if(("modifyQuantity").equals(action)) {
				checkout = true;
				String fo_no = req.getParameter("fo_no");
				Integer fo_quantity = new Integer(req.getParameter("fo_quantity"));
				for (int i = 0; i < buylist.size(); i++) {
					FooditemVO fooditemVObuy = buylist.get(i);
					if (fo_no.equals(fooditemVObuy.getFo_no())) {
						fooditemVObuy.setFo_quantity(fo_quantity); 
						}
				}
			}
			if(checkout == true) {
			session.setAttribute("shoppingCart", buylist);
			session.setAttribute("shoppingCartQuantity", buylist.size());
			int cartQuantity = buylist.size();
			
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				FooditemVO order = buylist.get(i);
				int price = order.getFo_price();
				int quantity = order.getFo_quantity();
				total += (price * quantity);
			}
			session.setAttribute("total", total);
			
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("cartQuantity", cartQuantity);
			map.put("cartAmount", total);
	        JSONObject responseJSONObject = new JSONObject(map);
	        PrintWriter out = res.getWriter();
	        out.println(responseJSONObject);
			}
		}
		else if (("checkout").equals(action)) {
//			int total = 0;
//			for (int i = 0; i < buylist.size(); i++) {
//				PointGoodsVO order = buylist.get(i);
//				int points = order.getNeedpoints();
//				int quantity = order.getPointgoodsquantity();
//				total += (points * quantity);
//			}

//				String amount = String.valueOf(total);
			String amount = (String) session.getAttribute("total");

//			req.setAttribute("amount", amount);
			String url = "/front-end/ord/ordConfirm.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);

		}
	}

}
