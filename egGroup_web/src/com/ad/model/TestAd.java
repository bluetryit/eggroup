package com.ad.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TestAd {
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("請輸入廣告編號");
//		String ad_no = sc.next();
//
//				
//		//FindByPK
//		
//		AdDAO_interface dao = new AdDAO();
//		AdVO ad = dao.findByPrimaryKey(ad_no);
//		System.out.println("ad_no = "+ad.getAd_no());
//		System.out.println("ad_resno = "+ad.getAd_resno());
//		System.out.println("ad_text = "+ad.getAd_text());
//		System.out.println("ad_img = "+ad.getAd_img());
//		System.out.println("ad_start = "+ad.getAd_start());
//		System.out.println("ad_end = "+ad.getAd_end());
//		System.out.println("ad_title = "+ad.getAd_title());


//		
//		System.out.println("============================================");
	
		//GetAll
		
		//從迭代器取值方法1
//		AdDAO_interface dao = new AdDAO();
//		List<AdVO> adList = dao.getAll();
		
//		for(AdVO ad:adList) {
//			System.out.println("ad_no = "+ad.getAd_no());
//			System.out.println("ad_resno = "+ad.getAd_resno());
//			System.out.println("ad_text = "+ad.getAd_text());
//			System.out.println("ad_img = "+ad.getAd_img());
//			System.out.println("ad_start = "+ad.getAd_start());
//			System.out.println("ad_end = "+ad.getAd_end());
//			System.out.println("ad_title = "+ad.getAd_title());
//			
//			System.out.println("============================================");
//		}
//		
//		//從迭代器取值方法2
//		for (int i = 0; i < adList.size(); i++) {
//			AdVO ad = adList.get(i);
//			System.out.println("ad_no = "+ad.getAd_no());
//			System.out.println("ad_resno = "+ad.getAd_resno());
//			System.out.println("ad_text = "+ad.getAd_text());
//			System.out.println("ad_img = "+ad.getAd_img());
//			System.out.println("ad_start = "+ad.getAd_start());
//			System.out.println("ad_end = "+ad.getAd_end());
//			System.out.println("ad_title = "+ad.getAd_title());
//			
//			System.out.println("============================================");
//		}
//		
//		//從迭代器取值方法3
//		Iterator<AdVO> objs = adList.iterator();
//		while(objs.hasNext()) {
//			AdVO ad = objs.next();
//			System.out.println("ad_no = "+ad.getAd_no());
//			System.out.println("ad_resno = "+ad.getAd_resno());
//			System.out.println("ad_text = "+ad.getAd_text());
//			System.out.println("ad_img = "+ad.getAd_img());
//			System.out.println("ad_start = "+ad.getAd_start());
//			System.out.println("ad_end = "+ad.getAd_end());
//			System.out.println("ad_title = "+ad.getAd_title());
//			
//			System.out.println("============================================");
//		}
			
			//updata
			//1.
//			AdDAO_interface dao = new AdDAO();
//			AdVO ad = dao.findByPrimaryKey("AD000001");
//			ad.setAd_title("壽星八折辣");
//			dao.update(ad);
			
			//2.
//			AdDAO_interface dao = new AdDAO();
//			
//			AdVO ad = new AdVO();
//			java.util.Date date = new java.util.Date();
//			
//			ad.setAd_no("AD000001");
//			ad.setAd_resno("RS000001");
//			ad.setAd_text("壽星八折");
//			ad.setAd_img(null);
//			ad.setAd_start(new java.sql.Timestamp(date.getTime()));
//			ad.setAd_end(new java.sql.Timestamp(date.getTime()));
//			ad.setAd_title("123456");
//	
//			dao.update(ad);
			//新增
			
//			AdDAO_interface dao = new AdDAO();
//			AdVO ad = new AdVO();
//			java.util.Date date = new java.util.Date();
//			ad.setAd_no("AD000006");
//			ad.setAd_resno("RS000001");
//			ad.setAd_text("臭韓粉還敢點進來看內文啊");
//			ad.setAd_img(null);
//			ad.setAd_start(new java.sql.Timestamp(date.getTime()));
//			ad.setAd_end(new java.sql.Timestamp(date.getTime()));
//			ad.setAd_title("韓粉打到骨折");
//			
//			dao.insert(ad);
	}
}
