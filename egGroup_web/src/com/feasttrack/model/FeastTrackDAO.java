package com.feasttrack.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeastTrackDAO implements FeastTrackDAO_interface
{
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String userid = "DA101G6";
    String passwd = "123456";

    private static final String INSERT_STMT = "INSERT INTO FEASTTRACK(TRA_FEANO, TRA_MEMNO) VALUES (?, ?)";
//  應該不需要修改
    private static final String UPDATE = "UPDATE FEASTTRACK set tra_feano=?, tra_memno=? where tra_feano = ? and tra_memno = ?";

    private static final String DELETE = "DELETE FROM FEASTTRACK where tra_feano = ? and tra_memno = ?";
    private static final String GET_ALL_STMT = "SELECT * FROM FEASTTRACK order by TRA_MEMNO";
    private static final String GET_ONE_STMT = "SELECT * FROM FEASTTRACK where tra_feano = ? and tra_memno = ?";

    @Override
    public void insert(FeastTrackVO feastTrackVO)
    {
        Connection con = null;
        PreparedStatement pstmt = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, feastTrackVO.getTra_feaNo());
            pstmt.setString(2, feastTrackVO.getTra_memNo());

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
    public void update(FeastTrackVO feastTrackVOModified, FeastTrackVO feastTrackVOToBeModified)
    {
        Connection con = null;
        PreparedStatement pstmt = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, feastTrackVOModified.getTra_feaNo());
            pstmt.setString(2, feastTrackVOModified.getTra_memNo());
            pstmt.setString(3, feastTrackVOToBeModified.getTra_feaNo());
            pstmt.setString(4, feastTrackVOToBeModified.getTra_memNo());

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
    public void delete(String tra_feaNo, String tra_memNo)
    {
        Connection con = null;
        PreparedStatement pstmt = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(DELETE);

            pstmt.setString(1, tra_feaNo);
            pstmt.setString(2, tra_memNo);

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
    public FeastTrackVO findByPrimaryKey(String tra_feaNo, String tra_memNo)
    {
        FeastTrackVO feastTrackVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, tra_feaNo);
            pstmt.setString(2, tra_memNo);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                feastTrackVO = new FeastTrackVO();
                feastTrackVO.setTra_feaNo(rs.getString("tra_feaNo"));
                feastTrackVO.setTra_memNo(rs.getString("tra_memNo"));
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
        return feastTrackVO;
    }

    @Override
    public List<FeastTrackVO> getAll()
    {
        List<FeastTrackVO> list = new ArrayList<FeastTrackVO>();
        FeastTrackVO feastTrackVO = null;

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
                feastTrackVO = new FeastTrackVO();
                feastTrackVO.setTra_feaNo(rs.getString("tra_feaNo"));
                feastTrackVO.setTra_memNo(rs.getString("tra_memNo"));

                list.add(feastTrackVO);
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

//    public static void main(String[] args)
//    {
//        FeastTrackDAO dao = new FeastTrackDAO();
//        // 新增
//        FeastTrackVO FeastTrackVO1 = new FeastTrackVO();
//        FeastTrackVO1.setTra_feaNo("FE000001");
//        FeastTrackVO1.setTra_memNo("ME000005");
//
//        dao.insert(FeastTrackVO1);
//
//        // 修改
//        FeastTrackVO FeastTrackVO2 = new FeastTrackVO();
//        FeastTrackVO FeastTrackVO3 = new FeastTrackVO();
//        FeastTrackVO2.setTra_feaNo("FE000001");
//        FeastTrackVO2.setTra_memNo("ME000003");
//        FeastTrackVO3.setTra_feaNo("FE000002");
//        FeastTrackVO3.setTra_memNo("ME000002");
//
//        dao.update(FeastTrackVO2, FeastTrackVO3);
//
//        // 刪除
//        dao.delete("FE000003", "ME000005");
//
//        // 查詢
//        FeastTrackVO FeastTrackVO4 = dao.findByPrimaryKey("FE000003", "ME000004");
//
//        System.out.print(FeastTrackVO4.getTra_feaNo() + ",");
//        System.out.println(FeastTrackVO4.getTra_memNo());
//        System.out.println("---------------------");
//        // 查詢
//
//        List<FeastTrackVO> list = dao.getAll();
//        for (FeastTrackVO FeastTrackVO : list)
//        {
//            System.out.print(FeastTrackVO.getTra_feaNo() + ",");
//            System.out.println(FeastTrackVO.getTra_memNo());
//            System.out.println("---------------------");
//        }
//    }
}
