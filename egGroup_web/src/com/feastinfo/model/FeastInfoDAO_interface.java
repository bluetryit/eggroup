package com.feastinfo.model;

import java.util.List;

public interface FeastInfoDAO_interface
{
    public String insert(FeastInfoVO feastInfoVO);

    public void update(FeastInfoVO feastInfoVO);

    public void delete(String fea_no);

    public FeastInfoVO findByPrimaryKey(String fea_no);

    public List<FeastInfoVO> getAll();
}
