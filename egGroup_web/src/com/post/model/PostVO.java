package com.post.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class PostVO implements Serializable{
	private String		post_no;
	private String		post_memno;
	private String		post_res_no;
	private String		post_text;
	private byte[]		post_img;
	private Timestamp	post_time;
	private String		post_respon;
	private Integer		post_rate;
	private String		post_status;
	
	public String getPost_no() {
		return post_no;
	}
	@Override
	public String toString() {
		return "PostVO [post_no=" + post_no + ", post_memno=" + post_memno + ", post_res_no=" + post_res_no
				+ ", post_text=" + post_text + ", post_img=" + Arrays.toString(post_img) + ", post_time=" + post_time
				+ ", post_respon=" + post_respon + ", post_rate=" + post_rate + ", post_status=" + post_status + "]";
	}
	public void setPost_no(String post_no) {
		this.post_no = post_no;
	}
	
	public String getPost_memno() {
		return post_memno;
	}
	public void setPost_memno(String post_memno) {
		this.post_memno = post_memno;
	}
	public String getPost_res_no() {
		return post_res_no;
	}
	public void setPost_res_no(String post_res_no) {
		this.post_res_no = post_res_no;
	}
	public String getPost_text() {
		return post_text;
	}
	public void setPost_text(String post_text) {
		this.post_text = post_text;
	}
	public byte[] getPost_img() {
		return post_img;
	}
	public void setPost_img(byte[] post_img) {
		this.post_img = post_img;
	}
	public Timestamp getPost_time() {
		return post_time;
	}
	public void setPost_time(Timestamp post_time) {
		this.post_time = post_time;
	}
	public String getPost_respon() {
		return post_respon;
	}
	public void setPost_respon(String post_respon) {
		this.post_respon = post_respon;
	}
	public Integer getPost_rate() {
		return post_rate;
	}
	public void setPost_rate(Integer post_rate) {
		this.post_rate = post_rate;
	}
	public String getPost_status() {
		return post_status;
	}
	public void setPost_status(String post_status) {
		this.post_status = post_status;
	}
	
	
	
	

}
