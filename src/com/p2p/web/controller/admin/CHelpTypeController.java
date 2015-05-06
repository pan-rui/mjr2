package com.p2p.web.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.model.CHelpType;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.CHelpTypeService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CHelpTypeController extends BaseController {
	private static Log log = LogFactory.getLog(CHelpTypeController.class);
	@Autowired
	private CHelpTypeService cHelpTypeService;

	@RequestMapping(value = "/queryCHelpTypeList")
	public String queryCHelpTypeList(CHelpType cHelpType) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		options.put("helpTypeName", SearchOperator.LIKE);
		
		PageList pageList = cHelpTypeService.queryCHelpTypePage(cHelpType, options,
				pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());

		request.setAttribute("model", cHelpType);
		return "admin/news/helptype-list";
	}

	@RequestMapping(value = "/addCHelpTypeIndex")
	public String addCHelpTypeIndex() {

		return "admin/news/helptype-add";
	}

	@RequestMapping(value = "/addCHelpType")
	public String addCHelpType(CHelpType cHelpType) {
		cHelpType.setCreateTime(new Date());
		long result = cHelpTypeService.addCHelpType(cHelpType);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/updateCHelpTypeIndex")
	public String updateCHelpTypeIndex(CHelpType cHelpType) {

		CHelpType cHelpTypeModel = cHelpTypeService.getCHelpType(cHelpType);
		request.setAttribute("cHelpTypeModel", cHelpTypeModel);
		return "admin/news/helptype-update";
	}

	@RequestMapping(value = "/updateCHelpType")
	public String updateCHelpType(CHelpType cHelpType) {

		long result = cHelpTypeService.updateCHelpType(cHelpType);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/queryCHelpTypeIndex")
	public String queryCHelpTypeIndex(CHelpType cHelpType) {
		CHelpType cHelpTypeModel = cHelpTypeService.getCHelpType(cHelpType);
		request.setAttribute("cHelpTypeModel", cHelpTypeModel);
		return "admin/news/helptype-detail";
	}
}
