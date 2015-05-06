package com.p2p.web.controller.front;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.TInvestDto;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class AboutFLMController extends BaseController {
	private static Log log = LogFactory.getLog(AboutFLMController.class);
	
	@RequestMapping(value = "/aboutus")
	public String aboutUs() {
		return "front/about/about-us";
	}
	
	@RequestMapping(value = "/contactus")
	public String contactUs() {
		return "front/about/contact-us";
	}
	
	
	@RequestMapping(value = "/serviceAgreement")
	public String serviceAgreement() {
		return "front/about/service-agreement";
	}
	
	@RequestMapping(value = "/safe")
	public String safe() {
		return "front/about/safe";
	}
	
	@RequestMapping(value = "/platformPrinciple")
	public String platformPrinciple() {
		return "front/about/platformPrinciple";
	}
	
}
