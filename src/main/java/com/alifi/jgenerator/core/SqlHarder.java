package com.alifi.jgenerator.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alifi.jgenerator.exception.DataSourceNotFondException;
import com.alifi.jgenerator.utils.DataSourceUtil;

public class SqlHarder {
	private Connection con;
	private String cat;

	public SqlHarder(String key) {
		try {
			con = DataSourceUtil.getByKey(key).getDataSource().getConnection();
		} catch (SQLException e) {
			throw new DataSourceNotFondException();
		}
	}

	public SqlHarder(int index) {
		try {
			con = DataSourceUtil.getByIndex(index).getDataSource()
					.getConnection();
			cat = con.getCatalog();
		} catch (SQLException e) {
			throw new DataSourceNotFondException();
		}
	}

	public String queryForString(String sql) {
		String cSql = "select * from (" + sql + ") a where 1=0";

		Statement s = null;
		ResultSet rs = null;
		try {
			s = con.createStatement();
			rs = s.executeQuery(cSql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			System.out.println(rsm.getTableName(1));
			for (int i = 1; i <= count; i++) {
				System.out.println(rsm.getColumnName(i) + " "
						+ rsm.getColumnClassName(i) + " "
						+ rsm.getColumnTypeName(i) + " "
						+ rsm.getColumnDisplaySize(i) + " "
						+ rsm.getColumnLabel(i) + " " + rsm.getSchemaName(i));
			}

			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					//System.out.print(" "+rsm.getColumnName(i)+": "+rs.getString
					// (rsm.getColumnName(i)));
				}
				System.out.println();
			}
			rs.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	protected void finalize() throws Throwable {
		try {
			this.con.close();
		} catch (Exception e) {
			System.out.println(this.getClass() + " 垃圾回收");
		}
		super.finalize();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"META-INF/SpringContext.xml");
		SqlHarder sh = new SqlHarder(0);
		String sql = "select * from jforum_test ";
		String result = sh.queryForString(sql);
		System.out.println(result);
	}
}
