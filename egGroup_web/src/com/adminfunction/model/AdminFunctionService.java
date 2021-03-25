package com.adminfunction.model;

import java.sql.Date;
import java.util.List;

import com.function.model.FunctionDAO;
import com.function.model.FunctionDAO_interface;
import com.function.model.FunctionVO;

public class AdminFunctionService
{
    private AdminFunctionDAO_interface dao;

    public AdminFunctionService()
    {
        dao = new AdminFunctionDAO();
    }

    public AdminFunctionVO addAdminFunction(String fun_name, String fun_des)
    {

    	AdminFunctionVO function = new AdminFunctionVO();


    	function.setFun_name(fun_name);
    	function.setFun_des(fun_des);

        dao.insert(function);

        return function;
    }

    public AdminFunctionVO updateAdminFunction(String fun_no, String fun_name, String fun_des)
    {

    	AdminFunctionVO function = new AdminFunctionVO();

    	function.setFun_no(fun_no);
    	function.setFun_name(fun_name);
    	function.setFun_des(fun_des);

        dao.update(function);

        return function;
    }

    public void deleteAdminFunction(String fun_no)
    {
        dao.delete(fun_no);
    }

    public AdminFunctionVO getOneFunction(String fun_no)
    {
        return dao.findByPrimaryKey(fun_no);
    }

    public List<AdminFunctionVO> getAll()
    {
        return dao.getAll();
    }
}
