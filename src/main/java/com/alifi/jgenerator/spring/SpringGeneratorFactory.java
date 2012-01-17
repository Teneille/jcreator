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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alifi.jgenerator.common.base.ColumnTypes;
import com.alifi.jgenerator.common.base.DataSourceMap;
import com.alifi.jgenerator.utils.DataSourceUtil;

/**
 * @author bangis.wangdf
 * 
 */
public class SpringGeneratorFactory {
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

	static void p(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"META-INF/SpringContext.xml");

		for (DataSourceMap m : DataSourceUtil.getDataSourceMap()) {

			DataSource ds = m.getDataSource();
			try {
				Connection con = ds.getConnection();
				DatabaseMetaData dmd = con.getMetaData();
				String catalog = con.getCatalog();
				String schemaPattern = dmd.getSchemaTerm();
				System.out.println("Catalog:" + con.getCatalog()
						+ " schemaPattern:" + schemaPattern);
				ResultSet rs = dmd.getTables(catalog, schemaPattern,
						"time_zone_transition", null);

				Statement s = null;
				ResultSet xrs = null;
				try {
					s = con.createStatement();
					xrs = s.executeQuery("select * from time_zone_transition");
					if (xrs.next()) {
						p("xxxxxxxxxxxxxxxxxxxxxxxx:" + xrs.getString(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
				}

				while (rs.next()) {
					p("-----------------------");
					p("TABLE_CAT  :" + rs.getString("TABLE_CAT"));
					p("TABLE_SCHEM:" + rs.getString("TABLE_SCHEM"));
					p("TABLE_NAME :" + rs.getString("TABLE_NAME"));
					p("TABLE_TYPE :" + rs.getString("TABLE_TYPE"));
					p("REMARKS:   " + rs.getString("REMARKS"));

					p("11111111111111111:"+rs.getMetaData().getColumnClassName(1));
					// 得到主键
					ResultSet pkeyRs = dmd.getPrimaryKeys(catalog,
							schemaPattern, rs.getString("TABLE_NAME"));
					while (pkeyRs.next()) {
						p("M-------------M");
						p("------COLUMN_NAME:"
								+ pkeyRs.getString("COLUMN_NAME"));
						p("------KEY_SEQ:" + pkeyRs.getString("KEY_SEQ"));
						p("------PK_NAME:" + pkeyRs.getString("PK_NAME"));
					}
					// 得到列
					ResultSet rss = dmd.getColumns(catalog, schemaPattern, rs
							.getString("TABLE_NAME"), null);
					int cCount = rss.getMetaData().getColumnCount();
					for (int i = 1; i <= cCount; i++) {
						p("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
						p("       getColumnClassName:"
								+ rss.getMetaData().getColumnClassName(i));
						p("       getColumnLabel:"
								+ rss.getMetaData().getColumnLabel(i));
						p("       getColumnName:"
								+ rss.getMetaData().getColumnName(i));
						p("       getColumnTypeName:"
								+ rss.getMetaData().getColumnTypeName(i));
						p("       getColumnType:"
								+ ColumnTypes.getType(rss.getMetaData()
										.getColumnType(i)));
					}
				}
				rs = dmd.getTableTypes();

				while (rs.next()) {
					p("=========================");
					p("TABLE_TYPE: " + rs.getString("TABLE_TYPE"));
				}
				// ResultSetMetaData rsmd = rs.getMetaData();

				// int numberOfColumns = rsmd.getColumnCount();
				// for (int i = 1; i <= numberOfColumns; i++)
				// p(rsmd.getColumnName(i));
			} catch (SQLException e) {
				e.printStackTrace();
			}

			p(m.toString());
		}
	}
}
