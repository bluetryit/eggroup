package com.mem.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;

public class MemTest {
	
	public static void main(String[] args) {
		Date mem_bd=new Date(2015-11-17);//��
		
//		Blob blob = con.createBlob();
//		byte[] mem_img=getPictureByteArray("item/1.jpg");
//		blob.setBytes(1, mem_img);
//		MemVO memVO=new MemVO("ME000006","��","����","�k",mem_bd,"0989648851","bluetryit@gmail.com",0,mem_img,"111","123456","�u���O�ܳ·�","mem2");
		MemDAO memDAO=new MemDAO();
		
		String mem_no_update="ME000005";
		String mem_no_find="ME000005";
		
		
		
		//memDAO.insert(memVO);
//		memDAO.delete(mem_no_delete);
	MemVO memVO=memDAO.findByPrimaryKey(mem_no_find);
		System.out.println(memVO.getMem_name()+memVO.getMem_adrs());
		memVO.setMem_status("mem1");
		memDAO.update(memVO);
		
	}
//	// �ϥ�byte[]�覡�A�N�ɮ׼g�i��Ʈw
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}
	


}
