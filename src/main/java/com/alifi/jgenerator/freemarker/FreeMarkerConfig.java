package com.alifi.jgenerator.freemarker;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerConfig {
	private Configuration configuration;
	private Properties p;

	private void init() {
		configuration = new Configuration();
		InputStream is = ClassLoader
				.getSystemResourceAsStream("freemarker.properties");
		p = new Properties();
		try {
			if (is != null) {
				p.load(is);
				configuration.setSettings(p);
			}
		} catch (Exception e) {
		}
	}

	/**
	 *使用 property文件中template_base_path 设置 如果未设置则取相对路径
	 * 
	 * @param basePath
	 */
	public FreeMarkerConfig() {
		init();
		if (p != null && p.get("template_base_path") != null) {
			configuration.setTemplateLoader(new FileTemplateLoader(p
					.getProperty("template_base_path")));
		}
	}

	/**
	 * 如果basePath 为null或者为"" 时使用 property文件中template_base_path 设置 如果都未设置则取相对路径
	 * 
	 * @param basePath
	 */
	public FreeMarkerConfig(String basePath) {
		init();
		if (StringUtils.isNotEmpty(basePath)) {
			configuration.setTemplateLoader(new FileTemplateLoader(basePath));
		} else if (p != null && p.get("template_base_path") != null) {
			configuration.setTemplateLoader(new FileTemplateLoader(p
					.getProperty("template_base_path")));
		}
	}

	public FreeMarkerConfig(TemplateLoader lodaer) {
		init();
		configuration.setTemplateLoader(lodaer);
	}

	public StringWriter process(String template, Map<String, ?> rootMap) {
		Template t;
		StringWriter out = new StringWriter();
		try {
			t = configuration.getTemplate(template);
			t.process(rootMap, out);
		} catch (IOException e1) {
		} catch (TemplateException e) {
		}
		return out;
	}

}
