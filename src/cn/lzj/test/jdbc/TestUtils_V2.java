package cn.lzj.test.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;


public class TestUtils_V2 {

	@Test
	public void testUpdateById() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCUtils_V2.getConnection();
			String sql = "update tbl_user set password=? where cid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "aaa");
			pstmt.setInt(2, 1);
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("更新成功");
			} else {
				System.out.println("更新失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils_V2.realease(conn, pstmt, null);
		}
	}
	
	@Test
	public void testDeleteByname() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JDBCUtils_V2.getConnection();
			String sql = "delete from tbl_user where cname=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "jack");
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("删除成功");
			} else {
				System.out.println("删除失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtils_V2.realease(connection, pstmt, null);
		}
	}
	
	@Test
	public void testInsert() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = JDBCUtils_V2.getConnection();
			String sql = "insert into tbl_user values(null,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "boby");
			pstmt.setString(2, "2232");
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("添加成功");
			} else {
				System.out.println("添加失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtils_V2.realease(connection, pstmt, null);
		}
	}
	
	@Test
	public void testFindUserByname() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			connection = JDBCUtils_V2.getConnection();
			String sql = "select * from tbl_user where cname=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "boby");
			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				System.out.println(rSet.getInt(1)+"==="+rSet.getString(2)+"==="+rSet.getString(3));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtils_V2.realease(connection, pstmt, rSet);
		}
	}
}
