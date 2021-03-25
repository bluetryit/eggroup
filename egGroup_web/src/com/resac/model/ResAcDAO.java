package com.resac.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ResAcDAO implements ResAcDAO_interface {
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

		private static final String INSERT_STMT = "INSERT INTO RESTAURANTAC (RESAC_NO, RESAC_RESNO, RESAC_PASS, RESAC_NAME, RESAC_PHONE,RESAC_PIC,RESAC_INTRO,RESAC_STATUS) \r\n" + 
				"VALUES (?, ?, ?, ?, ?, ?,?, ?)";
		private static final String GET_ALL_STMT = "SELECT * FROM RESTAURANTAC ORDER BY RESAC_NO,RESAC_RESNO";
		private static final String GET_ONE_STMT = "SELECT * FROM RESTAURANTAC WHERE RESAC_NO = ? AND RESAC_RESNO = ?";
		private static final String GET_ALLBYRESNO_STMT = "SELECT * FROM RESTAURANTAC  WHERE RESAC_RESNO = ? ORDER BY RESAC_NO DESC";
		private static final String UPDATE = "UPDATE RESTAURANTAC set RESAC_PASS=?,RESAC_NAME=?,RESAC_PHONE=?,RESAC_PIC=?,RESAC_INTRO=?,RESAC_STATUS=? WHERE RESAC_NO = ? AND RESAC_RESNO = ?";

		@Override
		public void insert(ResAcVO resAcVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

//				con = ds.getConnection();
//				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, resAcVO.getResac_no());
				pstmt.setString(2, resAcVO.getResac_resno());
				pstmt.setString(3, resAcVO.getResac_pass());
				pstmt.setString(4, resAcVO.getResac_name());
				pstmt.setString(5, resAcVO.getResac_phone());
				pstmt.setBytes(6, resAcVO.getResac_pic());
				pstmt.setString(7, resAcVO.getResac_intro());
				pstmt.setString(8, resAcVO.getResac_status());


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
		public void update(ResAcVO resAcVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

//				con = ds.getConnection();
//				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, resAcVO.getResac_pass());
				pstmt.setString(2, resAcVO.getResac_name());
				pstmt.setString(3, resAcVO.getResac_phone());
				pstmt.setBytes(4, resAcVO.getResac_pic());
				pstmt.setString(5, resAcVO.getResac_intro());
				pstmt.setString(6, resAcVO.getResac_status());
				pstmt.setString(7, resAcVO.getResac_no());
				pstmt.setString(8, resAcVO.getResac_resno());


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
		public ResAcVO findByPrimaryKey(String resac_no, String resac_resno) {
			ResAcVO resAcVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

//				con = ds.getConnection();
//				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, resac_no);
				pstmt.setString(2, resac_resno);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// resVO 也稱為 Domain objects
					resAcVO = new ResAcVO();

					resAcVO.setResac_no(rs.getString("resac_no"));
					resAcVO.setResac_resno(rs.getString("resac_resno"));
					resAcVO.setResac_pass(rs.getString("resac_pass"));
					resAcVO.setResac_name(rs.getString("resac_name"));
					resAcVO.setResac_phone(rs.getString("resac_phone"));
					resAcVO.setResac_pic(rs.getBytes("resac_pic"));
					resAcVO.setResac_intro(rs.getString("resac_intro"));
					resAcVO.setResac_status(rs.getString("resac_status"));

					
					
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
			return resAcVO;
		}

		@Override
		public List<ResAcVO> getAll() {
			List<ResAcVO> list = new ArrayList<ResAcVO>();
			ResAcVO resAcVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(GET_ALL_STMT);
//				rs = pstmt.executeQuery();

				while (rs.next()) {
					// resVO 也稱為 Domain objects

					resAcVO = new ResAcVO();
					
					resAcVO.setResac_no(rs.getString("resac_no"));
					resAcVO.setResac_resno(rs.getString("resac_resno"));
					resAcVO.setResac_pass(rs.getString("resac_pass"));
					resAcVO.setResac_name(rs.getString("resac_name"));
					resAcVO.setResac_phone(rs.getString("resac_phone"));
					resAcVO.setResac_pic(rs.getBytes("resac_pic"));
					resAcVO.setResac_intro(rs.getString("resac_intro"));
					resAcVO.setResac_status(rs.getString("resac_status"));


					list.add(resAcVO);// Store the row in the list
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



		@Override
		public List<ResAcVO> getAllByResNO(String resac_resno) {
			List<ResAcVO> list = new ArrayList<ResAcVO>();
			ResAcVO resAcVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALLBYRESNO_STMT);
				
				pstmt.setString(1, resac_resno);
				rs = pstmt.executeQuery();
				
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(GET_ALL_STMT);
//				rs = pstmt.executeQuery();

				while (rs.next()) {
					// resVO 也稱為 Domain objects

					resAcVO = new ResAcVO();
					
					resAcVO.setResac_no(rs.getString("resac_no"));
					resAcVO.setResac_resno(rs.getString("resac_resno"));
					resAcVO.setResac_pass(rs.getString("resac_pass"));
					resAcVO.setResac_name(rs.getString("resac_name"));
					resAcVO.setResac_phone(rs.getString("resac_phone"));
					resAcVO.setResac_pic(rs.getBytes("resac_pic"));
					resAcVO.setResac_intro(rs.getString("resac_intro"));
					resAcVO.setResac_status(rs.getString("resac_status"));


					list.add(resAcVO);// Store the row in the list
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
