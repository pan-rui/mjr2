package com.p2p.sys;

import org.springframework.web.context.WebApplicationContext;

public class SpringContext {
	private static WebApplicationContext springContext;

	public static WebApplicationContext getSpringContext() {
		return springContext;
	}

	public static void setSpringContext(WebApplicationContext springContext) {
		if(SpringContext.springContext==null){
			SpringContext.springContext = springContext;
		}
		
	}
	
	
}
