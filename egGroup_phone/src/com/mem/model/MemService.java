package com.mem.model;

import java.sql.Date;
import java.util.List;

public class MemService {
	private MemDAO_interface dao;
	
	public MemService() {
		dao=new MemDAO();
	}
	
	 public void memInsert(String mem_name,String mem_adrs,String mem_sex,Date mem_bd,String mem_ph,
			String mem_email,Integer mem_point,byte[] mem_img,String mem_pass,String mem_ac,String mem_intro,String mem_status) {
		 
			MemVO memVO =new MemVO();
			memVO.setMem_name(mem_name);
			memVO.setMem_adrs(mem_adrs);
			memVO.setMem_sex(mem_sex);
			memVO.setMem_bd(mem_bd);
			memVO.setMem_ph(mem_ph);
			memVO.setMem_email(mem_email);
			memVO.setMem_point(mem_point);
			memVO.setMem_img(mem_img);
			memVO.setMem_pass(mem_pass);
			memVO.setMem_ac(mem_ac);
			memVO.setMem_intro(mem_intro);
			memVO.setMem_status(mem_status);

			dao.insert(memVO);
	 }
	 public void memUpdate(String mem_no,String mem_name,String mem_adrs,String mem_sex,Date mem_bd,String mem_ph,
				String mem_email,Integer mem_point,byte[] mem_img,String mem_pass,String mem_ac,String mem_intro,String mem_status) {
		 	MemVO memVO =new MemVO();
		 	
			memVO.setMem_name(mem_name);
			memVO.setMem_adrs(mem_adrs);
			memVO.setMem_sex(mem_sex);
			memVO.setMem_bd(mem_bd);
			memVO.setMem_ph(mem_ph);
			memVO.setMem_email(mem_email);
			memVO.setMem_point(mem_point);
			memVO.setMem_img(mem_img);
			memVO.setMem_pass(mem_pass);
			memVO.setMem_ac(mem_ac);
			memVO.setMem_intro(mem_intro);
			memVO.setMem_status(mem_status);
			memVO.setMem_no(mem_no);
			
		 dao.update(memVO);
	 }
	 public void memDelete(String mem_no) {
		 dao.delete(mem_no);
	 }
	 public MemVO memFindByPrimaryKey(String mem_no) {
		 return dao.findByPrimaryKey(mem_no);
	 }
	 public MemVO memFindByAC(String mem_ac) {
		 return dao.findByAC(mem_ac);
	 }
	 
	 public List<MemVO> memGetAll(){
			return dao.getAll();
	 }
}
