package com.reportpost.model;

import java.util.List;

public interface ReportPostDAO_interface {
	public void insert (ReportPostVO ReportPostVO);
	public void update (ReportPostVO ReportPostVO);
	public void delete (String repopost_no);
	public ReportPostVO findByPrimaryKey(String repopost_no);
	public List<ReportPostVO> getAll();

}
