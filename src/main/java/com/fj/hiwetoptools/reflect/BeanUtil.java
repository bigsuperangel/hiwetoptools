package com.fj.hiwetoptools.reflect;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fj.hiwetoptools.CollectionUtil;

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
		if(CollectionUtil.isEmpty(list)) return Collections.EMPTY_LIST;
		
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
	
	/**
	 * 从list bean中取出map组装成list map,然后克隆值到list
	 * @param list
	 * @param needFields
	 * @param name
	 * @param value
	 * @return
	 */
	public static List<Map<String, Object>> getListFromBean(List<?> list,String[] needFields,String name,Object value){
		if(CollectionUtil.isEmpty(list)) return Collections.EMPTY_LIST;
		
		List<?> list1 = list;
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		for (Object object : list1) {
			Map<String, Object> map = getMapFromBean(object,needFields);
			try {
				BeanUtils.copyProperty(map, name, value);
			} catch (IllegalAccessException e) {
				log.error("beanUtil getListFromBean:"+e);
			} catch (InvocationTargetException e) {
				log.error("beanUtil getListFromBean:"+e);
			}
			resultList.add(map);
		}
		return resultList;
	}
	
	/**
	 * 从list entity中copy元素到list map ,并复制额外的值到list map
	 * @param list
	 * @param needFields 所需要的元素
	 * @param copyMap
	 * @return
	 */
	public static List<Map<String, Object>> getListFromBean(List<?> list,String[] needFields,Map<String,Object> copyMap){
		if(CollectionUtil.isEmpty(list))
			return Collections.EMPTY_LIST;
		
		List<?> list1 = list;
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		for (Object object : list1) {
			Map<String, Object> map = getMapFromBean(object,needFields);
			copyProperty(map, copyMap);
			resultList.add(map);
		}
		return resultList;
	}
	
	/**
	 * copy多个键值对到bean
	 * @param bean
	 * @param copyMap
	 */
    public static void copyProperty(Object bean, Map<String,Object> copyMap){
    	for(Map.Entry<String, Object> entry : copyMap.entrySet()){
    	    try {
				BeanUtils.copyProperty(bean, entry.getKey(),  entry.getValue());
			} catch (IllegalAccessException e) {
				log.error("beanUtil copyProperty:"+e);
			} catch (InvocationTargetException e) {
				log.error("beanUtil copyProperty:"+e);
			}
    	}
	}

}
