package cn.lzj.test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestLogin {

	@Test
	public void testLogin() {
		try {
			login("xiaoming", "123");
			login1("xiaoming", "1233");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param username
	 * @param pwd
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void login(String username,String pwd) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","123456");
			stmt = conn.createStatement();
			String sql = "select * from tbl_user where cname='" + username +"' and password='"+pwd+"'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println("恭喜，"+username + "登录成功！");
				System.out.println(sql);
			} else {
				System.out.println("账号或密码错误！");
			}
		} finally {
			// TODO: handle finally clause
			close(conn, stmt, rs);
		}
		
	}
	
	public void login1(String username,String pwd) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","123456");
			String sql = "select * from tbl_user where cname=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("恭喜，"+username + "登录成功！");
				System.out.println(sql);
			} else {
				System.out.println("账号或密码错误！");
			}
		} finally {
			// TODO: handle finally clause
			close(conn, pstmt, rs);
		}
		
	}
	
	public void close(Connection conn,Statement stmt,ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
