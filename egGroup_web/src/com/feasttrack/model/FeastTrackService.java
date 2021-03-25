package com.feasttrack.model;

import java.util.List;
import java.util.stream.Collectors;

import com.feastinfo.model.FeastInfoVO;

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
//無法修改
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
    
//用不到?
    public FeastTrackVO getOneFeastTrack(String tra_feaNo, String tra_memNo)
    {
        return dao.findByPrimaryKey(tra_feaNo, tra_memNo);
    }

//用不到
    public List<FeastTrackVO> getAllfeasFeastTrackVOs()
    {
        return dao.getAll();
    }

    public List<FeastTrackVO> getAllCourentfeasFeastTrackVOs(String tra_memNo)
    {
        return dao.getAll().stream()
                .filter(feastTrack -> feastTrack.getTra_memNo().equals(tra_memNo))
                .collect(Collectors.toList());
    }
    
//    public static void main(String[] args)
//    {
//        FeastTrackService feastTrackService = new FeastTrackService();
//        List<FeastTrackVO> feastTrackVOs = feastTrackService.getAllCourentfeasFeastTrackVOs("ME000005");
//        for (FeastTrackVO feastTrackVO : feastTrackVOs)
//        {
//            System.out.println(feastTrackVO);
//        }
//    }
}
