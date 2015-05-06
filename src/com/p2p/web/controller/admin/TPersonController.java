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
import com.p2p.model.TPerson;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TPersonService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TPersonController extends BaseController {
	private static Log log = LogFactory.getLog(TPersonController.class);
	@Autowired
	private TPersonService tPersonService;
 
	@RequestMapping(value = "/queryTPersonList")
	public String queryTPersonList (TPerson tPerson) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tPersonService.queryTPersonPage(tPerson, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tPerson);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addTPersonIndex")
	public String addTPersonIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTPerson")
	public String addTPerson (TPerson tPerson) {
		long result = tPersonService.addTPerson(tPerson);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTPersonIndex")
	public String updateTPersonIndex  (TPerson tPerson) {
		TPerson tPersonModel = tPersonService.getTPerson(tPerson);
		request.setAttribute("tPersonModel", tPersonModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTPerson")
	public String updateTPerson (TPerson tPerson) {
		long result = tPersonService.updateTPerson(tPerson);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTPersonIndex")
	public String queryTPersonIndex  (TPerson tPerson) {
		TPerson tPersonModel = tPersonService.getTPerson(tPerson);
		request.setAttribute("tPersonModel", tPersonModel);
		return "admin/xx/xx-detail";
	}
 }