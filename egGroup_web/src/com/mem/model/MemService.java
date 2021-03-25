package com.mem.model;

import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;

import Mail.MailService;

public class MemService {
	private MemDAO_interface dao;
	
	public MemService() {
		dao=new MemDAO();
	}
	
	 public MemVO memInsert(String mem_name,String mem_adrs,String mem_sex,Date mem_bd,String mem_ph,
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
			
			return memVO;
	 }
	 public MemVO memUpdate(String mem_no,String mem_name,String mem_adrs,String mem_sex,Date mem_bd,String mem_ph,
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
		 return memVO;
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
	 
	 
	 
	 public void sendMail(MemVO memVO) {
		 							
		 	String code = dao.setCode(memVO); //存 memVO 在 Jedis 並取出 亂碼寄信
		 
			String subject="請點選連結完成註冊";
			StringBuilder sb =new StringBuilder();
			String text  = memVO.getMem_name()+" 您好:\n";
			String text2 = "感謝您使用本系統，請將下方連結複製並貼在網誌列，才能完成驗證並使用本網站進行驗證\n"; 
			String hyperLink= "http://localhost:8081/DA101_G6/mem.do?action=confirm&code="+code;
					
			sb.append(text).append(text2).append(hyperLink);
			MailService mailSve = new MailService();
			mailSve.sendMail(memVO.getMem_email(), subject, sb.toString());	
			System.out.println(memVO.getMem_no());
			System.out.println("寄信成功");
	 }
	 	 
	 public boolean confirmCode(String code) {
		 boolean result;
		 MemVO memVO = null;
		 
		 memVO = dao.confirmCode(code);
		 memVO.setMem_status("mem2");
		 
		 dao.update(memVO);
		 result = true;
		 
		 
		 return result;
	 }
}
