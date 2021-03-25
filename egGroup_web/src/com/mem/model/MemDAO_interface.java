package com.mem.model;

import java.util.List;


public interface MemDAO_interface {
    public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public void delete(String mem_no);
    public MemVO findByPrimaryKey(String mem_no);
    public MemVO findByAC(String mem_ac);
    public List<MemVO> getAll();
    public String setCode(MemVO memVO);
    public MemVO  confirmCode(String code);
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<MemVO> getAll(Map<String, String[]> map); 
	

}
