package com.resac.model;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class TestResAc {

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("請輸入員工編號");
//		String resac_no = sc.next();
//		System.out.println("請輸入餐廳編號");
//		String resac_resno = sc.next();
//				
//		//FindByPK
//		
//		ResAcDAO_interface dao = new ResAcDAO();
//		ResAcVO resAc = dao.findByPrimaryKey(resac_no, resac_resno);
//		System.out.println("resac_no = "+resAc.getResac_no());
//		System.out.println("resac_resno = "+resAc.getResac_resno());
//		System.out.println("resac_pass = "+resAc.getResac_pass());
//		System.out.println("resac_name = "+resAc.getResac_name());
//		System.out.println("resac_phone = "+resAc.getResac_phone());
//		System.out.println("resac_pic = "+resAc.getResac_pic());
//		System.out.println("resac_intro = "+resAc.getResac_intro());
//		System.out.println("resac_status = "+resAc.getResac_status());

//		
//		System.out.println("============================================");
	
		//GetAll
		
		//從迭代器取值方法1
//		ResAcDAO_interface dao = new ResAcDAO();
//		List<ResAcVO> resAcList = dao.getAll();
//		
//		for(ResAcVO resAc:resAcList) {
//			System.out.println("resac_no = "+resAc.getResac_no());
//			System.out.println("resac_resno = "+resAc.getResac_resno());
//			System.out.println("resac_pass = "+resAc.getResac_pass());
//			System.out.println("resac_name = "+resAc.getResac_name());
//			System.out.println("resac_phone = "+resAc.getResac_phone());
//			System.out.println("resac_pic = "+resAc.getResac_pic());
//			System.out.println("resac_intro = "+resAc.getResac_intro());
//			System.out.println("resac_status = "+resAc.getResac_status());
//			
//			System.out.println("============================================");
//		}
//		
//		//從迭代器取值方法2
//		for (int i = 0; i < resAcList.size(); i++) {
//			ResAcVO resAc = resAcList.get(i);
//			System.out.println("resac_no = "+resAc.getResac_no());
//			System.out.println("resac_resno = "+resAc.getResac_resno());
//			System.out.println("resac_pass = "+resAc.getResac_pass());
//			System.out.println("resac_name = "+resAc.getResac_name());
//			System.out.println("resac_phone = "+resAc.getResac_phone());
//			System.out.println("resac_pic = "+resAc.getResac_pic());
//			System.out.println("resac_intro = "+resAc.getResac_intro());
//			System.out.println("resac_status = "+resAc.getResac_status());
//			
//			System.out.println("============================================");
//		}
//		
//		//從迭代器取值方法3
//		Iterator<ResAcVO> objs = resAcList.iterator();
//		while(objs.hasNext()) {
//			ResAcVO resAc = objs.next();
//			System.out.println("resac_no = "+resAc.getResac_no());
//			System.out.println("resac_resno = "+resAc.getResac_resno());
//			System.out.println("resac_pass = "+resAc.getResac_pass());
//			System.out.println("resac_name = "+resAc.getResac_name());
//			System.out.println("resac_phone = "+resAc.getResac_phone());
//			System.out.println("resac_pic = "+resAc.getResac_pic());
//			System.out.println("resac_intro = "+resAc.getResac_intro());
//			System.out.println("resac_status = "+resAc.getResac_status());
//			
//			System.out.println("============================================");
//		}
			
			//updata
			//1.
//			ResAcDAO_interface dao = new ResAcDAO();
//			ResAcVO resAc = dao.findByPrimaryKey("000001", "RS000001");
//			resAc.setResac_name("大衛海鮮");
//			dao.update(resAc);
			
			//2.
//			ResAcDAO_interface dao = new ResAcDAO();
//			
//			ResAcVO resAc = dao.findByPrimaryKey("000001", "RS000001");
//			
//			resAc.setResac_no("000001");
//			resAc.setResac_resno("RS000001");
//			resAc.setResac_pass("A123456");
//			resAc.setResac_name("韓國魚");
//			resAc.setResac_phone("0955-702-775");
//			resAc.setResac_pic(resAc.getResac_pic());
//			resAc.setResac_intro("123456");
//			resAc.setResac_status("resac1");
//
//			
//			dao.update(resAc);
			//新增
			
//			ResAcDAO_interface dao = new ResAcDAO();
//			ResAcVO resAc = new ResAcVO();
//			
//			resAc.setResac_no("000003");
//			resAc.setResac_resno("RS000001");
//			resAc.setResac_pass("A123456");
//			resAc.setResac_name("韓國魚");
//			resAc.setResac_phone("0955-702-775");
//			resAc.setResac_pic(resAc.getResac_pic()); //資料庫設定不能為空值 要測的話要用IO好麻煩 先把資料庫改可以空
//			resAc.setResac_intro("123456");
//			resAc.setResac_status("resac1");
//			
//			dao.insert(resAc);
	}

}
