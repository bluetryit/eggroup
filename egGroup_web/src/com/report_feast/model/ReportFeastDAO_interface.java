package com.report_feast.model;

import java.util.List;

public interface ReportFeastDAO_interface
{
    public void insert(ReportFeastVO reportFeastVO);

    public void update(ReportFeastVO reportFeastVO);

    public void delete(String repofea_no);

    public ReportFeastVO findByPrimaryKey(String repofea_no);

    public List<ReportFeastVO> getAll();
}
