/**
 * Project: jgenerator
 * 
 * File Created at 2011-12-21
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
package com.alifi.web.ext;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.FormService;
import com.alibaba.citrus.service.pull.ToolSetFactory;
import com.alibaba.citrus.util.CollectionUtil;

/**
 * @author bangis.wangdf
 */
public class JGrenratorToolFactory implements ToolSetFactory {

	ServletContext servletContext;

	@Autowired
	public void setServletContext(ServletContext servletContext) {
		System.out.println("servletContext"+this.servletContext);
		this.servletContext = servletContext;
	}
	
	@Autowired
	public void setFormService(FormService formService) {
		// JValidateExt jValidate = new JValidateExt(formService);
		// tools.put("jValidate", jValidate);
	}

	private final Map<String, Object> tools;

	public JGrenratorToolFactory() {
		tools = CollectionUtil.createHashMap();
	}

	public boolean isSingleton() {
		return true;
	}

	public Iterable<String> getToolNames() {
		return tools.keySet();
	}

	public Object createTool(String name) throws Exception {
		return tools.get(name);
	}

}
