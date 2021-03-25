package com.fooditem.model;

import java.util.List;

public interface FooditemDAO_interface {
	  public void insert(FooditemVO FooditemVO);
      public void update(FooditemVO FooditemVO);
      public void delete(String fo_no);
      public FooditemVO findByPrimaryKey(String fo_no);
      public List<FooditemVO> getAll();
      public List<FooditemVO> getByResNO(String fo_resno);
      //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//    public List<EmpVO> getAll(Map<String, String[]> map); 
      
      public List<FooditemVO> getAllReviewFooditemByRes(String fo_resno);
}

