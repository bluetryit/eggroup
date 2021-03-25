package com.pointtransaction.model;

import java.util.List;

import com.pointtransaction.model.PointtransactionVO;



public class PointtransactionService {
	private PointtransactionDAO_interface po;

	public PointtransactionService() {
		po = new PointtransactionDAO();
	}
	
	public PointtransactionVO addPointtransaction(String pt_no, String pt_memno,
			String pt_resno, Double pt_nt) {

		PointtransactionVO pointtransactionVO = new PointtransactionVO();

		pointtransactionVO.setPt_no(pt_no);
		pointtransactionVO.setPt_memno(pt_memno);
		pointtransactionVO.setPt_resno(pt_resno);
		pointtransactionVO.setPt_nt(pt_nt);
		
		po.insert(pointtransactionVO);

		return pointtransactionVO;
	}
	
	public void addPtt(PointtransactionVO pointtransactionVO) {

	
		po.insert(pointtransactionVO);

	}

	
	
	public PointtransactionVO updatePointtransaction(String pt_no, String pt_memno,
			String pt_resno, Double pt_nt) {

		PointtransactionVO pointtransactionVO = new PointtransactionVO();
		
		pointtransactionVO.setPt_no(pt_no);
		pointtransactionVO.setPt_memno(pt_memno);
		pointtransactionVO.setPt_resno(pt_resno);
		pointtransactionVO.setPt_nt(pt_nt);
		
		po.update(pointtransactionVO);

		return pointtransactionVO;
	}
	
	

	public void deletePointtransaction(String pt_no) {
		po.delete(pt_no);
	}

	public PointtransactionVO getOnePointtransaction(String pt_no) {
		return po.findByPrimaryKey(pt_no);
	}

	public List<PointtransactionVO> getAll() {
		return po.getAll();
	}
	
	public List<PointtransactionVO> getByMem(String pt_memno) {
		return po.getByMem(pt_memno);
	}
}
