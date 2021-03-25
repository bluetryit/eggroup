package com.resac.model;

import java.util.List;

public class ResAcService {
	private ResAcDAO_interface dao;
	
	public ResAcService() {
		dao = new ResAcDAO();
	}
	
	public ResAcVO addResAc(String resac_no,String resac_resno,String resac_pass,String resac_name,String resac_phone,byte[] resac_pic,String resac_intro,String resac_status) {
		ResAcVO resAcVO = new ResAcVO();
		
		resAcVO.setResac_no(resac_no);
		resAcVO.setResac_resno(resac_resno);
		resAcVO.setResac_pass(resac_pass);
		resAcVO.setResac_name(resac_name);
		resAcVO.setResac_phone(resac_phone);
		resAcVO.setResac_pic(resac_pic);
		resAcVO.setResac_intro(resac_intro);
		resAcVO.setResac_status(resac_status);
		
		dao.insert(resAcVO);
		
		return resAcVO;
	}
	
	public ResAcVO updateResAc(String resac_no,String resac_resno,String resac_pass,String resac_name,String resac_phone,byte[] resac_pic,String resac_intro,String resac_status) {
		ResAcVO resAcVO = new ResAcVO();
		
		resAcVO.setResac_no(resac_no);
		resAcVO.setResac_resno(resac_resno);
		resAcVO.setResac_pass(resac_pass);
		resAcVO.setResac_name(resac_name);
		resAcVO.setResac_phone(resac_phone);
		resAcVO.setResac_pic(resac_pic);
		resAcVO.setResac_intro(resac_intro);
		resAcVO.setResac_status(resac_status);
		
		dao.update(resAcVO);
		
		return resAcVO;
	}
	
	public ResAcVO getOneResAc(String resac_no,String resac_resno) {
		return dao.findByPrimaryKey(resac_no, resac_resno);
	}
	
	public List<ResAcVO> getAll(){
		return dao.getAll();
	}
}
