package com.p2p.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.TRepayBorrowerDto;
import com.p2p.model.TRepayBorrower;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TRepayBorrowerService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TRepayBorrowerController extends BaseController {


	@Autowired
	private TRepayBorrowerService tRepayBorrowerService;
 
	@RequestMapping(value = "/queryTRepayBorrowerList")
	public String queryTRepayBorrowerList (TRepayBorrowerDto tRepayBorrowerDto) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tRepayBorrowerService.queryTRepayBorrowerPage(tRepayBorrowerDto,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tRepayBorrowerDto);
		return "admin/repay/repay-borrower-list";
	}
 
	@RequestMapping(value = "/addTRepayBorrowerIndex")
	public String addTRepayBorrowerIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTRepayBorrower")
	public String addTRepayBorrower (TRepayBorrower tRepayBorrower) {
		long result = tRepayBorrowerService.addTRepayBorrower(tRepayBorrower);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTRepayBorrowerIndex")
	public String updateTRepayBorrowerIndex  (TRepayBorrower tRepayBorrower) {
		TRepayBorrower tRepayBorrowerModel = tRepayBorrowerService.getTRepayBorrower(tRepayBorrower);
		request.setAttribute("tRepayBorrowerModel", tRepayBorrowerModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTRepayBorrower")
	public String updateTRepayBorrower (TRepayBorrower tRepayBorrower) {
		long result = tRepayBorrowerService.updateTRepayBorrower(tRepayBorrower);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTRepayBorrowerIndex")
	public String queryTRepayBorrowerIndex  (TRepayBorrower tRepayBorrower) {
		TRepayBorrower tRepayBorrowerModel = tRepayBorrowerService.getTRepayBorrower(tRepayBorrower);
		request.setAttribute("tRepayBorrowerModel", tRepayBorrowerModel);
		return "admin/xx/xx-detail";
	}
 }