package com.alifi.jgenerator.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DoTestSql {
	public static void main(String[] args) {
		Map<String, String> source = new HashMap<String, String>();
		source.put("jdbc.driver", "com.mysql.jdbc.Driver");
		source
				.put(
						"jdbc.url",
						"jdbc:mysql://localhost:3306/mysql?useUnicode=true&amp;characterEncoding=UTF-8");
		source.put("jdbc.username", "root");
		source.put("jdbc.password", "12345");
		StaticContext.addDataSource("test", source);
		String dbName = "test";
		Connection connection = null;
		String driver = StaticContext.get(dbName, "jdbc.driver");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("not found jdbc driver class:[" + driver
					+ "]", e);
		}
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(StaticContext.get(
						dbName, "jdbc.url"), StaticContext.get(dbName,
						"jdbc.username"), StaticContext.get(dbName,
						"jdbc.password"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		String sql = "select count(*) as count from (select * from host) as ww";
		doSql(sql,connection);
		sql = "select * from host,jforum_words";
		doSql(sql,connection);
	}

	public static void doSql(String sql,Connection connection){
		try {
			Statement statement = connection.createStatement();
			System.out.println(sql+" "+statement.execute(sql));
			
			ResultSet rs = statement.executeQuery(sql);
		
			ResultSetMetaData rsMetaData = rs.getMetaData();

			int columnCount = rsMetaData.getColumnCount();
			System.out.println(columnCount);
			for(int i=1;i<=columnCount;i++){
				System.out.println(rsMetaData.getColumnClassName(i)+" " +rsMetaData.getColumnName(i)+" "+rsMetaData.getColumnTypeName(i));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
