package com.friendList.model;

import java.util.*;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.mem.model.*;

public class FriendListDAO implements FriendListDAO_interface 
{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String userid = "DA101G6";
    String passwd = "123456";

   
    
    private static final String INSERT_STMT = "INSERT INTO friendlist(F_NO,F_MEMNO,F_STATUS)VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE friendList set F_NO=? F_MEMNO=? F_STATUS=? where F_NO =? and F_MEMNO=?";
    private static final String DELETE="DELETE FROM friendList  where F_NO =? and F_MEMNO=?";
    private static final String GET_BY_MEM = "SELECT * FROM friendList where F_NO=? order by F_STATUS";//取得會員所有好友(包括送出申請的)
   
    
    private static final String GET_ONE_STMT = "SELECT * FROM friendList where F_NO=? and F_MEMNO = ?";
    private static final String REJECT ="INSERT INTO FriendsList (F_STATUS, F_NO, F_MEMNO)" 
    								   +"VALUES (-1,?,?);";
    
    private static final String GET_ALL_STMT = "select f_memno from friendlist where f_no = ? and f_status = 2";	
    
    private static final String GET_ALL_INVITE = "SELECT MEM.MEM_NAME,MEM.MEM_SEX,MEM.MEM_INTRO FROM MEM LEFT JOIN FRIENDLIST on FRIENDLIST.F_NO=MEM.MEM_NO WHERE"
									    		+ " (FRIENDLIST.F_MEMNO=? AND FRIENDLIST.F_STATUS=1)"
									    		+ " AND MEM.MEM_NO not in (SELECT F_MEMNO FROM FRIENDLIST WHERE (FRIENDLIST.F_NO=? AND FRIENDLIST.F_STATUS=-1))";
    
    private static final String GET_ALL_MATCH = "SELECT MEM.MEM_NAME,MEM.MEM_SEX,MEM.MEM_INTRO FROM MEM" + 
    		"				WHERE NOT EXISTS (SELECT 'x'  FROM FRIENDLIST WHERE" + 
    		"				(MEM.MEM_NO=FRIENDLIST.F_NO OR MEM.MEM_NO=FRIENDLIST.F_MEMNO) AND((FRIENDLIST.F_NO =? AND" + 
    		"				FRIENDLIST.F_STATUS = 2) OR ( FRIENDLIST.F_MEMNO =? AND FRIENDLIST.F_STATUS = 2) OR" + 
    		"				(FRIENDLIST.F_NO=? AND FRIENDLIST.F_STATUS = 1) OR (FRIENDLIST.F_NO=? AND FRIENDLIST.F_STATUS = -1)" + 
    		"			        OR(FRIENDLIST.F_MEMNO=? AND FRIENDLIST.F_STATUS=1)))";
    
    
    
    
	@Override
	public void insert(FriendListVO friendListVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, friendListVO.getF_no());
			pstmt.setString(2, friendListVO.getF_memno());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
	
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

//	@Override
//	public void update(FriendListVO friendListVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			
//			Class.forName(driver);
//            con = DriverManager.getConnection(url, userid, passwd);
//            pstmt = con.prepareStatement(UPDATE);
//
//            pstmt.setString(1, friendListVO.getF_no());
//			pstmt.setString(2, friendListVO.getF_memno());
//			pstmt.setString(3, friendListVO.getF_status());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public void reject(String F_NO,String F_MEMNO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
            pstmt = con.prepareStatement(REJECT);

            pstmt.setString(1,F_NO);
            pstmt.setString(2,F_MEMNO);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public FriendListVO findByPrimaryKey(String f_num) {

		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,f_num);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嚙稽嚙誶穿蕭 Domain objects
				friendListVO = new FriendListVO();
				friendListVO.setF_no(rs.getString("f_no"));
				friendListVO.setF_memno(rs.getString("f_memno"));
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
		return friendListVO;
	}

	@Override
	public List<MemVO> getAll(String mem_no) {	
		
		List<MemVO> list = new ArrayList<MemVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            pstmt.setString(1,mem_no);
            rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
//				MemVO memVO=new MemVO();
//				memVO.setMem_no(rs.getString("mem_no"));
//				memVO.setMem_name(rs.getString("mem_name"));
//				memVO.setMem_intro(rs.getString("mem_intro"));
				String f_memno=rs.getString(1);

				MemService memService = new MemService();
				MemVO memVO = memService.memFindByPrimaryKey(f_memno);
				list.add(memVO); // Store the row in the list
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

//	@Override
//	public List<MemVO> getAllInvite(String mem_no) {
//		List<MemVO> list = new ArrayList<MemVO>();
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con = ds.getConnection();
//            pstmt = con.prepareStatement(GET_ALL_INVITE);
//            pstmt.setString(1,mem_no);
//            pstmt.setString(2,mem_no);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				String mem_name = rs.getString(1);
//				String mem_sex =rs.getString(2);
//				String mem_intro= rs.getString(3);
//	
//		
//			}
//			return list;
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

//	@Override
//	public List<MemVO> match(String mem_no) {
//		List<MemVO> list = new ArrayList<MemVO>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			Class.forName(driver);
//            con = DriverManager.getConnection(url, userid, passwd);
//            pstmt = con.prepareStatement(GET_ALL_MATCH);
//            pstmt.setString(1, mem_no);
//			pstmt.setString(2, mem_no);
//			pstmt.setString(3, mem_no);
//			pstmt.setString(4, mem_no);
//			pstmt.setString(5, mem_no);
//			
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				String mem_name=rs.getString(1);
//				String mem_sex=rs.getString(2);
//				String mem_intro=rs.getString(3);
//				MemVO memVO=new MemVO(mem_name,mem_sex,mem_intro);
//				list.add(memVO); 
//			}
//			return list;
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

	
	

	@Override
	public FriendListVO findByTwoPrimaryKey(String f_no,String f_memno) {

		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setString(1,f_no);
            pstmt.setString(2,f_memno);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嚙稽嚙誶穿蕭 Domain objects
				friendListVO = new FriendListVO();
				friendListVO.setF_no(rs.getString("f_no"));
				friendListVO.setF_memno(rs.getString("f_memno"));
				friendListVO.setF_status(rs.getString("f_status"));
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
		return friendListVO;
	}

	@Override
	public void update(String f_no, String f_memno, String f_status) {
		// TODO Auto-generated method stub
		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setString(1,f_no);
            pstmt.setString(2,f_memno);
            pstmt.setString(3,f_status);
            pstmt.setString(4,f_no);
            pstmt.setString(5,f_memno);
			rs = pstmt.executeQuery();
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
	}

	@Override
	public void delete(String f_no, String f_memno) {
		// TODO Auto-generated method stub

		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
            pstmt = con.prepareStatement(DELETE);

            pstmt.setString(1,f_no);
            pstmt.setString(2,f_memno);
			
			rs = pstmt.executeQuery();

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
		
	}

	
}