package com.reportlm.model;

import java.util.List;

public interface ReportLmDAO_interface {
	public void insert(ReportLmVO ReportLmVO);
	public void update(ReportLmVO ReportLmVO);
	public void delete(String repolm_no);
	public ReportLmVO findByPrimaryKey(String repolm_no);
	public List<ReportLmVO> getAll();

}
