package com.p2p.interceptor;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.p2p.constans.SysConstans;
import com.p2p.model.BAdmin;
import com.p2p.model.BAdminActionLog;
import com.p2p.service.BAdminActionLogService;
import com.p2p.util.ServletUtil;

/**
 * 日志拦截器
 * 
 * @author
 * @Create Jun 3, 2011
 * 
 */

public class AdminOperLogInterceptor implements HandlerInterceptor {

	public static Log log = LogFactory.getLog(AdminOperLogInterceptor.class);
	@Autowired
	private BAdminActionLogService bAdminActionLogService;
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		log.info("=====afterCompletion=====");
		log.error(obj,e);
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView view)
			throws Exception {
		log.info("=====postHandle=====");
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) {
		log.info("=====preHandle==log===");
//		log.info("[" + getRemortIP(request) + "]用户request来自："
//				+ request.getHeader("Referer"));
//		log.info("[" + getRemortIP(request) + "]用户request访问："
//				+ request.getRequestURL());
		String referer = request.getHeader("Referer");
		String action = request.getRequestURI();
		log.info("action==>"+action);
		
		
		Long adminId = null;
		BAdmin bAdmin = (BAdmin)request.getSession().getAttribute(SysConstans.ADMIN_SESSION);
		if(bAdmin != null){
			adminId = bAdmin.getId();
		}
		BAdminActionLog bAdminActionLog = new BAdminActionLog();
		bAdminActionLog.setAction(action);
		bAdminActionLog.setRefAction(referer);
		bAdminActionLog.setAdminId(adminId);
		bAdminActionLog.setCreateTime(new Date());
		StringBuffer actionPara = new StringBuffer();
		Enumeration enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()){
			String name = (String)enumeration.nextElement();
			String value= request.getParameter(name);
			actionPara.append(name);
			actionPara.append("=");
			actionPara.append(value);
			actionPara.append("&");
		}
		int index = actionPara.lastIndexOf("&");
		if(index>0){
			actionPara.deleteCharAt(index);
		}
		bAdminActionLog.setIp(ServletUtil.getRemortIp(request));
		bAdminActionLog.setActionPara(actionPara.toString());
		try{
			bAdminActionLogService.addBAdminActionLog(bAdminActionLog);
		}catch(Exception e){
			log.error("后台用户行为异常",e);
		}
		return true;
	}



	/**
	 * 根据请求头数据判断是否是Ajax请求
	 * 
	 * @return
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header)) {
			return true;
		}
		return false;
	}
	
}
