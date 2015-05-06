package com.p2p.web.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.model.CHelp;
import com.p2p.model.CHelpType;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.CHelpService;
import com.p2p.service.CHelpTypeService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CHelpController extends BaseController {
	private static Log log = LogFactory.getLog(CHelpController.class);
	@Autowired
	private CHelpService cHelpService;

	@Autowired
	private CHelpTypeService cHelpTypeService;
	
	@RequestMapping(value = "/queryCHelpList")
	public String queryCHelpList(CHelp cHelp) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		options.put("questionName", SearchOperator.LIKE);
		options.put("helpTypeId", SearchOperator.EQ);
		PageList pageList = cHelpService.queryCHelpPage(cHelp, options,
				pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());

		request.setAttribute("model", cHelp);
		setHelpType();
		return "admin/news/help-list";
	}

	@RequestMapping(value = "/addCHelpIndex")
	public String addCHelpIndex() {
		setHelpType();
		return "admin/news/help-add";
	}

	@RequestMapping(value = "/addCHelp")
	public String addCHelp(CHelp cHelp) {
		cHelp.setCreateTime(new Date());
		long result = cHelpService.addCHelp(cHelp);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
	
	private void setHelpType(){
		CHelpType cHelpType = new CHelpType();
		List<CHelpType> helpTypeList = cHelpTypeService.queryCHelpTypeList(cHelpType);
		request.setAttribute("helpTypeList", helpTypeList);
	}

	@RequestMapping(value = "/updateCHelpIndex")
	public String updateCHelpIndex(CHelp cHelp) {
		setHelpType();
		CHelp cHelpModel = cHelpService.getCHelp(cHelp);
		request.setAttribute("cHelpModel", cHelpModel);
		return "admin/news/help-update";
	}

	@RequestMapping(value = "/updateCHelp")
	public String updateCHelp(CHelp cHelp) {

		long result = cHelpService.updateCHelp(cHelp);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/queryCHelpIndex")
	public String queryCHelpIndex(CHelp cHelp) {
		setHelpType();
		CHelp cHelpModel = cHelpService.getCHelp(cHelp);
		request.setAttribute("cHelpModel", cHelpModel);
		return "admin/news/help-detail";
	}
}
