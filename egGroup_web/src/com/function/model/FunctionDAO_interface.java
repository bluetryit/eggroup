package com.function.model;

import java.util.*;

public interface FunctionDAO_interface {
          public void insert(FunctionVO functionVO);
          public void update(FunctionVO functionVO);
          public void delete(String fun_no);
          public FunctionVO findByPrimaryKey(String fun_no);
          public List<FunctionVO> getAll();
          //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
