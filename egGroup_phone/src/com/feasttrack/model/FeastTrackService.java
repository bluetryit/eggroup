package com.feasttrack.model;

import java.util.List;

public class FeastTrackService
{
    private FeastTrackDAO_interface dao;

    public FeastTrackService()
    {
        dao = new FeastTrackDAO();
    }

    public FeastTrackVO addFeastTrack(String tra_feaNo, String tra_memNo)
    {
        FeastTrackVO FeastTrackVO = new FeastTrackVO();

        FeastTrackVO.setTra_feaNo(tra_feaNo);
        FeastTrackVO.setTra_memNo(tra_memNo);

        dao.insert(FeastTrackVO);

        return FeastTrackVO;
    }

    public FeastTrackVO updateFeastTrack(String tra_feaNoModified, String tra_memNoModified,
            String tra_feaNoToBeModified, String tra_memNoToBeModified)
    {
        FeastTrackVO feastTrackVOModified = new FeastTrackVO();
        FeastTrackVO feastTrackVOToBeModified = new FeastTrackVO();

        feastTrackVOModified.setTra_feaNo(tra_feaNoModified);
        feastTrackVOModified.setTra_memNo(tra_memNoModified);
        feastTrackVOToBeModified.setTra_feaNo(tra_feaNoToBeModified);
        feastTrackVOToBeModified.setTra_memNo(tra_memNoToBeModified);

        dao.update(feastTrackVOModified, feastTrackVOToBeModified);

        return feastTrackVOModified;
    }

    public void deleteFeastTrack(String tra_feaNo, String tra_memNo)
    {
        dao.delete(tra_feaNo, tra_memNo);
    }

    public FeastTrackVO getOneFeastTrack(String tra_feaNo, String tra_memNo)
    {
        return dao.findByPrimaryKey(tra_feaNo, tra_memNo);
    }

    public List<FeastTrackVO> getAllFeastTrack()
    {
        return dao.getAll();
    }
    public List<FeastTrackVO> getByMemFeastTrack(String tra_memNo)
    {
        return dao.getByMem(tra_memNo);
    }
}
