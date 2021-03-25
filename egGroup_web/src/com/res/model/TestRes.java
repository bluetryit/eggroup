package com.res.model;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TestRes {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入餐廳編號");
		String res_no = sc.next();
				
		//FindByPK
		
		ResDAO_interface dao = new ResDAO();
		ResVO res = dao.findByPrimaryKey(res_no);
		System.out.println("res_no = "+res.getRes_no());
		System.out.println("res_adrs = "+res.getRes_adrs());
		System.out.println("res_name = "+res.getRes_name());
		System.out.println("res_ph = "+res.getRes_ph());
		System.out.println("res_point = "+res.getRes_point());
		System.out.println("res_ac = "+res.getRes_ac());
		System.out.println("res_pass = "+res.getRes_pass());
		System.out.println("res_img = "+res.getRes_img());
		System.out.println("res_intro = "+res.getRes_intro());
		System.out.println("res_hours = "+res.getRes_start());
		System.out.println("res_lat = "+res.getRes_lat());
		System.out.println("res_lot = "+res.getRes_lot());
		System.out.println("res_score = "+res.getRes_score());
		System.out.println("res_cost = "+res.getRes_cost());
		System.out.println("res_comcount = "+res.getRes_comcount());
		System.out.println("res_type = "+res.getRes_type());
		System.out.println("res_status = "+res.getRes_status());
//		
//		System.out.println("============================================");
	
		//GetAll
		
		//從迭代器取值方法1
//		ResDAO_interface dao = new ResDAO();
//		List<ResVO> resList = dao.getAll();
		
//		for(ResVO res:resList) {
//			System.out.println("res_no = "+res.getRes_no());
//			System.out.println("res_adrs = "+res.getRes_adrs());
//			System.out.println("res_name = "+res.getRes_name());
//			System.out.println("res_ph = "+res.getRes_ph());
//			System.out.println("res_point = "+res.getRes_point());
//			System.out.println("res_ac = "+res.getRes_ac());
//			System.out.println("res_pass = "+res.getRes_pass());
//			System.out.println("res_img = "+res.getRes_img());
//			System.out.println("res_intro = "+res.getRes_intro());
//			System.out.println("res_hours = "+res.getRes_hours());
//			System.out.println("res_lat = "+res.getRes_lat());
//			System.out.println("res_lot = "+res.getRes_lot());
//			System.out.println("res_score = "+res.getRes_score());
//			System.out.println("res_cost = "+res.getRes_cost());
//			System.out.println("res_comcount = "+res.getRes_comcount());
//			System.out.println("res_type = "+res.getRes_type());
//			System.out.println("res_status = "+res.getRes_status());
//			
//			System.out.println("============================================");
//		}
		
		//從迭代器取值方法2
//		for (int i = 0; i < resList.size(); i++) {
//			ResVO res = resList.get(i);
//			System.out.println("res_no = "+res.getRes_no());
//			System.out.println("res_adrs = "+res.getRes_adrs());
//			System.out.println("res_name = "+res.getRes_name());
//			System.out.println("res_ph = "+res.getRes_ph());
//			System.out.println("res_point = "+res.getRes_point());
//			System.out.println("res_ac = "+res.getRes_ac());
//			System.out.println("res_pass = "+res.getRes_pass());
//			System.out.println("res_img = "+res.getRes_img());
//			System.out.println("res_intro = "+res.getRes_intro());
//			System.out.println("res_hours = "+res.getRes_hours());
//			System.out.println("res_lat = "+res.getRes_lat());
//			System.out.println("res_lot = "+res.getRes_lot());
//			System.out.println("res_score = "+res.getRes_score());
//			System.out.println("res_cost = "+res.getRes_cost());
//			System.out.println("res_comcount = "+res.getRes_comcount());
//			System.out.println("res_type = "+res.getRes_type());
//			System.out.println("res_status = "+res.getRes_status());
//			
//			System.out.println("============================================");
//		}
		
		//從迭代器取值方法3
//		Iterator<ResVO> objs = resList.iterator();
//		while(objs.hasNext()) {
//			ResVO res = objs.next();
//			System.out.println("res_no = "+res.getRes_no());
//			System.out.println("res_adrs = "+res.getRes_adrs());
//			System.out.println("res_name = "+res.getRes_name());
//			System.out.println("res_ph = "+res.getRes_ph());
//			System.out.println("res_point = "+res.getRes_point());
//			System.out.println("res_ac = "+res.getRes_ac());
//			System.out.println("res_pass = "+res.getRes_pass());
//			System.out.println("res_img = "+res.getRes_img());
//			System.out.println("res_intro = "+res.getRes_intro());
//			System.out.println("res_hours = "+res.getRes_hours());
//			System.out.println("res_lat = "+res.getRes_lat());
//			System.out.println("res_lot = "+res.getRes_lot());
//			System.out.println("res_score = "+res.getRes_score());
//			System.out.println("res_cost = "+res.getRes_cost());
//			System.out.println("res_comcount = "+res.getRes_comcount());
//			System.out.println("res_type = "+res.getRes_type());
//			System.out.println("res_status = "+res.getRes_status());
//			
//			System.out.println("============================================");
//		}
			//刪除
//			ResDAO_interface dao = new ResDAO();
//			dao.delete(res_no);
			
			//updata
			//1.
//			ResDAO_interface dao = new ResDAO();
//			ResVO res = dao.findByPrimaryKey("RS000002");
//			res.setRes_name("負面能量");
//			dao.update(res);
			
			//2.
//			ResDAO_interface dao = new ResDAO();
//			ResVO res = new ResVO();
//			
//			res.setRes_no("RS000001");
//			res.setRes_adrs("高雄發大財");
//			res.setRes_name("韓國魚");
//			res.setRes_ph("948888888");
//			res.setRes_point(0);
//			res.setRes_ac("ac123458");
//			res.setRes_pass("123456");
//			res.setRes_img(null);
//			res.setRes_intro("很多魚");
//			res.setRes_hours("0800~1700");
//			res.setRes_lat(24.25);
//			res.setRes_lot(121.36);
//			res.setRes_score(4.2);
//			res.setRes_cost(6666);
//			res.setRes_comcount(300);
//			res.setRes_type("海鮮料理");
//			res.setRes_status("res2");
//			
//			dao.update(res);
			//新增
			
//			ResDAO_interface dao = new ResDAO();
//			ResVO res = new ResVO();
//			
//			res.setRes_adrs("高雄發大財");
//			res.setRes_name("韓國魚");
//			res.setRes_ph("9488888886");
//			res.setRes_point(0);
//			res.setRes_ac("ac1234586");
//			res.setRes_pass("123456");
//			res.setRes_img(null);
//			res.setRes_intro("很多魚");
//			res.setRes_hours("0800~1700");
//			res.setRes_lat(24.25);
//			res.setRes_lot(121.36);
//			res.setRes_score(4.2);
//			res.setRes_cost(6666);
//			res.setRes_comcount(300);
//			res.setRes_type("海鮮料理");
//			res.setRes_status("res2");
//			
//			dao.insert(res);
	}

}
