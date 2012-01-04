package com.alifi.jgenerator.test;

import java.util.HashMap;
import java.util.Map;

public final class StaticContext {

	private static Map<String,Map<String,String>> dataSource = new HashMap<String,Map<String,String>>();
	
	public static void addDataSource(String dbName,Map<String,String> source){
		dataSource.put(dbName, source);
	}
	public static String get(String dbName,String key){
		Map<String,String> data = dataSource.get(dbName);
		if(data==null){
			return null;
		}
		return data.get(key);
	}
}
