package com.pictools;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class ResPhoto extends HttpServlet
{

    Connection con;

    private static DataSource ds = null;
    static
    {
        try
        {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
        }
        catch (NamingException e)
        {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException
    {

        res.setContentType("image/gif");
        ServletOutputStream out = res.getOutputStream();

        try
        {
            Statement stmt = con.createStatement();
            String res_no = req.getParameter("res_no").trim();
            ResultSet rs = stmt.executeQuery(
                    "SELECT res_img FROM restaurant WHERE res_no = '" + res_no + "'");

            if (rs.next())
            {
                BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("res_img"));
                byte[] buf = new byte[4 * 1024 * 1024]; // 4K buffer
                int len;
                while ((len = in.read(buf)) != -1)
                {
                    out.write(buf, 0, len);
                }
                in.close();
            }
            else
            {
                InputStream in = getServletContext().getResourceAsStream("/images/noimg.jpg");
                byte[] b = new byte[in.available()];
                in.read(b);
                out.write(b);
                in.close();
            }
            rs.close();
            stmt.close();
        }
        catch (Exception e)
        {
//			System.out.println(e);
            InputStream in = getServletContext().getResourceAsStream("/images/noimg.jpg");
            byte[] b = new byte[in.available()];
            in.read(b);
            out.write(b);
            in.close();
        }
    }

    public void init() throws ServletException
    {
        try
        {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "DA101G6", "123456");
            con = ds.getConnection();
        }
//        catch (ClassNotFoundException e)
//        {
//            throw new UnavailableException("Couldn't load JdbcOdbcDriver");
//        }
        catch (SQLException e)
        {
            throw new UnavailableException("Couldn't get db connection");
        }
    }

    public void destroy()
    {
        try
        {
            if (con != null)
                con.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

}