package com.pointtransaction.model;

import java.io.Serializable;

public class PointtransactionVO implements Serializable{
	
	private String pt_no;
	private String pt_memno;
	private String pt_resno;
	private Double pt_nt;
	
	
	public String getPt_no() {
		return pt_no;
	}
	public void setPt_no(String pt_no) {
		this.pt_no = pt_no;
	}
	public String getPt_memno() {
		return pt_memno;
	}
	public void setPt_memno(String pt_memno) {
		this.pt_memno = pt_memno;
	}
	public String getPt_resno() {
		return pt_resno;
	}
	public void setPt_resno(String pt_resno) {
		this.pt_resno = pt_resno;
	}
	public Double getPt_nt() {
		return pt_nt;
	}
	public void setPt_nt(Double pt_nt) {
		this.pt_nt = pt_nt;
	}
	
}
