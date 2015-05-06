package com.p2p.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.p2p.util.ServletUtil;

public class UrlFilter implements Filter {
	public static Log log = LogFactory.getLog(UrlFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String action = req.getRequestURI();
		log.info("UrlFilter===action==>" + action);
		if (!action.contains("callbackcash")&&!action.endsWith("/")&&!action.contains(".")&&!action.contains("admin/")) {
			resp.sendRedirect(ServletUtil.getServerPath(req) + "/index.html");
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
