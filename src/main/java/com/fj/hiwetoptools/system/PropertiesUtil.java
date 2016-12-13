package com.fj.hiwetoptools.system;


import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Properties工具类.
 */
public class PropertiesUtil {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	/**
	 * 配置文件.
	 */
	private Properties prop;
	/**
	 * 输入流.
	 */
	private InputStream is;

	/**
	 * 构造函数.
	 *
	 * @param filename
	 *            以“/”开头，根据工程项目路径读取
	 */
	public PropertiesUtil(String filename) {
		prop = new Properties();
		is = getClass().getResourceAsStream(filename);
		try {
			prop.load(is);
		} catch (IOException e) {
			logger.error("加载文件出错",e);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	/**
	 * 读取属性.
	 *
	 * @param propertyName
	 * @return
	 */
	public String getProperties(String propertyName) {
		return prop.getProperty(propertyName);
	}
	
	public Enumeration<?> propertyNames(){
		return prop.propertyNames();
	}
	
	public Collection<Object> values(){
		return prop.values();
	}
	
	public boolean isEmpty(){
		return prop.isEmpty();
	}
	
}
