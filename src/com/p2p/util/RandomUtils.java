package com.p2p.util;

import java.io.IOException;
import java.util.Random;

/**
 * 随机码
 * @author Administrator
 *
 */
public class RandomUtils {
	public static String createRadom(){
		Random radmon = new Random();
		Integer num = radmon.nextInt(900000);
		num+=100000;
		return num.toString();
	}
	
	public static String createFourRadom(){
		Random radmon = new Random();
		Integer num = radmon.nextInt(9000);
		num+=1000;
		return num.toString();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(createRadom());
	}
}
