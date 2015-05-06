package com.p2p.web.controller.front;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.CHelpType;
import com.p2p.service.CHelpService;
import com.p2p.service.CHelpTypeService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class HelpController extends BaseController {
	private static Log log = LogFactory.getLog(HelpController.class);
	
	@Autowired
	private CHelpService cHelpService;
	
	@Autowired
	private CHelpTypeService cHelpTypeService;
	
	@RequestMapping(value = "/helpInfo")
	public String helpInfo() throws Exception {
		try{
		List<CHelpType> htlist = cHelpTypeService.queryAllType();
		CHelpType htmp = htlist.get(0);
		PageList pageList  = cHelpService.queryHelpByType(htmp.getId(),pageBounds);
		request.setAttribute("whenHelpType", htmp);
		request.setAttribute("HTlist", htlist);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		}catch (Exception e) {
			log.error("帮助中心初始化异常：",e);
		}
		return "front/help/help-list";
	}

	@RequestMapping(value = "/helpShow")
	public String helpShow() throws Exception {
		String helpType = request.getParameter("typeId");
		if(StringUtils.isBlank(helpType)){
			helpInfo();
			return null;
		}
		CHelpType cHelpType = new CHelpType();
		cHelpType.setId(Long.parseLong(helpType));
		try{
		List<CHelpType> htlist = cHelpTypeService.queryAllType();
		CHelpType htmp = cHelpTypeService.getCHelpType(cHelpType);
		if(htmp==null){
			helpInfo();
			return null;
		}
		PageList pageList  = cHelpService.queryHelpByType(htmp.getId(),pageBounds);
		request.setAttribute("whenHelpType", htmp);
		request.setAttribute("HTlist", htlist);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		}catch (Exception e) {
			log.error("帮助中心初始化异常：",e);
		}
		return "front/help/help-list";
	}

}
