package com.reportlm.model;

import java.sql.*;
import java.util.*;



public class ReportLmDAO implements ReportLmDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT		= 
			"Insert into reportlm (REPOLM_NO, REPOLM_LMANO, REPOLM_MEMNO, REPOLM_TEXT, REPOLM_STATUS)"
					+"values ('RL'||LPAD(to_char(reportLm_seq.NEXTVAL), 6, '0'), ?,?,?,'repolm3')";
	private static final String GET_ALL_STMT	=
			"SELECT * FROM reportLm order by REPOLM_NO";
	private static final String GET_ONE_STMT	=
			"SELECT * FROM reportLm where REPOLM_NO = ?";
	private static final String DELETE			=
			"DELETE FROM reportLm where REPOLM_NO = ?";
	private static final String UPDATE			=
			"UPDATE reportLm set  REPOLM_STATUS=? where REPOLM_NO = ?";
	

	@Override
	public void insert(ReportLmVO ReportLmVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
	
			pstmt.setString(1, ReportLmVO.getRepolm_lmano());
			pstmt.setString(2, ReportLmVO.getRepolm_memno());
			pstmt.setString(3, ReportLmVO.getRepolm_text());
			
			
			
//			

			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+ se.getMessage());
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
	public void update(ReportLmVO ReportLmVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ReportLmVO.getRepolm_status());
			pstmt.setString(2, ReportLmVO.getRepolm_no());


			pstmt.executeUpdate();

		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(String repolm_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,repolm_no);
		
			

			pstmt.executeUpdate();

		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public ReportLmVO findByPrimaryKey(String repolm_no) {

		ReportLmVO ReportLmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, repolm_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo = Domain objects
				ReportLmVO = new ReportLmVO();
			
				ReportLmVO.setRepolm_lmano(rs.getString("repolm_lmano"));
				ReportLmVO.setRepolm_memno(rs.getString("repolm_memno"));
				ReportLmVO.setRepolm_text(rs.getString("repolm_text"));
				ReportLmVO.setRepolm_status(rs.getString("repolm_status"));

			}

		
		
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
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
		return ReportLmVO;
	}

	@Override
	public List<ReportLmVO> getAll() {
		List<ReportLmVO> list = new ArrayList<ReportLmVO>();
		ReportLmVO ReportLmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO = Domain objects
				ReportLmVO = new ReportLmVO();
				ReportLmVO.setRepolm_no(rs.getString("repolm_no"));
				ReportLmVO.setRepolm_lmano(rs.getString("repolm_lmano"));
				ReportLmVO.setRepolm_memno(rs.getString("repolm_memno"));
				ReportLmVO.setRepolm_text(rs.getString("repolm_text"));
				ReportLmVO.setRepolm_status(rs.getString("repolm_status"));

				list.add(ReportLmVO);
				// Store the row in the list
			}

			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}  catch (SQLException se) {
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
//	public static void main(String[] args) {
		
		
//		ReportLmDAO dao = new ReportLmDAO();
		
		//新增
//		ReportLmVO ReportLmVO1 = new ReportLmVO();
//		ReportLmVO1.setRepolm_lmano("LM000005");
//		ReportLmVO1.setRepolm_memno("ME000005");
//		ReportLmVO1.setRepolm_text("璇����璇����");
//		ReportLmVO1.setRepolm_status("repolm1");
//		dao.insert(ReportLmVO1);
		
		// 修改
//		ReportLmDAO_interface dao = new ReportLmDAO();
//		ReportLmVO rep = dao.findByPrimaryKey("RL000006");
//		rep.setRepolm_no("RL000006");
//		rep.setRepolm_lmano("LM000005");
//		rep.setRepolm_memno("ME000005");
//		rep.setRepolm_text("測試測試");
//
//		dao.update(rep);
//		
		//刪除
//		dao.delete("RL000006");
		
		//查詢
//		ReportLmVO ReportLmVO3 = dao.findByPrimaryKey("RL000001");
//		System.out.print(ReportLmVO3.getRepolm_lmano() + ",");
//		System.out.print(ReportLmVO3.getRepolm_memno() + ",");
//		System.out.print(ReportLmVO3.getRepolm_text() + ",");
//		System.out.print(ReportLmVO3.getRepolm_status() + ",");

		
		//�閰�
//		List<ReportLmVO> list = dao.getAll();
//		for (ReportLmVO aResl : list) {
//		System.out.print(aResl.getRepolm_lmano() + ",");
//		System.out.print(aResl.getRepolm_memno() + ",");
//		System.out.print(aResl.getRepolm_text() + ",");
//		System.out.print(aResl.getRepolm_status() + ",");
//
//		System.out.println();
//		}
//	}
	
}

