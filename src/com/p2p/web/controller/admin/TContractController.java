package com.p2p.web.controller.admin;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.dto.InfoMsg;
import com.p2p.model.TContract;
import com.p2p.service.TContractService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TContractController extends BaseController {

	private static Log log = LogFactory.getLog(TContractController.class);
	@Autowired
	private TContractService tContractService;
 
	@RequestMapping(value = "/queryTContractList")
	public String queryTContractList (TContract tContract) {
		//Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		//PageList pageList = tContractService.queryTContractPage(tContract, options,pageBounds);
		
		//request.setAttribute("pageBean", pageList);
		//request.setAttribute("page", pageList.getPaginator());
		List<TContract> list=tContractService.selectcontracttitle();
		
		request.setAttribute("list", list);
		return "admin/borrow/Releaseborrow";
	}
 
	@RequestMapping(value = "/addTContractIndex")
	public String addTContractIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTContract")
	public String addTContract (TContract tContract) {
		long result = tContractService.addTContract(tContract);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTContractIndex")
	public String updateTContractIndex  (TContract tContract) {
		TContract tContractModel = tContractService.getTContract(tContract);
		request.setAttribute("tContractModel", tContractModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTContract")
	public String updateTContract (TContract tContract) {
		long result = tContractService.updateTContract(tContract);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTContractIndex")
	public String queryTContractIndex  (TContract tContract) {
		TContract tContractModel = tContractService.getTContract(tContract);
		request.setAttribute("tContractModel", tContractModel);
		return "admin/xx/xx-detail";
	}
 }