package cn.lzj.test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;



public class QueryAll {
//	create table tbl_user(
//		     cid int primary key auto_increment,
//		     cname varchar(32) not null,
//		     password varchar(100) not null
//		     )
	@Test
	public void testQueryAll() {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//数据库连接
			String url = "jdbc:mysql://127.0.0.1:3306/java?userUnicode=true&characterEncoding=utf8";
			String user = "root";
			String pwd = "123456";
			conn = DriverManager.getConnection(url,user,pwd);
			statement = conn.createStatement();
			String sql = "select * from tbl_user";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				System.out.println("username:"+rs.getString(2)+"---password:"+rs.getString(3));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			if (statement != null) {
				try {
					statement.close();
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
}
