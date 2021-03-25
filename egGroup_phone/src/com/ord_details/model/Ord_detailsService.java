package com.ord_details.model;

import java.util.List;

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
	    
}
