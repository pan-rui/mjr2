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
import com.p2p.model.TTransfer;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TTransferService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TTransferController extends BaseController {


	@Autowired
	private TTransferService tTransferService;
 
	@RequestMapping(value = "/queryTTransferList")
	public String queryTTransferList (TTransfer tTransfer) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tTransferService.queryTTransferPage(tTransfer, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tTransfer);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addTTransferIndex")
	public String addTTransferIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTTransfer")
	public String addTTransfer (TTransfer tTransfer) {
		long result = tTransferService.addTTransfer(tTransfer);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTTransferIndex")
	public String updateTTransferIndex  (TTransfer tTransfer) {
		TTransfer tTransferModel = tTransferService.getTTransfer(tTransfer);
		request.setAttribute("tTransferModel", tTransferModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTTransfer")
	public String updateTTransfer (TTransfer tTransfer) {
		long result = tTransferService.updateTTransfer(tTransfer);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTTransferIndex")
	public String queryTTransferIndex  (TTransfer tTransfer) {
		TTransfer tTransferModel = tTransferService.getTTransfer(tTransfer);
		request.setAttribute("tTransferModel", tTransferModel);
		return "admin/xx/xx-detail";
	}
 }