package com.ord.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class OrdVO implements Serializable{
	
	private String ord_no;
	private String ord_fea_no;
	private String ord_memno;
	private String ord_resno;
	private Integer ord_price;
	private Timestamp ord_date;
	private String ord_status;
	private String ord_type;
	
	@Override
	public String toString() {
		return "OrdVO [ord_no=" + ord_no + ", ord_fea_no=" + ord_fea_no + ", ord_memno=" + ord_memno + ", ord_resno="
				+ ord_resno + ", ord_price=" + ord_price + ", ord_date=" + ord_date + ", ord_status=" + ord_status
				+ ", ord_type=" + ord_type + "]";
	}

	public OrdVO() {
		super();
	}

	public String getOrd_no() {
		return ord_no;
	}

	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}

	public String getOrd_fea_no() {
		return ord_fea_no;
	}

	public void setOrd_fea_no(String ord_fea_no) {
		this.ord_fea_no = ord_fea_no;
	}

	public String getOrd_memno() {
		return ord_memno;
	}

	public void setOrd_memno(String ord_memno) {
		this.ord_memno = ord_memno;
	}

	public String getOrd_resno() {
		return ord_resno;
	}

	public void setOrd_resno(String ord_resno) {
		this.ord_resno = ord_resno;
	}

	public Integer getOrd_price() {
		return ord_price;
	}

	public void setOrd_price(Integer ord_price) {
		this.ord_price = ord_price;
	}

	public Timestamp getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(Timestamp ord_date) {
		this.ord_date = ord_date;
	}

	public String getOrd_status() {
		return ord_status;
	}

	public void setOrd_status(String ord_status) {
		this.ord_status = ord_status;
	}

	public String getOrd_type() {
		return ord_type;
	}

	public void setOrd_type(String ord_type) {
		this.ord_type = ord_type;
	}

}
