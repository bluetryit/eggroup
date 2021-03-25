package com.ord.model;

import java.util.*;

import com.ord_details.model.*;;

public class TestOrd {
	public static void main(String[] args) {
		OrdDAO dao = new OrdDAO();

		OrdVO ordVO = new OrdVO();
		
		java.util.Date date = new java.util.Date();
		ordVO.setOrd_fea_no("FE000001");
		ordVO.setOrd_memno("ME000001");
		ordVO.setOrd_resno("RS000001");
		ordVO.setOrd_price(600);
		ordVO.setOrd_date(new java.sql.Timestamp(date.getTime()));
		ordVO.setOrd_status("ords1");
		ordVO.setOrd_type("ordt1");
		
		List<Ord_detailsVO> testList = new ArrayList<Ord_detailsVO>(); // 準備置入明細數筆
		Ord_detailsVO detXX = new Ord_detailsVO();   // 訂單明細POJO1
		detXX.setDet_fono("FO000001");
		detXX.setDet_price(200);
		detXX.setDet_quantity(1);


		Ord_detailsVO detYY = new Ord_detailsVO();   // 訂單明細POJO2
		detYY.setDet_fono("FO000002");
		detYY.setDet_price(100);
		detYY.setDet_quantity(4);

		testList.add(detXX);
		testList.add(detYY);
		
		dao.insertWithDetails(ordVO, testList);
		
		// 新增
//		OrdVO ordVO = new OrdVO();
		
//		java.util.Date date = new java.util.Date();
//		ordVO.setOrd_fea_no("FE000001");
//		ordVO.setOrd_memno("ME000001");
//		ordVO.setOrd_resno("RS000001");
//		ordVO.setOrd_price(600);
//		ordVO.setOrd_date(new java.sql.Timestamp(date.getTime()));
//		ordVO.setOrd_status("ords1");
//		ordVO.setOrd_type("ordt1");
//		dao.insert(ordVO);

		// 修改
//		OrdVO ordVO = new OrdVO();
//		java.util.Date date = new java.util.Date();
//		ordVO.setOrd_no("20190705-000013");
//		ordVO.setOrd_fea_no("FE000001");
//		ordVO.setOrd_memno("ME000001");
//		ordVO.setOrd_resno("RS000001");
//		ordVO.setOrd_price(800);
//		ordVO.setOrd_date(new java.sql.Timestamp(date.getTime()));
//		ordVO.setOrd_status("ords1");
//		ordVO.setOrd_type("ordt1");
//		dao.update(ordVO);



		// 查詢
//		OrdVO ordVO = dao.findByPrimaryKey("20190705-000013");
//		System.out.print(ordVO.getOrd_no() + ",");
//		System.out.print(ordVO.getOrd_fea_no() + ",");
//		System.out.print(ordVO.getOrd_memno() + ",");
//		System.out.print(ordVO.getOrd_resno() + ",");
//		System.out.print(ordVO.getOrd_price() + ",");
//		System.out.print(ordVO.getOrd_date() + ",");
//		System.out.print(ordVO.getOrd_status() + ",");
//		System.out.println(ordVO.getOrd_type());
//		System.out.println("---------------------");

		// 查詢訂單
//		List<OrdVO> list = dao.getAll();
//		for (OrdVO ordVO : list) {
//			System.out.print(ordVO.getOrd_no() + ",");
//			System.out.print(ordVO.getOrd_fea_no() + ",");
//			System.out.print(ordVO.getOrd_memno() + ",");
//			System.out.print(ordVO.getOrd_resno() + ",");
//			System.out.print(ordVO.getOrd_price() + ",");
//			System.out.print(ordVO.getOrd_date() + ",");
//			System.out.print(ordVO.getOrd_status() + ",");
//			System.out.println(ordVO.getOrd_type());
//			System.out.println("---------------------");
//			System.out.println();
//		}
		
		// 查詢某訂單的明細
//		Set<Ord_detailsVO> set = dao.getOrdDetByOrdno("20190704-000005");
//		for (Ord_detailsVO aDet : set) {
//			System.out.print(aDet.getDet_ordno() + ",");
//			System.out.print(aDet.getDet_fono() + ",");
//			System.out.print(aDet.getDet_price() + ",");
//			System.out.print(aDet.getDet_quantity() + ",");
//			System.out.println();
//		}
	}
	
}
