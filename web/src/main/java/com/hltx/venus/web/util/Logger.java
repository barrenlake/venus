package com.hltx.venus.web.util;

/**
 * 用于记录日志文件
 * @fileName Logger.java
 * @author Water
 * @version 1.0
 */
public class Logger {
	private org.apache.log4j.Logger loggerInfo = null;
	private org.apache.log4j.Logger loggerError =null;
	private String className = null;
	private Logger(@SuppressWarnings("rawtypes") Class clazz){
		className = clazz.getSimpleName()+" : ";
	}
	
	/**
	 * 获得Logger实例;
	 * @param clazz  设用此方法的类的Class;
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Logger getInstance(Class clazz){
		return new Logger(clazz);	
	}
	/**
	 * info级别的信息;
	 * @param message
	 */
	public void info(Object message){
		if(loggerInfo==null){
			loggerInfo = org.apache.log4j.Logger.getLogger("infoLog");
		}
		loggerInfo.info(className+message);
	}
	
	/**
	 * error级别的信息;
	 * @param message
	 */
	public void error(Object message){
		if(loggerError==null){
			loggerError = org.apache.log4j.Logger.getLogger("errorLog");
		}
		loggerError.error(className+message);
	}
}
