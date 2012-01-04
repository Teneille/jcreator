package com.alifi.jgenerator.jconfig.screen;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.jelly.TagLibrary;

import com.alibaba.service.pipeline.Pipeline;
import com.alibaba.service.pipeline.PipelineContext;
import com.alibaba.service.pipeline.PipelineException;
import com.alibaba.service.pipeline.PipelineInitializationException;
import com.alibaba.service.pipeline.ValveForward;
import com.alibaba.turbine.pipeline.PerformActionValve;
import com.alibaba.turbine.service.rundata.RunData;
import com.alibaba.webx.WebxComponent;

public class SetAlbumEffects extends PerformActionValve{

	@Override
	public void init() throws PipelineInitializationException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	public ValveForward invoke(RunData arg0) throws PipelineException {
		// TODO Auto-generated method stub
		return super.invoke(arg0);
	}

	@Override
	public void setActionParam(String actionParam) {
		// TODO Auto-generated method stub
		super.setActionParam(actionParam);
	}

	@Override
	public ValveForward invoke(PipelineContext arg0) throws PipelineException {
		// TODO Auto-generated method stub
		return super.invoke(arg0);
	}

	@Override
	public WebxComponent getWebxComponent() {
		// TODO Auto-generated method stub
		return super.getWebxComponent();
	}

	@Override
	public Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return super.getConfiguration();
	}

	@Override
	public TagLibrary getCustomizedTagLibrary() {
		// TODO Auto-generated method stub
		return super.getCustomizedTagLibrary();
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return super.getLabel();
	}

	@Override
	public Pipeline getPipeline() {
		// TODO Auto-generated method stub
		return super.getPipeline();
	}

	@Override
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		super.setLabel(label);
	}

	@Override
	public void setPipeline(Pipeline pipeline) {
		// TODO Auto-generated method stub
		super.setPipeline(pipeline);
	}
	
}
