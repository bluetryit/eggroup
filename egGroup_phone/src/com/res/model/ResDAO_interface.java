package com.res.model;

import java.util.List;

public interface ResDAO_interface {
    public void insert(ResVO resVO);
    public void update(ResVO resVO);
    public void delete(String res_no);
    public ResVO findByPrimaryKey(String res_no);
    public List<ResVO> getAll();
    byte[] getImage(String fo_no);
}
