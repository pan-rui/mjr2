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
import com.p2p.model.TAccount;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TAccountService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TAccountController extends BaseController {
	private static Log log = LogFactory.getLog(TAccountController.class);

	@Autowired
	private TAccountService tAccountService;
 
	@RequestMapping(value = "/queryTAccountList")
	public String queryTAccountList (TAccount tAccount) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tAccountService.queryTAccountPage(tAccount, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tAccount);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addTAccountIndex")
	public String addTAccountIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTAccount")
	public String addTAccount (TAccount tAccount) {
		long result = tAccountService.addTAccount(tAccount);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTAccountIndex")
	public String updateTAccountIndex  (TAccount tAccount) {
		TAccount tAccountModel = tAccountService.getTAccount(tAccount);
		request.setAttribute("tAccountModel", tAccountModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTAccount")
	public String updateTAccount (TAccount tAccount) {
		long result = tAccountService.updateTAccount(tAccount);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTAccountIndex")
	public String queryTAccountIndex  (TAccount tAccount) {
		TAccount tAccountModel = tAccountService.getTAccount(tAccount);
		request.setAttribute("tAccountModel", tAccountModel);
		return "admin/xx/xx-detail";
	}
 }