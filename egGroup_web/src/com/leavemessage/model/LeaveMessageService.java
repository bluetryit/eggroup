package com.leavemessage.model;

import java.util.List;
import java.util.stream.Collectors;

public class LeaveMessageService {
	
	private LeaveMessageDAO_interface dao;
	
	public LeaveMessageService() {
		dao = new LeaveMessageDAO(); 
	}
	
	public LeaveMessageVO addLeaveMessage(String lm_postno,String lm_memno,String lm_text,String lm_status) {
		
		LeaveMessageVO LeaveMessageVO = new LeaveMessageVO();
		
		
		LeaveMessageVO.setLm_postno(lm_postno);
		LeaveMessageVO.setLm_memno(lm_memno);
		LeaveMessageVO.setLm_text(lm_text);
		LeaveMessageVO.setLm_status(lm_status);
		dao.insert(LeaveMessageVO);
		return LeaveMessageVO;
	}
	
	public LeaveMessageVO updateLeaveMessage(String lm_no,String lm_postno,String lm_memno,String lm_text,String lm_status) {
		
		LeaveMessageVO LeaveMessageVO = new LeaveMessageVO();
		
		LeaveMessageVO.setLm_no(lm_no);
		LeaveMessageVO.setLm_postno(lm_postno);
		LeaveMessageVO.setLm_memno(lm_memno);
		LeaveMessageVO.setLm_text(lm_text);
		LeaveMessageVO.setLm_status(lm_status);
		dao.update(LeaveMessageVO);
		
		return LeaveMessageVO;
	}
	public void deleteLeaveMessage(String lm_no) {
		dao.delete(lm_no);
	}
	public LeaveMessageVO getOneLeaveMessage(String lm_no) {
		return dao.findByPrimaryKey(lm_no);
	}
	public List<LeaveMessageVO> getAll(){
		return dao.getAll();
	}
	public List<LeaveMessageVO> getAllLeaveMessageByPost(String post_no)
	{
		return dao.getAll().stream()
				.filter(leavemessage -> leavemessage.getLm_postno().equals(post_no))
				.filter(leavemessage -> leavemessage.getLm_status().equals("lm1"))
				.collect(Collectors.toList());
		
	}

}
