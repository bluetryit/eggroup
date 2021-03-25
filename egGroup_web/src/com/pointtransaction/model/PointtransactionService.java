package com.pointtransaction.model;

import java.util.List;
import java.util.stream.Collectors;

import com.fooditem.model.FooditemVO;
import com.pointtransaction.model.PointtransactionVO;



public class PointtransactionService {
	private PointtransactionDAO_interface pt;

	public PointtransactionService() {
		pt = new PointtransactionDAO();
	}
	
	public PointtransactionVO addPointtransaction(String pt_memno,
			String pt_resno, Double pt_nt) {

		PointtransactionVO pointtransactionVO = new PointtransactionVO();

		pointtransactionVO.setPt_memno(pt_memno);
		pointtransactionVO.setPt_resno(pt_resno);
		pointtransactionVO.setPt_nt(pt_nt);
		
		pt.insert(pointtransactionVO);

		return pointtransactionVO;
	}

		
	public PointtransactionVO updatePointtransaction(String pt_no, String pt_memno,
			String pt_resno, Double pt_nt) {

		PointtransactionVO pointtransactionVO = new PointtransactionVO();
		
		pointtransactionVO.setPt_no(pt_no);
		pointtransactionVO.setPt_memno(pt_memno);
		pointtransactionVO.setPt_resno(pt_resno);
		pointtransactionVO.setPt_nt(pt_nt);
		
		pt.update(pointtransactionVO);

		return pointtransactionVO;
	}
	public List<PointtransactionVO> getAllPointtransactionVOByPointtransaction(String pt_no)
	{
		return pt.getAll().stream()
				.filter(Pointtransaction -> Pointtransaction.getPt_no().equals(pt_no))
				.collect(Collectors.toList());
	}
	

	public void deletePointtransaction(String pt_no) {
		pt.delete(pt_no);
	}

	public PointtransactionVO getOnePointtransaction(String pt_no) {
		return pt.findByPrimaryKey(pt_no);
	}

	public List<PointtransactionVO> getAll() {
		return pt.getAll();
		
		
	}
	
	public List<PointtransactionVO> getAllPointByMem(String mem_no) {
		
		return pt.getAll().stream()
				.filter(Pointtransaction -> mem_no.equals(Pointtransaction.getPt_memno()))
				.collect(Collectors.toList());
	}
	
public List<PointtransactionVO> getAllPointByRes(String res_no) {
        
        return pt.getAll().stream()
                .filter(Pointtransaction -> res_no.equals(Pointtransaction.getPt_resno()))
                .collect(Collectors.toList());
    }
}
