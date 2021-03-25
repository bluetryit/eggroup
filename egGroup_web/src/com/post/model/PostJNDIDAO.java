package com.post.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PostJNDIDAO implements PostDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
			
		}
	}
	
	private static final String INSERT_STMT = 
			"Insert into post (POST_NO, POST_MEMNO, POST_RES_NO, POST_TEXT, POST_IMG, POST_TIME, POST_RESPON, POST_RATE, POST_STATUS)" 
					+"values ('PO'||LPAD(to_char(post_seq.NEXTVAL), 6, '0'), ? , ? , ? , ? , ? , ? , ? , ?)";
	private static final String GET_ALL = 
			"SELECT * FROM post order by post_no";
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
			con = ds.getConnection();
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
	public void update(PostVO PostVO) {
		
		
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, post_no);

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
	public PostVO findByPrimaryKey(String post_no) {
		// TODO Auto-generated method stub
		PostVO PostVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, post_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo = Domain objects
				PostVO = new PostVO();
				
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				PostVO = new PostVO();
				
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

			// Handle any driver errors
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
		return list;
	}


}
