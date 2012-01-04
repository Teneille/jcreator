package com.alifi.jgenerator.common.perform.valve;

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.support.FormTool;
import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.alibaba.fastjson.JSON;

public class PerformJsonValve extends AbstractValve {
	public final static String JSON_KEY = "jsonObj";

	// private static final Log LOG = LogFactory.getLog(ProformJsonValue.class);
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;

	public void invoke(PipelineContext pipelineContext) throws Exception {
		TurbineRunData rundata = getTurbineRunData(request);
		FormTool form = (FormTool) request.getAttribute("form");
		if (rundata instanceof TurbineRunDataInternal) {
			TurbineRunDataInternal rundataInternal = (TurbineRunDataInternal) rundata;
			Context context = rundataInternal.getContext();
			//Object obj = context.get(JSON_KEY);
			form = (FormTool) context.get("form");
			Writer writer = response.getWriter();
			response.setContentType("text/x-json;charset=UTF-8");
			writer.write(JSON.toJSONString(context));
			writer.write(JSON.toJSONString(form));
			writer.flush();
			writer.close();
		}
	}
}
