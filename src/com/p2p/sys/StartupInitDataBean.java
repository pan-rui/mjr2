package com.p2p.sys;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.p2p.filter.UrlFilter;
import com.p2p.model.TBorrow;
import com.p2p.service.BParaService;
import com.p2p.service.TBorrowService;

@Component 
public class StartupInitDataBean implements InitializingBean, ServletContextAware {
	public static Log log = LogFactory.getLog(UrlFilter.class);
	@Autowired
	private BParaService bParaService;
	
	@Autowired
	private TBorrowService tBorrowService;

	@Override
	public void setServletContext(ServletContext servletContext) {

		log.info("---------项目启动初始化数据------------");
		log.info("项目编码格式为："+System.getProperty("file.encoding"));
		SpringContext.setSpringContext(WebApplicationContextUtils.getWebApplicationContext(servletContext)); 
		
		//初始化投标定时
		log.info("---------项目启动初始化定时发标------------");
		tBorrowService.initStartInvestBorrow();
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		log.info("---------afterPropertiesSet 项目启动初始化数据------------");
	}
	
	

}
