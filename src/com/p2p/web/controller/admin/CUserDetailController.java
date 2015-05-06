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
import com.p2p.model.CUserDetail;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.CUserDetailService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CUserDetailController extends BaseController {
	private static Log log = LogFactory.getLog(CUserDetailController.class);

	@Autowired
	private CUserDetailService cUserDetailService;
 
	@RequestMapping(value = "/queryCUserDetailList")
	public String queryCUserDetailList (CUserDetail cUserDetail) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = cUserDetailService.queryCUserDetailPage(cUserDetail, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", cUserDetail);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addCUserDetailIndex")
	public String addCUserDetailIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addCUserDetail")
	public String addCUserDetail (CUserDetail cUserDetail) {
		long result = cUserDetailService.addCUserDetail(cUserDetail);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateCUserDetailIndex")
	public String updateCUserDetailIndex  (CUserDetail cUserDetail) {
		CUserDetail cUserDetailModel = cUserDetailService.getCUserDetail(cUserDetail);
		request.setAttribute("cUserDetailModel", cUserDetailModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateCUserDetail")
	public String updateCUserDetail (CUserDetail cUserDetail) {
		long result = cUserDetailService.updateCUserDetail(cUserDetail);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryCUserDetailIndex")
	public String queryCUserDetailIndex  (CUserDetail cUserDetail) {
		CUserDetail cUserDetailModel = cUserDetailService.getCUserDetail(cUserDetail);
		request.setAttribute("cUserDetailModel", cUserDetailModel);
		return "admin/xx/xx-detail";
	}
 }