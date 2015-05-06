package com.p2p.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.Request;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.p2p.constans.SysConstans;
import com.p2p.model.BAdmin;
import com.p2p.model.CUser;
import com.p2p.util.ServletUtil;

/**
 * 后台登录验证拦截器
 * 
 * @author
 * @Create Jun 3, 2011
 * 
 */
public class UserLoginSessionInterceptor implements HandlerInterceptor {

	public static Log log = LogFactory.getLog(UserLoginSessionInterceptor.class);

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		log.info("=====afterCompletion=====");

	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView view)
			throws Exception {
		log.info("=====postHandle=====");
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		log.info("=====preHandle=====");
		log.info("[" + getRemortIP(request) + "]用户request来自："
				+ request.getHeader("Referer"));
		log.info("[" + getRemortIP(request) + "]用户request访问："
				+ request.getRequestURL());
		HttpSession session = request.getSession();
		String action = request.getRequestURI();
		CUser cUser = (CUser) session.getAttribute(SysConstans.USER_SESSION);
		if (null == cUser) {
			if (isAjaxRequest(request)) {
				response.setContentType("text/html");
				response.getWriter().print(SysConstans.NO_LOGIN);
				response.getWriter().flush();
				log.info("ajax请求拦截No Login");
				return false;
			} else {
				log.info("普通请求拦截No Login");
				
				response.sendRedirect(ServletUtil.getServerPath(request)+"/loginIndex.html");  
				return false;
			}
		}

		
		if(action.contains("loginIndex")||action.contains("regIndex")){
			
			response.sendRedirect(ServletUtil.getServerPath(request)+"/index.html");
		}
		action = action.substring(action.lastIndexOf("/") + 1);
		log.info("action===>" + action);

		return true;
	}

	private String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
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
