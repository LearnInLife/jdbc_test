package cn.lzj.test.jdbcpool.datasource.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	
}
