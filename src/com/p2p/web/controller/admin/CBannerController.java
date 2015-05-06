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
import com.p2p.model.CBanner;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.CBannerService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CBannerController extends BaseController {
	private static Log log = LogFactory.getLog(CBannerController.class);
	@Autowired
	private CBannerService cBannerService;

	@RequestMapping(value = "/queryCBannerList")
	public String queryCBannerList(CBanner cBanner) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		options.put("paraName", SearchOperator.LIKE);
		options.put("paraCode", SearchOperator.LIKE);
		options.put("isDelete", SearchOperator.EQ);
		PageList pageList = cBannerService.queryCBannerPage(cBanner, options,
				pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());

		request.setAttribute("model", cBanner);
		return "admin/news/banner-list";
	}

	@RequestMapping(value = "/addCBannerIndex")
	public String addCBannerIndex() {

		return "admin/news/banner-add";
	}

	@RequestMapping(value = "/addCBanner")
	public String addCBanner(CBanner cBanner) {
		cBanner.setCreateTime(new Date());
		long result = cBannerService.addCBanner(cBanner);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/updateCBannerIndex")
	public String updateCBannerIndex(CBanner cBanner) {

		CBanner cBannerModel = cBannerService.getCBanner(cBanner);
		request.setAttribute("cBannerModel", cBannerModel);
		return "admin/news/banner-update";
	}

	@RequestMapping(value = "/updateCBanner")
	public String updateCBanner(CBanner cBanner) {

		long result = cBannerService.updateCBanner(cBanner);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/queryCBannerIndex")
	public String queryCBannerIndex(CBanner cBanner) {
		CBanner cBannerModel = cBannerService.getCBanner(cBanner);
		request.setAttribute("cBannerModel", cBannerModel);
		return "admin/news/banner-detail";
	}
}
