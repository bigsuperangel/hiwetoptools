package com.fj.hiwetoptools.codec;

public final class Base64 {
	private Base64() {
		throw new UnsupportedOperationException("非法构造 Base64 对象");
	}

	public static byte[] encodeToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return org.apache.commons.codec.binary.Base64
				.encodeBase64(paramArrayOfByte);
	}

	public static byte[] encodeToByteArray(String paramString) {
		return encodeToByteArray(paramString, "UTF-8");
	}

	public static byte[] encodeToByteArray(String paramString1,
			String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return org.apache.commons.codec.binary.Base64
					.encodeBase64(paramString1.getBytes(paramString2));
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String encodeToString(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return org.apache.commons.codec.binary.Base64
				.encodeBase64String(paramArrayOfByte);
	}

	public static String encodeToString(String paramString) {
		return encodeToString(paramString, "UTF-8");
	}

	public static String encodeToString(String paramString1, String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return org.apache.commons.codec.binary.Base64
					.encodeBase64String(paramString1.getBytes(paramString2));
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static byte[] decodeToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return org.apache.commons.codec.binary.Base64
				.decodeBase64(paramArrayOfByte);
	}

	public static byte[] decodeToByteArray(String paramString) {
		if (paramString == null)
			throw new IllegalArgumentException("string 参数异常");
		return org.apache.commons.codec.binary.Base64.decodeBase64(paramString);
	}

	public static String decodeToString(byte[] paramArrayOfByte) {
		return decodeToString(paramArrayOfByte, "UTF-8");
	}

	public static String decodeToString(byte[] paramArrayOfByte,
			String paramString) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		if (paramString == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		byte[] arrayOfByte = org.apache.commons.codec.binary.Base64
				.decodeBase64(paramArrayOfByte);
		try {
			return new String(arrayOfByte, paramString);
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String decodeToString(String paramString) {
		return decodeToString(paramString, "UTF-8");
	}

	public static String decodeToString(String paramString1, String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		byte[] arrayOfByte = org.apache.commons.codec.binary.Base64
				.decodeBase64(paramString1);
		try {
			return new String(arrayOfByte, paramString2);
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}
}
