package com.pointtransaction.model;

import java.util.List;

public interface PointtransactionDAO_interface {
	 public void insert(PointtransactionVO PointtransactionVO);
     public void update(PointtransactionVO PointtransactionVO);
     public void delete(String pt_no);
     public PointtransactionVO findByPrimaryKey(String pt_no);
     public List<PointtransactionVO> getAll();
     public List<PointtransactionVO> getByMem(String pt_memno);
     
     //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//   public List<EmpVO> getAll(Map<String, String[]> map); 
}
