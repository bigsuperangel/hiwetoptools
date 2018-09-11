package com.fj.hiwetoptools.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	private JsonUtil() {
		throw new UnsupportedOperationException("非法构造 JSON 对象");
	}

	public static String toJSON(Object paramObject) {
		if (paramObject == null)
			throw new IllegalArgumentException("object 参数异常");
		return com.alibaba.fastjson.JSON.toJSONString(paramObject);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Object> toArrayList(String paramString)
			throws Exception {
		Object localObject = toObject(paramString);
		if (!(localObject instanceof List))
			throw new IllegalArgumentException("jsonText 参数异常");
		return (ArrayList<Object>) localObject;
	}

	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, Object> toLinkedHashMap(
			String paramString) throws Exception {
		Object localObject = toObject(paramString);
		if (!(localObject instanceof Map))
			throw new IllegalArgumentException("jsonText 参数异常");
		return (LinkedHashMap<String, Object>) localObject;
	}

	private static Object toObject(String paramString) throws Exception {
		if (paramString == null)
			throw new IllegalArgumentException("jsonText 参数异常");
		Object localObject = null;
		try {
			localObject = com.alibaba.fastjson.JSON.parse(paramString);
		} catch (Exception localException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
			.append("JSON 文本解析失败（原因“")
			.append(localException.toString().replace("\r", "")
					.replace("\n", "").trim()).append("”）");
			throw new Exception(localStringBuilder.toString());
		}
		return traversalObject(localObject);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object traversalObject(Object paramObject) {
		Object localObject3;
		Object localObject1 = new LinkedHashMap<Object, Object>();
		Object localObject2 = paramObject;
		Iterator<?> localIterator;
		if ((paramObject instanceof JSONArray)) {
			localObject1 = new ArrayList<Object>();
			localObject2 = paramObject;
			for (localIterator = ((JSONArray) localObject2).iterator(); localIterator
					.hasNext();) {
				localObject3 = localIterator.next();
				if (((localObject3 instanceof JSONArray))
						|| ((localObject3 instanceof JSONObject)))
					((ArrayList<Object>) localObject1)
					.add(traversalObject(localObject3));
				else {
					((ArrayList<Object>) localObject1).add(localObject3);
				}
			}
			return localObject1;
		}

		for (localIterator = ((JSONObject) localObject2).entrySet().iterator(); localIterator
				.hasNext();) {
			localObject3 = localIterator.next();
			String str = (String) ((Map.Entry) localObject3).getKey();
			Object localObject4 = ((Map.Entry) localObject3).getValue();
			if (((localObject4 instanceof JSONArray))
					|| ((localObject4 instanceof JSONObject)))
				((LinkedHashMap) localObject1).put(str,
						traversalObject(localObject4));
			else {
				((LinkedHashMap) localObject1).put(str, localObject4);
			}
		}
		return localObject1;
	}
}