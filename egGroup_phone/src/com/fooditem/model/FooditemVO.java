package com.fooditem.model;

import java.io.Serializable;

public class FooditemVO implements Serializable {
	private String fo_no;
	private String fo_resno;
	private String fo_name;
	private Integer fo_price;
	private String fo_intro;
	private String fo_status;
	
	public String getFo_no() {
		return fo_no;
	}
	public void setFo_no(String fo_no) {
		this.fo_no = fo_no;
		
	}
	public String getFo_resno() {
		return fo_resno;
	}
	public void setFo_resno(String fo_resno) {
		this.fo_resno = fo_resno;
	}
	
	
	
	public String getFo_name() {
		return fo_name;
	}
	public void setFo_name(String fo_name) {
		this.fo_name = fo_name;
	}
	public Integer getFo_price() {
		return fo_price;
	}
	public void setFo_price(Integer fo_price) {
		this.fo_price = fo_price;
	}
	public String getFo_intro() {
		return fo_intro;
	}
	public void setFo_intro(String fo_intro) {
		this.fo_intro = fo_intro;
	}
	public String getFo_status() {
		return fo_status;
	}
	public void setFo_status(String fo_status) {
		this.fo_status = fo_status;
	}

	
	
	
}
