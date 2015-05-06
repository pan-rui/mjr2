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
import com.p2p.model.TNoticeSetting;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TNoticeSettingService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TNoticeSettingController extends BaseController {


	@Autowired
	private TNoticeSettingService tNoticeSettingService;
 
	@RequestMapping(value = "/queryTNoticeSettingList")
	public String queryTNoticeSettingList (TNoticeSetting tNoticeSetting) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tNoticeSettingService.queryTNoticeSettingPage(tNoticeSetting, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tNoticeSetting);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addTNoticeSettingIndex")
	public String addTNoticeSettingIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTNoticeSetting")
	public String addTNoticeSetting (TNoticeSetting tNoticeSetting) {
		long result = tNoticeSettingService.addTNoticeSetting(tNoticeSetting);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTNoticeSettingIndex")
	public String updateTNoticeSettingIndex  (TNoticeSetting tNoticeSetting) {
		TNoticeSetting tNoticeSettingModel = tNoticeSettingService.getTNoticeSetting(tNoticeSetting);
		request.setAttribute("tNoticeSettingModel", tNoticeSettingModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTNoticeSetting")
	public String updateTNoticeSetting (TNoticeSetting tNoticeSetting) {
		long result = tNoticeSettingService.updateTNoticeSetting(tNoticeSetting);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTNoticeSettingIndex")
	public String queryTNoticeSettingIndex  (TNoticeSetting tNoticeSetting) {
		TNoticeSetting tNoticeSettingModel = tNoticeSettingService.getTNoticeSetting(tNoticeSetting);
		request.setAttribute("tNoticeSettingModel", tNoticeSettingModel);
		return "admin/xx/xx-detail";
	}
 }