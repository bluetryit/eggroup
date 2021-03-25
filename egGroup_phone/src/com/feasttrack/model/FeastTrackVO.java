package com.feasttrack.model;

import java.io.Serializable;

public class FeastTrackVO implements Serializable
{
    private String tra_feaNo;
    private String tra_memNo;

    public String getTra_feaNo()
    {
        return tra_feaNo;
    }

    public void setTra_feaNo(String tra_feaNo)
    {
        this.tra_feaNo = tra_feaNo;
    }

    public String getTra_memNo()
    {
        return tra_memNo;
    }

    public void setTra_memNo(String tra_memNo)
    {
        this.tra_memNo = tra_memNo;
    }
}
