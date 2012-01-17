package com.alifi.jgenerator.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import com.alifi.jgenerator.common.base.DataSourceMap;
import com.alifi.jgenerator.exception.DataSourceNotFondException;

public class DataSourceUtil {
	private final static Map<String, DataSourceMap> dataSourceMaps = new HashMap<String, DataSourceMap>();
	private final static List<DataSourceMap> dataSourceList = new ArrayList<DataSourceMap>();

	public DataSourceUtil(String describe, DataSource dataSource) {
		DataSourceMap DataSourceMap = new DataSourceMap();
		DataSourceMap.setDataSource(dataSource);
		DataSourceMap.setDescribe(describe);
		DataSourceMap.setKey(UUID.randomUUID().toString());
		DataSourceUtil.dataSourceMaps
				.put(DataSourceMap.getKey(), DataSourceMap);
		dataSourceList.add(DataSourceMap);
	}

	public static DataSourceMap getByKey(String key) {
		return dataSourceMaps.get(key);
	}

	public static DataSourceMap getByDesc(String desc) {
		for (DataSourceMap m : dataSourceMaps.values()) {
			if (m.getDescribe().equalsIgnoreCase(desc)) {
				return m;
			}
		}
		return null;
	}

	public static DataSourceMap getByIndex(int index){
		if(index >= 0 && index < dataSourceList.size()){
			return dataSourceList.get(index);
		}else{
			throw new DataSourceNotFondException();
		}
	}

	public static Collection<DataSourceMap> getDataSourceMap() {
		return dataSourceMaps.values();
	}
}
