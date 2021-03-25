package com.report_feast.model;

import java.sql.Date;
import java.util.List;

public class ReportFeastService
{
    private ReportFeastDAO_interface dao;

    public ReportFeastService()
    {
        dao = new ReportFeastDAO();
    }

    public ReportFeastVO addReportFeast(String repofea_feaNo, String repofea_memNo, String repofea_text, String repofea_status)
    {

        ReportFeastVO reportFeast = new ReportFeastVO();

        reportFeast.setRepofea_feaNo(repofea_feaNo);
        reportFeast.setRepofea_memNo(repofea_memNo);
        reportFeast.setRepofea_text(repofea_text);
        reportFeast.setRepofea_status(repofea_status);

        dao.insert(reportFeast);

        return reportFeast;
    }

    public ReportFeastVO updateReportFeast(String repofea_no, String repofea_feaNo, String repofea_memNo, String repofea_text, String repofea_status)
    {

        ReportFeastVO reportFeast = new ReportFeastVO();

        reportFeast.setRepofea_no(repofea_no);
        reportFeast.setRepofea_feaNo(repofea_feaNo);
        reportFeast.setRepofea_memNo(repofea_memNo);
        reportFeast.setRepofea_text(repofea_text);
        reportFeast.setRepofea_status(repofea_status);

        dao.update(reportFeast);

        return reportFeast;
    }

    public void deleteReportFeast(String repofea_no)
    {
        dao.delete(repofea_no);
    }

    public ReportFeastVO getOneReportFeast(String repofea_no)
    {
        return dao.findByPrimaryKey(repofea_no);
    }

    public List<ReportFeastVO> getAll()
    {
        return dao.getAll();
    }
}
