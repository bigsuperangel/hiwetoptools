package com.fj.hiwetoptools.codec.alg;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.fj.hiwetoptools.codec.Hex;

public final class MD {
	private MD() {
		throw new UnsupportedOperationException("非法构造 MD 对象");
	}

	public static byte[] md2ToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return createMessageDigest("MD2").digest(paramArrayOfByte);
	}

	public static byte[] md2ToByteArray(InputStream paramInputStream)
			throws IOException {
		try {
			return inputStreamDigest(createMessageDigest("MD2"),
					paramInputStream);
		} catch (IOException localIOException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
			.append("MD2 摘要计算失败（原因“")
			.append(localIOException.toString().replace("\r", "")
					.replace("\n", "").trim()).append("”）");
			throw new IOException(localStringBuilder.toString());
		}
	}

	public static byte[] md2ToByteArray(String paramString) {
		return md2ToByteArray(paramString, "UTF-8");
	}

	public static byte[] md2ToByteArray(String paramString1, String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return md2ToByteArray(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String md2ToHex(byte[] paramArrayOfByte) {
		return Hex.encodeToString(md2ToByteArray(paramArrayOfByte));
	}

	public static String md2ToHex(InputStream paramInputStream)
			throws IOException {
		return Hex.encodeToString(md2ToByteArray(paramInputStream));
	}

	public static String md2ToHex(String paramString) {
		return Hex.encodeToString(md2ToByteArray(paramString, "UTF-8"));
	}

	public static String md2ToHex(String paramString1, String paramString2) {
		return Hex.encodeToString(md2ToByteArray(paramString1, paramString2));
	}

	public static byte[] md4ToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return createMessageDigest("MD4").digest(paramArrayOfByte);
	}

	public static byte[] md4ToByteArray(InputStream paramInputStream)
			throws IOException {
		try {
			return inputStreamDigest(createMessageDigest("MD4"),
					paramInputStream);
		} catch (IOException localIOException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
			.append("MD4 摘要计算失败（原因“")
			.append(localIOException.toString().replace("\r", "")
					.replace("\n", "").trim()).append("”）");
			throw new IOException(localStringBuilder.toString());
		}
	}

	public static byte[] md4ToByteArray(String paramString) {
		return md4ToByteArray(paramString, "UTF-8");
	}

	public static byte[] md4ToByteArray(String paramString1, String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return md4ToByteArray(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String md4ToHex(byte[] paramArrayOfByte) {
		return Hex.encodeToString(md4ToByteArray(paramArrayOfByte));
	}

	public static String md4ToHex(InputStream paramInputStream)
			throws IOException {
		return Hex.encodeToString(md4ToByteArray(paramInputStream));
	}

	public static String md4ToHex(String paramString) {
		return Hex.encodeToString(md4ToByteArray(paramString, "UTF-8"));
	}

	public static String md4ToHex(String paramString1, String paramString2) {
		return Hex.encodeToString(md4ToByteArray(paramString1, paramString2));
	}

	public static byte[] md5ToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return createMessageDigest("MD5").digest(paramArrayOfByte);
	}

	public static byte[] md5ToByteArray(InputStream paramInputStream)
			throws IOException {
		try {
			return inputStreamDigest(createMessageDigest("MD5"),
					paramInputStream);
		} catch (IOException localIOException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
			.append("MD5 摘要计算失败（原因“")
			.append(localIOException.toString().replace("\r", "")
					.replace("\n", "").trim()).append("”）");
			throw new IOException(localStringBuilder.toString());
		}
	}

	public static byte[] md5ToByteArray(String paramString) {
		return md5ToByteArray(paramString, "UTF-8");
	}

	public static byte[] md5ToByteArray(String paramString1, String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return md5ToByteArray(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String md5ToHex(byte[] paramArrayOfByte) {
		return Hex.encodeToString(md5ToByteArray(paramArrayOfByte));
	}

	public static String md5ToHex(InputStream paramInputStream)
			throws IOException {
		return Hex.encodeToString(md5ToByteArray(paramInputStream));
	}

	public static String md5ToHex(String paramString) {
		return Hex.encodeToString(md5ToByteArray(paramString, "UTF-8"));
	}

	public static String md5ToHex(String paramString1, String paramString2) {
		return Hex.encodeToString(md5ToByteArray(paramString1, paramString2));
	}

	private static MessageDigest createMessageDigest(String paramString) {
		try {
			return MessageDigest.getInstance(paramString);
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
		}
		throw new IllegalArgumentException("digestName 参数异常");
	}

	private static byte[] inputStreamDigest(MessageDigest paramMessageDigest,
			InputStream paramInputStream) throws IOException {
		if (paramInputStream == null)
			throw new IllegalArgumentException("inputStream 参数异常");
		byte[] arrayOfByte = new byte[1024];
		int i = paramInputStream.read(arrayOfByte, 0, 1024);
		while (i > -1) {
			paramMessageDigest.update(arrayOfByte, 0, i);
			i = paramInputStream.read(arrayOfByte, 0, 1024);
		}
		return paramMessageDigest.digest();
	}

	static {
		Security.addProvider(new BouncyCastleProvider());
	}
}
