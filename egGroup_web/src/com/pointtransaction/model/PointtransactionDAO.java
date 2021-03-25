package com.pointtransaction.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PointtransactionDAO implements PointtransactionDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G6";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PointTransaction (PT_NO,PT_MEMNO,PT_RESNO,PT_NT) VALUES ('PT'||LPAD(to_char(PointTransaction_seq.NEXTVAL), 6, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT PT_NO , PT_MEMNO, PT_RESNO,PT_NT FROM PointTransaction order by PT_NO";
	private static final String GET_ONE_STMT = "SELECT PT_NO , PT_MEMNO, PT_RESNO,PT_NT FROM PointTransaction where PT_NO = ?";
	private static final String DELETE = "DELETE FROM PointTransaction where PT_NO = ?";
	private static final String UPDATE = "UPDATE PointTransaction set PT_MEMNO=?, PT_RESNO=?,PT_NT=? where PT_NO = ?";

	@Override
	public void insert(PointtransactionVO PointtransactionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);  //註冊
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, PointtransactionVO.getPt_memno());
			pstmt.setString(2, PointtransactionVO.getPt_resno());
			pstmt.setDouble(3, PointtransactionVO.getPt_nt());

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
	public void update(PointtransactionVO PointtransactionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, PointtransactionVO.getPt_memno());
			pstmt.setString(2, PointtransactionVO.getPt_resno());
			pstmt.setDouble(3, PointtransactionVO.getPt_nt());
			pstmt.setString(4, PointtransactionVO.getPt_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("AAA database error occured. " + se.getMessage());
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
	public void delete(String pt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pt_no);

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
	public PointtransactionVO findByPrimaryKey(String pt_no) {

		PointtransactionVO pointtransactionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 嚙稽嚙誶穿蕭 Domain objects
				pointtransactionVO = new PointtransactionVO();
				pointtransactionVO.setPt_no(rs.getString("pt_no"));
				pointtransactionVO.setPt_memno(rs.getString("pt_memno"));
				pointtransactionVO.setPt_resno(rs.getString("pt_resno"));
				pointtransactionVO.setPt_nt(rs.getDouble("pt_nt"));
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
		return pointtransactionVO;
	}

	@Override
	public List<PointtransactionVO> getAll() {
		List<PointtransactionVO> list = new ArrayList<PointtransactionVO>();
		PointtransactionVO PointtransactionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				PointtransactionVO = new PointtransactionVO();
				PointtransactionVO.setPt_no(rs.getString("pt_no"));
				PointtransactionVO.setPt_memno(rs.getString("pt_memno"));
				PointtransactionVO.setPt_resno(rs.getString("pt_resno"));
				PointtransactionVO.setPt_nt(rs.getDouble("pt_nt"));

				list.add(PointtransactionVO);
				// Store the row in the list
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

	public static void main(String[] args) {

		PointtransactionDAO po = new PointtransactionDAO();
//
//		// 嚙編嚙磕
//		PointtransactionVO PointtransactionVO1 = new PointtransactionVO();
//		PointtransactionVO1.setPt_no("PT000001");
//		PointtransactionVO1.setPt_memno("ME000001");
//		PointtransactionVO1.setPt_resno("RS000001");
//		PointtransactionVO1.setPt_resno("100");
//		fo.insert(PointtransactionVO1);
//
//		// 嚙論改蕭
//		PointtransactionVO PointtransactionVO2 = new PointtransactionVO();
//		PointtransactionVO2.setPt_no("PT000001");
//		PointtransactionVO2.setPt_memno("ME000001");
//		PointtransactionVO2.setPt_resno("RS000001");
//				
//		fo.update(PointtransactionVO2);
// 
//		// 嚙磋嚙踝蕭   ��暺瘝���elet嚗� �瘜��= =
//		fo.delete("PT000004");

		// 嚙範嚙踝蕭
		PointtransactionVO PointtransactionVO3 = po.findByPrimaryKey("PT000004");
		System.out.print(PointtransactionVO3.getPt_no() + ",");
		System.out.print(PointtransactionVO3.getPt_memno() + ",");
		System.out.print(PointtransactionVO3.getPt_resno() + ",");
		System.out.print(PointtransactionVO3.getPt_nt() + ",");
		System.out.println("---------------------");

		// 嚙範嚙踝蕭
		List<PointtransactionVO> list = po.getAll();
		for (PointtransactionVO aEmp : list) {
			System.out.print(aEmp.getPt_no() + ",");
			System.out.print(aEmp.getPt_memno() + ",");
			System.out.print(aEmp.getPt_resno() + ",");
		    System.out.print(aEmp.getPt_nt() + ",");
			System.out.println();
		}
	}
}
