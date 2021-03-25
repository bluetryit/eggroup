package com.post.model;

import java.sql.*;
import java.util.*;


public class PostDAO implements PostDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G6";
	String passwd = "123456";
	

	
	private static final String INSERT_STMT = 
			"Insert into post (POST_NO, POST_MEMNO, POST_RES_NO, POST_TEXT, POST_IMG, POST_TIME, POST_RESPON, POST_RATE, POST_STATUS)" 
					+"values ('PO'||LPAD(to_char(post_seq.NEXTVAL), 6, '0'), ? , ? , ? , ? , ? , ? , ? , ?)";
	private static final String GET_ALL = 
			"SELECT * FROM post order by post_time desc";
	private static final String GET_ONE = 
			"SELECT * FROM post where post_no = ?";
	private static final String DELETE = 
			"DELETE	 FROM post where post_no = ?";
	private static final String UPDATE = 
			"UPDATE post set  POST_MEMNO=?, POST_RES_NO=?, POST_TEXT=?, POST_IMG=?, POST_TIME=?, POST_RESPON=?,POST_RATE=?, POST_STATUS=? where POST_NO = ?";
	@Override
	public void insert(PostVO PostVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, PostVO.getPost_memno());
			pstmt.setString(2, PostVO.getPost_res_no());
			pstmt.setString(3, PostVO.getPost_text());
			pstmt.setBytes(4, PostVO.getPost_img());
			pstmt.setTimestamp(5, PostVO.getPost_time());
			pstmt.setString(6, PostVO.getPost_respon());
			pstmt.setInt(7, PostVO.getPost_rate());
			pstmt.setString(8, PostVO.getPost_status());
			
			
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
	public void update(PostVO PostVO) {
		
		
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(9, PostVO.getPost_no());
			pstmt.setString(1, PostVO.getPost_memno());
			pstmt.setString(2, PostVO.getPost_res_no());
			pstmt.setString(3, PostVO.getPost_text());
			pstmt.setBytes(4, PostVO.getPost_img());
			pstmt.setTimestamp(5, PostVO.getPost_time());
			pstmt.setString(6, PostVO.getPost_respon());
			pstmt.setInt(7, PostVO.getPost_rate());
			pstmt.setString(8, PostVO.getPost_status());
			
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
	public void delete(String post_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, post_no);

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
	public PostVO findByPrimaryKey(String post_no) {
		// TODO Auto-generated method stub
		PostVO PostVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, post_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				PostVO = new PostVO();
				
				PostVO.setPost_no(rs.getString("post_no"));
				PostVO.setPost_memno(rs.getString("post_memno"));
				PostVO.setPost_res_no(rs.getString("post_res_no"));
				PostVO.setPost_text(rs.getString("post_text"));
				PostVO.setPost_img(rs.getBytes("post_img"));
				PostVO.setPost_time(rs.getTimestamp("post_time"));
				PostVO.setPost_respon(rs.getString("post_respon"));
				PostVO.setPost_rate(rs.getInt("post_rate"));
				PostVO.setPost_status(rs.getString("post_status"));

			
				
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
		return PostVO;
	}
	@Override
	public List<PostVO> getAll() {
		// TODO Auto-generated method stub
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO PostVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				PostVO = new PostVO();
				PostVO.setPost_no(rs.getString("post_no"));
				PostVO.setPost_memno(rs.getString("post_memno"));
				PostVO.setPost_res_no(rs.getString("post_res_no"));
				PostVO.setPost_text(rs.getString("post_text"));
				PostVO.setPost_img(rs.getBytes("post_img"));
				PostVO.setPost_time(rs.getTimestamp("post_time"));
				PostVO.setPost_respon(rs.getString("post_respon"));
				PostVO.setPost_rate(rs.getInt("post_rate"));
				PostVO.setPost_status(rs.getString("post_status"));
				list.add(PostVO); // Store the row in the list
			
				
			}

			
		} catch (SQLException | ClassNotFoundException se) {
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
//		Timestamp ts = new Timestamp(System.currentTimeMillis());  
//		
//		PostDAO dao = new PostDAO();
		
		
//		//新增
//		PostVO PostVO1 = new PostVO();
//		PostVO1.setPost_memno("ME000005");
//		PostVO1.setPost_res_no("RS000005");
//		PostVO1.setPost_text("123132132132");
//		PostVO1.setPost_img(null);
//		PostVO1.setPost_time(ts);
//		PostVO1.setPost_respon("5252525525");
//		PostVO1.setPost_rate(2);
//		PostVO1.setPost_status("post1");
//		dao.insert(PostVO1);
		
		// 修改
//		PostVO PostVO2 = new PostVO();
//		PostVO2.setPost_no("PO000005");
//		PostVO2.setPost_memno("ME000005");
//		PostVO2.setPost_res_no("RS000005");
//		PostVO2.setPost_text("DASDASDASDADS");
//		PostVO2.setPost_img(null);
//		PostVO2.setPost_time(ts);
//		PostVO2.setPost_respon("ASDASDADSJNOIJDOI");
//		PostVO2.setPost_rate(2);
//		PostVO2.setPost_status("post1");
//	
//		dao.update(PostVO2);

		//刪除
//		dao.delete("PO000010");
		
		
		// 查詢
//		PostVO PostVO3 = dao.findByPrimaryKey("PO000002");
//		System.out.print(PostVO3.getPost_memno() + ",");
//		System.out.print(PostVO3.getPost_res_no() + ",");
//		System.out.print(PostVO3.getPost_text() + ",");
//		System.out.print(PostVO3.getPost_img() + ",");
//		System.out.print(PostVO3.getPost_time() + ",");
//		System.out.print(PostVO3.getPost_respon() + ",");
//		System.out.println(PostVO3.getPost_rate());
//		System.out.println(PostVO3.getPost_status());
//		System.out.println("---------------------");

		//查詢
//		List<PostVO> list = dao.getAll();
//		for (PostVO aPos : list) {
//		System.out.print(aPos.getPost_memno() + ",");
//		System.out.print(aPos.getPost_res_no() + ",");
//		System.out.print(aPos.getPost_text() + ",");
//		System.out.print(aPos.getPost_img() + ",");
//		System.out.print(aPos.getPost_time() + ",");
//		System.out.print(aPos.getPost_respon() + ",");
//		System.out.print(aPos.getPost_rate() + ",");
//		System.out.print(aPos.getPost_status() + ",");
//
//		System.out.println();
//		}
		
//	}
}

