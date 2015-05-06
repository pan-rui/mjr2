package com.p2p.web.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.constans.ParaConstans;
import com.p2p.dto.InfoMsg;
import com.p2p.model.BArea;
import com.p2p.model.BPara;
import com.p2p.model.TJobBorrower;
import com.p2p.model.TPersonBorrower;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.BAreaService;
import com.p2p.service.BParaService;
import com.p2p.service.TJobBorrowerService;
import com.p2p.service.TPersonBorrowerService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TPersonBorrowerController extends BaseController {


	@Autowired
	private TPersonBorrowerService tPersonBorrowerService;
	
	@Autowired
	private TJobBorrowerService tJobBorrowerService;
	
	@Autowired
	private BParaService bParaService;
	
	@Autowired
	private BAreaService bAreaService;
	
	
 
	@RequestMapping(value = "/queryTPersonBorrowerList")
	public String queryTPersonBorrowerList (TPersonBorrower tPersonBorrower) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		options.put("borrowerName", SearchOperator.LIKE);
		options.put("borrowerCellPhone", SearchOperator.LIKE);
		
		PageList pageList = tPersonBorrowerService.queryTPersonBorrowerPage(tPersonBorrower, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tPersonBorrower);
		return "admin/person/person-borrower-list";
	}
	
	@RequestMapping(value = "/queryCommTPersonBorrowerList")
	public String queryCommTPersonBorrowerList  (TPersonBorrower tPersonBorrower) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tPersonBorrowerService.queryTPersonBorrowerPage(tPersonBorrower, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tPersonBorrower);
		return "admin/comm/comm-person-borrower-list";
	}
	
	private void setInitData(){
		BArea bArea = new BArea();
		bArea.setAreaLevel(1);
		List<BArea> bAreaList = bAreaService.queryBAreaList(bArea);
		request.setAttribute("bAreaList", bAreaList);
		List<BPara> companyIndustryList = bParaService.queryBparaByCode(ParaConstans.COMPANY_INDUSTRY);
		List<BPara> companyNatureList = bParaService.queryBparaByCode(ParaConstans.COMPANY_NATURE);
		List<BPara> jobYearList = bParaService.queryBparaByCode(ParaConstans.JOB_YEAR);
		List<BPara> monthlyIncomeLevelList = bParaService.queryBparaByCode(ParaConstans.MONTHLY_INCOME_LEVEL);
		request.setAttribute("companyIndustryList", companyIndustryList);
		request.setAttribute("companyNatureList", companyNatureList);
		request.setAttribute("jobYearList", jobYearList);
		request.setAttribute("monthlyIncomeLevelList", monthlyIncomeLevelList);
	}
 
	@RequestMapping(value = "/addTPersonBorrowerIndex")
	public String addTPersonBorrowerIndex () {
		request.setAttribute("isWindow", request.getParameter("isWindow"));
		setInitData();
		return "admin/person/person-borrower-add";
	}
 
	@RequestMapping(value = "/addTPersonBorrower")
	public String addTPersonBorrower (TPersonBorrower tPersonBorrower,TJobBorrower tJobBorrower) {
		tPersonBorrower.setCreateTime(new Date());
		tJobBorrower.setCreateTime(new Date());
		long result = tPersonBorrowerService.addTPersonBorrower(tPersonBorrower,tJobBorrower);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTPersonBorrowerIndex")
	public String updateTPersonBorrowerIndex  (TPersonBorrower tPersonBorrower) {
		setInitData();
		TPersonBorrower tPersonBorrowerModel = tPersonBorrowerService.getTPersonBorrower(tPersonBorrower);
		
		TJobBorrower tJobBorrowerModel = tJobBorrowerService.getTJobBorrowerByPersonId(tPersonBorrowerModel.getId());
		request.setAttribute("tPersonBorrowerModel", tPersonBorrowerModel);
		request.setAttribute("tJobBorrowerModel", tJobBorrowerModel);
		return "admin/person/person-borrower-update";
	}
 
	@RequestMapping(value = "/updateTPersonBorrower")
	public String updateTPersonBorrower (TPersonBorrower tPersonBorrower,TJobBorrower tJobBorrower) {
		long result = tPersonBorrowerService.updateTPersonBorrower(tPersonBorrower,tJobBorrower);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
	
	
 
	@RequestMapping(value = "/queryTPersonBorrowerIndex")
	public String queryTPersonBorrowerIndex  (TPersonBorrower tPersonBorrower) {
		setInitData();
		TPersonBorrower tPersonBorrowerModel = tPersonBorrowerService.getTPersonBorrower(tPersonBorrower);
		
		TJobBorrower tJobBorrowerModel = tJobBorrowerService.getTJobBorrowerByPersonId(tPersonBorrowerModel.getId());
		request.setAttribute("tPersonBorrowerModel", tPersonBorrowerModel);
		request.setAttribute("tJobBorrowerModel", tJobBorrowerModel);
		return "admin/person/person-borrower-detail";
	}
 }