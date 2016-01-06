package com.fj.hiwetoptools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class FileUtil extends FileUtils {
	public static long sizeOfDirectory(File paramFile) {
		return FileUtils.sizeOfDirectory(paramFile);
	}

	public static boolean deleteQuietly(File paramFile) {
		return FileUtils.deleteQuietly(paramFile);
	}

	public static void deleteDirectory(File paramFile) throws IOException {
		FileUtils.deleteDirectory(paramFile);
	}

	public static void copyFileToDirectory(File paramFile1, File paramFile2)
			throws IOException {
		FileUtils.copyFileToDirectory(paramFile1, paramFile2);
	}

	public static void copyFile(File paramFile1, File paramFile2)
			throws IOException {
		FileUtils.copyFile(paramFile1, paramFile2);
	}

	public static void copyFile(String paramString1, String paramString2)
			throws IOException {
		File localFile1 = null;
		File localFile2 = null;
		localFile2 = new File(paramString2);
		if ((paramString1.toLowerCase().indexOf("http://") == 0)
				|| (paramString1.toLowerCase().indexOf("file://") == 0)) {
			URL localURL = new URL(paramString1);
			FileUtils.copyURLToFile(localURL, localFile2);
		} else {
			localFile1 = new File(paramString1);
			copyFile(localFile1, localFile2);
		}
	}

	public static void copyDirectory(File paramFile1, File paramFile2)
			throws IOException {
		FileUtils.copyDirectory(paramFile1, paramFile2);
	}

	public static void cleanDirectory(File paramFile) throws IOException {
		FileUtils.cleanDirectory(paramFile);
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

	public static String getExtendName(String paramString) {
		int i = paramString.lastIndexOf(".");
		if (i == -1) {
			return "";
		}
		return paramString.substring(i + 1);
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
		int random = new RandomGenerator().nextNumber(1000, 9999);
		return type + "_" + random + "_" + time + "." + suffix;
	}


}
