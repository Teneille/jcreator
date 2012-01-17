package com.alifi.jgenerator.exception;

public class DataSourceNotFondException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1387544932383669400L;

    public DataSourceNotFondException( String message, Throwable cause) {
        super(message, cause);
    }
	public DataSourceNotFondException(){
		super("数据源没有找到");
	}
	

}
