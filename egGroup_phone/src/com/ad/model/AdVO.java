package com.ad.model;
import java.io.Serializable;
import java.sql.Date;

public class AdVO implements Serializable{
	
	private String ad_no;
	private String ad_resno;
	private String ad_text;
	private byte[] ad_img;
	private Date ad_start;
	private Date ad_end;
	private String ad_title;
	
	public AdVO() {
		super();
	}

	public String getAd_no() {
		return ad_no;
	}

	public void setAd_no(String ad_no) {
		this.ad_no = ad_no;
	}

	public String getAd_resno() {
		return ad_resno;
	}

	public void setAd_resno(String ad_resno) {
		this.ad_resno = ad_resno;
	}

	public String getAd_text() {
		return ad_text;
	}

	public void setAd_text(String ad_text) {
		this.ad_text = ad_text;
	}

	public byte[] getAd_img() {
		return ad_img;
	}

	public void setAd_img(byte[] ad_img) {
		this.ad_img = ad_img;
	}

	public Date getAd_start() {
		return ad_start;
	}

	public void setAd_start(Date ad_start) {
		this.ad_start = ad_start;
	}

	public Date getAd_end() {
		return ad_end;
	}

	public void setAd_end(Date ad_end) {
		this.ad_end = ad_end;
	}

	public String getAd_title() {
		return ad_title;
	}

	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}

}
