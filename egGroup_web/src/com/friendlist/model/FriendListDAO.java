package com.friendlist.model;

import java.util.*;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.mem.model.*;

public class FriendListDAO implements FriendListDAO_interface 
{
	String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String userid = "DA101G6";
    String passwd = "123456";

    private static final String REJECT ="INSERT INTO FriendList (F_NUM,F_STATUS,F_NO,F_MEMNO)" 
    								   +"VALUES (to_char(friend_seq.NEXTVAL),-1,?,?)";
    
    private static final String GET_ALL_STMT = "select f_memno from friendlist where f_no = ? and f_status = 2";	
    
    private static final String GET_ALL_INVITE = "select f_no from friendlist where f_memno = ? and f_status = 1";
    
    private static final String GET_ALL_MATCH = "SELECT MEM.MEM_NAME,MEM.MEM_SEX,MEM.MEM_INTRO FROM MEM" + 
    		"				WHERE NOT EXISTS (SELECT 'x'  FROM FRIENDLIST WHERE" + 
    		"				(MEM.MEM_NO=FRIENDLIST.F_NO OR MEM.MEM_NO=FRIENDLIST.F_MEMNO) AND((FRIENDLIST.F_NO =? AND" + 
    		"				FRIENDLIST.F_STATUS = 2) OR ( FRIENDLIST.F_MEMNO =? AND FRIENDLIST.F_STATUS = 2) OR" + 
    		"				(FRIENDLIST.F_NO=? AND FRIENDLIST.F_STATUS = 1) OR (FRIENDLIST.F_NO=? AND FRIENDLIST.F_STATUS = -1)" + 
    		"			        OR(FRIENDLIST.F_MEMNO=? AND FRIENDLIST.F_STATUS=1)))";
    
    private static final String GET_ONE_STMT = "SELECT * FROM friendList where F_NO=? and F_MEMNO = ?";

	@Override
	public void findByIds(String f_no,String f_memno){	  //送出邀請

		 String INSERT_STMT = "INSERT INTO friendlist(F_NUM,F_NO,F_MEMNO,F_STATUS)VALUES (friend_seq.NEXTVAL,?, ?, 1)";

		
		Connection con = null;
		PreparedStatement pstmt = null;


		try {
			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);

            pstmt = con.prepareStatement(INSERT_STMT);
            pstmt.setString(1, f_no);
            pstmt.setString(2, f_memno);
            pstmt.executeUpdate();
           

		}catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		try {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

	@Override
	public int findByIds2(String f_no,String f_memno) {		//接受邀請
		int count=0;
		int f_num,f_status;
		String status = "SELECT f_num FROM FriendList WHERE f_no=? AND f_memno=?";
		String update ="UPDATE FriendList SET f_status = 2 WHERE (f_num = ?)";
		String insert ="INSERT INTO FriendList (f_num, f_status, f_no, f_memno) VALUES (to_char(friend_seq.NEXTVAL), 2, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			conn.setAutoCommit(false); 
			pstmt = conn.prepareStatement(status);
			pstmt.setString(1,f_no );
			pstmt.setString(2,f_memno);
			ResultSet rs=pstmt.executeQuery();
			
			if (rs.next()) {
				f_num=rs.getInt(1);
				
				pstmt= conn.prepareStatement(update);
				pstmt.setInt(1, f_num);
				count = pstmt.executeUpdate();
					
				pstmt= conn.prepareStatement(insert);
				pstmt.setString(1,f_memno);
				pstmt.setString(2,f_no);
				count = pstmt.executeUpdate();
				}
			conn.commit();

			}catch (SQLException e) {
				e.printStackTrace();
				try {
					// roll back while SQLException
					conn.rollback();
					count = 0;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	@Override
	public int reject(String f_no,String f_memno) {
		int f_num,f_status;
		int count=0;
		String status ="SELECT f_num FROM FriendList WHERE f_no=? AND f_memno=?";
		String update ="UPDATE FriendList SET f_status = -1 WHERE (f_num = ?)";
		String insert ="INSERT INTO FriendList (f_num, f_status, f_no, f_memno) VALUES (to_char(friend_seq.NEXTVAL), -1, ?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false); 
			pstmt = con.prepareStatement(status);
			pstmt.setString(1,f_memno);
			pstmt.setString(2,f_no);
			ResultSet rs=pstmt.executeQuery();
			
			if (rs.next()) {
				f_num=rs.getInt(1);
				
				pstmt= con.prepareStatement(update);
				pstmt.setInt(1, f_num);
				count = pstmt.executeUpdate();
					
				pstmt= con.prepareStatement(insert);
				pstmt.setString(1,f_no);
				pstmt.setString(2,f_memno);
				count = pstmt.executeUpdate();
				}
			con.commit();
		}catch (SQLException e) {
			e.printStackTrace();
		try {
			// roll back while SQLException
			con.rollback();
			count = 0;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		}finally {
		try {
			if (pstmt != null) {
			pstmt.close();
		}
		if (con != null) {
			con.close();
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return count;
}

		
//		try {
//			
//			Class.forName(driver);
//            con = DriverManager.getConnection(url, userid, passwd);
//            pstmt = con.prepareStatement(REJECT);
//            
//            pstmt.setString(1,f_no);
//            pstmt.setString(2,f_memno);
//			pstmt.executeUpdate();
//			con.commit();
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
//	@Override
//	public FriendListVO findByPrimaryKey(String f_num) {
//
//		FriendListVO friendListVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//            con = DriverManager.getConnection(url, userid, passwd);
//            pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1,f_num);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo ��里��甽蝛輯 Domain objects
//				friendListVO = new FriendListVO();
//				friendListVO.setF_no(rs.getString("f_no"));
//				friendListVO.setF_memno(rs.getString("f_memno"));
//			}
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
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
//		return friendListVO;
//	}

	@Override
	public List<MemVO> getAll(String mem_no) {	
		
		List<MemVO> list = new ArrayList<MemVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            pstmt.setString(1,mem_no);
            rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ����蕭蹌� Domain objects
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<MemVO> getAllInvite(String f_memno) {
		List<MemVO> list = new ArrayList<MemVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_INVITE);
            pstmt.setString(1,f_memno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String f_no=rs.getString(1);
				MemService memService = new MemService();
				MemVO memVO = memService.memFindByPrimaryKey(f_no);
				list.add(memVO); // Store the row in the list
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return list;
	}

	@Override
	public List<MemVO> match(String mem_no) {
		List<MemVO> list = new ArrayList<MemVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_MATCH);
            pstmt.setString(1, mem_no);
			pstmt.setString(2, mem_no);
			pstmt.setString(3, mem_no);
			pstmt.setString(4, mem_no);
			pstmt.setString(5, mem_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String mem_name=rs.getString(1);
				String mem_sex=rs.getString(2);
				String mem_intro=rs.getString(3);
				MemVO memVO=new MemVO(mem_name,mem_sex,mem_intro);
				list.add(memVO); 
			}
			return list;
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return list;
	}

	
	

	

	

	

	
}