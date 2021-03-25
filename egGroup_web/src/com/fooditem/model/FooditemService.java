package com.fooditem.model;


import java.util.*;
import java.util.stream.Collectors;

import com.ord_details.model.*;



public class FooditemService {
	private FooditemDAO_interface fo;

	public FooditemService() {
		fo = new FooditemDAO();
	}

	public FooditemVO addFooditem(String fo_resno, String fo_name,
			int fo_price, byte[] fo_img, String fo_intro,String fo_status) {

		FooditemVO fooditemVO = new FooditemVO();

		fooditemVO.setFo_resno(fo_resno);
		fooditemVO.setFo_name(fo_name);
		fooditemVO.setFo_price(fo_price);
		fooditemVO.setFo_img(fo_img);
		fooditemVO.setFo_intro(fo_intro);
		fooditemVO.setFo_status(fo_status);
		fo.insert(fooditemVO);

		return fooditemVO;
	}

	
	
	public FooditemVO updateFooditem(String fo_no,String fo_resno, String fo_name,
			int fo_price, byte[] fo_img, String fo_intro,String fo_status) {

		FooditemVO fooditemVO = new FooditemVO();
		
		fooditemVO.setFo_no(fo_no);
		fooditemVO.setFo_resno(fo_resno);
		fooditemVO.setFo_name(fo_name);
		fooditemVO.setFo_price(fo_price);
		fooditemVO.setFo_img(fo_img);
		fooditemVO.setFo_intro(fo_intro);
		fooditemVO.setFo_status(fo_status);
		fo.update(fooditemVO);

		return fooditemVO;
	}
	
	

	public void deleteFooditem(String fo_no) {
		fo.delete(fo_no);
	}

	public FooditemVO getOneFooditem(String fo_no) {
		return fo.findByPrimaryKey(fo_no);
	}

	public List<FooditemVO> getAll() {
		return fo.getAll();
	}
	
	public List<FooditemVO> getAllFoodByOrd(String ord_no) {
		Ord_detailsService ordDSvc =new Ord_detailsService();
		List<FooditemVO> list = new ArrayList<FooditemVO>();
		List<Ord_detailsVO> ord_detailsVOs = ordDSvc.getAlldetByOrdno(ord_no);
		for (Ord_detailsVO ord_detailsVO : ord_detailsVOs) {
			String fo_no = ord_detailsVO.getDet_fono();
			FooditemVO fooditemVO = fo.findByPrimaryKey(fo_no);
			list.add(fooditemVO);
		}
		return list;
	}
	public List<FooditemVO> getByResNOFooditem(String fo_resno) {
		return fo.getByResNO(fo_resno);
	}
	
	public List<FooditemVO> getAllfooditemVOByRes(String fo_resno) {
	    return fo.getAll().stream()
                .filter(fo -> fo.getFo_resno().equals(fo_resno))
                .collect(Collectors.toList());
    }
	
	public List<FooditemVO> getAllReviewFooditemByRes(String fo_resno) {
		
	    return fo.getAllReviewFooditemByRes(fo_resno);
    }
}

