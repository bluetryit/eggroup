package com.reportpost.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ReportPostJNDIDAO implements ReportPostDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
			
		}
	}
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
	
			pstmt.setString(1, ReportPostVO.getRepopost_postno());
			pstmt.setString(2, ReportPostVO.getRepopost_memno());
			pstmt.setString(3, ReportPostVO.getRepopost_text());
			pstmt.setString(4, ReportPostVO.getRepopost_status());
			
			
			
//			

			pstmt.executeUpdate();
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. !!"+ se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(5, ReportPostVO.getRepopost_no());
			pstmt.setString(1, ReportPostVO.getRepopost_postno());
			pstmt.setString(2, ReportPostVO.getRepopost_memno());
			pstmt.setString(3, ReportPostVO.getRepopost_text());
			pstmt.setString(4, ReportPostVO.getRepopost_status());
			

			pstmt.executeUpdate();

			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,repopost_no);
		
			

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, repopost_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo = Domain objects
				ReportPostVO = new ReportPostVO();
				ReportPostVO.setRepopost_no(rs.getString("repopost_no"));
				ReportPostVO.setRepopost_postno(rs.getString("repopost_postno"));
				ReportPostVO.setRepopost_memno(rs.getString("repopost_memno"));
				ReportPostVO.setRepopost_text(rs.getString("repopost_text"));
				ReportPostVO.setRepopost_status(rs.getString("repopost_status"));

			}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO = Domain objects
				ReportPostVO = new ReportPostVO();
				ReportPostVO.setRepopost_no(rs.getString("repopost_no"));
				ReportPostVO.setRepopost_postno(rs.getString("repopost_postno"));
				ReportPostVO.setRepopost_memno(rs.getString("repopost_memno"));
				ReportPostVO.setRepopost_text(rs.getString("repopost_text"));
				ReportPostVO.setRepopost_status(rs.getString("repopost_status"));


				list.add(ReportPostVO);
				// Store the row in the list
			}

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

}
