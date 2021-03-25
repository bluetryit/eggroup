package com.fooditem.model;

import java.io.Serializable;

public class FooditemVO implements Serializable {
	private String fo_no;
	private String fo_resno;
	private String fo_name;
	private Integer fo_price;
	private byte[] fo_img;
	private String fo_intro;
	private String fo_status;
	private Integer fo_quantity; // for 訂單使用 不存資料庫
	
	public Integer getFo_quantity() {
		return fo_quantity;
	}
	public void setFo_quantity(Integer fo_quantity) {
		this.fo_quantity = fo_quantity;
	}
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
	public byte[] getFo_img() {
		return fo_img;
	}
	public void setFo_img(byte[] fo_img) {
		this.fo_img = fo_img;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fo_no == null) ? 0 : fo_no.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FooditemVO other = (FooditemVO) obj;
		if (fo_no == null) {
			if (other.fo_no != null)
				return false;
		} else if (!fo_no.equals(other.fo_no))
			return false;
		return true;
	}

	
	
	
}
