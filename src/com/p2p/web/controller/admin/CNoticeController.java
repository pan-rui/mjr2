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
import com.p2p.model.CNotice;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.CNoticeService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CNoticeController extends BaseController {
	private static Log log = LogFactory.getLog(CNoticeController.class);
	@Autowired
	private CNoticeService cNoticeService;

	@RequestMapping(value = "/queryCNoticeList")
	public String queryCNoticeList(CNotice cNotice) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		options.put("noticeTitle", SearchOperator.LIKE);
		options.put("isDelete", SearchOperator.EQ);
		PageList pageList = cNoticeService.queryCNoticePage(cNotice, options,
				pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());

		request.setAttribute("model", cNotice);
		return "admin/news/notice-list";
	}

	@RequestMapping(value = "/addCNoticeIndex")
	public String addCNoticeIndex() {

		return "admin/news/notice-add";
	}

	@RequestMapping(value = "/addCNotice")
	public String addCNotice(CNotice cNotice) {
		cNotice.setCreateTime(new Date());
		cNotice.setAdminId(this.getBAdmin().getId());
		long result = cNoticeService.addCNotice(cNotice);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/updateCNoticeIndex")
	public String updateCNoticeIndex(CNotice cNotice) {

		CNotice cNoticeModel = cNoticeService.getCNotice(cNotice);
		request.setAttribute("cNoticeModel", cNoticeModel);
		return "admin/news/notice-update";
	}

	@RequestMapping(value = "/updateCNotice")
	public String updateCNotice(CNotice cNotice) {

		long result = cNoticeService.updateCNotice(cNotice);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/queryCNoticeIndex")
	public String queryCNoticeIndex(CNotice cNotice) {
		CNotice cNoticeModel = cNoticeService.getCNotice(cNotice);
		request.setAttribute("cNoticeModel", cNoticeModel);
		return "admin/news/notice-detail";
	}
}
