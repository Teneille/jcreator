package com.alifi.jgenerator.freemarker;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.alifi.jgenerator.exception.TemplateNotFondException;

import freemarker.cache.TemplateLoader;

public class FileTemplateLoader implements TemplateLoader {
	private String basePath;
	private long lastModified;

	public FileTemplateLoader() {

	}

	/**
	 * 指定文件跟路径
	 * 
	 * @param basePath
	 */
	public FileTemplateLoader(String basePath) {
		if(basePath==null){
			this.basePath = "";
		}else{
			this.basePath = basePath;
		}
	}

	public void closeTemplateSource(Object templateSource) throws IOException {
		((FileReader) templateSource).close();
	}

	public Object findTemplateSource(String fileName) throws IOException {
		String filePath = this.basePath + File.separator + fileName;
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			lastModified = file.lastModified();
			return new FileReader(file);
		} else {
			throw new TemplateNotFondException(filePath);
		}
	}

	public long getLastModified(Object templateSource) {
		return lastModified;
	}

	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		return (FileReader) templateSource;
	}

}
