package com.resac.model;

import java.util.List;

public interface ResAcDAO_interface {
    public void insert(ResAcVO resAcVO);
    public void update(ResAcVO resAcVO);
    public ResAcVO findByPrimaryKey(String resac_no,String resac_resno);
    public List<ResAcVO> getAll();
}
