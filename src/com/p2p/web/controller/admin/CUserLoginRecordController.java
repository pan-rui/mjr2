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
import com.p2p.model.CUserLoginRecord;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.CUserLoginRecordService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CUserLoginRecordController extends BaseController {

	private static Log log = LogFactory.getLog(CUserLoginRecordController.class);
	@Autowired
	private CUserLoginRecordService cUserLoginRecordService;
 
	@RequestMapping(value = "/queryCUserLoginRecordList")
	public String queryCUserLoginRecordList (CUserLoginRecord cUserLoginRecord) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = cUserLoginRecordService.queryCUserLoginRecordPage(cUserLoginRecord, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", cUserLoginRecord);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addCUserLoginRecordIndex")
	public String addCUserLoginRecordIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addCUserLoginRecord")
	public String addCUserLoginRecord (CUserLoginRecord cUserLoginRecord) {
		long result = cUserLoginRecordService.addCUserLoginRecord(cUserLoginRecord);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateCUserLoginRecordIndex")
	public String updateCUserLoginRecordIndex  (CUserLoginRecord cUserLoginRecord) {
		CUserLoginRecord cUserLoginRecordModel = cUserLoginRecordService.getCUserLoginRecord(cUserLoginRecord);
		request.setAttribute("cUserLoginRecordModel", cUserLoginRecordModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateCUserLoginRecord")
	public String updateCUserLoginRecord (CUserLoginRecord cUserLoginRecord) {
		long result = cUserLoginRecordService.updateCUserLoginRecord(cUserLoginRecord);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryCUserLoginRecordIndex")
	public String queryCUserLoginRecordIndex  (CUserLoginRecord cUserLoginRecord) {
		CUserLoginRecord cUserLoginRecordModel = cUserLoginRecordService.getCUserLoginRecord(cUserLoginRecord);
		request.setAttribute("cUserLoginRecordModel", cUserLoginRecordModel);
		return "admin/xx/xx-detail";
	}
 }