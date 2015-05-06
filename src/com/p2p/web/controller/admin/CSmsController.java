package com.p2p.web.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.CSms;
import com.p2p.service.CSmsService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CSmsController extends BaseController {
	private static Log log = LogFactory.getLog(CSmsController.class);
	@Autowired
	private CSmsService cSmsService;
 
	@RequestMapping(value = "/queryCSmsList")
	public String queryCSmsList (CSms cSms) {

		PageList pageList = cSmsService.queryCSmsPage(cSms,pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", cSms);
		return "admin/user/sms-list";
	}
 
	@RequestMapping(value = "/queryCSmsIndex")
	public String queryCSmsIndex  (CSms cSms) {
		PageList pageList = cSmsService.queryCSmsPage(cSms, pageBounds);
		if (pageList!=null) {
			request.setAttribute("cSmsModel", pageList.get(0));
		}
		return "admin/user/sms-detail";
	}
 }