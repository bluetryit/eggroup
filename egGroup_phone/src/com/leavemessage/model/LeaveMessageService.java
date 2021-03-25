package com.leavemessage.model;

import java.util.List;

public class LeaveMessageService {
	
	private LeaveMessageDAO_interface dao;
	
	public LeaveMessageService() {
		dao = new LeaveMessageDAO(); 
	}
	
	public LeaveMessageVO addLm(String lm_no,String lm_postno,String lm_memno,String lm_text,String lm_status) {
		
		LeaveMessageVO LeaveMessageVO = new LeaveMessageVO();
		
		LeaveMessageVO.setLm_no(lm_no);
		LeaveMessageVO.setLm_postno(lm_postno);
		LeaveMessageVO.setLm_memno(lm_memno);
		LeaveMessageVO.setLm_text(lm_text);
		LeaveMessageVO.setLm_status(lm_status);
		dao.insert(LeaveMessageVO);
		return LeaveMessageVO;
	}
	
	public LeaveMessageVO updateLm(String lm_no,String lm_postno,String lm_memno,String lm_text,String lm_status) {
		
		LeaveMessageVO LeaveMessageVO = new LeaveMessageVO();
		
		LeaveMessageVO.setLm_no(lm_no);
		LeaveMessageVO.setLm_postno(lm_postno);
		LeaveMessageVO.setLm_memno(lm_memno);
		LeaveMessageVO.setLm_text(lm_text);
		LeaveMessageVO.setLm_status(lm_status);
		dao.update(LeaveMessageVO);
		
		return LeaveMessageVO;
	}
	public void deleteLm(String lm_no) {
		dao.delete(lm_no);
	}
	public LeaveMessageVO getOneLm(String lm_no) {
		return dao.findByPrimaryKey(lm_no);
	}
	public List<LeaveMessageVO> getAll(){
		return dao.getAll();
	}

}
