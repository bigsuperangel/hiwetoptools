package com.fj.hiwetoptools.codec.alg;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.fj.hiwetoptools.codec.Hex;

public final class SHA {
	private SHA() {
		throw new UnsupportedOperationException("非法构造 SHA 对象");
	}

	public static byte[] sha1ToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return createMessageDigest("SHA-1").digest(paramArrayOfByte);
	}

	public static byte[] sha1ToByteArray(InputStream paramInputStream)
			throws IOException {
		try {
			return inputStreamDigest(createMessageDigest("SHA-1"),
					paramInputStream);
		} catch (IOException localIOException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
			.append("SHA-1 摘要计算失败（原因“")
			.append(localIOException.toString().replace("\r", "")
					.replace("\n", "").trim()).append("”）");
			throw new IOException(localStringBuilder.toString());
		}
	}

	public static byte[] sha1ToByteArray(String paramString) {
		return sha1ToByteArray(paramString, "UTF-8");
	}

	public static byte[] sha1ToByteArray(String paramString1,
			String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return sha1ToByteArray(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String sha1ToHex(byte[] paramArrayOfByte) {
		return Hex.encodeToString(sha1ToByteArray(paramArrayOfByte));
	}

	public static String sha1ToHex(InputStream paramInputStream)
			throws IOException {
		return Hex.encodeToString(sha1ToByteArray(paramInputStream));
	}

	public static String sha1ToHex(String paramString) {
		return Hex.encodeToString(sha1ToByteArray(paramString, "UTF-8"));
	}

	public static String sha1ToHex(String paramString1, String paramString2) {
		return Hex.encodeToString(sha1ToByteArray(paramString1, paramString2));
	}

	public static byte[] sha224ToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return createMessageDigest("SHA-224").digest(paramArrayOfByte);
	}

	public static byte[] sha224ToByteArray(InputStream paramInputStream)
			throws IOException {
		try {
			return inputStreamDigest(createMessageDigest("SHA-224"),
					paramInputStream);
		} catch (IOException localIOException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
			.append("SHA-224 摘要计算失败（原因“")
			.append(localIOException.toString().replace("\r", "")
					.replace("\n", "").trim()).append("”）");
			throw new IOException(localStringBuilder.toString());
		}
	}

	public static byte[] sha224ToByteArray(String paramString) {
		return sha224ToByteArray(paramString, "UTF-8");
	}

	public static byte[] sha224ToByteArray(String paramString1,
			String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return sha224ToByteArray(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String sha224ToHex(byte[] paramArrayOfByte) {
		return Hex.encodeToString(sha224ToByteArray(paramArrayOfByte));
	}

	public static String sha224ToHex(InputStream paramInputStream)
			throws IOException {
		return Hex.encodeToString(sha224ToByteArray(paramInputStream));
	}

	public static String sha224ToHex(String paramString) {
		return Hex.encodeToString(sha224ToByteArray(paramString, "UTF-8"));
	}

	public static String sha224ToHex(String paramString1, String paramString2) {
		return Hex
				.encodeToString(sha224ToByteArray(paramString1, paramString2));
	}

	public static byte[] sha256ToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return createMessageDigest("SHA-256").digest(paramArrayOfByte);
	}

	public static byte[] sha256ToByteArray(InputStream paramInputStream)
			throws IOException {
		try {
			return inputStreamDigest(createMessageDigest("SHA-256"),
					paramInputStream);
		} catch (IOException localIOException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
			.append("SHA-256 摘要计算失败（原因“")
			.append(localIOException.toString().replace("\r", "")
					.replace("\n", "").trim()).append("”）");
			throw new IOException(localStringBuilder.toString());
		}
	}

	public static byte[] sha256ToByteArray(String paramString) {
		return sha256ToByteArray(paramString, "UTF-8");
	}

	public static byte[] sha256ToByteArray(String paramString1,
			String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return sha256ToByteArray(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String sha256ToHex(byte[] paramArrayOfByte) {
		return Hex.encodeToString(sha256ToByteArray(paramArrayOfByte));
	}

	public static String sha256ToHex(InputStream paramInputStream)
			throws IOException {
		return Hex.encodeToString(sha256ToByteArray(paramInputStream));
	}

	public static String sha256ToHex(String paramString) {
		return Hex.encodeToString(sha256ToByteArray(paramString, "UTF-8"));
	}

	public static String sha256ToHex(String paramString1, String paramString2) {
		return Hex
				.encodeToString(sha256ToByteArray(paramString1, paramString2));
	}

	public static byte[] sha384ToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return createMessageDigest("SHA-384").digest(paramArrayOfByte);
	}

	public static byte[] sha384ToByteArray(InputStream paramInputStream)
			throws IOException {
		try {
			return inputStreamDigest(createMessageDigest("SHA-384"),
					paramInputStream);
		} catch (IOException localIOException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
			.append("SHA-384 摘要计算失败（原因“")
			.append(localIOException.toString().replace("\r", "")
					.replace("\n", "").trim()).append("”）");
			throw new IOException(localStringBuilder.toString());
		}
	}

	public static byte[] sha384ToByteArray(String paramString) {
		return sha384ToByteArray(paramString, "UTF-8");
	}

	public static byte[] sha384ToByteArray(String paramString1,
			String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return sha384ToByteArray(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String sha384ToHex(byte[] paramArrayOfByte) {
		return Hex.encodeToString(sha384ToByteArray(paramArrayOfByte));
	}

	public static String sha384ToHex(InputStream paramInputStream)
			throws IOException {
		return Hex.encodeToString(sha384ToByteArray(paramInputStream));
	}

	public static String sha384ToHex(String paramString) {
		return Hex.encodeToString(sha384ToByteArray(paramString, "UTF-8"));
	}

	public static String sha384ToHex(String paramString1, String paramString2) {
		return Hex
				.encodeToString(sha384ToByteArray(paramString1, paramString2));
	}

	public static byte[] sha512ToByteArray(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		return createMessageDigest("SHA-512").digest(paramArrayOfByte);
	}

	public static byte[] sha512ToByteArray(InputStream paramInputStream)
			throws IOException {
		try {
			return inputStreamDigest(createMessageDigest("SHA-512"),
					paramInputStream);
		} catch (IOException localIOException) {
			StringBuilder localStringBuilder = new StringBuilder();
			localStringBuilder
			.append("SHA-512 摘要计算失败（原因“")
			.append(localIOException.toString().replace("\r", "")
					.replace("\n", "").trim()).append("”）");
			throw new IOException(localStringBuilder.toString());
		}
	}

	public static byte[] sha512ToByteArray(String paramString) {
		return sha512ToByteArray(paramString, "UTF-8");
	}

	public static byte[] sha512ToByteArray(String paramString1,
			String paramString2) {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return sha512ToByteArray(paramString1.getBytes(paramString2));
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String sha512ToHex(byte[] paramArrayOfByte) {
		return Hex.encodeToString(sha512ToByteArray(paramArrayOfByte));
	}

	public static String sha512ToHex(InputStream paramInputStream)
			throws IOException {
		return Hex.encodeToString(sha512ToByteArray(paramInputStream));
	}

	public static String sha512ToHex(String paramString) {
		return Hex.encodeToString(sha512ToByteArray(paramString, "UTF-8"));
	}

	public static String sha512ToHex(String paramString1, String paramString2) {
		return Hex
				.encodeToString(sha512ToByteArray(paramString1, paramString2));
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
