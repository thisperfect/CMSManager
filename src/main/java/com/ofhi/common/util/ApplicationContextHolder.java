package com.ofhi.common.util;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean  {

	
	private static ApplicationContext applicationContext = null;
	private ApplicationContextHolder() {}
	private static Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);

	
	@Override
	public void destroy() throws Exception {
		clearHolder();
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		if (applicationContext != null) {
			logger.warn("ApplicationContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + applicationContext);
		}
		applicationContext = arg0;
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		if (StringUtils.isBlank(name)) return null;
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}
	public static <T> T getBean(String name, Class<T> requiredType)
			throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 清除ApplicationContextHolder中的ApplicationContext为Null.
	 */
	public static void clearHolder() {
		logger.debug("清除ApplicationContextHolder中的ApplicationContext:" + applicationContext);
		applicationContext = null;
	}
	

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		Validate.validState(applicationContext != null, "applicaitonContext属性未注入, 请在applicationContext.xml中定义ApplicationContextHolder.");
	}
	
	public static ServletContext getServletContext() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
		return  webApplicationContext.getServletContext();  
	}

}
