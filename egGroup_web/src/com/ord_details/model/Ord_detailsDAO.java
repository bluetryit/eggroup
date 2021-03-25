package com.ord_details.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Ord_detailsDAO implements Ord_detailsDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	// jndi不能使用main測試 要用jsp servlet 待v c完成後解鎖********
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// vc完成後使用datasource
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA101G6";
//	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO ORD_DETAILS (DET_ORDNO,DET_FONO,DET_PRICE,DET_QUANTITY)\r\n" + 
			"VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ord_details order by DET_ORDNO";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ord_details where DET_ORDNO = ?  and DET_FONO=?";
	private static final String UPDATE = 
		"UPDATE ord_details set  DET_ORDNO=?,DET_FONO=?,DET_PRICE=?,DET_QUANTITY=? where DET_ORDNO = ? and  DET_FONO=?";
	
	
	
	//注意雙主鍵，設兩個條件取單筆資料

	@Override
	public void insert(Ord_detailsVO ord_detailsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,ord_detailsVO.getDet_ordno());
			pstmt.setString(2,ord_detailsVO.getDet_fono());
			pstmt.setInt(3,ord_detailsVO.getDet_price());
			pstmt.setInt(4,ord_detailsVO.getDet_quantity());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void update(Ord_detailsVO ord_detailsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {


			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,ord_detailsVO.getDet_ordno());//�i�H����
			pstmt.setString(2,ord_detailsVO.getDet_fono());//�i�H����
			
			pstmt.setInt(3,ord_detailsVO.getDet_price());
			pstmt.setInt(4,ord_detailsVO.getDet_quantity());
			pstmt.setString(5,ord_detailsVO.getDet_ordno());
			pstmt.setString(6,ord_detailsVO.getDet_fono());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public Ord_detailsVO findByPrimaryKey(String det_ordno,String det_fono) {
		// TODO Auto-generated method stub
		Ord_detailsVO ord_detailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, det_ordno);
			pstmt.setString(2, det_fono);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				ord_detailsVO = new Ord_detailsVO();
				
				ord_detailsVO.setDet_ordno(rs.getString("det_ordno"));
				ord_detailsVO.setDet_fono(rs.getString("det_fono"));
				ord_detailsVO.setDet_price(rs.getInt("det_price"));
				ord_detailsVO.setDet_quantity(rs.getInt("det_quantity"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return ord_detailsVO;
	}

	@Override
	public List<Ord_detailsVO> getAll() {
		// TODO Auto-generated method stub
		List<Ord_detailsVO> list = new ArrayList<Ord_detailsVO>();
		Ord_detailsVO ord_detailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				ord_detailsVO = new Ord_detailsVO();
				
				ord_detailsVO.setDet_ordno(rs.getString("det_ordno"));
				ord_detailsVO.setDet_fono(rs.getString("det_fono"));
				ord_detailsVO.setDet_price(rs.getInt("det_price"));
				ord_detailsVO.setDet_quantity(rs.getInt("det_quantity"));
				
				list.add(ord_detailsVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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

	@Override
	public void insert2(Ord_detailsVO ord_detailsVO, Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,ord_detailsVO.getDet_ordno());
			pstmt.setString(2,ord_detailsVO.getDet_fono());
			pstmt.setInt(3,ord_detailsVO.getDet_price());
			pstmt.setInt(4,ord_detailsVO.getDet_quantity());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-details");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}



}
