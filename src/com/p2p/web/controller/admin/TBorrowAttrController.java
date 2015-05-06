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
import com.p2p.model.TBorrowAttr;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TBorrowAttrService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TBorrowAttrController extends BaseController {
	private static Log log = LogFactory.getLog(TBorrowAttrController.class);

	@Autowired
	private TBorrowAttrService tBorrowAttrService;
 
	@RequestMapping(value = "/queryTBorrowAttrList")
	public String queryTBorrowAttrList (TBorrowAttr tBorrowAttr) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tBorrowAttrService.queryTBorrowAttrPage(tBorrowAttr, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tBorrowAttr);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addTBorrowAttrIndex")
	public String addTBorrowAttrIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTBorrowAttr")
	public String addTBorrowAttr (TBorrowAttr tBorrowAttr) {
		long result = tBorrowAttrService.addTBorrowAttr(tBorrowAttr);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTBorrowAttrIndex")
	public String updateTBorrowAttrIndex  (TBorrowAttr tBorrowAttr) {
		TBorrowAttr tBorrowAttrModel = tBorrowAttrService.getTBorrowAttr(tBorrowAttr);
		request.setAttribute("tBorrowAttrModel", tBorrowAttrModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTBorrowAttr")
	public String updateTBorrowAttr (TBorrowAttr tBorrowAttr) {
		long result = tBorrowAttrService.updateTBorrowAttr(tBorrowAttr);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTBorrowAttrIndex")
	public String queryTBorrowAttrIndex  (TBorrowAttr tBorrowAttr) {
		TBorrowAttr tBorrowAttrModel = tBorrowAttrService.getTBorrowAttr(tBorrowAttr);
		request.setAttribute("tBorrowAttrModel", tBorrowAttrModel);
		return "admin/xx/xx-detail";
	}
 }