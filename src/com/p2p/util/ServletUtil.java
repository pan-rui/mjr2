package com.p2p.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

	/**
	 * @Description: 获取根目录
	 */
	public static String getServerRootDirectory(HttpServletRequest request) {
		return request.getSession().getServletContext()
				.getRealPath(File.separator);
	}
	
	
	public static String getServerPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getContextPath();
	}

	public static String getRemortIp(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
	/**
	 * 根据request获取用户IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		String localIP = "127.0.0.1";
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}	
}
