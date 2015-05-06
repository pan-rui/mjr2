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
import com.p2p.model.CMediaReport;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.CMediaReportService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CMediaReportController extends BaseController {
	private static Log log = LogFactory.getLog(CMediaReportController.class);
	@Autowired
	private CMediaReportService cMediaReportService;

	@RequestMapping(value = "/queryCMediaReportList")
	public String queryCMediaReportList(CMediaReport cMediaReport) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		options.put("title", SearchOperator.LIKE);
		options.put("src", SearchOperator.LIKE);
		options.put("isDelete", SearchOperator.EQ);
		PageList pageList = cMediaReportService.queryCMediaReportPage(cMediaReport, options,
				pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());

		request.setAttribute("model", cMediaReport);
		return "admin/news/media-report-list";
	}

	@RequestMapping(value = "/addCMediaReportIndex")
	public String addCMediaReportIndex() {

		return "admin/news/media-report-add";
	}

	@RequestMapping(value = "/addCMediaReport")
	public String addCMediaReport(CMediaReport cMediaReport) {
		cMediaReport.setAdminId(this.getBAdmin().getId());
		cMediaReport.setCreateTime(new Date());
		long result = cMediaReportService.addCMediaReport(cMediaReport);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/updateCMediaReportIndex")
	public String updateCMediaReportIndex(CMediaReport cMediaReport) {

		CMediaReport cMediaReportModel = cMediaReportService.getCMediaReport(cMediaReport);
		request.setAttribute("cMediaReportModel", cMediaReportModel);
		return "admin/news/media-report-update";
	}

	@RequestMapping(value = "/updateCMediaReport")
	public String updateCMediaReport(CMediaReport cMediaReport) {

		long result = cMediaReportService.updateCMediaReport(cMediaReport);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/queryCMediaReportIndex")
	public String queryCMediaReportIndex(CMediaReport cMediaReport) {
		CMediaReport cMediaReportModel = cMediaReportService.getCMediaReport(cMediaReport);
		request.setAttribute("cMediaReportModel", cMediaReportModel);
		return "admin/news/media-report-detail";
	}
}
