package com.reportpost.model;

import java.util.List;

public class ReportPostService {
	private ReportPostDAO_interface dao;
	
	public ReportPostService() {
		dao = new ReportPostDAO();
	}
	public ReportPostVO addRepoPost(String repopost_no,String repopost_postno,
			String repopost_memno,String repopost_text,String repopost_status) {
		
		ReportPostVO ReportPostVO = new ReportPostVO();
		
		ReportPostVO.setRepopost_no(repopost_no);
		ReportPostVO.setRepopost_postno(repopost_postno);
		ReportPostVO.setRepopost_memno(repopost_memno);
		ReportPostVO.setRepopost_text(repopost_text);
		ReportPostVO.setRepopost_status(repopost_status);
		dao.insert(ReportPostVO);
		return ReportPostVO;
		
	}
	public ReportPostVO updateRepoPost(String repopost_no,String repopost_postno,
			String repopost_memno,String repopost_text,String repopost_status) {
		
		ReportPostVO ReportPostVO = new ReportPostVO();
		
		ReportPostVO.setRepopost_no(repopost_no);
		ReportPostVO.setRepopost_postno(repopost_postno);
		ReportPostVO.setRepopost_memno(repopost_memno);
		ReportPostVO.setRepopost_text(repopost_text);
		ReportPostVO.setRepopost_status(repopost_status);
		dao.update(ReportPostVO);
		return ReportPostVO;
		
	}
	public void deleteRepoPost(String repopost_no) {
		dao.delete(repopost_no);
	}
	public ReportPostVO getOneRepoPost(String repopost_no) {
		return dao.findByPrimaryKey(repopost_no);
	}
	public List<ReportPostVO> getAll(){
		return dao.getAll();
	}
}
