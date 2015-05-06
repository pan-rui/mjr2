package com.p2p.web.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.model.BArea;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.BAreaService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class BAreaController extends BaseController {

	private static Log log = LogFactory.getLog(BAreaController.class);
	@Autowired
	private BAreaService bAreaService;
 
	@RequestMapping(value = "/queryBAreaList")
	public String queryBAreaList (BArea bArea) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = bAreaService.queryBAreaPage(bArea, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", bArea);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addBAreaIndex")
	public String addBAreaIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addBArea")
	public String addBArea (BArea bArea) {
		long result = bAreaService.addBArea(bArea);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateBAreaIndex")
	public String updateBAreaIndex  (BArea bArea) {
		BArea bAreaModel = bAreaService.getBArea(bArea);
		request.setAttribute("bAreaModel", bAreaModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateBArea")
	public String updateBArea (BArea bArea) {
		long result = bAreaService.updateBArea(bArea);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryBAreaIndex")
	public String queryBAreaIndex  (BArea bArea) {
		BArea bAreaModel = bAreaService.getBArea(bArea);
		request.setAttribute("bAreaModel", bAreaModel);
		return "admin/xx/xx-detail";
	}
	
	@RequestMapping(value = "/queryCityList")
	public String queryCityList(BArea bArea){
		List<BArea> bAreaList = bAreaService.queryBAreaList(bArea);
		this.writeJson(bAreaList);
		return null;
	}
 }