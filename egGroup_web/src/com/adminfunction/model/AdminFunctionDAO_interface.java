package com.adminfunction.model;

import java.util.*;

public interface AdminFunctionDAO_interface {
          public void insert(AdminFunctionVO adminfunctionVO);
          public void update(AdminFunctionVO adminfunctionVO);
          public void delete(String fun_no);
          public AdminFunctionVO findByPrimaryKey(String fun_no);
          public List<AdminFunctionVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
