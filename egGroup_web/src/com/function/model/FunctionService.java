package com.function.model;

import java.sql.Date;
import java.util.List;

public class FunctionService
{
    private FunctionDAO_interface dao;

    public FunctionService()
    {
        dao = new FunctionDAO();
    }

    public FunctionVO addFunction(String fun_no, String fun_name, String fun_des)
    {

    	FunctionVO function = new FunctionVO();

    	function.setFun_no(fun_no);
    	function.setFun_name(fun_name);
    	function.setFun_des(fun_des);

        dao.insert(function);

        return function;
    }

    public FunctionVO updateFunction(String fun_no, String fun_name, String fun_des)
    {

    	FunctionVO function = new FunctionVO();

    	function.setFun_no(fun_no);
    	function.setFun_name(fun_name);
    	function.setFun_des(fun_des);

        dao.update(function);

        return function;
    }

    public void deleteFunction(String fun_no)
    {
        dao.delete(fun_no);
    }

    public FunctionVO getOneFunction(String fun_no)
    {
        return dao.findByPrimaryKey(fun_no);
    }

    public List<FunctionVO> getAll()
    {
        return dao.getAll();
    }
}
