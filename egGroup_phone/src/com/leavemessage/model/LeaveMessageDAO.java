package com.leavemessage.model;

import java.sql.*;
import java.util.*;









public class LeaveMessageDAO implements LeaveMessageDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G6";
	String passwd = "123456";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
	
			pstmt.setString(1, LeavemessageVO.getLm_postno());
			pstmt.setString(2, LeavemessageVO.getLm_memno());
			pstmt.setString(3, LeavemessageVO.getLm_text());
			pstmt.setString(4, LeavemessageVO.getLm_status());
			
				

			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("沒有連上DB"+e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("錯誤發生!"+ se.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, LeavemessageVO.getLm_no());
			pstmt.setString(2, LeavemessageVO.getLm_postno());
			pstmt.setString(3, LeavemessageVO.getLm_memno());
			pstmt.setString(4, LeavemessageVO.getLm_text());
			pstmt.setString(5, LeavemessageVO.getLm_status());
			

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
	public void delete(String lm_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,lm_no);
		
			

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
	public LeaveMessageVO findByPrimaryKey(String lm_no) {

		LeaveMessageVO LeavemessageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, lm_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				LeavemessageVO = new LeaveMessageVO();
				LeavemessageVO.setLm_no(rs.getString("lm_no"));
				LeavemessageVO.setLm_postno(rs.getString("lm_postno"));
				LeavemessageVO.setLm_memno(rs.getString("lm_memno"));
				LeavemessageVO.setLm_text(rs.getString("lm_text"));
				LeavemessageVO.setLm_status(rs.getString("lm_status"));

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				LeavemessageVO = new LeaveMessageVO();
				LeavemessageVO.setLm_no(rs.getString("lm_no"));
				LeavemessageVO.setLm_postno(rs.getString("lm_postno"));
				LeavemessageVO.setLm_memno(rs.getString("lm_memno"));
				LeavemessageVO.setLm_text(rs.getString("lm_text"));
				LeavemessageVO.setLm_status(rs.getString("lm_status"));
				list.add(LeavemessageVO);
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

		LeaveMessageDAO dao = new LeaveMessageDAO();

		// 新增
//		LeaveMessageVO LeaveMessageVO1 = new LeaveMessageVO();
//		
//		LeaveMessageVO1.setLm_postno("PO000005");
//		LeaveMessageVO1.setLm_memno("ME000005");
//		LeaveMessageVO1.setLm_text("123456");
//		LeaveMessageVO1.setLm_status(("lm1"));
//		dao.insert(LeaveMessageVO1);
//	
	
	// 修改
//		LeaveMessageVO LeaveMessageVO2 = new LeaveMessageVO();
//		LeaveMessageVO2.setLm_no("LM000020");
//		LeaveMessageVO2.setLm_postno("PO000005");
//		LeaveMessageVO2.setLm_memno("ME000005");
//		LeaveMessageVO2.setLm_text("123456789");
//		LeaveMessageVO2.setLm_status("lm1");
//	
//		dao.update(LeaveMessageVO2);
		
		// 刪除
//		dao.delete("LM000026");
		// 查詢
//		LeaveMessageVO LeaveMessageVO3 = dao.findByPrimaryKey("LM000005");
//		System.out.print(LeaveMessageVO3.getLm_postno() + ",");
//		System.out.print(LeaveMessageVO3.getLm_memno() + ",");
//		System.out.print(LeaveMessageVO3.getLm_text() + ",");
//		System.out.print(LeaveMessageVO3.getLm_status() + ",");
//	
//		System.out.println("---------------------");
//		
		// 查詢
//		List<LeaveMessageVO> list = dao.getAll();
//		for (LeaveMessageVO aEmp : list) {
//		System.out.print(aEmp.getLm_postno() + ",");
//		System.out.print(aEmp.getLm_memno() + ",");
//		System.out.print(aEmp.getLm_text() + ",");
//		System.out.print(aEmp.getLm_status() + ",");
//
//		System.out.println();
//		}
	}
}


