package com.alifi.jgenerator.common.utils;

import java.util.ArrayList;
import java.util.List;

public class DataSourceUtil {
	public final static List<DateSourceMap> dataSourceMaps = new ArrayList<DateSourceMap>();

	public void setDateSourceMap(DateSourceMap dateSourceMap) {
		DataSourceUtil.dataSourceMaps.add(dateSourceMap);
	}

}
