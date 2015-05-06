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
import com.p2p.model.TClaim;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TClaimService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TClaimController extends BaseController {


	@Autowired
	private TClaimService tClaimService;
 
	@RequestMapping(value = "/queryTClaimList")
	public String queryTClaimList (TClaim tClaim) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tClaimService.queryTClaimPage(tClaim, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tClaim);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addTClaimIndex")
	public String addTClaimIndex () {
		return "admin/xx/xx-add";
	}
 
	
 
	@RequestMapping(value = "/updateTClaimIndex")
	public String updateTClaimIndex  (TClaim tClaim) {
		TClaim tClaimModel = tClaimService.getTClaim(tClaim);
		request.setAttribute("tClaimModel", tClaimModel);
		return "admin/xx/xx-update";
	}
 
 
	@RequestMapping(value = "/queryTClaimIndex")
	public String queryTClaimIndex  (TClaim tClaim) {
		TClaim tClaimModel = tClaimService.getTClaim(tClaim);
		request.setAttribute("tClaimModel", tClaimModel);
		return "admin/xx/xx-detail";
	}
 }