package com.administrator.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdministratorDAO implements AdministratorDAO_interface 
{
	String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:XE";
    String userid = "DA101G6";
    String passwd = "123456";

    private static final String INSERT_STMT = "INSERT INTO administrator  (ADMIN_NO, ADMIN_AC, ADMIN_PASS, ADMIN_NAME)"+
            "VALUES ('RF'||LPAD(to_char(administrator_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE administrator set ADMIN_NO=?, ADMIN_AC=?, ADMIN_PASS=?, ADMIN_NAME=? where ADMIN_NO = ?";
    
    private static final String DELETE = "DELETE FROM administrator where ADMIN_NO = ?";

    private static final String GET_ALL_STMT = "SELECT * FROM administrator order by ADMIN_NO";
    private static final String GET_ONE_STMT = "SELECT * FROM administrator where ADMIN_NO = ?";

    private static final String GET_ONE_STMT_BT_AC = "SELECT * FROM administrator where ADMIN_AC = ?";
	@Override
	public void insert(AdministratorVO administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, administratorVO.getAdmin_no());
			pstmt.setString(2, administratorVO.getAdmin_ac());
			pstmt.setString(3, administratorVO.getAdmin_pass());
			pstmt.setString(4, administratorVO.getAdmin_name());
			pstmt.executeUpdate();

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

	}

	@Override
	public void update(AdministratorVO administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, administratorVO.getAdmin_no());
			pstmt.setString(2, administratorVO.getAdmin_ac());
			pstmt.setString(3, administratorVO.getAdmin_pass());
			pstmt.setString(4, administratorVO.getAdmin_name());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	}

	@Override
	public void delete(String admin_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,admin_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

	}
	@Override
	public AdministratorVO findByPrimaryKey(String admin_no) {

		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, admin_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				administratorVO = new AdministratorVO();
				administratorVO.setAdmin_no(rs.getString("admin_no"));
				administratorVO.setAdmin_ac(rs.getString("admin_ac"));
				administratorVO.setAdmin_pass(rs.getString("admin_pass"));
				administratorVO.setAdmin_name(rs.getString("admin_name"));
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
		return administratorVO;
	}

	@Override
	public List<AdministratorVO> getAll() {
		List<AdministratorVO> list = new ArrayList<AdministratorVO>();
		AdministratorVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				AdministratorVO administratorVO = new AdministratorVO();
				administratorVO.setAdmin_no(rs.getString("admin_no"));
				administratorVO.setAdmin_ac(rs.getString("admin_ac"));
				administratorVO.setAdmin_pass(rs.getString("admin_pass"));
				administratorVO.setAdmin_name(rs.getString("admin_name"));
				list.add(empVO); // Store the row in the list
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
    public AdministratorVO findByPrimaryKeyByAc(String admin_ac) {

        AdministratorVO administratorVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT_BT_AC);

            pstmt.setString(1, admin_ac);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo �]�٬� Domain objects
                administratorVO = new AdministratorVO();
                administratorVO.setAdmin_no(rs.getString("admin_no"));
                administratorVO.setAdmin_ac(rs.getString("admin_ac"));
                administratorVO.setAdmin_pass(rs.getString("admin_pass"));
                administratorVO.setAdmin_name(rs.getString("admin_name"));
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
        return administratorVO;
    }
}