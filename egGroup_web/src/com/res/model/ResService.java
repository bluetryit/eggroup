package com.res.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResService {
    private ResDAO_interface dao;
    
    public ResService() {
        dao = new ResDAO();
    }
    
    public ResVO addRes(String res_adrs, String res_name, String res_ph, Integer res_point, String res_ac,
            String res_pass, byte[] res_img, String res_intro, String res_start,String res_end, Double res_lat, Double res_lot,
            Integer res_score, Integer res_cost, Integer res_comcount, String res_type, String res_status) {
        
        ResVO resVO = new ResVO();
        
        
        resVO.setRes_adrs(res_adrs);
        resVO.setRes_name(res_name);
        resVO.setRes_ph(res_ph);
        resVO.setRes_point(res_point);
        resVO.setRes_ac(res_ac);
        resVO.setRes_pass(res_pass);
        resVO.setRes_img(res_img);
        resVO.setRes_intro(res_intro);
        resVO.setRes_start(res_start);
        resVO.setRes_end(res_end);
        resVO.setRes_lat(res_lat);
        resVO.setRes_lot(res_lot);
        resVO.setRes_score(res_score);
        resVO.setRes_cost(res_cost);
        resVO.setRes_comcount(res_comcount);
        resVO.setRes_type(res_type);
        resVO.setRes_status(res_status);
        
        dao.insert(resVO);
        
        return resVO;
    }
    
    public ResVO updateRes(String res_no, String res_adrs, String res_name, String res_ph, Integer res_point, String res_ac,
            String res_pass, byte[] res_img, String res_intro, String res_start,String res_end, Double res_lat, Double res_lot,
            Integer res_score, Integer res_cost, Integer res_comcount, String res_type, String res_status) {
        
        
        ResVO resVO = new ResVO();
        
        resVO.setRes_no(res_no);
        resVO.setRes_adrs(res_adrs);
        resVO.setRes_name(res_name);
        resVO.setRes_ph(res_ph);
        resVO.setRes_point(res_point);
        resVO.setRes_ac(res_ac);
        resVO.setRes_pass(res_pass);
        resVO.setRes_img(res_img);
        resVO.setRes_intro(res_intro);
        resVO.setRes_start(res_start);
        resVO.setRes_end(res_end);
        resVO.setRes_lat(res_lat);
        resVO.setRes_lot(res_lot);
        resVO.setRes_score(res_score);
        resVO.setRes_cost(res_cost);
        resVO.setRes_comcount(res_comcount);
        resVO.setRes_type(res_type);
        resVO.setRes_status(res_status);
        
        dao.update(resVO);
        
        return resVO;
    }
    
    public void deleteRes(String res_no) {
        dao.delete(res_no);
    }
    
    public ResVO getOneRes(String res_no) {
        return dao.findByPrimaryKey(res_no);
    }
    
    public List<ResVO> getAll(){
        return dao.getAll();
    }
    
    public List<ResVO> getAllNoReview(){
        return dao.getAllReview();
    }
    
    public List<ResVO> getAllReviewAgain(){
        return dao.getAllReviewAgain();
    }
    
    public ResVO resFindByAC(String res_ac) {       
        return dao.findByAC(res_ac);
    }
    
    public List<ResVO> getAllOnlineRes()
    {
        return dao.getAllOnliseRes();
    }

    public List<ResVO> getAllByQuery(String query)
    {
        String[] queryArr = query.split(" ");
        System.out.println("res query");
        
        for (ResVO resVO : dao.getAllOnliseRes())
        {
            System.out.println(resVO);
        }
        
        Stream<ResVO> stream = dao.getAllOnliseRes().stream();
        
        for (int i = 0; i < queryArr.length; i++)
        {
            String eachQuery = queryArr[i];
            stream = stream.filter(res -> res.toString().toLowerCase().contains(eachQuery.toLowerCase()));
        }
        
        List<ResVO> list = stream.collect(Collectors.toList());
        
        for (ResVO resVO : list)
        {
            System.out.println(resVO);
        }
        
        return list;
    }
}
