package com.p2p.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

/**
 * MD5工具类
 * @author Hai
 *
 */
public final class MD5 {
	
	/**
	 * 将数据加密MD5值(默认编码格式为UTF-8)
	 * @param content 需要MD5的值
	 * @return
	 */
	public final static String encrypt(String content) {
		return encrypt(content, "UTF-8");
	}

	/**
	 * 将数据加密MD5值
	 * @param content 需要MD5的值
	 * @param encoding 内容的编码格式 
	 * @return 返回MD5值
	 */
	public final static String encrypt(String content, String encoding) {
		String md5 = null;
		if (content == null) {
			content = "";
		}
		if (encoding == null || "".equals(encoding)) {
			encoding = "UTF-8";
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = content.getBytes(encoding);
			md.update(bytes);
			byte md5Bytes[] = md.digest();
			md5 = HexUtil.bytesToHexString(md5Bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md5;
	}

	
	@Test
	public void test(){
		
		System.out.println("MD5==>"+MD5.encrypt("12345A6", "UTF-8"));
		System.out.println("MD5==>"+MD5.encrypt("123456"+"1111", "GBK"));
	
	}
}
