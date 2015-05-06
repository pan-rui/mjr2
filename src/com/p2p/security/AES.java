package com.p2p.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

/**
 * AES加密，解密工具类
 * 
 * @author Administrator
 * 
 */
public final class AES {

	private static Cipher getCipher(int cipherMode, String key, String params)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

		keyGenerator.init(128, new SecureRandom(key.getBytes()));

		SecretKey secretKey = keyGenerator.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器

		AlgorithmParameterSpec paramSpec = new IvParameterSpec(
				params.getBytes());
		cipher.init(cipherMode, keySpec, paramSpec);

		return cipher;
	}

	/**
	 * AES加密
	 * 
	 * @param content
	 *            加密内容
	 * @param key
	 *            加密key
	 * @return
	 */
	public final static String encrypt(String content, String key) {
		return encrypt(content, key, key, "UTF-8");
	}

	/**
	 * AES解密
	 * 
	 * @param content
	 *            加密内容
	 * @param key
	 *            加密key
	 * @return
	 */
	public final static String decrypt(String content, String key) {
		return decrypt(content, key, key, "UTF-8");
	}

	/**
	 * 
	 * @param content
	 * @param key
	 *            加密密钥
	 * @param params
	 *            算法参数
	 * @param encoding
	 * @return
	 */
	public final static String encrypt(String content, String key,
			String params, String encoding) {
		String aes = "";
		try {
			Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, key, params);
			byte[] contentBytes = content.getBytes(encoding);
			byte[] aesBytes = cipher.doFinal(contentBytes);
			aes = HexUtil.bytesToHexString(aesBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return aes;
	}

	public final static String decrypt(String content, String key,
			String params, String encoding) {
		String aes = "";
		try {
			Cipher cipher = getCipher(Cipher.DECRYPT_MODE, key, params);
			byte[] contentBytes = HexUtil.hexToBinary(content);
			byte[] aesBytes = cipher.doFinal(contentBytes);
			aes = new String(aesBytes, encoding);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return aes;
	}

	@Test
	public void testEncrypt() {
		String aes = AES.encrypt("你好啊，fdsaf112e21真的很fdsafasd好哦fdasfdas",
				"12345678", "3212121212121212", "UTF-8");
		System.out.println(aes);
		System.out.println(AES.decrypt(aes, "12345678", "3212121212121212",
				"UTF-8"));

		String aes2 = AES.encrypt("你好啊，fdsaf112e21真的很fdsafasd好哦fdasfdas", "1",
				"3212121212121212", "UTF-8");
		System.out.println(aes2);
		System.out.println(AES.decrypt(aes2, "1", "3212121212121212", "UTF-8"));
	}
}
