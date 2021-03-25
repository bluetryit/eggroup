package com.myfeast.model;

import java.io.Serializable;
import java.util.Objects;

public class MyFeastVO implements Serializable
{
    private String mye_feaNo;
    private String mye_memNo;



    @Override
    public int hashCode()
    {
        return Objects.hash(mye_feaNo, mye_memNo);
    }

    

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyFeastVO other = (MyFeastVO) obj;
        return Objects.equals(mye_feaNo, other.mye_feaNo) && Objects.equals(mye_memNo, other.mye_memNo);
    }



    @Override
    public String toString()
    {
        return "MyFeastVO [mye_feaNo=" + mye_feaNo + ", mye_memNo=" + mye_memNo + "]";
    }



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
