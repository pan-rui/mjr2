package com.p2p.web.controller.admin;

import java.util.List;
import java.util.Map;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.model.BAdminActionLog;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.BAdminActionLogService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class BAdminActionLogController extends BaseController {


	@Autowired
	private BAdminActionLogService bAdminActionLogService;
 
	@RequestMapping(value = "/queryBAdminActionLogList")
	public String queryBAdminActionLogList (BAdminActionLog bAdminActionLog) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = bAdminActionLogService.queryBAdminActionLogPage(bAdminActionLog, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", bAdminActionLog);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addBAdminActionLogIndex")
	public String addBAdminActionLogIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addBAdminActionLog")
	public String addBAdminActionLog (BAdminActionLog bAdminActionLog) {
		long result = bAdminActionLogService.addBAdminActionLog(bAdminActionLog);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateBAdminActionLogIndex")
	public String updateBAdminActionLogIndex  (BAdminActionLog bAdminActionLog) {
		BAdminActionLog bAdminActionLogModel = bAdminActionLogService.getBAdminActionLog(bAdminActionLog);
		request.setAttribute("bAdminActionLogModel", bAdminActionLogModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateBAdminActionLog")
	public String updateBAdminActionLog (BAdminActionLog bAdminActionLog) {
		long result = bAdminActionLogService.updateBAdminActionLog(bAdminActionLog);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryBAdminActionLogIndex")
	public String queryBAdminActionLogIndex  (BAdminActionLog bAdminActionLog) {
		BAdminActionLog bAdminActionLogModel = bAdminActionLogService.getBAdminActionLog(bAdminActionLog);
		request.setAttribute("bAdminActionLogModel", bAdminActionLogModel);
		return "admin/xx/xx-detail";
	}
 }