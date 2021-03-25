package com.ord.model;

import java.util.List;
import java.util.Set;

import com.ord_details.model.Ord_detailsVO;

public interface OrdDAO_interface {
    public void insert(OrdVO ordVO);
    public void update(OrdVO ordVO);
    
    public OrdVO findByPrimaryKey(String ord_no);
    public List<OrdVO> getAll();
    
    //查詢某訂單的明細(一對多) 回傳set
    public Set<Ord_detailsVO> getOrdDetByOrdno(String ord_no);
    
    //同時新增訂單與訂單明細
    public void insertWithDetails(OrdVO ordVO, List<Ord_detailsVO> list);
}
