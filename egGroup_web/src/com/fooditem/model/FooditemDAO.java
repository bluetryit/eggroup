package com.fooditem.model;

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

public class FooditemDAO implements FooditemDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA101G6";
//	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO FOODITEM  (FO_NO,FO_RESNO,FO_NAME,FO_PRICE,FO_IMG,FO_INTRO,FO_STATUS) VALUES ('FO'||LPAD(to_char(FOODITEM_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT FO_NO,FO_RESNO,FO_NAME,FO_PRICE,FO_IMG,FO_INTRO,FO_STATUS FROM FOODITEM order by FO_NO";
	private static final String GET_ONE_STMT = "SELECT FO_NO,FO_RESNO,FO_NAME,FO_PRICE,FO_IMG,FO_INTRO,FO_STATUS FROM FOODITEM where FO_NO = ?";
	private static final String DELETE = "DELETE FROM FOODITEM where FO_NO = ?";
	private static final String UPDATE = "UPDATE FOODITEM set fo_resno=?, fo_name=?, fo_price=?, fo_img=?,fo_intro=?,fo_status=? where fo_no = ?";
	private static final String GET_BY_RESNO="SELECT * FROM FOODITEM WHERE FO_RESNO=? and fo_status='fo1' order by FO_NO";
	private static final String GET_FO_BY_RESNO="SELECT * FROM FOODITEM WHERE FO_RESNO=? and fo_status='fo3' order by FO_NO";
	@Override
	public void insert(FooditemVO FooditemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
		

			pstmt.setString(1, FooditemVO.getFo_resno());
			pstmt.setString(2, FooditemVO.getFo_name());
			pstmt.setInt(3, FooditemVO.getFo_price());
			pstmt.setBytes(4, FooditemVO.getFo_img());
			pstmt.setString(5, FooditemVO.getFo_intro());
			pstmt.setString(6, FooditemVO.getFo_status());

			pstmt.executeUpdate();

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
	public void update(FooditemVO FooditemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, FooditemVO.getFo_resno());
			pstmt.setString(2, FooditemVO.getFo_name());
			pstmt.setInt(3, FooditemVO.getFo_price());
			pstmt.setBytes(4, FooditemVO.getFo_img());
			pstmt.setString(5, FooditemVO.getFo_intro());
			pstmt.setString(6, FooditemVO.getFo_status());
			pstmt.setString(7, FooditemVO.getFo_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String fo_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, fo_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public FooditemVO findByPrimaryKey(String fo_no) {

		FooditemVO foodItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, fo_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嚙稽嚙誶穿蕭 Domain objects
				foodItemVO = new FooditemVO();
				foodItemVO.setFo_no(rs.getString("fo_no"));
				foodItemVO.setFo_resno(rs.getString("fo_resno"));
				foodItemVO.setFo_name(rs.getString("fo_name"));
				foodItemVO.setFo_price(rs.getInt("fo_price"));
				foodItemVO.setFo_img(rs.getBytes("fo_img"));
				foodItemVO.setFo_intro(rs.getString("fo_intro"));
				foodItemVO.setFo_status(rs.getString("fo_status"));

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
		return foodItemVO;
	}

	@Override
	public List<FooditemVO> getAll() {
		List<FooditemVO> list = new ArrayList<FooditemVO>();
		FooditemVO FooditemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				FooditemVO = new FooditemVO();
				FooditemVO.setFo_no(rs.getString("fo_no"));
				FooditemVO.setFo_resno(rs.getString("fo_resno"));
				FooditemVO.setFo_name(rs.getString("fo_name"));
				FooditemVO.setFo_price(rs.getInt("fo_price"));
				FooditemVO.setFo_img(rs.getBytes("fo_img"));
				FooditemVO.setFo_intro(rs.getString("fo_intro"));
				FooditemVO.setFo_status(rs.getString("fo_status"));

				list.add(FooditemVO); // Store the row in the list
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
		return list;
	}
	
	@Override
	public List<FooditemVO> getByResNO(String fo_resno) {
		List<FooditemVO> list = new ArrayList<FooditemVO>();
		FooditemVO FooditemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_RESNO);
			
			pstmt.setString(1, fo_resno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				FooditemVO = new FooditemVO();
				FooditemVO.setFo_no(rs.getString("fo_no"));
				FooditemVO.setFo_resno(rs.getString("fo_resno"));
				FooditemVO.setFo_name(rs.getString("fo_name"));
				FooditemVO.setFo_price(rs.getInt("fo_price"));
				FooditemVO.setFo_img(rs.getBytes("fo_img"));
				FooditemVO.setFo_intro(rs.getString("fo_intro"));
				FooditemVO.setFo_status(rs.getString("fo_status"));

				list.add(FooditemVO); // Store the row in the list
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
		return list;
	}

	public static void main(String[] args) {

		FooditemDAO fo = new FooditemDAO();
//
//		// 嚙編嚙磕
//		FooditemVO FooditemVO1 = new FooditemVO();
//		FooditemVO1.setFo_no("FO000001");
//		FooditemVO1.setFo_fesno("RS000001");
//		FooditemVO1.setFo_name("������");
//		FooditemVO1.setFo_price(130);
//		FooditemVO1.setFo_img(null);
//		FooditemVO1.setFo_intro("lm1");
//		FooditemVO1.setFo_status("lm1");
//		fo.insert(FooditemVO1);
//
//		// 嚙論改蕭
//		FooditemVO FooditemVO2 = new FooditemVO();
//		FooditemVO2.setFo_no("LM000005");
//		FooditemVO2.setFo_fesno("P000005");
//		FooditemVO2.setFo_name("ME000005");
//		FooditemVO2.setFo_price(5000);
//		FooditemVO2.setFo_img(null);
//		FooditemVO2.setFo_intro("111");
//		FooditemVO2.setFo_status("111");
//		
//		fo.update(FooditemVO2);
//
//		// 嚙磋嚙踝蕭
//		fo.delete("LM000004");

		// 嚙範嚙踝蕭
		FooditemVO FooditemVO3 = fo.findByPrimaryKey("FO000001");
		System.out.print(FooditemVO3.getFo_no() + ",");
		System.out.print(FooditemVO3.getFo_resno() + ",");
		System.out.print(FooditemVO3.getFo_name() + ",");
		System.out.print(FooditemVO3.getFo_price() + ",");
		System.out.print(FooditemVO3.getFo_img() + ",");
		System.out.print(FooditemVO3.getFo_intro() + ",");
		System.out.println(FooditemVO3.getFo_status() + ",");
		System.out.println("---------------------");

		// 嚙範嚙踝蕭
		List<FooditemVO> list = fo.getAll();
		for (FooditemVO aEmp : list) {
			System.out.print(aEmp.getFo_no() + ",");
			System.out.print(aEmp.getFo_resno() + ",");
			System.out.print(aEmp.getFo_name() + ",");
			System.out.print(aEmp.getFo_price() + ",");
			System.out.print(aEmp.getFo_img() + ",");
			System.out.print(aEmp.getFo_intro() + ",");
			System.out.print(aEmp.getFo_status() + ",");
			System.out.println();

		}
	}

	@Override
	public List<FooditemVO> getAllReviewFooditemByRes(String fo_resno) {
		List<FooditemVO> list = new ArrayList<FooditemVO>();
		FooditemVO FooditemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FO_BY_RESNO);
			
			pstmt.setString(1, fo_resno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				FooditemVO = new FooditemVO();
				FooditemVO.setFo_no(rs.getString("fo_no"));
				FooditemVO.setFo_resno(rs.getString("fo_resno"));
				FooditemVO.setFo_name(rs.getString("fo_name"));
				FooditemVO.setFo_price(rs.getInt("fo_price"));
				FooditemVO.setFo_img(rs.getBytes("fo_img"));
				FooditemVO.setFo_intro(rs.getString("fo_intro"));
				FooditemVO.setFo_status(rs.getString("fo_status"));

				list.add(FooditemVO); // Store the row in the list
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
		return list;
	}
}
