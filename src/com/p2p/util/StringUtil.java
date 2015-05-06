package com.p2p.util;

import java.util.ArrayList;
import java.util.List;

public final class StringUtil {
	/**
	 * 将指定字符串隐藏为星号
	 * @param src 字符串
	 * @param start 开始隐藏位置
	 * @param len 隐藏的多少位字符串
	 * @return
	 */
	public static String replaceStar(String src,int start,int len){
		
		StringBuffer buf = new StringBuffer();
		if(src == null){
			return buf.toString();
		}
		int strLen = src.length();
		if(strLen <= start){
			buf.append(src);
		}else{
			buf.append(src.substring(0, start-1));
			if(strLen < start+len){
				len = strLen-start+1;
			}
			for(int i = 0;i< len;i++){
				buf.append("*");
			}
			
			if(strLen >= start+len){
				buf.append(src.substring(start+len-1));
			}
		}
		return buf.toString();
	}
	
	public static boolean chackUserName(String uname){
		List<String> notName = new ArrayList<String>();
		notName.add("p2p");
		notName.add("fuck");
	
		return notName.contains(uname);
	}
	
	public static void main(String[] args){
		System.out.println(replaceStar("测试啦",2,1));
		System.out.println(replaceStar("12345678",8,3));
		System.out.println(replaceStar("12345678",9,3));
		
		System.out.println(replaceStar("12345678",3,3));
		System.out.println(replaceStar("12345678",3,6));
		System.out.println(replaceStar("12345678",3,8));
	}
}
