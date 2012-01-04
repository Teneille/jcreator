package com.alifi.jgenerator.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoTestGetTable {

	/**
	 * @param args
	 */
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

		try {
			DatabaseMetaData dbMetaData = connection.getMetaData();
			ResultSet rs = dbMetaData.getTables(null, null, null, null);
			List tables = new ArrayList();
			while(rs.next()) {
				createTable(connection, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTable(Connection conn, ResultSet rs) throws SQLException {
		String realTableName = null;
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			String schemaName = rs.getString("TABLE_SCHEM") == null ? "" : rs.getString("TABLE_SCHEM");
			realTableName = rs.getString("TABLE_NAME");
			String tableType = rs.getString("TABLE_TYPE");
			String remarks = rs.getString("REMARKS");
			System.out.println("schemaName:"+schemaName+" realTableName:"+realTableName+" tableType:"+tableType+" remarks:"+remarks);
		}catch(SQLException e) {
			throw new RuntimeException("create table object error,tableName:"+realTableName,e);
		}
	}
}
