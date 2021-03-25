package com.res.model;

import java.io.Serializable;

public class ResVO implements Serializable {

	private String res_no;
	private String res_adrs;
	private String res_name;
	private String res_ph;
	private Integer res_point;
	private String res_ac;
	private String res_pass;
	private byte[] res_img;
	private String res_intro;
	private String res_start;
	private String res_end;
	private Double res_lat;
	private Double res_lot;
	private Integer res_score;
	private Integer res_cost;
	private Integer res_comcount;
	private String res_type;
	private String res_status;
	

	@Override
    public String toString()
    {
        return "ResVO [res_adrs=" + res_adrs + ", res_name=" + res_name + ", res_intro=" + res_intro + ", res_type="
                + res_type;
    }


    public ResVO() {
		super();
	}


	public String getRes_no() {
		return res_no;
	}


	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}


	public String getRes_adrs() {
		return res_adrs;
	}


	public void setRes_adrs(String res_adrs) {
		this.res_adrs = res_adrs;
	}


	public String getRes_name() {
		return res_name;
	}


	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}


	public String getRes_ph() {
		return res_ph;
	}


	public void setRes_ph(String res_ph) {
		this.res_ph = res_ph;
	}


	public Integer getRes_point() {
		return res_point;
	}


	public void setRes_point(Integer res_point) {
		this.res_point = res_point;
	}


	public String getRes_ac() {
		return res_ac;
	}


	public void setRes_ac(String res_ac) {
		this.res_ac = res_ac;
	}


	public String getRes_pass() {
		return res_pass;
	}


	public void setRes_pass(String res_pass) {
		this.res_pass = res_pass;
	}


	public byte[] getRes_img() {
		return res_img;
	}


	public void setRes_img(byte[] res_img) {
		this.res_img = res_img;
	}


	public String getRes_intro() {
		return res_intro;
	}


	public void setRes_intro(String res_intro) {
		this.res_intro = res_intro;
	}


	public String getRes_start() {
		return res_start;
	}


	public void setRes_start(String res_start) {
		this.res_start = res_start;
	}


	public String getRes_end() {
		return res_end;
	}


	public void setRes_end(String res_end) {
		this.res_end = res_end;
	}


	public Double getRes_lat() {
		return res_lat;
	}


	public void setRes_lat(Double res_lat) {
		this.res_lat = res_lat;
	}


	public Double getRes_lot() {
		return res_lot;
	}


	public void setRes_lot(Double res_lot) {
		this.res_lot = res_lot;
	}


	public Integer getRes_score() {
		return res_score;
	}


	public void setRes_score(Integer res_score) {
		this.res_score = res_score;
	}


	public Integer getRes_cost() {
		return res_cost;
	}


	public void setRes_cost(Integer res_cost) {
		this.res_cost = res_cost;
	}


	public Integer getRes_comcount() {
		return res_comcount;
	}


	public void setRes_comcount(Integer res_comcount) {
		this.res_comcount = res_comcount;
	}


	public String getRes_type() {
		return res_type;
	}


	public void setRes_type(String res_type) {
		this.res_type = res_type;
	}


	public String getRes_status() {
		return res_status;
	}


	public void setRes_status(String res_status) {
		this.res_status = res_status;
	}
	
}
