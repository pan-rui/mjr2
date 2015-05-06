package com.p2p.util.file;

import java.io.File;

public class TestFTP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try { 
			File[] files = new File[3];
			files[0] = new File(
					"E:/Tank/开发工具/JsonViewerPackage/Fiddler/FiddlerJsonViewer.dll");
			files[1] = new File(
					"E:/Tank/开发工具/JsonViewerPackage/Fiddler/JsonViewer.dll");
			files[2] = new File(
					"E:/Tank/开发工具/JsonViewerPackage/Fiddler/Newtonsoft.Json.dll");

			//upLoad("192.168.0.189", 21, "ftp", "ftp", "/upload", files);
		 
			//downLoad("192.168.0.189", 21, "ftp", "ftp", "/upload", "1321344646781_FiddlerJsonViewer.dll", "E:/Tank/");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
