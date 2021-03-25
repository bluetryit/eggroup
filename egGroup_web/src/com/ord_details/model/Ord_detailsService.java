package com.ord_details.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.feastinfo.model.FeastInfoService;
import com.feastinfo.model.FeastInfoVO;
import com.fooditem.model.FooditemService;
import com.fooditem.model.FooditemVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.res.model.ResVO;

public class Ord_detailsService {
		private Ord_detailsDAO_interface dao;
		
		public Ord_detailsService() {
			dao=new Ord_detailsDAO();
		}
	 	public Ord_detailsVO addOrd_details(String det_ordno,String det_fono,Integer det_price,Integer det_quantity) {
	 		
	 		Ord_detailsVO ord_detailsVO=new Ord_detailsVO();
	 		ord_detailsVO.setDet_ordno(det_ordno);
			ord_detailsVO.setDet_fono(det_fono);
			ord_detailsVO.setDet_price(det_price);
			ord_detailsVO.setDet_quantity(det_quantity);
	 		
	 		dao.insert(ord_detailsVO);
	 		
	 		return ord_detailsVO;
	 	}
	    public Ord_detailsVO updateOrd_details(String det_ordno,String det_fono,Integer det_price,Integer det_quantity) {
	    	Ord_detailsVO ord_detailsVO=new Ord_detailsVO();
	 		ord_detailsVO.setDet_ordno(det_ordno);
			ord_detailsVO.setDet_fono(det_fono);
			ord_detailsVO.setDet_price(det_price);
			ord_detailsVO.setDet_quantity(det_quantity);
	    	dao.update(ord_detailsVO);
	    	
	    	return ord_detailsVO;
	    }

	    public Ord_detailsVO getOneOrdDet(String det_ordno,String det_fono) {
	    	return dao.findByPrimaryKey(det_ordno, det_fono);
	    }
	    public List<Ord_detailsVO> getAll(){
	    	return dao.getAll();
	    }
	    
	    public List<Ord_detailsVO> getAlldetByOrdno(String ord_no){
	    	return dao.getAll().stream()
	    			.filter(d -> d.getDet_ordno().equals(ord_no))
	    			.collect(Collectors.toList());
	    }
	    public void insert2(Ord_detailsVO ord_detailsVO,java.sql.Connection con) {
	    	dao.insert2(ord_detailsVO, con);
	    }
	    
	    
}
