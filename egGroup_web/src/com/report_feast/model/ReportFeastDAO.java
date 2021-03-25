package com.report_feast.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportFeastDAO implements ReportFeastDAO_interface
{
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String userid = "DA101G6";
    String passwd = "123456";

    private static final String INSERT_STMT = "INSERT INTO report_feast (REPOFEA_NO, REPOFEA_FEANO, REPOFEA_MEMNO, REPOFEA_TEXT, REPOFEA_STATUS)"
            +
            "VALUES ('RF'||LPAD(to_char(REPORT_FEAST_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE report_feast set REPOFEA_FEANO=?, REPOFEA_MEMNO=?, REPOFEA_TEXT=?, REPOFEA_STATUS=? where REPOFEA_NO = ?";
    // 應該不需要刪除
    private static final String DELETE = "DELETE FROM report_feast where REPOFEA_NO = ?";

    private static final String GET_ALL_STMT = "SELECT * FROM report_feast order by REPOFEA_NO";
    private static final String GET_ONE_STMT = "SELECT * FROM report_feast where REPOFEA_NO = ?";

    @Override
    public void insert(ReportFeastVO reportFeastVO)
    {
        Connection con = null;
        PreparedStatement pstmt = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, reportFeastVO.getRepofea_feaNo());
            pstmt.setString(2, reportFeastVO.getRepofea_memNo());
            pstmt.setString(3, reportFeastVO.getRepofea_text());
            pstmt.setString(4, reportFeastVO.getRepofea_status());

            pstmt.executeUpdate();

        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        }
        catch (SQLException se)
        {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        }
        finally
        {
            if (pstmt != null)
            {
                try
                {
                    pstmt.close();
                }
                catch (SQLException se)
                {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null)
            {
                try
                {
                    con.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void update(ReportFeastVO reportFeastVO)
    {
        Connection con = null;
        PreparedStatement pstmt = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, reportFeastVO.getRepofea_feaNo());
            pstmt.setString(2, reportFeastVO.getRepofea_memNo());
            pstmt.setString(3, reportFeastVO.getRepofea_text());
            pstmt.setString(4, reportFeastVO.getRepofea_status());
            pstmt.setString(5, reportFeastVO.getRepofea_no());

            pstmt.executeUpdate();

        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        }
        catch (SQLException se)
        {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        }
        finally
        {
            if (pstmt != null)
            {
                try
                {
                    pstmt.close();
                }
                catch (SQLException se)
                {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null)
            {
                try
                {
                    con.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void delete(String repofea_no)
    {
        Connection con = null;
        PreparedStatement pstmt = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(DELETE);

            pstmt.setString(1, repofea_no);

            pstmt.executeUpdate();

        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        }
        catch (SQLException se)
        {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        }
        finally
        {
            if (pstmt != null)
            {
                try
                {
                    pstmt.close();
                }
                catch (SQLException se)
                {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null)
            {
                try
                {
                    con.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public ReportFeastVO findByPrimaryKey(String repofea_no)
    {
        ReportFeastVO reportFeastVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, repofea_no);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                reportFeastVO = new ReportFeastVO();
                reportFeastVO.setRepofea_no(rs.getString("repofea_no"));
                reportFeastVO.setRepofea_feaNo(rs.getString("repofea_feaNo"));
                reportFeastVO.setRepofea_memNo(rs.getString("repofea_memNo"));
                reportFeastVO.setRepofea_text(rs.getString("repofea_text"));
                reportFeastVO.setRepofea_status(rs.getString("repofea_status"));
            }
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        }
        catch (SQLException se)
        {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        }
        finally
        {
            if (pstmt != null)
            {
                try
                {
                    pstmt.close();
                }
                catch (SQLException se)
                {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null)
            {
                try
                {
                    con.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
        return reportFeastVO;
    }

    @Override
    public List<ReportFeastVO> getAll()
    {
        List<ReportFeastVO> list = new ArrayList<ReportFeastVO>();
        ReportFeastVO reportFeastVO = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                reportFeastVO = new ReportFeastVO();
                reportFeastVO.setRepofea_no(rs.getString("repofea_no"));
                reportFeastVO.setRepofea_feaNo(rs.getString("repofea_feaNo"));
                reportFeastVO.setRepofea_memNo(rs.getString("repofea_memNo"));
                reportFeastVO.setRepofea_text(rs.getString("repofea_text"));
                reportFeastVO.setRepofea_status(rs.getString("repofea_status"));

                list.add(reportFeastVO);
            }
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException("Couldn't load database driver. "
                    + e.getMessage());
            // Handle any SQL errors
        }
        catch (SQLException se)
        {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
            // Clean up JDBC resources
        }
        finally
        {
            if (pstmt != null)
            {
                try
                {
                    pstmt.close();
                }
                catch (SQLException se)
                {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null)
            {
                try
                {
                    con.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }
    
//  public static void main(String[] args)
//  {
//      ReportFeastDAO dao = new ReportFeastDAO();
//      // 新增
//      ReportFeastVO ReportFeastVO1 = new ReportFeastVO();
//      ReportFeastVO1.setRepofea_feaNo("FE000005");
//      ReportFeastVO1.setRepofea_memNo("ME000003");
//      ReportFeastVO1.setRepofea_text("456");
//      ReportFeastVO1.setRepofea_status("repofea3");
//
////      dao.insert(ReportFeastVO1);
//
//      // 修改
//      ReportFeastVO ReportFeastVO2 = new ReportFeastVO();
//      ReportFeastVO2.setRepofea_feaNo("FE000005");
//      ReportFeastVO2.setRepofea_memNo("ME000003");
//      ReportFeastVO2.setRepofea_text("456");
//      ReportFeastVO2.setRepofea_status("repofea2");
//      ReportFeastVO2.setRepofea_no("RF000001");
//
////      dao.update(ReportFeastVO2);
//
//      // 刪除
////      dao.delete("RF000002");
//
//      // 查詢
//      ReportFeastVO ReportFeastVO3 = dao.findByPrimaryKey("RF000003");
//
//      System.out.print(ReportFeastVO3.getRepofea_no() + ",");
//      System.out.print(ReportFeastVO3.getRepofea_feaNo() + ",");
//      System.out.print(ReportFeastVO3.getRepofea_memNo() + ",");
//      System.out.print(ReportFeastVO3.getRepofea_text() + ",");
//      System.out.println(ReportFeastVO3.getRepofea_status());
//      System.out.println("---------------------");
//      // 查詢
//
//      List<ReportFeastVO> list = dao.getAll();
//      for (ReportFeastVO ReportFeastVO : list)
//      {
//          System.out.print(ReportFeastVO.getRepofea_no() + ",");
//          System.out.print(ReportFeastVO.getRepofea_feaNo() + ",");
//          System.out.print(ReportFeastVO.getRepofea_memNo() + ",");
//          System.out.print(ReportFeastVO.getRepofea_text() + ",");
//          System.out.println(ReportFeastVO.getRepofea_status());
//          System.out.println("---------------------");
//      }
//  }
}
