package com.report_feast.model;

import java.io.Serializable;

public class ReportFeastVO implements Serializable
{
    private String repofea_no;
    private String repofea_feaNo;
    private String repofea_memNo;
    private String repofea_text;
    private String repofea_status;

    public String getRepofea_no()
    {
        return repofea_no;
    }

    public void setRepofea_no(String repofea_no)
    {
        this.repofea_no = repofea_no;
    }

    public String getRepofea_feaNo()
    {
        return repofea_feaNo;
    }

    public void setRepofea_feaNo(String repofea_feaNo)
    {
        this.repofea_feaNo = repofea_feaNo;
    }

    public String getRepofea_memNo()
    {
        return repofea_memNo;
    }

    public void setRepofea_memNo(String repofea_memNo)
    {
        this.repofea_memNo = repofea_memNo;
    }

    public String getRepofea_text()
    {
        return repofea_text;
    }

    public void setRepofea_text(String repofea_text)
    {
        this.repofea_text = repofea_text;
    }

    public String getRepofea_status()
    {
        return repofea_status;
    }

    public void setRepofea_status(String repofea_status)
    {
        this.repofea_status = repofea_status;
    }

}
