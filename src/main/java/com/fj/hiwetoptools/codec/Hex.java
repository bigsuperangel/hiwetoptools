package com.fj.hiwetoptools.codec;


public final class Hex {
	private Hex() {
		throw new UnsupportedOperationException("非法构造 Hex 对象");
	}

	public static byte[] encodeToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return new org.apache.commons.codec.binary.Hex()
				.encode(paramArrayOfByte);
	}

	public static byte[] encodeToByteArray(char[] paramArrayOfChar) {
		return encodeToByteArray(paramArrayOfChar, "UTF-8");
	}

	public static byte[] encodeToByteArray(char[] paramArrayOfChar,
			String paramString) {
		if (paramArrayOfChar == null)
			throw new IllegalArgumentException("charArray 参数异常");
		return encodeToByteArray(new String(paramArrayOfChar), paramString);
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
			return encodeToByteArray(paramString1.getBytes(paramString2));
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static char[] encodeToCharArray(byte[] paramArrayOfByte) {
		return encodeToCharArray(paramArrayOfByte, false);
	}

	public static char[] encodeToCharArray(byte[] paramArrayOfByte,
			boolean paramBoolean) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		if (paramBoolean) {
			return org.apache.commons.codec.binary.Hex.encodeHex(
					paramArrayOfByte, false);
		}
		return org.apache.commons.codec.binary.Hex.encodeHex(paramArrayOfByte,
				true);
	}

	public static char[] encodeToCharArray(char[] paramArrayOfChar) {
		return encodeToCharArray(paramArrayOfChar, "UTF-8", false);
	}

	public static char[] encodeToCharArray(char[] paramArrayOfChar,
			String paramString) {
		return encodeToCharArray(paramArrayOfChar, paramString, false);
	}

	public static char[] encodeToCharArray(char[] paramArrayOfChar,
			boolean paramBoolean) {
		return encodeToCharArray(paramArrayOfChar, "UTF-8", paramBoolean);
	}

	public static char[] encodeToCharArray(char[] paramArrayOfChar,
			String paramString, boolean paramBoolean) {
		if (paramArrayOfChar == null)
			throw new IllegalArgumentException("charArray 参数异常");
		return encodeToCharArray(new String(paramArrayOfChar), paramString,
				paramBoolean);
	}

	public static char[] encodeToCharArray(String paramString) {
		return encodeToCharArray(paramString, "UTF-8", false);
	}

	public static char[] encodeToCharArray(String paramString1,
			String paramString2) {
		return encodeToCharArray(paramString1, paramString2, false);
	}

	public static char[] encodeToCharArray(String paramString,
			boolean paramBoolean) {
		return encodeToCharArray(paramString, "UTF-8", paramBoolean);
	}

	public static char[] encodeToCharArray(String paramString1,
			String paramString2, boolean paramBoolean) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return encodeToCharArray(paramString1.getBytes(paramString2),
					paramBoolean);
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String encodeToString(byte[] paramArrayOfByte) {
		return encodeToString(paramArrayOfByte, false);
	}

	public static String encodeToString(byte[] paramArrayOfByte,
			boolean paramBoolean) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		if (paramBoolean) {
			return org.apache.commons.codec.binary.Hex.encodeHexString(
					paramArrayOfByte).toUpperCase();
		}
		return org.apache.commons.codec.binary.Hex.encodeHexString(
				paramArrayOfByte).toLowerCase();
	}

	public static String encodeToString(char[] paramArrayOfChar) {
		return encodeToString(paramArrayOfChar, "UTF-8", false);
	}

	public static String encodeToString(char[] paramArrayOfChar,
			String paramString) {
		return encodeToString(paramArrayOfChar, paramString, false);
	}

	public static String encodeToString(char[] paramArrayOfChar,
			boolean paramBoolean) {
		return encodeToString(paramArrayOfChar, "UTF-8", paramBoolean);
	}

	public static String encodeToString(char[] paramArrayOfChar,
			String paramString, boolean paramBoolean) {
		if (paramArrayOfChar == null)
			throw new IllegalArgumentException("charArray 参数异常");
		return encodeToString(new String(paramArrayOfChar), paramString,
				paramBoolean);
	}

	public static String encodeToString(String paramString) {
		return encodeToString(paramString, "UTF-8", false);
	}

	public static String encodeToString(String paramString1, String paramString2) {
		return encodeToString(paramString1, paramString2, false);
	}

	public static String encodeToString(String paramString, boolean paramBoolean) {
		return encodeToString(paramString, "UTF-8", paramBoolean);
	}

	public static String encodeToString(String paramString1,
			String paramString2, boolean paramBoolean) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return encodeToString(paramString1.getBytes(paramString2),
					paramBoolean);
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static byte[] decodeToByteArray(byte[] paramArrayOfByte)
			throws Exception {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		try {
			return new org.apache.commons.codec.binary.Hex()
					.decode(paramArrayOfByte);
		} catch (org.apache.commons.codec.DecoderException localDecoderException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
					.append("十六进制解码失败（原因“")
					.append(localDecoderException.toString().replace("\r", "")
							.replace("\n", "").trim()).append("”）");
			throw new Exception(localStringBuilder.toString());
		}
	}

	public static byte[] decodeToByteArray(char[] paramArrayOfChar)
			throws Exception {
		if (paramArrayOfChar == null)
			throw new IllegalArgumentException("charArray 参数异常");
		try {
			return org.apache.commons.codec.binary.Hex
					.decodeHex(paramArrayOfChar);
		} catch (org.apache.commons.codec.DecoderException localDecoderException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
					.append("十六进制解码失败（原因“")
					.append(localDecoderException.toString().replace("\r", "")
							.replace("\n", "").trim()).append("”）");
			throw new Exception(localStringBuilder.toString());
		}
	}

	public static byte[] decodeToByteArray(String paramString) throws Exception {
		if (paramString == null)
			throw new IllegalArgumentException("string 参数异常");
		return decodeToByteArray(paramString.toCharArray());
	}

	public static char[] decodeToCharArray(byte[] paramArrayOfByte)
			throws Exception {
		return decodeToCharArray(paramArrayOfByte, "UTF-8");
	}

	public static char[] decodeToCharArray(byte[] paramArrayOfByte,
			String paramString) throws Exception {
		if (paramString == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return new String(decodeToByteArray(paramArrayOfByte), paramString)
					.toCharArray();
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static char[] decodeToCharArray(char[] paramArrayOfChar)
			throws Exception {
		return decodeToCharArray(paramArrayOfChar, "UTF-8");
	}

	public static char[] decodeToCharArray(char[] paramArrayOfChar,
			String paramString) throws Exception {
		if (paramString == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return new String(decodeToByteArray(paramArrayOfChar), paramString)
					.toCharArray();
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static char[] decodeToCharArray(String paramString) throws Exception {
		return decodeToCharArray(paramString, "UTF-8");
	}

	public static char[] decodeToCharArray(String paramString1,
			String paramString2) throws Exception {
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return new String(decodeToByteArray(paramString1), paramString2)
					.toCharArray();
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String decodeToString(byte[] paramArrayOfByte)
			throws Exception {
		return decodeToString(paramArrayOfByte, "UTF-8");
	}

	public static String decodeToString(byte[] paramArrayOfByte,
			String paramString) throws Exception {
		if (paramString == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return new String(decodeToByteArray(paramArrayOfByte), paramString);
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String decodeToString(char[] paramArrayOfChar)
			throws Exception {
		return decodeToString(paramArrayOfChar, "UTF-8");
	}

	public static String decodeToString(char[] paramArrayOfChar,
			String paramString) throws Exception {
		if (paramString == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return new String(decodeToByteArray(paramArrayOfChar), paramString);
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String decodeToString(String paramString) throws Exception {
		return decodeToString(paramString, "UTF-8");
	}

	public static String decodeToString(String paramString1, String paramString2)
			throws Exception {
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return new String(decodeToByteArray(paramString1), paramString2);
		} catch (java.io.UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}
}
