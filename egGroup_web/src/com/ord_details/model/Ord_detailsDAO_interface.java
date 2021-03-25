package com.ord_details.model;

import java.util.List;


public interface Ord_detailsDAO_interface {
    public void insert(Ord_detailsVO ord_detailsVO);
    public void update(Ord_detailsVO ord_detailsVO);
    
    public Ord_detailsVO findByPrimaryKey(String det_ordno,String det_fono);
    public List<Ord_detailsVO> getAll();

    //同時新增訂單與訂單明細
    public void insert2(Ord_detailsVO ord_detailsVO,java.sql.Connection con);
}
