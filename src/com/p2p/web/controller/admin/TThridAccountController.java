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
import com.p2p.model.TThridAccount;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TThridAccountService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TThridAccountController extends BaseController {


	@Autowired
	private TThridAccountService tThridAccountService;
 
	@RequestMapping(value = "/queryTThridAccountList")
	public String queryTThridAccountList (TThridAccount tThridAccount) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tThridAccountService.queryTThridAccountPage(tThridAccount, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tThridAccount);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addTThridAccountIndex")
	public String addTThridAccountIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTThridAccount")
	public String addTThridAccount (TThridAccount tThridAccount) {
		long result = tThridAccountService.addTThridAccount(tThridAccount);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTThridAccountIndex")
	public String updateTThridAccountIndex  (TThridAccount tThridAccount) {
		TThridAccount tThridAccountModel = tThridAccountService.getTThridAccount(tThridAccount);
		request.setAttribute("tThridAccountModel", tThridAccountModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTThridAccount")
	public String updateTThridAccount (TThridAccount tThridAccount) {
		long result = tThridAccountService.updateTThridAccount(tThridAccount);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTThridAccountIndex")
	public String queryTThridAccountIndex  (TThridAccount tThridAccount) {
		TThridAccount tThridAccountModel = tThridAccountService.getTThridAccount(tThridAccount);
		request.setAttribute("tThridAccountModel", tThridAccountModel);
		return "admin/xx/xx-detail";
	}
 }