package com.fj.hiwetoptools;

import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.Map;

/**
 * 对象工具类.
 */
public class ObjectUtil {

	/**
	 * 字符串转对象.
	 *
	 * @param str
	 * @param c
	 * @return
	 * @author qingwu
	 * @date 2014-1-16 下午6:49:33
	 */
	public static Object strToObj(String str, Class<?> rClass) {
		String rType = rClass.getName();
		if ("java.lang.String".equals(rType)) {// 字符串类 型
			return str;
		} else if ("java.lang.Integer".equals(rType) || "int".equals(rType)) {// 整形
			return Integer.parseInt(str);
		} else if ("java.lang.Float".equals(rType) || "float".equals(rType)) {// 浮点型
			return Float.parseFloat(rType);
		} else if ("java.lang.Double".equals(rType) || "double".equals(rType)) {// 双精度
			return Double.parseDouble(str);
		} else if ("java.lang.Boolean".equals(rType) || "boolean".equals(rType)) {// 布尔型
			return Boolean.parseBoolean(str);
		} else if ("java.lang.Long".equals(rType) || "long".equals(rType)) {// Long类型
			return Long.parseLong(str);
		} else if ("java.lang.Short".equals(rType) || "short".equals(rType)) {// Short类型
			return Short.parseShort(str);
		}
		return str;
	}

	/**
	 * One of the following conditions isEmpty = true, else = false :
	 * 满足下列一个条件则为空<br>
	 * 1. null : 空<br>
	 * 2. "" or " " : 空串<br>
	 * 3. no item in [] or all item in [] are null : 数组中没有元素, 数组中所有元素为空<br>
	 * 4. no item in (Collection, Map, Dictionary) : 集合中没有元素<br>
	 *
	 * @param value
	 * @return
	 * @author qingwu
	 * @date 2014-1-16 下午6:49:33
	 */
	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		}
		if ((value instanceof String)
				&& ((((String) value).trim().length() <= 0) || "null"
						.equalsIgnoreCase((String) value))) {
			return true;
		}
		if ((value instanceof Object[]) && (((Object[]) value).length <= 0)) {
			return true;
		}
		if (value instanceof Object[]) { // all item in [] are null :
			// 数组中所有元素为空
			Object[] t = (Object[]) value;
			for (int i = 0; i < t.length; i++) {
				if (t[i] != null) {
					if (t[i] instanceof String) {
						if (((String) t[i]).trim().length() > 0
								|| "null".equalsIgnoreCase((String) t[i])) {
							return false;
						}
					} else {
						return false;
					}
				}
			}
			return true;
		}
		if ((value instanceof Collection)
				&& ((Collection<?>) value).size() <= 0) {
			return true;
		}
		if ((value instanceof Dictionary)
				&& ((Dictionary<?, ?>) value).size() <= 0) {
			return true;
		}
		if ((value instanceof Map) && ((Map<?, ?>) value).size() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 对象是否是值类型.
	 *
	 * @param obj
	 * @return
	 * @author qingwu
	 * @date 2013-7-9 下午03:01:44
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValueType(Class rClass) {
		String rType = rClass.getName();
		if ("java.lang.String".equals(rType)) {// 字符串类 型
			return true;
		} else if ("java.lang.Integer".equals(rType) || "int".equals(rType)) {// 整形
			return true;
		} else if ("java.lang.Float".equals(rType) || "float".equals(rType)) {// 浮点型
			return true;
		} else if ("java.lang.Double".equals(rType) || "double".equals(rType)) {// 双精度
			return true;
		} else if ("java.lang.Boolean".equals(rType) || "boolean".equals(rType)) {// 布尔型
			return true;
		} else if ("java.lang.Long".equals(rType) || "long".equals(rType)) {// Long类型
			return true;
		} else if ("java.lang.Short".equals(rType) || "short".equals(rType)) {// Short类型
			return true;
		} else if ("java.sql.Timestamp".equals(rType)) { // Timestamp类型
			return true;
		} else if ("java.util.Date".equals(rType)) { // Date类型
			return true;
		}
		return false;
	}

	/**
	 * 对象是否是值类型.
	 *
	 * @param obj
	 * @return
	 * @author qingwu
	 * @date 2013-7-9 下午03:01:44
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValueTypeWithoutDate(Class rClass) {
		String rType = rClass.getName();
		if ("java.lang.String".equals(rType)) {// 字符串类 型
			return true;
		} else if ("java.lang.Integer".equals(rType) || "int".equals(rType)) {// 整形
			return true;
		} else if ("java.lang.Float".equals(rType) || "float".equals(rType)) {// 浮点型
			return true;
		} else if ("java.lang.Double".equals(rType) || "double".equals(rType)) {// 双精度
			return true;
		} else if ("java.lang.Boolean".equals(rType) || "boolean".equals(rType)) {// 布尔型
			return true;
		} else if ("java.lang.Long".equals(rType) || "long".equals(rType)) {// Long类型
			return true;
		} else if ("java.lang.Short".equals(rType) || "short".equals(rType)) {// Short类型
			return true;
		}
		return false;
	}

	/**
	 * 是否是集合.
	 *
	 * @param obj
	 * @return
	 * @author qingwu
	 * @date 2012-9-26 下午03:50:55
	 */
	public static boolean isCollection(Object obj) {
		if (obj instanceof Collection<?>) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 对象是否是值类型.
	 *
	 * @param obj
	 * @return
	 * @author qingwu
	 * @date 2012-9-26 下午03:01:44
	 */
	public static boolean isValueType(Object obj) {
		if (obj == null || obj instanceof String || obj instanceof Number
				|| obj instanceof Boolean || obj instanceof Character
				|| obj instanceof Date) {
			return true;
		} else {
			return false;
		}
	}

}
