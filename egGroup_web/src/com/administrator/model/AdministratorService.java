package com.administrator.model;

import java.util.List;

public class AdministratorService {
	private AdministratorDAO_interface dao;
	
	public AdministratorService() {
		dao = new AdministratorDAO();
	}
	public AdministratorVO addAdministrator(String admin_no,String admin_ac,
			String admin_pass,String admin_name) {
		
		AdministratorVO AdministratorVO = new AdministratorVO();
		
		AdministratorVO.setAdmin_no(admin_no);
		AdministratorVO.setAdmin_ac(admin_ac);
		AdministratorVO.setAdmin_pass(admin_pass);
		AdministratorVO.setAdmin_name(admin_name);
		dao.insert(AdministratorVO);
		return AdministratorVO;
		
	}
	public AdministratorVO updateAdministrator(String admin_no,String admin_ac,
			String admin_pass,String admin_name) {
		
		AdministratorVO AdministratorVO = new AdministratorVO();
		
		AdministratorVO.setAdmin_no(admin_no);
		AdministratorVO.setAdmin_ac(admin_ac);
		AdministratorVO.setAdmin_pass(admin_pass);
		AdministratorVO.setAdmin_name(admin_name);
		dao.update(AdministratorVO);
		return AdministratorVO;
		
	}
	public void deleteAdministrator(String admin_no) {
		dao.delete(admin_no);
	}
	public AdministratorVO getOneAdministrator(String admin_no) {
		return dao.findByPrimaryKey(admin_no);
	}
	
	public AdministratorVO getOneAdminByAccount(String admin_ac) {
        return dao.findByPrimaryKeyByAc(admin_ac);
    }
	
	public List<AdministratorVO> getAll(){
		return dao.getAll();
	}
}
