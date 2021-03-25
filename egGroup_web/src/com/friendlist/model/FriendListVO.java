package com.friendlist.model;
import java.sql.Date;

public class FriendListVO implements java.io.Serializable{
	
	private String f_no;
	private String f_memno;
	private String f_status;
	
	public String getF_no() {
		return f_no;
	}
	public void setF_no(String f_no) {
		this.f_no = f_no;
	}
	public String getF_memno() {
		return f_memno;
	}
	public void setF_memno(String f_memno) {
		this.f_memno = f_memno;
	}
	public String getF_status() {
		return f_status;
	}
	public void setF_status(String f_status) {
		this.f_status = f_status;
	}
	
	
	
	
}
