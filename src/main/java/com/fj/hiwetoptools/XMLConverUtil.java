package com.fj.hiwetoptools;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.ContentListFacade;
import org.dom4j.tree.DefaultAttribute;

/**
 * XML 数据接收对象转换工具类
 */
public class XMLConverUtil{

	private static Map<Class<?>,Unmarshaller> uMap = new HashMap<Class<?>,Unmarshaller>();

	/**
	 * XML to Object
	 * @param <T>
	 * @param clazz
	 * @param xml
	 * @return
	 */
	public static <T> T convertToObject(Class<T> clazz,String xml){
		return convertToObject(clazz,new StringReader(xml));
	}

	/**
	 * XML to Object
	 * @param <T>
	 * @param clazz
	 * @param inputStream
	 * @return
	 */
	public static <T> T convertToObject(Class<T> clazz,InputStream inputStream){
		return convertToObject(clazz,new InputStreamReader(inputStream));
	}

	/**
	 * XML to Object
	 * @param <T>
	 * @param clazz
	 * @param reader
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertToObject(Class<T> clazz,Reader reader){
		try {
			if(!uMap.containsKey(clazz)){
				JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				uMap.put(clazz,unmarshaller);
			}
			return (T)uMap.get(clazz).unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * OBJ TO XML
	 * @param obj
	 * @return
	 */
	public static String convertToXML(Object obj)  {
		StringWriter w = new StringWriter();
		JAXB.marshal(obj, w);
		return w.toString().replaceAll("[\\r\\n]", "");
	}

	public static Map<String,Object> convertToMap(String xml) throws DocumentException {
		return convertToMap(DocumentHelper.parseText(xml));
	}

	/**
	 * XML转MAP
	 * @param doc
	 * @return
	 */
	public static Map<String, Object> convertToMap(Document doc) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 判断需要解析的文档是否为空
		if (doc == null) {
			return map;
		}
		// 获取根节点
		Element root = doc.getRootElement();
		saveRootAttribute2Map(map, root);

		// 获取根节点下的子节点迭代器
		Iterator<?> iterator = root.elementIterator();

		// 循环子节点，开始向map中存值
		while (iterator.hasNext()) {
			Element e = (Element) iterator.next();
			List<?> list = e.elements();

			// 判断当前节点是否有子节点
			// 如果存在子节点调用element2Map(Element e)方法，不存在子节点直接存进map中
			if (list.size() > 0) {
				map.put(e.getName(), element2Map(e));
			} else {
				saveAttribute2Map(map, e);
				map.put(e.getName(), e.getText());
			}
		}
		return map;
	}

	/**
	 * 元素转成map
	 * @param e
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> element2Map(Element e) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<?> list = e.elements();
		saveAttribute2Map(map, e);
		if (list.size() > 0) {
			for (int i = 0, j = list.size(); i < j; i++) {
				Element iter = (Element) list.get(i);
				List<Object> mapList = new ArrayList<Object>();
				// 存在子节点
				if (iter.elements().size() > 0) {
					Map<?, ?> m = element2Map(iter);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName()
								.equals("java.util.ArrayList")) {
							mapList = new ArrayList<Object>();
							mapList.add(obj);
							mapList.add(m);
						}
						if (obj.getClass().getName()
								.equals("java.util.ArrayList")) {
							mapList = (List<Object>) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), m);
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!obj.getClass().getName()
								.equals("java.util.ArrayList")) {
							mapList = new ArrayList<Object>();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj.getClass().getName()
								.equals("java.util.ArrayList")) {
							mapList = (List<Object>) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), iter.getText());
				}
			}
		} else {
			saveAttribute2Map(map, e);
			map.put(e.getName(), e.getText());
		}

		return map;
	}

	/**
	 * 保存xml属性
	 * @param map
	 * @param e
	 */
	private static void saveAttribute2Map(Map<String, Object> map, Element e) {
		ContentListFacade attributes = (ContentListFacade) e.attributes();
		if (attributes.size() > 0) {
			HashMap<String, String> attrMap = new HashMap<String, String>();
			map.put("attribute", attrMap);
			DefaultAttribute attrTmp = null;
			for (int i = 0, j = attributes.size(); i < j; i++) {
				attrTmp = (DefaultAttribute) attributes.get(i);
				attrMap.put(attrTmp.getName(), attrTmp.getValue());
			}
		}
	}

	/**
	 * 保存根结点属性值到同一级
	 * @param map
	 * @param e
	 */
	private static void saveRootAttribute2Map(Map<String, Object> map, Element e) {
		ContentListFacade attributes = (ContentListFacade) e.attributes();
		if (attributes.size() > 0) {
			DefaultAttribute attrTmp = null;
			for (int i = 0, j = attributes.size(); i < j; i++) {
				attrTmp = (DefaultAttribute) attributes.get(i);
				map.put(attrTmp.getName(), attrTmp.getValue());
			}
		}
	}
}
