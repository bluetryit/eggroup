package com.reportpost.model;

import java.io.Serializable;

public class ReportPostVO implements Serializable {
	private String	repopost_no;
	private String	repopost_postno;
	private String	repopost_memno;
	private String	repopost_text;
	private String	repopost_status;
	
	public String getRepopost_no() {
		return repopost_no;
	}
	public void setRepopost_no(String repopost_no) {
		this.repopost_no = repopost_no;
	}
	public String getRepopost_postno() {
		return repopost_postno;
	}
	public void setRepopost_postno(String repopost_postno) {
		this.repopost_postno = repopost_postno;
	}
	public String getRepopost_memno() {
		return repopost_memno;
	}
	public void setRepopost_memno(String repopost_memno) {
		this.repopost_memno = repopost_memno;
	}
	public String getRepopost_text() {
		return repopost_text;
	}
	public void setRepopost_text(String repopost_text) {
		this.repopost_text = repopost_text;
	}
	public String getRepopost_status() {
		return repopost_status;
	}
	public void setRepopost_status(String repopost_status) {
		this.repopost_status = repopost_status;
	}
	
	

}
