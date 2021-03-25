package com.adminfunction.model;

import java.util.*;

public interface AdminFunctionDAO_interface {
          public void insert(AdminFunctionVO adminfunctionVO);
          public void update(AdminFunctionVO adminfunctionVO);
          public void delete(String fun_no);
          public AdminFunctionVO findByPrimaryKey(String fun_no);
          public List<AdminFunctionVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
