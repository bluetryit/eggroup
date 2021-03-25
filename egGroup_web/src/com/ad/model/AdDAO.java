package com.ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdDAO implements AdDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		// jndi不能使用main測試 要用jsp servlet 待v c完成後解鎖********
//		private static DataSource ds = null;
//		static {
//			try {
//				Context ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}
		// vc完成後使用datasource

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "DA101G6";
		String passwd = "123456";

		private static final String INSERT_STMT = "Insert into AD (AD_NO,AD_RESNO,AD_TEXT,AD_IMG,AD_START,AD_END,AD_TITLE,AD_STATUS)\r\n" + 
				"values ('AD'||LPAD(to_char(ad_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = "SELECT * FROM AD ORDER BY AD_NO";
		private static final String GET_ONE_STMT = "SELECT * FROM AD WHERE AD_NO = ?";
		private static final String DELETE = "DELETE FROM AD WHERE AD_NO = ?";
		private static final String UPDATE = "UPDATE AD set AD_RESNO=?,AD_TEXT=?,AD_IMG=?,AD_START=?,AD_END=?,AD_TITLE=?,AD_STATUS=?  WHERE AD_NO = ?";

	@Override
	public void insert(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adVO.getAd_resno());
			pstmt.setString(2, adVO.getAd_text());
			pstmt.setBytes(3, adVO.getAd_img());
			pstmt.setTimestamp(4, adVO.getAd_start());
			pstmt.setTimestamp(5, adVO.getAd_end());
			pstmt.setString(6, adVO.getAd_title());
			pstmt.setString(7, adVO.getAd_status());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, adVO.getAd_resno());
			pstmt.setString(2, adVO.getAd_text());
			pstmt.setBytes(3, adVO.getAd_img());
			pstmt.setTimestamp(4, adVO.getAd_start());
			pstmt.setTimestamp(5, adVO.getAd_end());
			pstmt.setString(6, adVO.getAd_title());
			pstmt.setString(7, adVO.getAd_status());
			pstmt.setString(8, adVO.getAd_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String ad_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ad_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public AdVO findByPrimaryKey(String ad_no) {
		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ad_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adVO 也稱為 Domain objects
				adVO = new AdVO();

				adVO.setAd_no(rs.getString("ad_no"));
				adVO.setAd_resno(rs.getString("ad_resno"));
				adVO.setAd_text(rs.getString("ad_text"));
				adVO.setAd_img(rs.getBytes("ad_img"));
				adVO.setAd_start(rs.getTimestamp("ad_start"));
				adVO.setAd_end(rs.getTimestamp("ad_end"));
				adVO.setAd_title(rs.getString("ad_title"));
				adVO.setAd_status(rs.getString("ad_status"));
				
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return adVO;
	}

	@Override
	public List<AdVO> getAll() {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();

			while (rs.next()) {
				// adVO 也稱為 Domain objects

				adVO = new AdVO();

				adVO.setAd_no(rs.getString("ad_no"));
				adVO.setAd_resno(rs.getString("ad_resno"));
				adVO.setAd_text(rs.getString("ad_text"));
				adVO.setAd_img(rs.getBytes("ad_img"));
				adVO.setAd_start(rs.getTimestamp("ad_start"));
				adVO.setAd_end(rs.getTimestamp("ad_end"));
				adVO.setAd_title(rs.getString("ad_title"));
				adVO.setAd_status(rs.getString("ad_status"));
				
				list.add(adVO);// Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

}
