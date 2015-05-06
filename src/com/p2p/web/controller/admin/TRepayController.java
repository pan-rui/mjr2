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
import com.p2p.dto.RepayDto;
import com.p2p.model.TRepay;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TRepayService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TRepayController extends BaseController {

	private static Log log = LogFactory.getLog(TRepayController.class);
	@Autowired
	private TRepayService tRepayService;
 
	@RequestMapping(value = "/queryTRepayList")
	public String queryTRepayList (RepayDto repayDto) {
		
		PageList pageList = tRepayService.queryTRepayBorrowPage(repayDto,pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", repayDto);
		return "admin/repay/repay-list";
	}
 
	@RequestMapping(value = "/addTRepayIndex")
	public String addTRepayIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTRepay")
	public String addTRepay (TRepay tRepay) {
		long result = tRepayService.addTRepay(tRepay);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTRepayIndex")
	public String updateTRepayIndex  (TRepay tRepay) {
		TRepay tRepayModel = tRepayService.getTRepay(tRepay);
		request.setAttribute("tRepayModel", tRepayModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTRepay")
	public String updateTRepay (TRepay tRepay) {
		long result = tRepayService.updateTRepay(tRepay);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTRepayIndex")
	public String queryTRepayIndex  (TRepay tRepay) {
		TRepay tRepayModel = tRepayService.getTRepay(tRepay);
		request.setAttribute("tRepayModel", tRepayModel);
		return "admin/xx/xx-detail";
	}
 }