package com.leavemessage.model;

import java.util.List;

public interface LeaveMessageDAO_interface {
	public void insert(LeaveMessageVO LeavemessageVO);
	public void update(LeaveMessageVO LeavemessageVO);
	public void delete(String lm_no);
	public LeaveMessageVO findByPrimaryKey(String lm_no);
	public List<LeaveMessageVO> getAll();

}
