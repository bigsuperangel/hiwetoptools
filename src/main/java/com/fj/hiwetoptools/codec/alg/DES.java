package com.fj.hiwetoptools.codec.alg;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.fj.hiwetoptools.codec.Hex;

public final class DES {
	private DES() {
		throw new UnsupportedOperationException("非法构造 DES 对象");
	}

	public static byte[] create56BitKeyToByteArray() {
		return createKey(56);
	}

	public static String create56BitKeyToHex() {
		return Hex.encodeToString(createKey(56));
	}

	public static byte[] create64BitKeyToByteArray() {
		return createKey(64);
	}

	public static String create64BitKeyToHex() {
		return Hex.encodeToString(createKey(64));
	}

	private static byte[] createKey(int paramInt) {
		KeyGenerator localKeyGenerator = createKeyGenerator("DES");
		initKeyGenerator(localKeyGenerator, paramInt);
		SecretKey localSecretKey = localKeyGenerator.generateKey();
		return localSecretKey.getEncoded();
	}

	private static KeyGenerator createKeyGenerator(String paramString) {
		try {
			return KeyGenerator.getInstance(paramString);
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
		}
		throw new IllegalArgumentException("algorithmName 参数异常");
	}

	private static void initKeyGenerator(KeyGenerator paramKeyGenerator,
			int paramInt) {
		try {
			paramKeyGenerator.init(paramInt);
		} catch (InvalidParameterException localInvalidParameterException) {
			throw new IllegalArgumentException("bit 参数异常");
		}
	}

	public static byte[] encryptWithECBToByteArray(byte[] paramArrayOfByte1,
			byte[] paramArrayOfByte2) throws Exception {
		return encryptWithECBToByteArray(paramArrayOfByte1, paramArrayOfByte2,
				"PKCS5Padding");
	}

