package com.alifi.jgenerator.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {
	private Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}

	public DbHelper() {

	}

	public DbHelper(Connection con) {
		this.con = con;
	}

	public String queryForString(String sql) {
		Statement s = null;
		ResultSet rs = null;
		try {
			s = con.createStatement();
			rs = s.executeQuery(sql);
			if (rs.next()) {
				return rs.getString(1);
			}
			rs.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
