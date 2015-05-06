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
import com.p2p.model.TJobBorrower;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TJobBorrowerService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TJobBorrowerController extends BaseController {


	@Autowired
	private TJobBorrowerService tJobBorrowerService;
 
	@RequestMapping(value = "/queryTJobBorrowerList")
	public String queryTJobBorrowerList (TJobBorrower tJobBorrower) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tJobBorrowerService.queryTJobBorrowerPage(tJobBorrower, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tJobBorrower);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addTJobBorrowerIndex")
	public String addTJobBorrowerIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTJobBorrower")
	public String addTJobBorrower (TJobBorrower tJobBorrower) {
		long result = tJobBorrowerService.addTJobBorrower(tJobBorrower);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTJobBorrowerIndex")
	public String updateTJobBorrowerIndex  (TJobBorrower tJobBorrower) {
		TJobBorrower tJobBorrowerModel = tJobBorrowerService.getTJobBorrower(tJobBorrower);
		request.setAttribute("tJobBorrowerModel", tJobBorrowerModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTJobBorrower")
	public String updateTJobBorrower (TJobBorrower tJobBorrower) {
		long result = tJobBorrowerService.updateTJobBorrower(tJobBorrower);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTJobBorrowerIndex")
	public String queryTJobBorrowerIndex  (TJobBorrower tJobBorrower) {
		TJobBorrower tJobBorrowerModel = tJobBorrowerService.getTJobBorrower(tJobBorrower);
		request.setAttribute("tJobBorrowerModel", tJobBorrowerModel);
		return "admin/xx/xx-detail";
	}
 }