package com.alifi.jgenerator.common.perform.valve;

import static com.alibaba.citrus.turbine.util.TurbineUtil.getTurbineRunData;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.form.FormService;
import com.alibaba.citrus.service.form.support.FormTool;
import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.alibaba.common.logging.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alifi.jgenerator.webx.ext.JValidateExt;

public class PerformValidateValve extends AbstractValve {
	private static Log logger = LoggerFactory.getLogger(PerformValidateValve.class);
	public final static String JSON_KEY = "jsonObj";

	// private static final Log LOG = LogFactory.getLog(ProformJsonValue.class);
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	private FormService formService;

	@Autowired
	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	/*
	 * url 格式 xxx_0.vjs 其中 xxx 代表form名，0代码group组个数，其中最有一个数字为准 (non-Javadoc)
	 * 
	 * @see
	 * com.alibaba.citrus.service.pipeline.Valve#invoke(com.alibaba.citrus.service
	 * .pipeline.PipelineContext)
	 */
	public void invoke(PipelineContext pipelineContext) throws Exception {
		String[] uris = analysisURL(request.getRequestURI());
		TurbineRunData rundata = getTurbineRunData(request);
		if (rundata instanceof TurbineRunDataInternal && uris != null
				&& uris.length == 2) {
			TurbineRunDataInternal rundataInternal = (TurbineRunDataInternal) rundata;
			Context context = rundataInternal.getContext();
			FormTool formTool = (FormTool) context.get("form");
			Writer writer = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			String val=JValidateExt.getInstance().validate(
					formService, uris, formTool);
			if(logger.isInfoEnabled()){
				logger.info(val);
			}
			System.out.println(val);
			writer.write(val);
			writer.flush();
			writer.close();
		}
	}

	private String[] analysisURL(String uri) {
		int i = uri.lastIndexOf("/") + 1;
		uri = uri.substring(i);
		String[] uris = uri.substring(0, uri.lastIndexOf(".")).split("_");
		return uris;
	}

	public static void main(String[] args) {
		PerformValidateValve x = new PerformValidateValve();
		x.analysisURL("/ww/www_0.vjs");
		x.analysisURL("/www_0.vjs");
		x.analysisURL("www_0.vjs");
	}
}
