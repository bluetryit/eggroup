package com.mem.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class MemDAO implements MemDAO_interface {
	
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

	private static final String INSERT = 
			"Insert into mem (MEM_NO, MEM_NAME, MEM_ADRS, MEM_SEX, MEM_BD, MEM_PH, MEM_EMAIL,MEM_POINT, MEM_IMG, MEM_PASS, MEM_AC,MEM_INTRO,MEM_STATUS) values \r\n" + 
			"('ME'||LPAD(to_char(mem_seq.NEXTVAL),6, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL = 
		"SELECT * FROM mem order by mem_no";
	private static final String GET_ONE_PK = 
		"SELECT * FROM mem where mem_no = ?";
	private static final String GET_ONE_AC = 
			"SELECT * FROM mem where mem_ac = ?";
	private static final String DELETE = 
		"DELETE FROM mem where mem_no = ?";
	private static final String UPDATE = 
		"UPDATE mem set  MEM_NAME=?, MEM_ADRS=?, MEM_SEX=?, MEM_BD=?, MEM_PH=?, MEM_EMAIL=?,MEM_POINT=?, MEM_IMG=?, MEM_PASS=?, MEM_AC=?,MEM_INTRO=?,MEM_STATUS=? where mem_no = ?";
	
	@Override
	public void insert(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1,memVO.getMem_name());
			pstmt.setString(2,memVO.getMem_adrs());
			pstmt.setString(3,memVO.getMem_sex());
			pstmt.setDate(4,memVO.getMem_bd());
			pstmt.setString(5,memVO.getMem_ph());
			pstmt.setString(6,memVO.getMem_email());
			pstmt.setInt(7,memVO.getMem_point());
			pstmt.setBytes(8, memVO.getMem_img());
			pstmt.setString(9,memVO.getMem_pass());
			pstmt.setString(10,memVO.getMem_ac());
			pstmt.setString(11,memVO.getMem_intro());
			pstmt.setString(12, memVO.getMem_status());
			
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
	public void update(MemVO memVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,memVO.getMem_name());
			pstmt.setString(2,memVO.getMem_adrs());
			pstmt.setString(3,memVO.getMem_sex());
			pstmt.setDate(4,memVO.getMem_bd());
			pstmt.setString(5,memVO.getMem_ph());
			pstmt.setString(6,memVO.getMem_email());
			pstmt.setInt(7,memVO.getMem_point());
			pstmt.setBytes(8, memVO.getMem_img());
			pstmt.setString(9,memVO.getMem_pass());
			pstmt.setString(10,memVO.getMem_ac());
			pstmt.setString(11,memVO.getMem_intro());
			pstmt.setString(12, memVO.getMem_status());
			pstmt.setString(13, memVO.getMem_no());
			pstmt.executeUpdate();

			// Handle any driver errors
	
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
	public void delete(String mem_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		
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
	public MemVO findByPrimaryKey(String mem_no) {
		// TODO Auto-generated method stub
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PK);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				memVO = new MemVO();
				
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_adrs(rs.getString("mem_adrs"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_bd(rs.getDate("mem_bd"));
				memVO.setMem_ph(rs.getString("mem_ph"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_point(rs.getInt("mem_point"));
				memVO.setMem_img(rs.getBytes("mem_img"));
				memVO.setMem_pass(rs.getString("mem_pass"));
				memVO.setMem_ac(rs.getString("mem_ac"));
				memVO.setMem_intro(rs.getString("mem_intro"));
				memVO.setMem_status(rs.getString("mem_status"));
				memVO.setMem_no(rs.getString("mem_no"));
			}

			// Handle any driver errors
	
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
		return memVO;
	}
	
	@Override
	public MemVO findByAC(String mem_ac) {
		// TODO Auto-generated method stub
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_AC);

			pstmt.setString(1, mem_ac);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				memVO = new MemVO();
				
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_adrs(rs.getString("mem_adrs"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_bd(rs.getDate("mem_bd"));
				memVO.setMem_ph(rs.getString("mem_ph"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_point(rs.getInt("mem_point"));
				memVO.setMem_img(rs.getBytes("mem_img"));
				memVO.setMem_pass(rs.getString("mem_pass"));
				memVO.setMem_ac(rs.getString("mem_ac"));
				memVO.setMem_intro(rs.getString("mem_intro"));
				memVO.setMem_status(rs.getString("mem_status"));
				memVO.setMem_no(rs.getString("mem_no"));
			}

			// Handle any driver errors
	
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		// TODO Auto-generated method stub
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				memVO = new MemVO();
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_adrs(rs.getString("mem_adrs"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_bd(rs.getDate("mem_bd"));
				memVO.setMem_ph(rs.getString("mem_ph"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_point(rs.getInt("mem_point"));
				memVO.setMem_img(rs.getBytes("mem_img"));
				memVO.setMem_pass(rs.getString("mem_pass"));
				memVO.setMem_ac(rs.getString("mem_ac"));
				memVO.setMem_intro(rs.getString("mem_intro"));
				memVO.setMem_status(rs.getString("mem_status"));
				memVO.setMem_no(rs.getString("mem_no"));
				list.add(memVO); // Store the row in the list
			}

			// Handle any driver errors

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
	
	public static void main(String arg[]) {
		MemDAO memdao=new MemDAO();
		MemVO memVO=new MemVO();
		System.out.println("------------------ALL---------------");
		List<MemVO> Listmem=memdao.getAll();
		for(int i=0;i<Listmem.size();i++){
			memVO=Listmem.get(i);
			System.out.println(memVO.getMem_no()+
					memVO.getMem_name()+
					memVO.getMem_adrs()+
					memVO.getMem_bd()+
					memVO.getMem_ph()+
					memVO.getMem_email()+
					memVO.getMem_point()+
					memVO.getMem_img()+
					memVO.getMem_pass()+
					memVO.getMem_ac()+
					memVO.getMem_intro()+
					memVO.getMem_status());
			//塞圖
				memVO.setMem_img(setpic("fuck"));
				memdao.update(memVO);
		}
		System.out.println("------------------ONEIP---------------");
		System.out.println((memVO=memdao.findByPrimaryKey("ME000001")).getMem_email());
		System.out.println("------------------ONEAC---------------");
		System.out.println("AC"+(memVO=memdao.findByPrimaryKey("ME000001")).getMem_email());
		System.out.println("------------------UPDATE---------------");
		memVO.setMem_email("bluetryit@gmail.com");
		memdao.update(memVO);
		System.out.println("------------------INSERT---------------");	
		memVO.setMem_img(setpic("fuck"));
		memdao.insert(memVO);
		
		
		
		
	}
	//外部讀圖
	static public byte[] setpic(String path) {
		if(path=="fuck"){path="image/01.jpg";}
		File file = new File(path);
		int size = (int) file.length();
		byte[] bytes = new byte[size];
		try {
		    BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
		    buf.read(bytes, 0, bytes.length);
		    buf.close();
		} catch (FileNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		return bytes;
	}
	
}
