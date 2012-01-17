package com.alifi.jgenerator.freemarker;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import freemarker.cache.TemplateLoader;

public class StringTemplateLoader implements TemplateLoader {

	public void closeTemplateSource(Object templateSource) throws IOException {
		((StringReader) templateSource).close();
	}

	public Object findTemplateSource(String templateSource) throws IOException {
//		Locale locale = Locale.getDefault();
//		if(templateSource.endsWith(locale.toString())){
//			return new StringReader(templateSource.substring(0, templateSource.length()-locale.toString().length()-1));
//		}
		return new StringReader(templateSource);
	}

	public long getLastModified(Object templateSource) {
		return 0;
	}

	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		return (StringReader) templateSource;
	}

}