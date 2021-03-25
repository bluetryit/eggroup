package com.function.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FunctionDAO implements FunctionDAO_interface 
{
	String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String userid = "DA101G6";
    String passwd = "123456";

    private static final String INSERT_STMT = "INSERT INTO Function  (FUN_NO, FUN_NAME,FUN_DES)"+
            "VALUES ('FU'||LPAD(to_char(function_SEQ.NEXTVAL), 6, '0'), ?, ?)";
    private static final String UPDATE = "UPDATE Function set FUN_NAME=?where FUN_NO = ?";
    
    private static final String DELETE = "DELETE FROM Function where FUN_NO = ?";

    private static final String GET_ALL_STMT = "SELECT * FROM Function order by FUN_NO";
    private static final String GET_ONE_STMT = "SELECT * FROM Function where FUN_NO = ?";

	@Override
	public void insert(FunctionVO functionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, functionVO.getFun_no());
			pstmt.setString(2, functionVO.getFun_name());
			pstmt.setString(3, functionVO.getFun_des());
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
	public void update(FunctionVO functionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1, functionVO.getFun_no());
			pstmt.setString(2, functionVO.getFun_name());
			pstmt.setString(3, functionVO.getFun_des());

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
	public FunctionVO findByPrimaryKey(String fun_no) {

		FunctionVO functionVO = null;
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
				// empVo �]�٬� Domain objects
				functionVO = new FunctionVO();
				functionVO.setFun_no(rs.getString("fun_no"));
				functionVO.setFun_name(rs.getString("fun_name"));
				functionVO.setFun_des(rs.getString("fun_des"));
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
		return functionVO;
	}

	@Override
	public List<FunctionVO> getAll() {
		List<FunctionVO> list = new ArrayList<FunctionVO>();
		FunctionVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				FunctionVO functionVO = new FunctionVO();
				functionVO.setFun_no(rs.getString("fun_no"));
				functionVO.setFun_name(rs.getString("fun_name"));
				functionVO.setFun_des(rs.getString("fun_des"));
				list.add(empVO); // Store the row in the list
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

	

	

	

	

	
}