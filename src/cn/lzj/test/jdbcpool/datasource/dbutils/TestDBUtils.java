package cn.lzj.test.jdbcpool.datasource.dbutils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.lzj.test.jdbcpool.datasource.utils.DBCPUtils;

/**
 * DBUtils 测试方法
 * 
 * @author meitunmama
 *
 */
public class TestDBUtils {

	@Test
	public void testAddUser() {
		try {
			QueryRunner qr = new QueryRunner(DBCPUtils.getDataSource());
			String sql = "insert into tbl_user values(null,?,?)";
			Object[] params = { "acd", "2132" };
			int rows = qr.update(sql, params);
			if (rows > 0) {
				System.out.println("添加成功");
			} else {
				System.out.println("添加失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有用户
	 */
	@Test
	public void testQueryAll() {

		try {
			QueryRunner qr = new QueryRunner(DBCPUtils.getDataSource());
			String sql = "select * from tbl_user";
			List<User> query = qr.query(sql, new BeanListHandler<User>(User.class));
			for (User user : query) {
				System.out.println(user.getCname() + ":" + user.getPassword());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据id查询
	 */
	@Test
	public void testQueryById() {
		try {
			QueryRunner qr = new QueryRunner(DBCPUtils.getDataSource());
			String sql = "select * from tbl_user where cid=?";
			Object[] params = { 2 };
			User user = qr.query(sql, new BeanHandler<User>(User.class), params);
			if (user == null) {
				System.out.println("查无此人");
			} else {
				System.out.println(user.getCname() + ":" + user.getPassword());
			}
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 查询所有用户的总个数
	 */
	@Test
	public void testQueryCount() {
		try {
			QueryRunner qr = new QueryRunner(DBCPUtils.getDataSource());
			String sql = "select count(*) from tbl_user";
			Long count = (Long) qr.query(sql, new ScalarHandler());
			System.out.println(count);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void testQueryAll_1() {

		try {
			QueryRunner qr = new QueryRunner(DBCPUtils.getDataSource());
			String sql = "select * from tbl_user";
			List<Map<String, Object>> query = qr.query(sql, new MapListHandler());
			for (Map<String, Object> map : query) {
				System.out.println(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryAll_2() {

		try {
			QueryRunner qr = new QueryRunner(DBCPUtils.getDataSource());
			String sql = "select * from tbl_user";
			List<Object> query = qr.query(sql, new ColumnListHandler("cname"));
			for (Object object : query) {
				System.out.println(object);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
