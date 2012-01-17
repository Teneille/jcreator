package com.alifi.jgenerator.exception;

public class TemplateNotFondException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1387544932383669400L;

	public TemplateNotFondException(String message, Throwable cause) {
		super(message + "模板没有找到", cause);
	}

	public TemplateNotFondException() {
		super("模板没有找到");
	}

	public TemplateNotFondException(String fileName) {
		super(fileName + " 模板没有找到");
	}

}
