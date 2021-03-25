package com.feastinfo.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

public class FeastInfoService
{
    private FeastInfoDAO_interface dao;

    public FeastInfoService()
    {
        dao = new FeastInfoDAO();
    }

    public FeastInfoVO addFeastInfo(String fea_resNo, String fea_memNo, String fea_title,
            String fea_text, Integer fea_number, Integer fea_upLim, Integer fea_lowLim, Timestamp fea_date,
            Timestamp fea_startDate, Timestamp fea_endDate, String fea_type, String fea_loc, String fea_status)
    {

        FeastInfoVO feastInfoVO = new FeastInfoVO();

        feastInfoVO.setFea_resNo(fea_resNo);
        feastInfoVO.setFea_memNo(fea_memNo);
        feastInfoVO.setFea_title(fea_title);
        feastInfoVO.setFea_text(fea_text);
        feastInfoVO.setFea_number(fea_number);
        feastInfoVO.setFea_upLim(fea_upLim);
        feastInfoVO.setFea_lowLim(fea_lowLim);
        feastInfoVO.setFea_date(fea_date);
        feastInfoVO.setFea_startDate(fea_startDate);
        feastInfoVO.setFea_endDate(fea_endDate);
        feastInfoVO.setFea_type(fea_type);
        feastInfoVO.setFea_loc(fea_loc);
        feastInfoVO.setFea_status(fea_status);

        String fea_no = dao.insert(feastInfoVO);

        feastInfoVO.setFea_no(fea_no);
        
        return feastInfoVO;
    }

    public FeastInfoVO updateFeastInfo(String fea_no, String fea_resNo, String fea_memNo, String fea_title,
            String fea_text, Integer fea_number, Integer fea_upLim, Integer fea_lowLim, Timestamp fea_date,
            Timestamp fea_startDate, Timestamp fea_endDate, String fea_type, String fea_loc, String fea_status)
    {

        FeastInfoVO feastInfoVO = new FeastInfoVO();

        feastInfoVO.setFea_no(fea_no);
        feastInfoVO.setFea_resNo(fea_resNo);
        feastInfoVO.setFea_memNo(fea_memNo);
        feastInfoVO.setFea_title(fea_title);
        feastInfoVO.setFea_text(fea_text);
        feastInfoVO.setFea_number(fea_number);
        feastInfoVO.setFea_upLim(fea_upLim);
        feastInfoVO.setFea_lowLim(fea_lowLim);
        feastInfoVO.setFea_date(fea_date);
        feastInfoVO.setFea_startDate(fea_startDate);
        feastInfoVO.setFea_endDate(fea_endDate);
        feastInfoVO.setFea_type(fea_type);
        feastInfoVO.setFea_loc(fea_loc);
        feastInfoVO.setFea_status(fea_status);

        dao.update(feastInfoVO);

        return feastInfoVO;
    }
//無法刪
    public void deleteFeastInfo(String fea_no)
    {
        dao.delete(fea_no);
    }

    public FeastInfoVO getOneFeastInfo(String fea_no)
    {
        return dao.findByPrimaryKey(fea_no);
    }

    public List<FeastInfoVO> getAllFeastInfoVOs()
    {
        return dao.getAll();
    }
    
    public List<FeastInfoVO> getAllFeastInfoVOsRandomly()
    {
        return dao.getAll().stream()
                .filter(feastInfo -> feastInfo.getFea_status().equals("fea1"))
                .collect(Collectors.toList());
    }
    
    public List<FeastInfoVO> getAllHistoryFeastInfo()
    {
        return dao.getAll().stream()
                .filter(feastInfo -> feastInfo.getFea_status().equals("fea3") || feastInfo.getFea_date().before(new java.sql.Timestamp(System.currentTimeMillis())))
                .collect(Collectors.toList());
    }
    
    public List<FeastInfoVO> getAllCurrentFeastInfo()
    {
        return dao.getAll().stream()
                .filter(feastInfo -> feastInfo.getFea_date().after(new java.sql.Timestamp(System.currentTimeMillis())) && (feastInfo.getFea_status().equalsIgnoreCase("fea1") || feastInfo.getFea_status().equalsIgnoreCase("fea2")))
                .collect(Collectors.toList());
    }
    
    public List<FeastInfoVO> getAllFeastByRes(String res_no){
        return dao.getAll().stream()
        		.filter(f -> f.getFea_resNo().equals(res_no))
        		.collect(Collectors.toList());
    }
    
}
