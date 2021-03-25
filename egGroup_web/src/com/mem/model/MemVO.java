package com.mem.model;

import java.io.Serializable;
import java.sql.Date;

public class MemVO implements Serializable{
	private String mem_no;
	private String mem_name;
	private String mem_adrs;
	private String mem_sex;
	private Date mem_bd;
	private String mem_ph;
	private String mem_email;
	private Integer mem_point;
	private byte[] mem_img;
	private String mem_pass;
	private String mem_ac;
	private String mem_intro;
	private String mem_status;
	
	public MemVO() {
		super();
	}
	
	public MemVO(String mem_name,String mem_sex,String mem_intro) {
        super();
        this.mem_name= mem_name;
        this.mem_sex = mem_sex;
        this.mem_intro = mem_intro;
    }

	public MemVO(String mem_no, String mem_name, String mem_adrs, String mem_sex, Date mem_bd, String mem_ph,
			String mem_email, Integer mem_point, byte[] mem_img, String mem_pass, String mem_ac, String mem_intro,
			String mem_status) {
		super();
		this.mem_no = mem_no;
		this.mem_name = mem_name;
		this.mem_adrs = mem_adrs;
		this.mem_sex = mem_sex;
		this.mem_bd = mem_bd;
		this.mem_ph = mem_ph;
		this.mem_email = mem_email;
		this.mem_point = mem_point;
		this.mem_img = mem_img;
		this.mem_pass = mem_pass;
		this.mem_ac = mem_ac;
		this.mem_intro = mem_intro;
		this.mem_status = mem_status;
	}



	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_adrs() {
		return mem_adrs;
	}

	public void setMem_adrs(String mem_adrs) {
		this.mem_adrs = mem_adrs;
	}

	public String getMem_sex() {
		return mem_sex;
	}

	public void setMem_sex(String mem_sex) {
		this.mem_sex = mem_sex;
	}

	public Date getMem_bd() {
		return mem_bd;
	}

	public void setMem_bd(Date mem_bd) {
		this.mem_bd = mem_bd;
	}

	public String getMem_ph() {
		return mem_ph;
	}

	public void setMem_ph(String mem_ph) {
		this.mem_ph = mem_ph;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public Integer getMem_point() {
		return mem_point;
	}

	public void setMem_point(Integer mem_point) {
		this.mem_point = mem_point;
	}

	public byte[] getMem_img() {
		return mem_img;
	}

	public void setMem_img(byte[] mem_img) {
		this.mem_img = mem_img;
	}

	public String getMem_pass() {
		return mem_pass;
	}

	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}

	public String getMem_ac() {
		return mem_ac;
	}

	public void setMem_ac(String mem_ac) {
		this.mem_ac = mem_ac;
	}

	public String getMem_intro() {
		return mem_intro;
	}

	public void setMem_intro(String mem_intro) {
		this.mem_intro = mem_intro;
	}

	public String getMem_status() {
		return mem_status;
	}

	public void setMem_status(String mem_status) {
		this.mem_status = mem_status;
	}

	
	
	
	

}
