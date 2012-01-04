/**
 * Project: jgenerator
 * 
 * File Created at 2011-12-22
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
package com.alifi.jgenerator.webx.ext;

import com.alifi.jgenerator.common.utils.StringFormatUtils;

/**
 * @author bangis.wangdf
 */
public class JSConfig {
    public static void main(String[] args) {

        StringFormatUtils mf = new StringFormatUtils("\"DateValidatorFormat\":\"{0} 不符合格式{1}\"");
        Object[] objs = { "wdf" };
        String result = mf.format(objs);
        JSConfig.fieldValPix.format(new Object[] { "wdf" });
        System.out.println(result);
    }

    /**
     * 验证前缀 "\"{0}\":"
     */
    public final static StringFormatUtils fieldValPix = new StringFormatUtils("\"{0}\":");

    public final static class msg {
        /**
         * (1)required: 必输字段 "required:\"{0} 必填\""
         */
        public final static StringFormatUtils REQUIRED_FORMAT        = new StringFormatUtils(
                                                                             "\"required\":\"{0} 必填\"");
        /**
         * (2)DateValidatorFormat: 日期格式验证 "DateValidatorFormat:\"{0} 不符合格式{1}\""
         */
        public final static StringFormatUtils DATE_VALIDATOR_FORMAT  = new StringFormatUtils(
                                                                             "\"DateValidatorFormat\":\"{0} 不符合格式{1}\"");
        /**
         * 不是一个正确的时间 ",DateValidatorIsDate:\"{0} 不是一个正确的时间\""
         */
        public final static StringFormatUtils DATE_VALIDATOR_IS_DATE = new StringFormatUtils(
                                                                             ",\"DateValidatorIsDate\":\"{0} 不是一个正确的时间\"");
        /**
         * (3)email:true 必须输入正确格式的电子邮件
         */
        public final static StringFormatUtils EMAIL_FORMAT           = new StringFormatUtils(
                                                                             "\"email\":\"{0} 必须输入正确格式的电子邮件\"");
    }

    public static class val {
        /**
         * 日期格式验证 "DateValidatorFormat:\"{0}\""
         */
        public final static StringFormatUtils DATE_VALIDATOR_FORMAT  = new StringFormatUtils(
                                                                             "\"DateValidatorFormat\":\"{0}\"");
        /**
         * 不是一个正确的时间
         */
        public final static String            DATE_VALIDATOR_IS_DATE = ",\"DateValidatorIsDate\":\"true\"";

        /**
         * 最小日期 ",DateValidatorMin:\"{0}\""
         */
        public final static StringFormatUtils DATE_VALIDATOR_MIN     = new StringFormatUtils(
                                                                             ",\"DateValidatorMin\":\"{0}\"");
        /**
         * 最大日期
         */
        public final static StringFormatUtils DATE_VALIDATOR_MAX     = new StringFormatUtils(
                                                                             ",\"DateValidatorMax\":\"{0}\"");
        /**
         * (1)required:true 必输字段
         */
        public final static String            REQUIRED               = "\"required\":\"true\"";

        /**
         * (3)email:true 必须输入正确格式的电子邮件
         */
        public final static String            EMAIL                  = "\"email\":\"true\"";
    }
}
