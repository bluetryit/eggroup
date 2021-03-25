package com.ord.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.feastinfo.model.FeastInfoVO;

public class OrdByFeastVO implements Serializable{
	private List<OrdVO> ordVO;
	private String mem_name;
	private String mem_phone;
	private FeastInfoVO feastinfoVO;
	private int total;
	
	
	public OrdByFeastVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrdByFeastVO(List<OrdVO> ordVO, String mem_name, String mem_phone, FeastInfoVO feastinfoVO, int total) {
		super();
		this.ordVO = ordVO;
		this.mem_name = mem_name;
		this.mem_phone = mem_phone;
		this.feastinfoVO = feastinfoVO;
		this.total = total;
	}


	public List<OrdVO> getOrdVO() {
		return ordVO;
	}


	public void setOrdVO(List<OrdVO> ordVO) {
		this.ordVO = ordVO;
	}


	public String getMem_name() {
		return mem_name;
	}


	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}


	public String getMem_phone() {
		return mem_phone;
	}


	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}


	public FeastInfoVO getFeastinfoVO() {
		return feastinfoVO;
	}


	public void setFeastinfoVO(FeastInfoVO feastinfoVO) {
		this.feastinfoVO = feastinfoVO;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	

}
