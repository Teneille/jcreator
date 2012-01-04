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

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author bangis.wangdf
 *
 */
public class SpringServiceFactory {
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
            ctx = new ClassPathXmlApplicationContext((String[]) configPath.toArray(new String[configPath.size()]));
        }
    }

    public void destory() {
        if (ctx != null) {
            ctx.close();
        }
    }
}
