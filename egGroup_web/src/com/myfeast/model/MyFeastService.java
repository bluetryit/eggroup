package com.myfeast.model;

import java.util.List;
import java.util.stream.Collectors;

import com.feastinfo.model.FeastInfoDAO;
import com.feastinfo.model.FeastInfoDAO_interface;
import com.feastinfo.model.FeastInfoVO;

public class MyFeastService
{
    private MyFeastDAO_interface dao;
    private FeastInfoDAO_interface feaInfoDao;
    public MyFeastService()
    {
        dao = new MyFeastDAO();
        feaInfoDao = new FeastInfoDAO();
    }

    public MyFeastVO addMyFeast(String mye_feaNo, String mye_memNo)
    {
        MyFeastVO MyFeastVO = new MyFeastVO();
        
        

        MyFeastVO.setMye_feaNo(mye_feaNo);
        MyFeastVO.setMye_memNo(mye_memNo);

        dao.insert(MyFeastVO);

      //飯局人數+1
        FeastInfoVO feastInfoVO = feaInfoDao.findByPrimaryKey(mye_feaNo);
        feastInfoVO.setFea_number(feastInfoVO.getFea_number() + 1);
        feaInfoDao.update(feastInfoVO);
        
        return MyFeastVO;
    }
  //用不到
    public MyFeastVO updateMyFeast(String mye_feaNoModified, String mye_memNoModified,
            String mye_feaNoToBeModified, String mye_memNoToBeModified)
    {
        MyFeastVO MyFeastVOModified = new MyFeastVO();
        MyFeastVO MyFeastVOToBeModified = new MyFeastVO();

        MyFeastVOModified.setMye_feaNo(mye_feaNoModified);
        MyFeastVOModified.setMye_memNo(mye_memNoModified);
        MyFeastVOToBeModified.setMye_feaNo(mye_feaNoToBeModified);
        MyFeastVOToBeModified.setMye_memNo(mye_memNoToBeModified);

        dao.update(MyFeastVOModified, MyFeastVOToBeModified);

        return MyFeastVOModified;
    }

    public void deleteMyFeast(String mye_feaNo, String mye_memNo)
    {
        dao.delete(mye_feaNo, mye_memNo); 
        
        
      //飯局人數-1
        FeastInfoVO feastInfoVO = feaInfoDao.findByPrimaryKey(mye_feaNo);
        feastInfoVO.setFea_number(feastInfoVO.getFea_number() - 1);
        feaInfoDao.update(feastInfoVO);
        
        
    }
    
  //用不到?
    public MyFeastVO getOneMyFeast(String mye_feaNo, String mye_memNo)
    {
        return dao.findByPrimaryKey(mye_feaNo, mye_memNo);
    }

    public List<MyFeastVO> getAllMyFeastVOs()
    {
        return dao.getAll();
    }
    
    public List<MyFeastVO> getAllMyFeastVOsByFeaNo(String mye_feaNo)
    {
        return dao.getAll().stream()
               .filter(Myfeast -> Myfeast.getMye_feaNo().equals(mye_feaNo))
               .collect(Collectors.toList());
    }
    
    public List<MyFeastVO> getAllMyFeastVOsByMemNo(String mye_memNo)
    {
        return dao.getAll().stream()
               .filter(Myfeast -> Myfeast.getMye_memNo().equals(mye_memNo))
               .collect(Collectors.toList());
    }
    
    public static void main(String[] args)
    {
        MyFeastService myFeastService = new MyFeastService();
        List<MyFeastVO> myFeastVOs = myFeastService.getAllMyFeastVOsByFeaNo("FE000002");
//        List<MyFeastVO> myFeastVOs = myFeastService.getAllMyFeastVOsByMemNo("ME000001");
        
        
        
//        System.out.println(myFeastVOs.contains(myFeastService.getOneMyFeast("FE000001", "ME000001")));
        for (MyFeastVO myFeastVO : myFeastVOs)
        {
            System.out.println(myFeastVO);
        }
    }
    
    public List<MyFeastVO> getByMem(String mye_memNo){
        return dao.findByMem(mye_memNo);
    }
    public List<MyFeastVO> getByFea(String mye_feaNo){
        return dao.findByFea(mye_feaNo);
    }
}
