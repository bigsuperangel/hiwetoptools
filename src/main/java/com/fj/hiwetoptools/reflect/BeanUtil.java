package com.fj.hiwetoptools.reflect;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 操作bean 与map之间的转换
 * @author sshss
 */
public class BeanUtil {
	private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);
	/**
	 * 从对象中取出属性
	 * @param object
	 * @param needFileds
	 * @return
	 */
	public static Map<String, Object> getMapFromBean(Object object,
			String[] needFileds) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String str : needFileds) {
			try {
				map.put(str, PropertyUtils.getProperty(object, str));
			} catch (IllegalAccessException e) {
				log.error("beanUtil getMapFromBean:"+e);
			} catch (InvocationTargetException e) {
				log.error("beanUtil getMapFromBean:"+e);
			} catch (NoSuchMethodException e) {
				log.error("beanUtil getMapFromBean:"+e);
			}
		}
		return map;
	}

	/**
	 * 从BAEN中取出所需的属性
	 * @param list
	 * @param needFileds
	 * @return
	 */
	public static List<Map<String, Object>> getListFromBean(List<?> list,String[] needFields){
		List<?> list1 = list;
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		for (Object object : list1) {
			Map<String, Object> map = getMapFromBean(object,needFields);
			resultList.add(map);
		}
		return resultList;
	}

	/**
	 * map转bean
	 * @param bean
	 * @param map
	 * @return
	 */
	public static<T> T getBeanFromMap(T bean ,Map<String, ?> map){
		try {
			BeanUtils.populate(bean, map);
		} catch (IllegalAccessException e) {
			log.error("beanUtil getBeanFromMap:"+e);
		} catch (InvocationTargetException e) {
			log.error("beanUtil getBeanFromMap:"+e);
		}
		return bean;
	}

}
