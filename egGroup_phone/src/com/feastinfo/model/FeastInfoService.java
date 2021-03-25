package com.feastinfo.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

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

        dao.insert(feastInfoVO);

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

    public void deleteFeastInfo(String fea_no)
    {
        dao.delete(fea_no);
    }

    public FeastInfoVO getOneFeastInfo(String fea_no)
    {
        return dao.findByPrimaryKey(fea_no);
    }

    public List<FeastInfoVO> getAll()
    {
        return dao.getAll();
    }
}
