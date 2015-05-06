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
import com.p2p.dto.RechargeRecordDto;
import com.p2p.model.TRecharge;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TRechargeService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TRechargeController extends BaseController {


	@Autowired
	private TRechargeService tRechargeService;
 
	@RequestMapping(value = "/queryTRechargeList")
	public String queryTRechargeList (RechargeRecordDto rechargeRecordDto) {
		
		PageList pageList=(PageList) tRechargeService.queryRechargeRecord(rechargeRecordDto,pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", rechargeRecordDto);
		return "admin/finance/RechargeRecord";
	}
	
	@RequestMapping(value = "/queryTRechargeListDetailed")
	public String queryTRechargeDetailed (RechargeRecordDto rechargeRecordDto) {
		//Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		//PageList pageList = tRechargeService.queryTRechargePage(tRecharge, options,pageBounds);
		RechargeRecordDto tempRechargeRecordDto=tRechargeService.selectRechargeRecord(rechargeRecordDto.getId());
		request.setAttribute("recharge", tempRechargeRecordDto);
	
		return "admin/finance/RechargeRecordIndex";
	}

	@RequestMapping(value = "/addTRecharge")
	public String addTRecharge (TRecharge tRecharge) {
		long result = tRechargeService.addTRecharge(tRecharge);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTRechargeIndex")
	public String updateTRechargeIndex  (TRecharge tRecharge) {
		TRecharge tRechargeModel = tRechargeService.getTRecharge(tRecharge);
		request.setAttribute("tRechargeModel", tRechargeModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTRecharge")
	public String updateTRecharge (TRecharge tRecharge) {
		long result = tRechargeService.updateTRecharge(tRecharge);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTRechargeIndex")
	public String queryTRechargeIndex  (TRecharge tRecharge) {
		TRecharge tRechargeModel = tRechargeService.getTRecharge(tRecharge);
		request.setAttribute("tRechargeModel", tRechargeModel);
		return "admin/xx/xx-detail";
	}
 }