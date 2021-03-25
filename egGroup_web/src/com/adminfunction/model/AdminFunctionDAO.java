package com.adminfunction.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.function.model.FunctionDAO_interface;
import com.function.model.FunctionVO;

public class AdminFunctionDAO implements AdminFunctionDAO_interface 
{
	String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String userid = "DA101G6";
    String passwd = "123456";

    private static final String INSERT_STMT = "INSERT INTO AdminFunction  (FUN_NO, FUN_NAME,FUN_DES)"+
            "VALUES ('FU'||LPAD(to_char(Adminfunction_SEQ.NEXTVAL), 6, '0'), ?, ?)";
    private static final String UPDATE = "UPDATE AdminFunction set FUN_NAME=?, FUN_DES=? where FUN_NO = ?";
    
    private static final String DELETE = "DELETE FROM AdminFunction where FUN_NO = ?";

    private static final String GET_ALL_STMT = "SELECT * FROM AdminFunction order by FUN_NO";
    private static final String GET_ONE_STMT = "SELECT * FROM AdminFunction where FUN_NO = ?";

	@Override
	public void insert(AdminFunctionVO adminfunctionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adminfunctionVO.getFun_name());
			pstmt.setString(2, adminfunctionVO.getFun_des());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(AdminFunctionVO adminfunctionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adminfunctionVO.getFun_name());
			pstmt.setString(2, adminfunctionVO.getFun_des());
			pstmt.setString(3, adminfunctionVO.getFun_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String fun_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,fun_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	@Override
	public AdminFunctionVO findByPrimaryKey(String fun_no) {

		AdminFunctionVO adminfunctionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,fun_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				adminfunctionVO = new AdminFunctionVO();
				adminfunctionVO.setFun_no(rs.getString("fun_no"));
				adminfunctionVO.setFun_name(rs.getString("fun_name"));
				adminfunctionVO.setFun_des(rs.getString("fun_des"));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return adminfunctionVO;
	}

	@Override
	public List<AdminFunctionVO> getAll() {
		List<AdminFunctionVO> list = new ArrayList<AdminFunctionVO>();
//		AdminFunctionVO adminfunctionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				AdminFunctionVO adminfunctionVO = new AdminFunctionVO();
				adminfunctionVO.setFun_no(rs.getString("fun_no"));
				adminfunctionVO.setFun_name(rs.getString("fun_name"));
				adminfunctionVO.setFun_des(rs.getString("fun_des"));
				list.add(adminfunctionVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	
public static void main(String[] args) {
	AdminFunctionDAO adminFunctionDAO = new AdminFunctionDAO();
	AdminFunctionVO adminFunctionVO=new AdminFunctionVO();
	adminFunctionVO.setFun_name("fun_name");
	adminFunctionVO.setFun_des("fun_des");
	adminFunctionDAO.insert(adminFunctionVO);
	
}
	

	

	

	
}