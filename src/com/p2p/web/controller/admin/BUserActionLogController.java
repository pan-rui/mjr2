package com.p2p.web.controller.admin;

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
import com.p2p.model.BUserActionLog;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.BUserActionLogService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class BUserActionLogController extends BaseController {

	private static Log log = LogFactory.getLog(BUserActionLogController.class);
	@Autowired
	private BUserActionLogService bUserActionLogService;
 
	@RequestMapping(value = "/queryBUserActionLogList")
	public String queryBUserActionLogList (BUserActionLog bUserActionLog) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = bUserActionLogService.queryBUserActionLogPage(bUserActionLog, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", bUserActionLog);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addBUserActionLogIndex")
	public String addBUserActionLogIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addBUserActionLog")
	public String addBUserActionLog (BUserActionLog bUserActionLog) {
		long result = bUserActionLogService.addBUserActionLog(bUserActionLog);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateBUserActionLogIndex")
	public String updateBUserActionLogIndex  (BUserActionLog bUserActionLog) {
		BUserActionLog bUserActionLogModel = bUserActionLogService.getBUserActionLog(bUserActionLog);
		request.setAttribute("bUserActionLogModel", bUserActionLogModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateBUserActionLog")
	public String updateBUserActionLog (BUserActionLog bUserActionLog) {
		long result = bUserActionLogService.updateBUserActionLog(bUserActionLog);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryBUserActionLogIndex")
	public String queryBUserActionLogIndex  (BUserActionLog bUserActionLog) {
		BUserActionLog bUserActionLogModel = bUserActionLogService.getBUserActionLog(bUserActionLog);
		request.setAttribute("bUserActionLogModel", bUserActionLogModel);
		return "admin/xx/xx-detail";
	}
 }