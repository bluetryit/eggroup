package com.resac.model;

import java.io.Serializable;

public class ResAcVO implements Serializable {
	
	private String resac_no;
	private String resac_resno;
	private String resac_pass;
	private String resac_name;
	private String resac_phone;
	private byte[] resac_pic;
	private String resac_intro;
	private String resac_status;
	
	public ResAcVO() {
		super();
	}

	public String getResac_no() {
		return resac_no;
	}

	public void setResac_no(String resac_no) {
		this.resac_no = resac_no;
	}

	public String getResac_resno() {
		return resac_resno;
	}

	public void setResac_resno(String resac_resno) {
		this.resac_resno = resac_resno;
	}

	public String getResac_pass() {
		return resac_pass;
	}

	public void setResac_pass(String resac_pass) {
		this.resac_pass = resac_pass;
	}

	public String getResac_name() {
		return resac_name;
	}

	public void setResac_name(String resac_name) {
		this.resac_name = resac_name;
	}

	public String getResac_phone() {
		return resac_phone;
	}

	public void setResac_phone(String resac_phone) {
		this.resac_phone = resac_phone;
	}

	public byte[] getResac_pic() {
		return resac_pic;
	}

	public void setResac_pic(byte[] resac_pic) {
		this.resac_pic = resac_pic;
	}

	public String getResac_intro() {
		return resac_intro;
	}

	public void setResac_intro(String resac_intro) {
		this.resac_intro = resac_intro;
	}

	public String getResac_status() {
		return resac_status;
	}

	public void setResac_status(String resac_status) {
		this.resac_status = resac_status;
	}


	
	
}
