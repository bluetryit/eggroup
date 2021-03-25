package com.ord.model;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import com.ord_details.model.Ord_detailsVO;

public class OrdService {
	private OrdDAO_interface dao;

	public OrdService() {
		dao = new OrdDAO();
	}

	public OrdVO addOrd(String ord_fea_no, String ord_memno, String ord_resno, Integer ord_price, Timestamp ord_date,
			String ord_status, String ord_type) {
		OrdVO ordVO = new OrdVO();

		ordVO.setOrd_fea_no(ord_fea_no);
		ordVO.setOrd_memno(ord_memno);
		ordVO.setOrd_resno(ord_resno);
		ordVO.setOrd_price(ord_price);
		ordVO.setOrd_date(ord_date);
		ordVO.setOrd_status(ord_status);
		ordVO.setOrd_type(ord_type);

		dao.insert(ordVO);

		return ordVO;
	}

	public OrdVO updateOrd(String ord_no, String ord_fea_no, String ord_memno, String ord_resno, Integer ord_price,
			Timestamp ord_date, String ord_status, String ord_type) {
		OrdVO ordVO = new OrdVO();

		ordVO.setOrd_no(ord_no);
		ordVO.setOrd_fea_no(ord_fea_no);
		ordVO.setOrd_memno(ord_memno);
		ordVO.setOrd_resno(ord_resno);
		ordVO.setOrd_price(ord_price);
		ordVO.setOrd_date(ord_date);
		ordVO.setOrd_status(ord_status);
		ordVO.setOrd_type(ord_type);

		dao.update(ordVO);

		return ordVO;
	}

	public OrdVO getOneOrd(String ord_no) {
		return dao.findByPrimaryKey(ord_no);
	}

	public List<OrdVO> getAll() {
		return dao.getAll();
	}
	
	public Map<String,List<OrdVO>> getAllOrdByResFromFea(String ord_resno) {
		
		Map<String,List<OrdVO>> groupMap = new HashMap<String, List<OrdVO>>();
		groupMap = dao.getAll().stream()
		.filter(ord -> ord.getOrd_resno().equals(ord_resno))
		.collect(Collectors.groupingBy(OrdVO::getOrd_fea_no));
		
		return groupMap;
	}

	public Set<Ord_detailsVO> getOrdDetByOrdno(String ord_no) {
		return dao.getOrdDetByOrdno(ord_no);
	}
	
	public List<OrdVO> getAllOrdByMem(String mem_no){
		return dao.getAll().stream()
				.filter(ord -> ord.getOrd_memno().equals(mem_no))
				.collect(Collectors.toList());
	}
	public Integer getAllOrdTwdByMemAndFea(String fea_no,String mem_no){
		return dao.getAll().stream()
				.filter(ord -> ord.getOrd_fea_no().equals(fea_no))
				.filter(ord -> ord.getOrd_memno().equals(mem_no))
				.mapToInt(ord -> ord.getOrd_price())
				.sum();
				
	}
	
	public List<OrdVO> getAllUnOrdByFea(String fea_no){
		return dao.getAll().stream()
				.filter(ord -> ord.getOrd_status().equals("ords1"))
				.filter(ord -> ord.getOrd_fea_no().equals(fea_no))
				.collect(Collectors.toList());
	}
	
	public Integer getAllUnOrdTwdByFea(String fea_no){
		return dao.getAll().stream()
				.filter(ord -> ord.getOrd_status().equals("ords1"))
				.filter(ord -> ord.getOrd_fea_no().equals(fea_no))
				.mapToInt(ord -> ord.getOrd_price())
				.sum();
				
	}
	
	public List<OrdVO> getAllOkOrdByFea(String fea_no){
		return dao.getAll().stream()
				.filter(ord -> ord.getOrd_status().equals("ords4"))
				.filter(ord -> ord.getOrd_fea_no().equals(fea_no))
				.collect(Collectors.toList());
	}
	
	public Integer getAllOkOrdTwdByFea(String fea_no){
		return dao.getAll().stream()
				.filter(ord -> ord.getOrd_status().equals("ords4"))
				.filter(ord -> ord.getOrd_fea_no().equals(fea_no))
				.mapToInt(ord -> ord.getOrd_price())
				.sum();
				
	}

	public OrdVO addWithDetails(String ord_fea_no, String ord_memno, String ord_resno, Integer ord_price,
			Timestamp ord_date, String ord_status, String ord_type, List<Ord_detailsVO> list) {
		OrdVO ordVO = new OrdVO();
		
		
		ordVO.setOrd_fea_no(ord_fea_no);
		ordVO.setOrd_memno(ord_memno);
		ordVO.setOrd_resno(ord_resno);
		ordVO.setOrd_price(ord_price);
		ordVO.setOrd_date(ord_date);
		ordVO.setOrd_status(ord_status);
		ordVO.setOrd_type(ord_type);
		
		dao.insertWithDetails(ordVO, list);
		
		return ordVO;
	}
	
	public boolean isMemOrdInFea(String ord_fea_no, String ord_memno) {
	       List<OrdVO> ordList = dao.getAll().stream()
                                    .filter(ord -> ord.getOrd_fea_no().equals(ord_fea_no))
                                    .filter(ord -> ord.getOrd_memno().equals(ord_memno))
                                    .collect(Collectors.toList());
	         
           if(ordList.size() == 0) 
           {
               return false;
           }
           else 
           {
               return true;
           }
	        
    }

//	public static void main(String[] args) {
//		OrdService ordSvc = new OrdService();
//		String str = "RS000001";
//		
//		Map<String,List<OrdVO>> groupMap = ordSvc.getAllOrdByResFromFea(str);
//
//		
//		groupMap.forEach((k, v) -> {
//			System.out.println("\nOrd_fea_no: " + k);
//			v.forEach(System.out::println);
//		});
//	}
}
