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
package com.alifi.jgenerator.common.utils;

import java.text.MessageFormat;

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

}
