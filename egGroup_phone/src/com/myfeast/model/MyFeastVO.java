package com.myfeast.model;

import java.io.Serializable;

public class MyFeastVO implements Serializable
{
    private String mye_feaNo;
    private String mye_memNo;

    public String getMye_feaNo()
    {
        return mye_feaNo;
    }

    public void setMye_feaNo(String mye_feaNo)
    {
        this.mye_feaNo = mye_feaNo;
    }

    public String getMye_memNo()
    {
        return mye_memNo;
    }

    public void setMye_memNo(String mye_memNo)
    {
        this.mye_memNo = mye_memNo;
    }
}
