package com.res.model;

import java.util.List;

public interface ResDAO_interface {
    public void insert(ResVO resVO);
    public void update(ResVO resVO);
    public void delete(String res_no);
    public ResVO findByPrimaryKey(String res_no);
    public List<ResVO> getAll();
	ResVO findByAC(String res_ac);
	public List<ResVO> getAllOnliseRes();
	List<ResVO> getAllReview();
	List<ResVO> getAllReviewAgain();
	
}
