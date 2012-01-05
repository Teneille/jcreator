package com.alifi.jgenerator.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

public class DataSourceUtil {
	public final static List<DateSourceMap> dataSourceMaps = new ArrayList<DateSourceMap>();

	public DataSourceUtil(String describe, DataSource dataSource) {
		DateSourceMap dateSourceMap = new DateSourceMap();
		dateSourceMap.setDataSource(dataSource);
		dateSourceMap.setDescribe(describe);
		dateSourceMap.setKey(UUID.randomUUID().toString());
		DataSourceUtil.dataSourceMaps.add(dateSourceMap);
	}

}
