package com.p2p.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.p2p.constans.SysConstans;
import com.p2p.model.CUser;
import com.p2p.model.TThridAccount;
import com.p2p.service.BUserActionLogService;
import com.p2p.service.TThridAccountService;
import com.p2p.util.ServletUtil;

/**
 * 宝付是否开户拦截器
 * 
 * @author
 * @Create Jun 3, 2011
 * 
 */

public class BaofooInterceptor implements HandlerInterceptor {

	public static Log log = LogFactory.getLog(BaofooInterceptor.class);
	@Autowired
	private BUserActionLogService bUserActionLogService;
	
	@Autowired
	private TThridAccountService tThridAccountService;
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		log.info("=====baofoo===afterCompletion=====");
		log.error(obj,e);
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView view)
			throws Exception {
		log.info("=====baofoo===postHandle=====");
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) {
		log.info("==baofoo===preHandle==log===");

		
		try {
			Long userId = null;
			CUser cUser = (CUser) request.getSession().getAttribute(
					SysConstans.USER_SESSION);
			if (cUser != null) {
				userId = cUser.getId();
			} else {
				response.sendRedirect(ServletUtil.getServerPath(request)
						+ "/loginIndex.html");
			}
			TThridAccount tThridAccount = tThridAccountService
					.getThridAccountByUserId(userId);
			if (tThridAccount == null || !tThridAccount.getIsOpen().equals(1)) {

				response.sendRedirect(ServletUtil.getServerPath(request)
						+ "/regPersonInfo.html");

			}
		} catch (IOException e) {
			log.error("宝付跳转失败", e);
		}  
		return true;
	}

}
