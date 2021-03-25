package com.reportlm.model;

import java.util.List;
import java.util.stream.Collectors;

import com.post.model.PostVO;

public class ReportLmService {
	
	private ReportLmDAO_interface dao;
	
	public ReportLmService() {
		dao = new ReportLmDAO();
	}
	
	public ReportLmVO addRepLm(String repolm_lmano,
			String repolm_memno,String repolm_text) {
		ReportLmVO ReportLmVO = new ReportLmVO();
		
		
		ReportLmVO.setRepolm_lmano(repolm_lmano);
		ReportLmVO.setRepolm_memno(repolm_memno);
		ReportLmVO.setRepolm_text(repolm_text);
		
		dao.insert(ReportLmVO);
		return ReportLmVO;
	}
	
	public ReportLmVO updateRepoLm(String repolm_no,String repolm_status) {
		ReportLmVO ReportLmVO = new ReportLmVO();
		
		ReportLmVO.setRepolm_no(repolm_no);
		ReportLmVO.setRepolm_status(repolm_status);
		
		dao.update(ReportLmVO);
		return ReportLmVO;
	}
	public void deleteRepoLm(String repolm_no) {
		dao.delete(repolm_no);
	}
	public ReportLmVO getOneReportLm(String repolm_no) {
		return dao.findByPrimaryKey(repolm_no);
	}
	public List<ReportLmVO> getAll(){
		return dao.getAll();
	}
	
	public List<ReportLmVO> getAllUnprocesse() {
		return dao.getAll().stream()
			.filter(reportLM -> reportLM.getRepolm_status().equals("repolm3"))
			.collect(Collectors.toList());
	}

}
