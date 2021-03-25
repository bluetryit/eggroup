package com.myfeast.model;

import java.util.List;

public class MyFeastService
{
    private MyFeastDAO_interface dao;

    public MyFeastService()
    {
        dao = new MyFeastDAO();
    }

    public MyFeastVO addMyFeast(String mye_feaNo, String mye_memNo)
    {
        MyFeastVO MyFeastVO = new MyFeastVO();

        MyFeastVO.setMye_feaNo(mye_feaNo);
        MyFeastVO.setMye_memNo(mye_memNo);

        dao.insert(MyFeastVO);

        return MyFeastVO;
    }

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
    }

    public MyFeastVO getOneMyFeast(String mye_feaNo, String mye_memNo)
    {
        return dao.findByPrimaryKey(mye_feaNo, mye_memNo);
    }

    public List<MyFeastVO> getAll()
    {
        return dao.getAll();
    }
    public List<MyFeastVO> getByMem(String mye_memNo){
    	return dao.findByMem(mye_memNo);
    }
    public List<MyFeastVO> getByFea(String mye_feaNo){
    	return dao.findByFea(mye_feaNo);
    }
}
