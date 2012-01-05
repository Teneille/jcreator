/**
 * Project: jgenerator
 * 
 * File Created at 2011-12-20
 * $Id$
 * 
 * Copyright 2008 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alifi.jgenerator.spring;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alifi.jgenerator.common.utils.DataSourceUtil;
import com.alifi.jgenerator.common.utils.DateSourceMap;

/**
 * @author bangis.wangdf
 * 
 */
public class SpringServiceFactory {
	private static ClassPathXmlApplicationContext ctx = null;

	public static Object getService(String serviceName) {
		return ctx.getBean(serviceName);
	}

	private List<String> configPath;

	public void setConfigPath(List<String> configPath) {
		this.configPath = configPath;
	}

	public synchronized void init() {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext((String[]) configPath
					.toArray(new String[configPath.size()]));
		}
	}

	public void destory() {
		if (ctx != null) {
			ctx.close();
		}
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"META-INF/SpringContext.xml");

		for (DateSourceMap m : DataSourceUtil.dataSourceMaps) {

			DataSource ds = m.getDataSource();
			try {
				DatabaseMetaData dmd = ds.getConnection().getMetaData();
				ResultSet rs = dmd.getTables("", "", null, null);
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					System.out.println("-----------------------");
					System.out.println(rs.getString("TABLE_CAT"));
					System.out.println(rs.getString("TABLE_SCHEM"));
					System.out.println(rs.getString("TABLE_NAME"));
					System.out.println(rs.getString("TABLE_TYPE"));
					System.out.println(rs.getString("REMARKS"));
				}

				//int numberOfColumns = rsmd.getColumnCount();
				// for (int i = 1; i <= numberOfColumns; i++)
				// System.out.println(rsmd.getColumnName(i));
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println(m.toString());
		}
	}
}
