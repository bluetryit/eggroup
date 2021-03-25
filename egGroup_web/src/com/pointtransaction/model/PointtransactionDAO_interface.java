package com.pointtransaction.model;

import java.util.List;

public interface PointtransactionDAO_interface {
	 public void insert(PointtransactionVO PointtransactionVO);
     public void update(PointtransactionVO PointtransactionVO);
     public void delete(String pt_no);
     public PointtransactionVO findByPrimaryKey(String pt_no);
     public List<PointtransactionVO> getAll();
     
   //萬用複合查詢(傳入參數型態Map)(回傳 List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 
}
