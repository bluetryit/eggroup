package com.ord_details.model;

import java.io.Serializable;

public class Ord_detailsVO implements Serializable{
	private String det_ordno;
	private String det_fono;
	private Integer det_price;
	private Integer det_quantity;
	
	public Ord_detailsVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Ord_detailsVO(String det_ordno, String det_fono, Integer det_price, Integer det_quantity) {
		super();
		this.det_ordno = det_ordno;
		this.det_fono = det_fono;
		this.det_price = det_price;
		this.det_quantity = det_quantity;
	}

	public String getDet_ordno() {
		return det_ordno;
	}
	public void setDet_ordno(String det_ordno) {
		this.det_ordno = det_ordno;
	}
	public String getDet_fono() {
		return det_fono;
	}
	public void setDet_fono(String det_fono) {
		this.det_fono = det_fono;
	}
	public Integer getDet_price() {
		return det_price;
	}
	public void setDet_price(Integer det_price) {
		this.det_price = det_price;
	}
	public Integer getDet_quantity() {
		return det_quantity;
	}
	public void setDet_quantity(Integer det_quantity) {
		this.det_quantity = det_quantity;
	}
	
	
	
}
