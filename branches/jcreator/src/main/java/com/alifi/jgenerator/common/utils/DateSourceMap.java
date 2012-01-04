package com.alifi.jgenerator.common.utils;

import java.util.HashMap;

import javax.sql.DataSource;

public class DateSourceMap extends HashMap<String, DataSource> {

	private static final long serialVersionUID = 6464164517649533358L;

	public DateSourceMap(String name, DataSource dataSource) {
		this.put(name, dataSource);
	}

}