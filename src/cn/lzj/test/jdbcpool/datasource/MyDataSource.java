package cn.lzj.test.jdbcpool.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;


import cn.lzj.test.jdbc.JDBCUtils_V2;

public class MyDataSource implements DataSource {

	private static LinkedList<Connection> pool = new LinkedList<>();

	static {
		// 创建5个连接到容器中去
		for (int i = 0; i < 5; i++) {
			Connection connection = JDBCUtils_V2.getConnection();
			MyConnection myConnection = new MyConnection(connection, pool);
			pool.add(myConnection);
		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = null;

		if (pool.size() == 0) {
			// 池子中如果没有，在创建一些
			for (int i = 0; i < 5; i++) {
				connection = JDBCUtils_V2.getConnection();
				MyConnection myConnection = new MyConnection(connection, pool);
				pool.add(myConnection);
			}
		}

		// 从迟滞里面获取一个连接对象
		connection = pool.removeFirst();

		return connection;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
