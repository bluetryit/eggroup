package com.leavemessage.model;

import java.util.*;
import java.sql.*;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class LeaveMessageJNDIDAO implements LeaveMessageDAO_interface {
	
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
			"Insert into leaveMessage (LM_NO, LM_POSTNO, LM_MEMNO, LM_TEXT, LM_STATUS)"
			+ "values ('LM'||LPAD(to_char(leaveMessage_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT	=
			"SELECT LM_NO, LM_POSTNO, LM_MEMNO, LM_TEXT, LM_STATUS FROM leaveMessage order by LM_NO";
	private static final String GET_ONE_STMT	=
			"SELECT LM_NO, LM_POSTNO, LM_MEMNO, LM_TEXT, LM_STATUS FROM leaveMessage where LM_NO = ?";
	private static final String DELETE			=
			"DELETE FROM leaveMessage where LM_NO = ?";
	private static final String UPDATE			=
			"UPDATE leaveMessage set LM_POSTNO=?, LM_MEMNO=?, LM_TEXT=?, LM_STATUS=? where LM_NO = ?";
	@Override
	public void insert(LeaveMessageVO LeavemessageVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
	
			pstmt.setString(1, LeavemessageVO.getLm_postno());
			pstmt.setString(2, LeavemessageVO.getLm_memno());
			pstmt.setString(3, LeavemessageVO.getLm_text());
			pstmt.setString(4, LeavemessageVO.getLm_status());
			
				

			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. !"+ se.getMessage());
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
	public void update(LeaveMessageVO LeavemessageVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, LeavemessageVO.getLm_no());
			pstmt.setString(2, LeavemessageVO.getLm_postno());
			pstmt.setString(3, LeavemessageVO.getLm_memno());
			pstmt.setString(4, LeavemessageVO.getLm_text());
			pstmt.setString(5, LeavemessageVO.getLm_status());
			

			pstmt.executeUpdate();

		
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
	public void delete(String lm_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,lm_no);
		
			

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
	public LeaveMessageVO findByPrimaryKey(String lm_no) {

		LeaveMessageVO LeavemessageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, lm_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo = Domain objects
				LeavemessageVO = new LeaveMessageVO();
				LeavemessageVO.setLm_no(rs.getString("lm_no"));
				LeavemessageVO.setLm_postno(rs.getString("lm_postno"));
				LeavemessageVO.setLm_memno(rs.getString("lm_memno"));
				LeavemessageVO.setLm_text(rs.getString("lm_text"));
				LeavemessageVO.setLm_status(rs.getString("lm_status"));

			}

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
		return LeavemessageVO;
	}

	@Override
	public List<LeaveMessageVO> getAll() {
		List<LeaveMessageVO> list = new ArrayList<LeaveMessageVO>();
		LeaveMessageVO LeavemessageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO = Domain objects
				LeavemessageVO = new LeaveMessageVO();
				LeavemessageVO.setLm_no(rs.getString("lm_no"));
				LeavemessageVO.setLm_postno(rs.getString("lm_postno"));
				LeavemessageVO.setLm_memno(rs.getString("lm_memno"));
				LeavemessageVO.setLm_text(rs.getString("lm_text"));
				LeavemessageVO.setLm_status(rs.getString("lm_status"));
				list.add(LeavemessageVO);
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