package com.p2p.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.gson.Gson;
import com.p2p.constans.SysConstans;
import com.p2p.model.BAdmin;
import com.p2p.model.CUser;

public abstract class BaseController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	// 页面表单存放数据
	protected Map<String, String> paramMap = new HashMap<String, String>();

	protected PageBounds pageBounds;

	@ModelAttribute
	public void setReqAndResp(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();

		String start = request.getParameter("curPage");
		String limit = request.getParameter("pageSize");

		if (StringUtils.isBlank(start)) {
			start = "1";
		}
		if (StringUtils.isBlank(limit)) {
			limit = "10";
		}
		if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(limit)) {
			pageBounds = new PageBounds(Integer.valueOf(start),
					Integer.valueOf(limit));
		}
	}

	
	protected Map<String, String> getRequestMap() {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement();
			map.put(name, request.getParameter(name));
		}
		return map;
	}

	public void toWrite(String str) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = null;
			pw = response.getWriter();
			pw.write(str);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void writeJson(Object obj) {

		Gson gson = new Gson();
		String json = gson.toJson(obj);
		System.out.println("json==>" + json);
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/x-json;charset=UTF-8");
			PrintWriter pw = null;
			pw = response.getWriter();
			pw.write(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	protected void write(String content) {

		
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = null;
			pw = response.getWriter();
			pw.write(content);
			pw.flush();
			pw.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public BAdmin getBAdmin() {
		BAdmin bAdmin = (BAdmin) session
				.getAttribute(SysConstans.ADMIN_SESSION);
		return bAdmin;
	}

	public CUser getCUser() {
		CUser cUser = (CUser) session.getAttribute(SysConstans.USER_SESSION);
		return cUser;
	}


	public Map<String, String> getParamMap() {
		return paramMap;
	}


	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}
	
}