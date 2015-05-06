package com.p2p.web.controller.admin;

import java.util.Map;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.model.CSmsRecord;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.CSmsRecordService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CSmsRecordController extends BaseController {


	@Autowired
	private CSmsRecordService cSmsRecordService;
 
	@RequestMapping(value = "/queryCSmsRecordList")
	public String queryCSmsRecordList (CSmsRecord cSmsRecord) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = cSmsRecordService.queryCSmsRecordPage(cSmsRecord, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", cSmsRecord);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addCSmsRecordIndex")
	public String addCSmsRecordIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addCSmsRecord")
	public String addCSmsRecord (CSmsRecord cSmsRecord) {
		long result = cSmsRecordService.addCSmsRecord(cSmsRecord);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateCSmsRecordIndex")
	public String updateCSmsRecordIndex  (CSmsRecord cSmsRecord) {
		CSmsRecord cSmsRecordModel = cSmsRecordService.getCSmsRecord(cSmsRecord);
		request.setAttribute("cSmsRecordModel", cSmsRecordModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateCSmsRecord")
	public String updateCSmsRecord (CSmsRecord cSmsRecord) {
		long result = cSmsRecordService.updateCSmsRecord(cSmsRecord);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryCSmsRecordIndex")
	public String queryCSmsRecordIndex  (CSmsRecord cSmsRecord) {
		CSmsRecord cSmsRecordModel = cSmsRecordService.getCSmsRecord(cSmsRecord);
		request.setAttribute("cSmsRecordModel", cSmsRecordModel);
		return "admin/xx/xx-detail";
	}
 }