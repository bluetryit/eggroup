package com.res.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ResDAO implements ResDAO_interface {

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

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G6";
	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into RESTAURANT (RES_NO,RES_ADRS,RES_NAME,RES_PH,RES_POINT,RES_AC,RES_PASS,RES_IMG,RES_INTRO,RES_START,RES_END,RES_LAT,RES_LOT,RES_SCORE,RES_COST,RES_COMCOUNT,RES_TYPE,RES_STATUS)\r\n"
			+ "values ('RS'||LPAD(to_char(res_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT RES_NO,RES_ADRS,RES_NAME,RES_PH,RES_POINT,RES_AC,RES_PASS,RES_INTRO,RES_START,RES_END,RES_LAT,RES_LOT,RES_SCORE,RES_COST,RES_COMCOUNT,RES_TYPE,RES_STATUS FROM RESTAURANT ORDER BY RES_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM RESTAURANT WHERE RES_NO = ?";
	// 餐廳不能刪除只能更改狀態
	private static final String DELETE = "DELETE FROM RESTAURANT WHERE RES_NO = ?";
	private static final String UPDATE = "UPDATE RESTAURANT set RES_ADRS=?,RES_NAME=?,RES_PH=?,RES_POINT=?,RES_AC=?,RES_PASS=?,RES_INTRO=?,RES_START=?,RES_END=?,RES_LAT=?,RES_LOT=?,RES_SCORE=?,RES_COST=?,RES_COMCOUNT=?,RES_TYPE=?,RES_STATUS=? WHERE RES_NO = ?";
	private static final String GET_IMG_BY_RSNO="SELECT RES_IMG FROM RESTAURANT WHERE RES_NO = ?";
	@Override
	public void insert(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, resVO.getRes_adrs());
			pstmt.setString(2, resVO.getRes_name());
			pstmt.setString(3, resVO.getRes_ph());
			pstmt.setInt(4, resVO.getRes_point());
			pstmt.setString(5, resVO.getRes_ac());
			pstmt.setString(6, resVO.getRes_pass());
	
			pstmt.setString(7, resVO.getRes_intro());
			pstmt.setString(8, resVO.getRes_start());
			pstmt.setString(9, resVO.getRes_end());
			pstmt.setDouble(10, resVO.getRes_lat());
			pstmt.setDouble(11, resVO.getRes_lot());
			pstmt.setDouble(12, resVO.getRes_score());
			pstmt.setInt(13, resVO.getRes_cost());
			pstmt.setInt(14, resVO.getRes_comcount());
			pstmt.setString(15, resVO.getRes_type());
			pstmt.setString(16, resVO.getRes_status());
			pstmt.setString(17, resVO.getRes_no());
			pstmt.executeUpdate();

			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
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
	public void update(ResVO resVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, resVO.getRes_adrs());
			pstmt.setString(2, resVO.getRes_name());
			pstmt.setString(3, resVO.getRes_ph());
			pstmt.setInt(4, resVO.getRes_point());
			pstmt.setString(5, resVO.getRes_ac());
			pstmt.setString(6, resVO.getRes_pass());
			pstmt.setString(7, resVO.getRes_intro());
			pstmt.setString(8, resVO.getRes_start());
			pstmt.setString(9, resVO.getRes_end());
			pstmt.setDouble(10, resVO.getRes_lat());
			pstmt.setDouble(11, resVO.getRes_lot());
			pstmt.setDouble(12, resVO.getRes_score());
			pstmt.setInt(13, resVO.getRes_cost());
			pstmt.setInt(14, resVO.getRes_comcount());
			pstmt.setString(15, resVO.getRes_type());
			pstmt.setString(16, resVO.getRes_status());
			pstmt.setString(17, resVO.getRes_no());

			pstmt.executeUpdate();

			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(String res_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, res_no);

			pstmt.executeUpdate();

			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public ResVO findByPrimaryKey(String res_no) {
		ResVO resVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, res_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// resVO 也稱為 Domain objects
				resVO = new ResVO();

				resVO.setRes_no(rs.getString("res_no"));
				resVO.setRes_adrs(rs.getString("res_adrs"));
				resVO.setRes_name(rs.getString("res_name"));
				resVO.setRes_ph(rs.getString("res_ph"));
				resVO.setRes_point(rs.getInt("res_point"));
				resVO.setRes_ac(rs.getString("res_ac"));
				resVO.setRes_pass(rs.getString("res_pass"));
				resVO.setRes_intro(rs.getString("res_intro"));
				resVO.setRes_start(rs.getString("res_start"));
				resVO.setRes_end(rs.getString("res_end"));
				resVO.setRes_lat(rs.getDouble("res_lat"));
				resVO.setRes_lot(rs.getDouble("res_lot"));
				resVO.setRes_score(rs.getInt("res_score"));
				resVO.setRes_cost(rs.getInt("res_cost"));
				resVO.setRes_comcount(rs.getInt("res_comcount"));
				resVO.setRes_type(rs.getString("res_type"));
				resVO.setRes_status(rs.getString("res_status"));
				
				
			}

			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return resVO;
	}

	@Override
	public List<ResVO> getAll() {
		List<ResVO> list = new ArrayList<ResVO>();
		ResVO resVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// resVO 也稱為 Domain objects

				resVO = new ResVO();

				resVO.setRes_no(rs.getString("res_no"));
				resVO.setRes_adrs(rs.getString("res_adrs"));
				resVO.setRes_name(rs.getString("res_name"));
				resVO.setRes_ph(rs.getString("res_ph"));
				resVO.setRes_point(rs.getInt("res_point"));
				resVO.setRes_ac(rs.getString("res_ac"));
				resVO.setRes_pass(rs.getString("res_pass"));
				resVO.setRes_intro(rs.getString("res_intro"));
				resVO.setRes_start(rs.getString("res_start"));
				resVO.setRes_end(rs.getString("res_end"));
				resVO.setRes_lat(rs.getDouble("res_lat"));
				resVO.setRes_lot(rs.getDouble("res_lot"));
				resVO.setRes_score(rs.getInt("res_score"));
				resVO.setRes_cost(rs.getInt("res_cost"));
				resVO.setRes_comcount(rs.getInt("res_comcount"));
				resVO.setRes_type(rs.getString("res_type"));
				resVO.setRes_status(rs.getString("res_status"));

				list.add(resVO);// Store the row in the list
			}

			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public byte[] getImage(String res_no) {
		// TODO Auto-generated method stub
				byte[] picture = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_IMG_BY_RSNO);
					
					pstmt.setString(1, res_no);
					
					rs = pstmt.executeQuery();

					if (rs.next()) {
						picture = rs.getBytes(1);
					}
					// Handle any driver errors
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
				return picture;
	}
}
