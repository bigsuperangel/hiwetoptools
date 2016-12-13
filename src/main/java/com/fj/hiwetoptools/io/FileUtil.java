package com.fj.hiwetoptools.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.fj.hiwetoptools.RandomUtil;
import com.fj.hiwetoptools.StrUtil;
import com.fj.hiwetoptools.exception.bean.FileException;
import org.apache.commons.io.FileUtils;

import com.fj.hiwetoptools.io.IoUtil;
import com.fj.hiwetoptools.reflect.ReflectionUtil;


public class FileUtil extends FileUtils {
	public static long sizeOfDirectory(File paramFile) {
		return FileUtils.sizeOfDirectory(paramFile);
	}

	public static String getAndCreateDirectoryPath(String paramString)
			throws Exception {
		File localFile = new File(paramString);
		return getAndCreateDirectoryPath(localFile);
	}

	public static String getAndCreateDirectoryPath(File paramFile)
			throws Exception {
		if (paramFile.exists()) {
			if (paramFile.isDirectory()) {
				return paramFile.getAbsolutePath() + File.separator;
			}
			if (paramFile.mkdirs()) {
				return paramFile.getAbsolutePath() + File.separator;
			}
			throw new Exception("not found directory");
		}
		if (paramFile.mkdirs()) {
			return paramFile.getAbsolutePath() + File.separator;
		}
		throw new Exception("not found directory");
	}

	public static String getFileName(String paramString) {

		if (Pattern.compile("[a-zA-Z]:\\\\").matcher(paramString).lookingAt()) {
			int i = paramString.lastIndexOf("\\");
			if (i != -1) {
				return paramString.substring(i + 1);
			}
			return paramString;
		}
		int i = paramString.lastIndexOf("/");
		if (i != -1) {
			return paramString.substring(i + 1);
		}
		return paramString;
	}

	/**
	 * 获取文件后缀 jpg
	 * @param paramString
	 * @return
	 */
	public static String getExtendName(String paramString) {
		int i = paramString.lastIndexOf(".");
		if (i == -1) {
			return "";
		}
		return paramString.substring(i + 1);
	}

	/**
	 * 获取文件后缀 jpg
	 * @param file
	 * @return
	 */
	public static String getExtendName(File file) {
		if(!file.exists()){
			throw new FileException("文件不存在");
		}
		return getExtendName(file.getName());
	}

	/**
	 * 获取文件后缀包括.jpg
	 * @param paramString
	 * @return
	 */
	public static String getExtendNameAddDot(String paramString){
		int i = paramString.lastIndexOf(".");
		if (i == -1) {
			return "";
		}
		return paramString.substring(i);
	}

	/**
	 * 获取文件后缀包括.jpg
	 * @param file
	 * @return
	 */
	public static String getExtendNameAddDot(File file){
		if(!file.exists()){
			throw new FileException("文件不存在");
		}
		return getExtendNameAddDot(file.getName());
	}

	public static String getFileId(String paramString) {
		if ((paramString == null) || (paramString.equals("")))
			return "";
		int i = paramString.lastIndexOf(".");
		if (i == -1) {
			return paramString;
		}
		return paramString.substring(0, i);
	}

	public static String getFileCreateDate(File paramFile) {
		File localFile = paramFile;
		try {
			Process localProcess = Runtime.getRuntime().exec(
					"cmd.exe /c dir " + localFile.getAbsolutePath() + " /tc");

			BufferedReader localBufferedReader = new BufferedReader(
					new InputStreamReader(localProcess.getInputStream()));

			for (int i = 0; i < 5; i++) {
				localBufferedReader.readLine();
			}
			String str1 = localBufferedReader.readLine();
			StringTokenizer localStringTokenizer = new StringTokenizer(str1);
			String str2 = localStringTokenizer.nextToken();
			String str3 = localStringTokenizer.nextToken();
			String str4 = str2.concat(str3);
			localBufferedReader.close();
			return str4;
		} catch (Exception localException) {
		}
		return null;
	}

	/**
	 * 生成(尽量)不重复的文件名
	 * @param type
	 * @param suffix
	 * @return
	 */
	public static String generateFileName(int type, String suffix) {
		long time = System.currentTimeMillis();
		int random = RandomUtil.randomInt(1000, 9999);
		return type + "_" + random + "_" + time + "." + suffix;
	}

