package com.fj.hiwetop.http;


import com.fj.hiwetoptools.io.FileUtil;
import com.fj.hiwetoptools.http.HttpUtil;

/**
 * 下载样例
 * @author Looly
 *
 */
public class DownloadDemo {
	public static void main(String[] args) {
		// 下载文件
		long size = HttpUtil.downloadFile("https://www.baidu.com/", FileUtil.file("e:/"));
		System.out.println("Download size: " + size);
	}
}
