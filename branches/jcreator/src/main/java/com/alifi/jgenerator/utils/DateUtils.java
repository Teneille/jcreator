package com.alifi.jgenerator.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;

import com.alibaba.common.logging.LoggerFactory;

public final class DateUtils extends org.apache.commons.lang.time.DateUtils{
	private static Log logger = LoggerFactory.getLogger(DateUtils.class);
	public static final String DEFAULT_STYLE = "yyyy-MM-dd";

	public static Date toDate(String data, String style) {
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		try {
			return sdf.parse(data);
		} catch (ParseException e) {
			logger.warn("date format failed Date:" + data + " style:" + style,
					e);
		}
		return null;
	}

	public static boolean isDate(String data, String style) {
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		try {
			sdf.parse(data);
			return true;
		} catch (ParseException e) {
			logger.warn("date format failed Date:" + data + " style:" + style,
					e);
		}
		return false;
	}

}
