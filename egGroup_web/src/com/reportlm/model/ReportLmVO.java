package com.reportlm.model;

import java.io.Serializable;

public class ReportLmVO implements Serializable {
	private String	repolm_no;
	private String	repolm_lmano;
	private String	repolm_memno;
	private String	repolm_text;
	private String	repolm_status;
	
	public String getRepolm_no() {
		return repolm_no;
	}
	public void setRepolm_no(String repolm_no) {
		this.repolm_no = repolm_no;
	}
	public String getRepolm_lmano() {
		return repolm_lmano;
	}
	public void setRepolm_lmano(String repolm_lmano) {
		this.repolm_lmano = repolm_lmano;
	}
	public String getRepolm_memno() {
		return repolm_memno;
	}
	public void setRepolm_memno(String repolm_memno) {
		this.repolm_memno = repolm_memno;
	}
	public String getRepolm_text() {
		return repolm_text;
	}
	public void setRepolm_text(String repolm_text) {
		this.repolm_text = repolm_text;
	}
	public String getRepolm_status() {
		return repolm_status;
	}
	public void setRepolm_status(String repolm_status) {
		this.repolm_status = repolm_status;
	}
	
	
}
