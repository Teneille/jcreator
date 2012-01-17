package com.alifi.web.config.screen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.support.FormTool;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.dataresolver.Param;

public class GetJson {
	@Autowired
	private HttpServletRequest request;

	public void execute(@Param("name") String name, Context context) {
		// context.put("wdf", groups);
		FormTool form = (FormTool) context.get("form");
		@SuppressWarnings("unused")
		HttpSession session = request.getSession();
		context.put("form", form);
	}
}
