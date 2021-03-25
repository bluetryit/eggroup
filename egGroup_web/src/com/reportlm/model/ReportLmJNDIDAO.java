package com.reportlm.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReportLmJNDIDAO implements ReportLmDAO_interface {
	
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
			"Insert into reportlm (REPOLM_NO, REPOLM_LMANO, REPOLM_MEMNO, REPOLM_TEXT, REPOLM_STATUS)"
					+"values ('RL'||LPAD(to_char(reportLm_seq.NEXTVAL), 6, '0'), ?,?,?,?)";
	private static final String GET_ALL_STMT	=
			"SELECT * FROM reportLm order by REPOLM_NO";
	private static final String GET_ONE_STMT	=
			"SELECT * FROM reportLm where REPOLM_NO = ?";
	private static final String DELETE			=
			"DELETE FROM reportLm where REPOLM_NO = ?";
	private static final String UPDATE			=
			"UPDATE reportLm set REPOLM_LMANO=?, REPOLM_MEMNO=?, REPOLM_TEXT=?, REPOLM_STATUS=? where REPOLM_NO = ?";
	

	@Override
	public void insert(ReportLmVO ReportLmVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
	
			pstmt.setString(1, ReportLmVO.getRepolm_lmano());
			pstmt.setString(2, ReportLmVO.getRepolm_memno());
			pstmt.setString(3, ReportLmVO.getRepolm_text());
			pstmt.setString(4, ReportLmVO.getRepolm_status());
			
			
//			

			pstmt.executeUpdate();
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(5, ReportLmVO.getRepolm_no());
			pstmt.setString(1, ReportLmVO.getRepolm_lmano());
			pstmt.setString(2, ReportLmVO.getRepolm_memno());
			pstmt.setString(3, ReportLmVO.getRepolm_text());
			pstmt.setString(4, ReportLmVO.getRepolm_status());
			

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
	public void delete(String repolm_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,repolm_no);
		
			

			pstmt.executeUpdate();

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
	public ReportLmVO findByPrimaryKey(String repolm_no) {

		ReportLmVO ReportLmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, repolm_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				ReportLmVO = new ReportLmVO();
			
				ReportLmVO.setRepolm_lmano(rs.getString("repolm_lmano"));
				ReportLmVO.setRepolm_memno(rs.getString("repolm_memno"));
				ReportLmVO.setRepolm_text(rs.getString("repolm_text"));
				ReportLmVO.setRepolm_status(rs.getString("repolm_status"));

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
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
