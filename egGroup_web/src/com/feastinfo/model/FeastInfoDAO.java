package com.feastinfo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FeastInfoDAO implements FeastInfoDAO_interface
{
    String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String userid = "DA101G6";
    String passwd = "123456";

    private static final String INSERT_STMT = "INSERT INTO feastinfo (FEA_NO, FEA_RESNO, FEA_MEMNO, FEA_TITLE, FEA_TEXT, FEA_NUMBER, FEA_UPLIM, FEA_LOWLIM,"
            + " FEA_DATE, FEA_STARTDATE, FEA_ENDDATE, FEA_TYPE, FEA_LOC, FEA_STATUS)" +
            "VALUES ('FE'||LPAD(to_char(FEASTINFO_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE FEASTINFO set FEA_RESNO=?, FEA_MEMNO=?, FEA_TITLE=?, FEA_TEXT=?, FEA_NUMBER=?, FEA_UPLIM=?,"
            + "FEA_LOWLIM=?, FEA_DATE=?, FEA_STARTDATE=?, FEA_ENDDATE=?, FEA_TYPE=?, FEA_LOC=?, FEA_STATUS=? where FEA_NO = ?";
    // 應該不需要刪除
    private static final String DELETE = "DELETE FROM FEASTINFO where FEA_NO = ?";

    private static final String GET_ALL_STMT = "SELECT * FROM feastinfo order by FEA_NO";
    private static final String GET_ONE_STMT = "SELECT * FROM feastinfo where FEA_NO = ?";

    @Override
    public String insert(FeastInfoVO feastInfoVO)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        String key = null;
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);

            String cols[] = {"FEA_NO"};

            pstmt = con.prepareStatement(INSERT_STMT, cols);

            pstmt.setString(1, feastInfoVO.getFea_resNo());
            pstmt.setString(2, feastInfoVO.getFea_memNo());
            pstmt.setString(3, feastInfoVO.getFea_title());
            pstmt.setString(4, feastInfoVO.getFea_text());
            pstmt.setInt(5, feastInfoVO.getFea_number());
            pstmt.setInt(6, feastInfoVO.getFea_upLim());
            pstmt.setInt(7, feastInfoVO.getFea_lowLim());
            pstmt.setTimestamp(8, feastInfoVO.getFea_date());
            pstmt.setTimestamp(9, feastInfoVO.getFea_startDate());
            pstmt.setTimestamp(10, feastInfoVO.getFea_endDate());
            pstmt.setString(11, feastInfoVO.getFea_type());
            pstmt.setString(12, feastInfoVO.getFea_loc());
            pstmt.setString(13, feastInfoVO.getFea_status());

            pstmt.executeUpdate();

            ResultSet rsKeys = pstmt.getGeneratedKeys();
            ResultSetMetaData rsmd = rsKeys.getMetaData();
            int columnCount = rsmd.getColumnCount();
            
            if (rsKeys.next())
            {
                do
                {
                    for (int i = 1; i <= columnCount; i++)
                    {
                        key = rsKeys.getString(i);
                        System.out.println("自增主鍵值(i=" + i + ") = " + key + "(剛新增成功的飯局編號)");
                    }
                }
                while (rsKeys.next());
            }
            else
            {
                System.out.println("NO KEYS WERE GENERATED.");
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
        return key;
    }

    @Override
    public void update(FeastInfoVO feastInfoVO)
    {
        Connection con = null;
        PreparedStatement pstmt = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, feastInfoVO.getFea_resNo());
            pstmt.setString(2, feastInfoVO.getFea_memNo());
            pstmt.setString(3, feastInfoVO.getFea_title());
            pstmt.setString(4, feastInfoVO.getFea_text());
            pstmt.setInt(5, feastInfoVO.getFea_number());
            pstmt.setInt(6, feastInfoVO.getFea_upLim());
            pstmt.setInt(7, feastInfoVO.getFea_lowLim());
            pstmt.setTimestamp(8, feastInfoVO.getFea_date());
            pstmt.setTimestamp(9, feastInfoVO.getFea_startDate());
            pstmt.setTimestamp(10, feastInfoVO.getFea_endDate());
            pstmt.setString(11, feastInfoVO.getFea_type());
            pstmt.setString(12, feastInfoVO.getFea_loc());
            pstmt.setString(13, feastInfoVO.getFea_status());
            pstmt.setString(14, feastInfoVO.getFea_no());

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
    public void delete(String fea_no)
    {

        Connection con = null;
        PreparedStatement pstmt = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(DELETE);

            pstmt.setString(1, fea_no);

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
    public FeastInfoVO findByPrimaryKey(String fea_no)
    {
        FeastInfoVO feastInfoVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1, fea_no);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                feastInfoVO = new FeastInfoVO(rs.getString("fea_no"), rs.getString("fea_resNo"),
                        rs.getString("fea_memNo"), rs.getString("fea_title"), rs.getString("fea_text"),
                        rs.getInt("fea_number"), rs.getInt("fea_upLim"), rs.getInt("fea_lowLim"),
                        rs.getTimestamp("fea_date"), rs.getTimestamp("fea_startDate"), rs.getTimestamp("fea_endDate"),
                        rs.getString("fea_type"), rs.getString("fea_loc"), rs.getString("fea_status"));
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
        return feastInfoVO;
    }

    @Override
    public List<FeastInfoVO> getAll()
    {
        List<FeastInfoVO> list = new ArrayList<FeastInfoVO>();
        FeastInfoVO feastInfoVO = null;

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
                feastInfoVO = new FeastInfoVO(rs.getString("fea_no"), rs.getString("fea_resNo"),
                        rs.getString("fea_memNo"), rs.getString("fea_title"), rs.getString("fea_text"),
                        rs.getInt("fea_number"), rs.getInt("fea_upLim"), rs.getInt("fea_lowLim"),
                        rs.getTimestamp("fea_date"), rs.getTimestamp("fea_startDate"), rs.getTimestamp("fea_endDate"),
                        rs.getString("fea_type"), rs.getString("fea_loc"), rs.getString("fea_status"));

                list.add(feastInfoVO);
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

    public static void main(String[] args)
    {
        FeastInfoDAO dao = new FeastInfoDAO();
        // 新增
        FeastInfoVO feastInfoVO1 = new FeastInfoVO();
        feastInfoVO1.setFea_resNo("RS000005");
        feastInfoVO1.setFea_memNo("ME000003");
        feastInfoVO1.setFea_title("本燔壽喜燒團");
        feastInfoVO1.setFea_text("中壢好吃壽喜燒");
        feastInfoVO1.setFea_number(3);
        feastInfoVO1.setFea_upLim(10);
        feastInfoVO1.setFea_lowLim(4);
        feastInfoVO1.setFea_date(new Timestamp(System.currentTimeMillis() + 8 * 24 * 60 * 60 * 1000));
        feastInfoVO1.setFea_startDate(new Timestamp(System.currentTimeMillis() + 4 * 24 * 60 * 60 * 1000));
        feastInfoVO1.setFea_endDate(null);
        feastInfoVO1.setFea_type("內用");
        feastInfoVO1.setFea_loc("302桃園市中壢區五興路331巷29號");
        feastInfoVO1.setFea_status("fea1");
//        dao.insert(feastInfoVO1);

        // 修改
        FeastInfoVO feastInfoVO2 = new FeastInfoVO();
        feastInfoVO2.setFea_resNo("RS000005");
        feastInfoVO2.setFea_memNo("ME000003");
        feastInfoVO2.setFea_title("本燔壽喜燒團");
        feastInfoVO2.setFea_text("中壢好吃壽喜燒");
        feastInfoVO2.setFea_number(3);
        feastInfoVO2.setFea_upLim(10);
        feastInfoVO2.setFea_lowLim(4);
        feastInfoVO2.setFea_date(new Timestamp(System.currentTimeMillis() + 8 * 24 * 60 * 60 * 1000));
        feastInfoVO2.setFea_startDate(new Timestamp(System.currentTimeMillis() + 4 * 24 * 60 * 60 * 1000));
        feastInfoVO2.setFea_endDate(null);
        feastInfoVO2.setFea_type("內用");
        feastInfoVO2.setFea_loc("302桃園市中壢區五興路331巷29號");
        feastInfoVO2.setFea_status("fea1");
        feastInfoVO2.setFea_no("FE000004");
        dao.update(feastInfoVO2);

        // 刪除
//        dao.delete("FE000006");

        // 查詢
        FeastInfoVO feastInfoVO3 = dao.findByPrimaryKey("FE000004");

        System.out.print(feastInfoVO3.getFea_no() + ",");
        System.out.print(feastInfoVO3.getFea_resNo() + ",");
        System.out.print(feastInfoVO3.getFea_memNo() + ",");
        System.out.print(feastInfoVO3.getFea_title() + ",");
        System.out.print(feastInfoVO3.getFea_text() + ",");
        System.out.print(feastInfoVO3.getFea_number() + ",");
        System.out.print(feastInfoVO3.getFea_upLim() + ",");
        System.out.print(feastInfoVO3.getFea_lowLim() + ",");
        System.out.print(feastInfoVO3.getFea_date() + ",");
        System.out.print(feastInfoVO3.getFea_startDate() + ",");
        System.out.print(feastInfoVO3.getFea_endDate() + ",");
        System.out.print(feastInfoVO3.getFea_type() + ",");
        System.out.print(feastInfoVO3.getFea_loc() + ",");
        System.out.println(feastInfoVO3.getFea_status());
        System.out.println("---------------------");
        // 查詢

        List<FeastInfoVO> list = dao.getAll();
        for (FeastInfoVO feastInfoVO : list)
        {
            System.out.print(feastInfoVO.getFea_no() + ",");
            System.out.print(feastInfoVO.getFea_resNo() + ",");
            System.out.print(feastInfoVO.getFea_memNo() + ",");
            System.out.print(feastInfoVO.getFea_title() + ",");
            System.out.print(feastInfoVO.getFea_text() + ",");
            System.out.print(feastInfoVO.getFea_number() + ",");
            System.out.print(feastInfoVO.getFea_upLim() + ",");
            System.out.print(feastInfoVO.getFea_lowLim() + ",");
            System.out.print(feastInfoVO.getFea_date() + ",");
            System.out.print(feastInfoVO.getFea_startDate() + ",");
            System.out.print(feastInfoVO.getFea_endDate() + ",");
            System.out.print(feastInfoVO.getFea_type() + ",");
            System.out.print(feastInfoVO.getFea_loc() + ",");
            System.out.println(feastInfoVO.getFea_status());
            System.out.println("---------------------");
        }
    }
}
