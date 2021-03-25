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
    public String insertWithDetails(OrdVO ordVO, List<Ord_detailsVO> list);
   
    
    //找一個餐廳所有的訂單
    public List<OrdVO> getByRes(String ord_resno);
    //找出一個會員所有的訂單
    public List<OrdVO> getByMem(String ord_memno);
    //找出會員是否已完成指定餐團的訂購
    public OrdVO findByMemFeast(String ord_memno,String ord_fea_no);
    //找出一個餐團有幾筆訂單
    public int CountFeastOrd(String ord_fea_no);
    //找一個餐團所有的訂單
    public List<OrdVO> getByFeast(String ord_fea_no);
    
    
}
