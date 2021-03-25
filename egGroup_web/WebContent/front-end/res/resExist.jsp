<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    try{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con =(Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "DA101G6", "123456");
            PreparedStatement ps = con.prepareStatement("SELECT  * FROM restaurant WHERE res_ac = ?");
            ps.setString(1,request.getParameter("res_ac"));
            ResultSet rs = ps.executeQuery();
            

            if (rs.next() == false) 
            { 
            	System.out.println("false");
            	out.println("false"); //帳號合法
            } else { 
            	System.out.println("true");
            	out.println("true"); //帳號存在
            }

            
        }catch (Exception e){
            System.out.println(e);  
        }
%>