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
import com.p2p.model.TApplyBorrow;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TApplyBorrowService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TApplyBorrowController extends BaseController {


	@Autowired
	private TApplyBorrowService tApplyBorrowService;
 
	@RequestMapping(value = "/queryTApplyBorrowList")
	public String queryTApplyBorrowList (TApplyBorrow tApplyBorrow) {
		PageList pageList = tApplyBorrowService.queryTApplyBorrowPage(tApplyBorrow, pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tApplyBorrow);
		return "admin/borrow/applyBorrow-list";
	}
 
	@RequestMapping(value = "/addTApplyBorrowIndex")
	public String addTApplyBorrowIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTApplyBorrow")
	public String addTApplyBorrow (TApplyBorrow tApplyBorrow) {
		long result = tApplyBorrowService.addTApplyBorrow(tApplyBorrow);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTApplyBorrowIndex")
	public String updateTApplyBorrowIndex  (TApplyBorrow tApplyBorrow) {
		TApplyBorrow tApplyBorrowModel = tApplyBorrowService.getTApplyBorrow(tApplyBorrow);
		request.setAttribute("tApplyBorrowModel", tApplyBorrowModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTApplyBorrow")
	public String updateTApplyBorrow (TApplyBorrow tApplyBorrow) {
		long result = tApplyBorrowService.updateTApplyBorrow(tApplyBorrow);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTApplyBorrowIndex")
	public String queryTApplyBorrowIndex  (TApplyBorrow tApplyBorrow) {
		TApplyBorrow tApplyBorrowModel = tApplyBorrowService.getTApplyBorrow(tApplyBorrow);
		request.setAttribute("tApplyBorrowModel", tApplyBorrowModel);
		return "admin/xx/xx-detail";
	}
 }