package com.p2p.util.file;

import java.util.ResourceBundle;

public class FTPServer {

	private static ResourceBundle cache = null;
	static{
		try {
			cache = ResourceBundle.getBundle("ftpserver");
		} catch (RuntimeException e){
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return cache.getString(key);
	}
	public static void main(String args[]) {
		 
		System.out.println(FTPServer.getValue("ftp.url"));
		System.out.println(FTPServer.getValue("ftp.port"));
	}
}