	public static byte[] encryptWithECBToByteArray(byte[] paramArrayOfByte1,
			byte[] paramArrayOfByte2, String paramString) throws Exception {
		if (paramArrayOfByte1 == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		if (paramString == null)
			throw new IllegalArgumentException("paddingType 参数异常");
		SecretKey localSecretKey = createSecretKey("DES",
				createDESKeySpec(paramArrayOfByte2));
		StringBuilder localStringBuilder1 = new StringBuilder();
		localStringBuilder1.append("DES/ECB/").append(paramString);
		try {
			Cipher localCipher = Cipher.getInstance(localStringBuilder1
					.toString());
			localCipher.init(1, localSecretKey);
			return localCipher.doFinal(paramArrayOfByte1);
		} catch (Exception localException) {
			StringBuilder localStringBuilder2 = new StringBuilder();
			localStringBuilder2
					.append("DES 加密失败（原因“")
					.append(localException.toString().replace("\r", "")
							.replace("\n", "").trim()).append("”）");
			throw new Exception(localStringBuilder2.toString());
		}
	}

	public static byte[] encryptWithECBToByteArray(String paramString1,
			String paramString2) throws Exception {
		return encryptWithECBToByteArray(paramString1, "UTF-8", paramString2,
				"PKCS5Padding");
	}

	public static byte[] encryptWithECBToByteArray(String paramString1,
			String paramString2, String paramString3) throws Exception {
		return encryptWithECBToByteArray(paramString1, paramString2,
				paramString3, "PKCS5Padding");
	}

	public static byte[] encryptWithECBToByteArray(String paramString1,
			String paramString2, String paramString3, String paramString4)
			throws Exception {
		if (paramString1 == null)
			throw new IllegalArgumentException("string 参数异常");
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		byte[] arrayOfByte = null;
		try {
			arrayOfByte = Hex.decodeToByteArray(paramString3);
		} catch (Exception localException) {
			throw new IllegalArgumentException("key 参数异常");
		}
		try {
			return encryptWithECBToByteArray(
					paramString1.getBytes(paramString2), arrayOfByte,
					paramString4);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String encryptWithECBToHex(byte[] paramArrayOfByte1,
			byte[] paramArrayOfByte2) throws Exception {
		return Hex.encodeToString(encryptWithECBToByteArray(paramArrayOfByte1,
				paramArrayOfByte2));
	}

	public static String encryptWithECBToHex(byte[] paramArrayOfByte1,
			byte[] paramArrayOfByte2, String paramString) throws Exception {
		return Hex.encodeToString(encryptWithECBToByteArray(paramArrayOfByte1,
				paramArrayOfByte2, paramString));
	}

	public static String encryptWithECBToHex(String paramString1,
			String paramString2) throws Exception {
		return Hex.encodeToString(encryptWithECBToByteArray(paramString1,
				paramString2));
	}

	public static String encryptWithECBToHex(String paramString1,
			String paramString2, String paramString3) throws Exception {
		return Hex.encodeToString(encryptWithECBToByteArray(paramString1,
				paramString2, paramString3));
	}

	public static String encryptWithECBToHex(String paramString1,
			String paramString2, String paramString3, String paramString4)
			throws Exception {
		return Hex.encodeToString(encryptWithECBToByteArray(paramString1,
				paramString2, paramString3, paramString4));
	}

	public static byte[] decryptWithECBToByteArray(byte[] paramArrayOfByte1,
			byte[] paramArrayOfByte2) throws Exception {
		return decryptWithECBToByteArray(paramArrayOfByte1, paramArrayOfByte2,
				"PKCS5Padding");
	}

	public static byte[] decryptWithECBToByteArray(byte[] paramArrayOfByte1,
			byte[] paramArrayOfByte2, String paramString) throws Exception {
		if (paramArrayOfByte1 == null)
			throw new IllegalArgumentException("byteArray 参数异常");
		if (paramString == null)
			throw new IllegalArgumentException("paddingType 参数异常");
		SecretKey localSecretKey = createSecretKey("DES",
				createDESKeySpec(paramArrayOfByte2));
		StringBuilder localStringBuilder1 = new StringBuilder();
		localStringBuilder1.append("DES/ECB/").append(paramString);
		try {
			Cipher localCipher = Cipher.getInstance(localStringBuilder1
					.toString());
			localCipher.init(2, localSecretKey);
			return localCipher.doFinal(paramArrayOfByte1);
		} catch (Exception localException) {
			StringBuilder localStringBuilder2 = new StringBuilder();
			localStringBuilder2
					.append("DES 解密失败（原因“")
					.append(localException.toString().replace("\r", "")
							.replace("\n", "").trim()).append("”）");
			throw new Exception(localStringBuilder2.toString());
		}
	}

	public static byte[] decryptWithECBToByteArray(String paramString1,
			String paramString2) throws Exception {
		return decryptWithECBToByteArray(paramString1, paramString2,
				"PKCS5Padding");
	}

	public static byte[] decryptWithECBToByteArray(String paramString1,
			String paramString2, String paramString3) throws Exception {
		byte[] arrayOfByte1 = null;
		try {
			arrayOfByte1 = Hex.decodeToByteArray(paramString1);
		} catch (Exception localException1) {
			throw new IllegalArgumentException("string 参数异常");
		}
		byte[] arrayOfByte2 = null;
		try {
			arrayOfByte2 = Hex.decodeToByteArray(paramString2);
		} catch (Exception localException2) {
			throw new IllegalArgumentException("key 参数异常");
		}
		return decryptWithECBToByteArray(arrayOfByte1, arrayOfByte2,
				paramString3);
	}

	public static String decryptWithECBToString(byte[] paramArrayOfByte1,
			byte[] paramArrayOfByte2) throws Exception {
		return decryptWithECBToString(paramArrayOfByte1, "UTF-8",
				paramArrayOfByte2, "PKCS5Padding");
	}

	public static String decryptWithECBToString(byte[] paramArrayOfByte1,
			String paramString, byte[] paramArrayOfByte2) throws Exception {
		return decryptWithECBToString(paramArrayOfByte1, paramString,
				paramArrayOfByte2, "PKCS5Padding");
	}

	public static String decryptWithECBToString(byte[] paramArrayOfByte1,
			String paramString1, byte[] paramArrayOfByte2, String paramString2)
			throws Exception {
		if (paramString1 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		try {
			return new String(decryptWithECBToByteArray(paramArrayOfByte1,
					paramArrayOfByte2, paramString2), paramString1);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	public static String decryptWithECBToString(String paramString1,
			String paramString2) throws Exception {
		return decryptWithECBToString(paramString1, "UTF-8", paramString2,
				"PKCS5Padding");
	}

	public static String decryptWithECBToString(String paramString1,
			String paramString2, String paramString3) throws Exception {
		return decryptWithECBToString(paramString1, paramString2, paramString3,
				"PKCS5Padding");
	}

	public static String decryptWithECBToString(String paramString1,
			String paramString2, String paramString3, String paramString4)
			throws Exception {
		if (paramString2 == null)
			throw new IllegalArgumentException("charsetName 参数异常");
		byte[] arrayOfByte1 = null;
		try {
			arrayOfByte1 = Hex.decodeToByteArray(paramString1);
		} catch (Exception localException1) {
			throw new IllegalArgumentException("string 参数异常");
		}
		byte[] arrayOfByte2 = null;
		try {
			arrayOfByte2 = Hex.decodeToByteArray(paramString3);
		} catch (Exception localException2) {
			throw new IllegalArgumentException("key 参数异常");
		}
		try {
			return new String(decryptWithECBToByteArray(arrayOfByte1,
					arrayOfByte2, paramString4), paramString2);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		throw new IllegalArgumentException("charsetName 参数异常");
	}

	private static DESKeySpec createDESKeySpec(byte[] paramArrayOfByte) {
		if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
			throw new IllegalArgumentException("key 参数异常");
		try {
			return new DESKeySpec(paramArrayOfByte);
		} catch (InvalidKeyException localInvalidKeyException) {
		}
		throw new IllegalArgumentException("key 参数异常");
	}

	private static SecretKey createSecretKey(String paramString,
			DESKeySpec paramDESKeySpec) {
		try {
			return SecretKeyFactory.getInstance(paramString).generateSecret(
					paramDESKeySpec);
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			throw new IllegalArgumentException("algorithmName 参数异常");
		} catch (InvalidKeySpecException localInvalidKeySpecException) {
		}
		throw new IllegalArgumentException("desKeySpec 参数异常");
	}

	static {
		Security.addProvider(new BouncyCastleProvider());
	}
}