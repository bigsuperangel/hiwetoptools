package com.fj.hiwetoptools;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * 对象工具类.
 */
public class ObjectUtil {
	/**
	 * 比较两个对象是否相等。<br>
	 * 相同的条件有两个，满足其一即可：<br>
	 * 1. obj1 == null && obj2 == null; 2. obj1.equals(obj2)
	 * 
	 * @param obj1 对象1
	 * @param obj2 对象2
	 * @return 是否相等
	 */
	public static boolean equals(Object obj1, Object obj2) {
		return (obj1 != null) ? (obj1.equals(obj2)) : (obj2 == null);
	}
	
	/**
	 * 对象中是否包含元素
	 * 
	 * @param obj 对象
	 * @param element 元素
	 * @return 是否包含
	 */
	public static boolean contains(Object obj, Object element) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof String) {
			if (element == null) {
				return false;
			}
			return ((String) obj).contains(element.toString());
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).contains(element);
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).values().contains(element);
		}

		if (obj instanceof Iterator) {
			Iterator<?> iter = (Iterator<?>) obj;
			while (iter.hasNext()) {
				Object o = iter.next();
				if (equals(o, element)) {
					return true;
				}
			}
			return false;
		}
		if (obj instanceof Enumeration) {
			Enumeration<?> enumeration = (Enumeration<?>) obj;
			while (enumeration.hasMoreElements()) {
				Object o = enumeration.nextElement();
				if (equals(o, element)) {
					return true;
				}
			}
			return false;
		}
		if (obj.getClass().isArray() == true) {
			int len = Array.getLength(obj);
			for (int i = 0; i < len; i++) {
				Object o = Array.get(obj, i);
				if (equals(o, element)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 字符串转对象.
	 *
	 * @param str
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
     * 计算对象长度，如果是字符串调用其length函数，集合类调用其size函数，数组调用其length属性，其他可遍历对象遍历计算长度
	 *
     * @param obj 被计算长度的对象
	 * @return 长度
	 */
    public static int length(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).size();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).size();
        }

        int count;
        if (obj instanceof Iterator) {
            Iterator<?> iter = (Iterator<?>) obj;
            count = 0;
            while (iter.hasNext()) {
                count++;
                iter.next();
            }
            return count;
        }
        if (obj instanceof Enumeration) {
            Enumeration<?> enumeration = (Enumeration<?>) obj;
            count = 0;
            while (enumeration.hasMoreElements()) {
                count++;
                enumeration.nextElement();
            }
            return count;
        }
        if (obj.getClass().isArray() == true) {
            return Array.getLength(obj);
        }
        return -1;
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
	 * @return
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
	 * 对象是否是值类型.
	 *
	 */
	public static boolean isValueType(Object object) {
		return object instanceof Byte || 
				object instanceof Character || 
				object instanceof Short || 
				object instanceof Integer || 
				object instanceof Long || 
				object instanceof Boolean || 
				object instanceof Float || 
				object instanceof Double || 
				object instanceof String || 
				object instanceof BigInteger || 
				object instanceof BigDecimal;
	}

	/**
	 * 检查对象是否为null
	 * 
	 * @param obj 对象
	 * @return 是否为null
	 */
	public static boolean isNull(Object obj) {
		return null == obj;
	}
	
	/**
	 * 检查对象是否不为null
	 * 
	 * @param obj 对象
	 * @return 是否为null
	 */
	public static boolean isNotNull(Object obj) {
		return null != obj;
	}
	
	/**
	 * 是否是集合.
	 * @param obj
	 */
	public static boolean isCollection(Object obj) {
		return obj instanceof Collection<?>;
	}

	/**
	 * 对象是否为数组对象
	 * @param obj 对象
	 * @return 是否为数组对象
	 */
	public static boolean isArray(Object obj) {
		if(null == obj){
			throw new NullPointerException("Object check for isArray is null");
		}
		return obj.getClass().isArray();
	}

}
