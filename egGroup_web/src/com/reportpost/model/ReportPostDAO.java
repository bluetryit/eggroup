package com.reportpost.model;

import java.sql.*;
import java.util.*;

public class ReportPostDAO implements ReportPostDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT		= 
			"INSERT INTO reportPost (repopost_no, repopost_postno, repopost_memno, repopost_text, repopost_status)"
			+"VALUES ('RP'||LPAD(to_char(reportPost_seq.NEXTVAL),6, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT	=
			"SELECT repopost_no, repopost_postno, repopost_memno, repopost_text, repopost_status FROM reportPost order by repopost_no";
	private static final String GET_ONE_STMT	=
			"SELECT repopost_no, repopost_postno, repopost_memno, repopost_text, repopost_status FROM reportPost where repopost_no = ?";
	private static final String DELETE			=
			"DELETE FROM reportPost where repopost_no = ?";
	private static final String UPDATE			=
			"UPDATE reportPost set repopost_postno=?, repopost_memno=?, repopost_text=?, repopost_status=? where repopost_no = ?";
	

	@Override
	public void insert(ReportPostVO ReportPostVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
	
			pstmt.setString(1, ReportPostVO.getRepopost_postno());
			pstmt.setString(2, ReportPostVO.getRepopost_memno());
			pstmt.setString(3, ReportPostVO.getRepopost_text());
			pstmt.setString(4, ReportPostVO.getRepopost_status());
			
			
			
//			

			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("�S���s�WDB"+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("���~�o��!"+ se.getMessage());
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
	public void update(ReportPostVO ReportPostVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(5, ReportPostVO.getRepopost_no());
			pstmt.setString(1, ReportPostVO.getRepopost_postno());
			pstmt.setString(2, ReportPostVO.getRepopost_memno());
			pstmt.setString(3, ReportPostVO.getRepopost_text());
			pstmt.setString(4, ReportPostVO.getRepopost_status());
			

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String repopost_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,repopost_no);
		
			

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ReportPostVO findByPrimaryKey(String repopost_no) {

		ReportPostVO ReportPostVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, repopost_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				ReportPostVO = new ReportPostVO();
				ReportPostVO.setRepopost_no(rs.getString("repopost_no"));
				ReportPostVO.setRepopost_postno(rs.getString("repopost_postno"));
				ReportPostVO.setRepopost_memno(rs.getString("repopost_memno"));
				ReportPostVO.setRepopost_text(rs.getString("repopost_text"));
				ReportPostVO.setRepopost_status(rs.getString("repopost_status"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return ReportPostVO;
	}

	@Override
	public List<ReportPostVO> getAll() {
		List<ReportPostVO> list = new ArrayList<ReportPostVO>();
		ReportPostVO ReportPostVO = null;

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
				ReportPostVO = new ReportPostVO();
				ReportPostVO.setRepopost_no(rs.getString("repopost_no"));
				ReportPostVO.setRepopost_postno(rs.getString("repopost_postno"));
				ReportPostVO.setRepopost_memno(rs.getString("repopost_memno"));
				ReportPostVO.setRepopost_text(rs.getString("repopost_text"));
				ReportPostVO.setRepopost_status(rs.getString("repopost_status"));


				list.add(ReportPostVO);
				// Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	public static void main(String[] args) {
		
		ReportPostDAO dao = new ReportPostDAO();
		
		//新增
//		ReportPostVO ReportPostVO1 = new ReportPostVO();
//		ReportPostVO1.setRepopost_postno("PO000005");
//		ReportPostVO1.setRepopost_memno("ME000005");
//		ReportPostVO1.setRepopost_text("測試測試測試");
//		ReportPostVO1.setRepopost_status("repolm3");
//		dao.insert(ReportPostVO1);
		
		// 修改
//		ReportPostDAO_interface dao = new ReportPostDAO();
//		ReportPostVO repo = dao.findByPrimaryKey("RP000006");
//		ReportPostVO ReportPostVO2 = new ReportPostVO();
//		repo.setRepopost_no("RP000006");
//		repo.setRepopost_postno("PO000005");
//		repo.setRepopost_memno("ME000005");
//		repo.setRepopost_text("今天是星期幾呢 我也不知道qqqq");
//		repo.setRepopost_status("repopost3");
//
//		dao.update(repo);
		
		//刪除
//		dao.delete("RP000006");
		
		// 單查詢
//		ReportPostVO ReportPostVO3 = dao.findByPrimaryKey("RP000001");
//		System.out.print(ReportPostVO3.getRepopost_postno() + ",");
//		System.out.print(ReportPostVO3.getRepopost_memno() + ",");
//		System.out.print(ReportPostVO3.getRepopost_text() + ",");
//		System.out.print(ReportPostVO3.getRepopost_status() + ",");
		
		//查詢
//		List<ReportPostVO> list = dao.getAll();
//		for (ReportPostVO aRepp : list) {
//		System.out.print(aRepp.getRepopost_no() + ",");
//		System.out.print(aRepp.getRepopost_postno() + ",");
//		System.out.print(aRepp.getRepopost_memno() + ",");
//		System.out.print(aRepp.getRepopost_text() + ",");
//		System.out.print(aRepp.getRepopost_status() + ",");
//
//		System.out.println();
//		}
	}

}
