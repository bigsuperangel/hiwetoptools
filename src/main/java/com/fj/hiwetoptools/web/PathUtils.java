package com.fj.hiwetoptools.web;

import cn.hutool.core.exceptions.UtilException;
import com.fj.hiwetoptools.util.StrUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 路径处理工具
 */
public class PathUtils {

	private static final String DEFAULT_CHARSET = "UTF-8";

	public static String rebuild(String path) {
		if (StrUtil.isBlank(path)) {
			return null;
		}
		String newPath = path;
		try {
			newPath =  URLDecoder.decode(newPath, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new UtilException("url进行编码错误!", e);
		}
		newPath = rebuildNoDecode(newPath);
		
		return newPath;
	}
	
	public static String rebuildNoDecode(String path) {
		String newPath = path;
		newPath = newPath.replaceAll("\\\\", "/");
		newPath = newPath.replaceAll("//", "/");
		
		return newPath;
	}

	/**
	 * 验证是否是URL
	 * @author YOLANDA
	 * @param url
	 * @return
	 */
	public static boolean isTrueURL(String url){
		String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
		Pattern patt = Pattern. compile(regex );
		Matcher matcher = patt.matcher(url);
		return matcher.matches();
	}
}
