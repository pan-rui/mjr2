package com.p2p.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.p2p.filter.UrlFilter;
import com.p2p.model.BPara;
import com.p2p.service.BParaService;

public class StartupListener implements ServletContextListener {
	public static Log log = LogFactory.getLog(UrlFilter.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		log.info("---------项目启动------------");
		log.info("项目编码格式为："+System.getProperty("file.encoding"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		log.info("---------项目停止------------");

	}

}
