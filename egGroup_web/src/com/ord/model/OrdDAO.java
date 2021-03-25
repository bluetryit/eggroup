package com.ord.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.ord_details.model.Ord_detailsDAO;
import com.ord_details.model.Ord_detailsVO;


public class OrdDAO implements OrdDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	// jndi不能使用main測試 要用jsp servlet 待v c完成後解鎖********
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	// vc完成後使用datasource

//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA101G6";
//	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO ORD (ORD_NO,ORD_FEA_NO,ORD_MEMNO,ORD_RESNO,ORD_PRICE,ORD_DATE,ORD_STATUS,ORD_TYPE)\r\n" + 
			"VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(ord_seq.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ORD ORDER BY ORD_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM ORD WHERE ORD_NO = ?";
	private static final String GET_DETAILS_BYORDNO_STMT = "SELECT * FROM ORD_DETAILS WHERE DET_ORDNO = ?";
	private static final String UPDATE = "UPDATE ORD set ORD_FEA_NO=?,ORD_MEMNO=?,ORD_RESNO=?,ORD_PRICE=?,ORD_DATE=?,ORD_STATUS=?,ORD_TYPE=? WHERE ORD_NO = ?";

	@Override
	public void insert(OrdVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ordVO.getOrd_fea_no());
			pstmt.setString(2, ordVO.getOrd_memno());
			pstmt.setString(3, ordVO.getOrd_resno());
			pstmt.setInt(4, ordVO.getOrd_price());
			pstmt.setTimestamp(5, ordVO.getOrd_date());
			pstmt.setString(6, ordVO.getOrd_status());
			pstmt.setString(7, ordVO.getOrd_type());


			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(OrdVO ordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, ordVO.getOrd_fea_no());
			pstmt.setString(2, ordVO.getOrd_memno());
			pstmt.setString(3, ordVO.getOrd_resno());
			pstmt.setInt(4, ordVO.getOrd_price());
			pstmt.setTimestamp(5, ordVO.getOrd_date());
			pstmt.setString(6, ordVO.getOrd_status());
			pstmt.setString(7, ordVO.getOrd_type());
			pstmt.setString(8, ordVO.getOrd_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public OrdVO findByPrimaryKey(String ord_no){
		OrdVO ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ord_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ordVO 也稱為 Domain objects
				ordVO = new OrdVO();

				ordVO.setOrd_no(rs.getString("ord_no"));
				ordVO.setOrd_fea_no(rs.getString("ord_fea_no"));
				ordVO.setOrd_memno(rs.getString("ord_memno"));
				ordVO.setOrd_resno(rs.getString("ord_resno"));
				ordVO.setOrd_price(rs.getInt("ord_price"));
				ordVO.setOrd_date(rs.getTimestamp("ord_date"));
				ordVO.setOrd_status(rs.getString("ord_status"));
				ordVO.setOrd_type(rs.getString("ord_type"));
				
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return ordVO;
	}

	@Override
	public List<OrdVO> getAll(){
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ordVO 也稱為 Domain objects

				ordVO = new OrdVO();

				ordVO.setOrd_no(rs.getString("ord_no"));
				ordVO.setOrd_fea_no(rs.getString("ord_fea_no"));
				ordVO.setOrd_memno(rs.getString("ord_memno"));
				ordVO.setOrd_resno(rs.getString("ord_resno"));
				ordVO.setOrd_price(rs.getInt("ord_price"));
				ordVO.setOrd_date(rs.getTimestamp("ord_date"));
				ordVO.setOrd_status(rs.getString("ord_status"));
				ordVO.setOrd_type(rs.getString("ord_type"));

				list.add(ordVO);// Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public Set<Ord_detailsVO> getOrdDetByOrdno(String ord_no) {
		Set<Ord_detailsVO> set = new HashSet<Ord_detailsVO>();
		Ord_detailsVO ord_detailsVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_DETAILS_BYORDNO_STMT);
//			pstmt.setString(1, ord_no);
//			rs = pstmt.executeQuery();
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_DETAILS_BYORDNO_STMT);
			pstmt.setString(1, ord_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				ord_detailsVO = new Ord_detailsVO();
				
				ord_detailsVO.setDet_ordno(rs.getString("det_ordno"));
				ord_detailsVO.setDet_fono(rs.getString("det_fono"));
				ord_detailsVO.setDet_price(rs.getInt("det_price"));
				ord_detailsVO.setDet_quantity(rs.getInt("det_quantity"));
				
				set.add(ord_detailsVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}

	@Override
	public void insertWithDetails(OrdVO ordVO, List<Ord_detailsVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單
			String cols[] = {"ord_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, ordVO.getOrd_fea_no());
			pstmt.setString(2, ordVO.getOrd_memno());
			pstmt.setString(3, ordVO.getOrd_resno());
			pstmt.setInt(4, ordVO.getOrd_price());
			pstmt.setTimestamp(5, ordVO.getOrd_date());
			pstmt.setString(6, ordVO.getOrd_status());
			pstmt.setString(7, ordVO.getOrd_type());
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_ord_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_ord_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_ord_no +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			ordVO.setOrd_no(next_ord_no);//將綁出來的主鍵 set到VO 方便秀資料
			// 再同時新增員工
			Ord_detailsDAO dao = new Ord_detailsDAO();
			System.out.println("list.size()-A="+list.size());
			for (Ord_detailsVO aDet : list) {
				aDet.setDet_ordno(next_ord_no);
				dao.insert2(aDet,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_ord_no + "時,共有訂單明細" + list.size()
					+ "筆同時被新增");
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-ord");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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

}
