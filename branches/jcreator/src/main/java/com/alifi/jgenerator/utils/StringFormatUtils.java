/**
 * Project: webXValidate
 * 
 * File Created at 2011-12-23
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
package com.alifi.jgenerator.utils;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串格式化
 * 
 * @author bangis.wangdf
 */
public class StringFormatUtils {
	private MessageFormat mf;

	public StringFormatUtils(String pattern) {
		mf = new MessageFormat(pattern);
	}

	public String format(Object... args) {
		return mf.format(args);
	}

	public static String formats(String pattern, Object... args) {
		return MessageFormat.format(pattern, args);
	}

	/**
	 * 驼峰法
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String hump(String str) {
		if (StringUtils.isNotEmpty(str)) {
			String s = str.replaceAll("_", " ");
			s = StringUtils.capitaliseAllWords(str);
			return s.replaceAll(" ", "");
		}
		return null;
	}
	/**
	 * 驼峰法 去前缀
	 * 
	 * @param str
	 * @return
	 */
	public static String humpCPix(String pix,String str) {
		if (StringUtils.isNotEmpty(str)) {
			String strs = str.toLowerCase();
			String pixx = pix.toLowerCase();
			return hump( strs.replaceFirst(pixx, "").replaceAll("_", " "));
		}
		return null;
	}

	public static void main(String[] args) {

		System.out.println(humpCPix("","WWW_WWW_WWW_WWW_S"));

	}

}
