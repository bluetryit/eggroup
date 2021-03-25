package com.leavemessage.model;

import java.io.Serializable;

public class LeaveMessageVO implements Serializable {
	private String lm_no;
	private String lm_postno;
	private String lm_memno;
	private String lm_text;
	private String lm_status;
	
	public String getLm_no() {
		return lm_no;
	}

	public void setLm_no(String lm_no) {
		this.lm_no = lm_no;
	}
	public String getLm_postno() {
		return lm_postno;
	}
	public void setLm_postno(String lm_postno) {
		this.lm_postno = lm_postno;
	}
	public String getLm_memno() {
		return lm_memno;
	}
	public void setLm_memno(String lm_memno) {
		this.lm_memno = lm_memno;
	}
	public String getLm_text() {
		return lm_text;
	}
	public void setLm_text(String lm_text) {
		this.lm_text = lm_text;
	}
	public String getLm_status() {
		return lm_status;
	}
	public void setLm_status(String lm_status) {
		this.lm_status = lm_status;
	}
	
	
	

	

}

