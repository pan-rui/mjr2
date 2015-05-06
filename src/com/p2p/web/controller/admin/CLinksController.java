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
import com.p2p.model.CLinks;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.CLinksService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CLinksController extends BaseController {
	private static Log log = LogFactory.getLog(CLinksController.class);
	@Autowired
	private CLinksService cLinksService;

	@RequestMapping(value = "/queryCLinksList")
	public String queryCLinksList(CLinks cLinks) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		options.put("linkName", SearchOperator.LIKE);
		options.put("linkUrl", SearchOperator.LIKE);
		
		PageList pageList = cLinksService.queryCLinksPage(cLinks, options,
				pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());

		request.setAttribute("model", cLinks);
		return "admin/news/links-list";
	}

	@RequestMapping(value = "/addCLinksIndex")
	public String addCLinksIndex() {

		return "admin/news/links-add";
	}

	@RequestMapping(value = "/addCLinks")
	public String addCLinks(CLinks cLinks) {
		cLinks.setCreateTime(new Date());
		long result = cLinksService.addCLinks(cLinks);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/updateCLinksIndex")
	public String updateCLinksIndex(CLinks cLinks) {

		CLinks cLinksModel = cLinksService.getCLinks(cLinks);
		request.setAttribute("cLinksModel", cLinksModel);
		return "admin/news/links-update";
	}

	@RequestMapping(value = "/updateCLinks")
	public String updateCLinks(CLinks cLinks) {

		long result = cLinksService.updateCLinks(cLinks);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/queryCLinksIndex")
	public String queryCLinksIndex(CLinks cLinks) {
		CLinks cLinksModel = cLinksService.getCLinks(cLinks);
		request.setAttribute("cLinksModel", cLinksModel);
		return "admin/news/links-detail";
	}
}
