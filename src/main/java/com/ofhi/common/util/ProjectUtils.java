package com.ofhi.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


public class ProjectUtils {
	private ProjectUtils() {}
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	private static Logger logger = LoggerFactory.getLogger(ProjectUtils.class);
	private final static Properties properties = loadProperties("/properties/config.properties");
	

	/**
	 * 加载指定的配置文件
	 * @param propertiesPath
	 * @return
	 */
	public static Properties getProperties(String propertiesPath) {
		return loadProperties(propertiesPath);
	}

	/**
	 * 取出Property，但以System的Property优先,取不到返回空字符串.
	 */
	private static String getValue(String key) {
		String systemProperty = System.getProperty(key);
		if (systemProperty != null) {
			return systemProperty;
		}
		if (properties.containsKey(key)) {
			try {
				return new String(properties.getProperty(key).getBytes("ISO8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(),e);
			}
		}
		return "";
	}

	/**
	 * 取出String类型的Property，但以System的Property优先,如果都为Null则抛出异常.
	 */
	public static String getProperty(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return value;
	}

	/**
	 * 取出String类型的Property，但以System的Property优先.如果都为Null则返回Default值.
	 */
	public static String getProperty(String key, String defaultValue) {
		String value = getValue(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * 取出Integer类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
	 */
	public static Integer getInteger(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value);
	}

	/**
	 * 取出Integer类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
	 */
	public static Integer getInteger(String key, Integer defaultValue) {
		String value = getValue(key);
		return value != null ? Integer.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
	 */
	public static Double getDouble(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Double.valueOf(value);
	}

	/**
	 * 取出Double类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
	 */
	public static Double getDouble(String key, Integer defaultValue) {
		String value = getValue(key);
		return value != null ? Double.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Boolean类型的Property，但以System的Property优先.如果都为Null抛出异常,如果内容不是true/
	 * false则返回false.
	 */
	public static Boolean getBoolean(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Boolean.valueOf(value);
	}

	/**
	 * 取出Boolean类型的Property，但以System的Property优先.如果都为Null则返回Default值,如果内容不为true/
	 * false则返回false.
	 */
	public static Boolean getBoolean(String key, boolean defaultValue) {
		String value = getValue(key);
		return value != null ? Boolean.valueOf(value) : defaultValue;
	}

	private static Properties loadPropertie(String resourcePath) {
		Properties pros = new Properties();
		try {
			pros.load(new InputStreamReader(Object.class.getResourceAsStream(resourcePath), "UTF-8"));
		} catch (IOException e) {
			logger.error("初始化配置文件出错", e);
		}
		return pros;
	}

	/**
	 * 载入多个文件, 文件路径使用Spring Resource格式.
	 */
	private static Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();

		for (String location : resourcesPaths) {

			logger.debug("Loading properties file from:{}" , location);

			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				props.load(is);
			} catch (IOException ex) {
				logger.error("初始化配置文件出错", ex);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					return null;
				}
			}
		}
		return props;
	}
}