	/**
	 * 通过origin文件构造dest
	 * @param origin
	 * @return
	 */
	public static String generateFileName(File origin){
		if(!origin.exists()){
			throw new FileException("文件不存在");
		}
		return generateFileName(origin.getName());
	}

	/**
	 * 通过origin文件构造dest
	 * @param origin
	 * @return
	 */
	public static String generateFileName(String origin){
		long time = System.currentTimeMillis();
		int random = RandomUtil.randomInt(1000,9999);
		return random + "_" + time + getExtendNameAddDot(origin);
	}

	/**
	 * 将文件写入流中
	 * 
	 * @param file 文件
	 * @param out 流
	 * @throws IOException
	 */
	public static void writeToStream(File file, OutputStream out) throws IOException {
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			IoUtil.copy(in, out);
		} finally {
			IoUtil.close(in);
		}
	}

	/**
	 * 将流的内容写入文件<br>
	 * 
	 * @param fullFilePath 文件绝对路径
	 * @param out 输出流
	 * @throws IOException
	 */
	public static void writeToStream(String fullFilePath, OutputStream out) throws IOException {
		writeToStream(touch(fullFilePath), out);
	}
	
	/**
	 * 创建文件，如果这个文件存在，直接返回这个文件
	 * 
	 * @param fullFilePath 文件的全路径，使用POSIX风格
	 * @return 文件，若路径为null，返回null
	 * @throws IOException
	 */
	public static File touch(String fullFilePath) throws IOException {
		if (fullFilePath == null) {
			return null;
		}
		return touch2(file(fullFilePath));
	}
	
	/**
	 * 创建文件，如果这个文件存在，直接返回这个文件
	 * 
	 * @param file 文件对象
	 * @return 文件，若路径为null，返回null
	 * @throws IOException
	 */
	public static File touch2(File file) throws IOException {
		if (null == file) {
			return null;
		}

		if (false == file.exists()) {
			mkParentDirs(file);
			file.createNewFile();
		}
		return file;
	}
	
	/**
	 * 创建所给文件或目录的父目录
	 * 
	 * @param file 文件或目录
	 * @return 父目录
	 */
	public static File mkParentDirs(File file) {
		final File parentFile = file.getParentFile();
		if (null != parentFile && false == parentFile.exists()) {
			parentFile.mkdirs();
		}
		return parentFile;
	}
	
	/**
	 * 创建File对象，自动识别相对或绝对路径，相对路径将自动从ClassPath下寻找
	 * 
	 * @param path 文件路径
	 * @return File
	 */
	public static File file(String path) {
		if (StrUtil.isBlank(path)) {
			throw new NullPointerException("File path is blank!");
		}
		return new File(getAbsolutePath(path));
	}
	
	/**
	 * 获取绝对路径，相对于classes的根目录<br>
	 * 如果给定就是绝对路径，则返回原路径，原路径把所有\替换为/
	 * 
	 * @param path 相对路径
	 * @return 绝对路径
	 */
	public static String getAbsolutePath(String path) {
		if (path == null) {
			path = StrUtil.EMPTY;
		} else {
			path = normalize(path);

			if (path.startsWith("/") || path.matches("^[a-zA-Z]:/.*")) {
				// 给定的路径已经是绝对路径了
				return path;
			}
		}

		// 相对路径
		ClassLoader classLoader = ReflectionUtil.getClassLoader();
		URL url = classLoader.getResource(path);
		String reultPath = url != null ? url.getPath() : ReflectionUtil.getClassLoader().getResource(StrUtil.EMPTY).getPath() + path;
		return reultPath;
	}
	
	/**
	 * 修复路径<br>
	 * 1. 统一用 / <br>
	 * 2. 多个 / 转换为一个
	 * 
	 * @param path 原路径
	 * @return 修复后的路径
	 */
	public static String normalize(String path) {
		return path.replaceAll("[/\\\\]{1,}", "/");
	}
	
	/**
	 * 获得一个输出流对象
	 * 
	 * @param file 文件
	 * @return 输出流对象
	 * @throws IOException
	 */
	public static BufferedOutputStream getOutputStream(File file) throws IOException {
		return new BufferedOutputStream(new FileOutputStream(touch2(file)));
	}

	/**
	 * 获得一个输出流对象
	 * 
	 * @param path 输出到的文件路径，绝对路径
	 * @return 输出流对象
	 * @throws IOException
	 */
	public static BufferedOutputStream getOutputStream(String path) throws IOException {
		return getOutputStream(touch(path));
	}

}
