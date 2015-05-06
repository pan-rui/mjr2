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
import com.p2p.dto.InvestAwardUserDto;
import com.p2p.model.TInvestAward;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TInvestAwardService;
import com.p2p.web.controller.BaseController;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TInvestAwardController extends BaseController {
private static Log log=LogFactory.getLog(TInvestAwardController.class);

	@Autowired
	private TInvestAwardService tInvestAwardService;
 
	@RequestMapping(value = "/queryTInvestAwardList")
	public String queryTInvestAwardList (TInvestAward tInvestAward) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = tInvestAwardService.queryTInvestAwardPage(tInvestAward, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tInvestAward);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addTInvestAwardIndex")
	public String addTInvestAwardIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTInvestAward")
	public String addTInvestAward (TInvestAward tInvestAward) {
		long result = tInvestAwardService.addTInvestAward(tInvestAward);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTInvestAwardIndex")
	public String updateTInvestAwardIndex  (TInvestAward tInvestAward) {
		TInvestAward tInvestAwardModel = tInvestAwardService.getTInvestAward(tInvestAward);
		request.setAttribute("tInvestAwardModel", tInvestAwardModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTInvestAward")
	public String updateTInvestAward (TInvestAward tInvestAward) {
		long result = tInvestAwardService.updateTInvestAward(tInvestAward);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTInvestAwardIndex")
	public String queryTInvestAwardIndex  (TInvestAward tInvestAward) {
		TInvestAward tInvestAwardModel = tInvestAwardService.getTInvestAward(tInvestAward);
		request.setAttribute("tInvestAwardModel", tInvestAwardModel);
		return "admin/xx/xx-detail";
	}
	
	/**
	 * 投资奖励查询列表
	 * @param investAwardUserDto
	 * @return
	 */
	@RequestMapping(value="/selectInvestAward")
	public String selectInvestAward(InvestAwardUserDto investAwardUserDto){
		try{
		PageList pagelist=(PageList)tInvestAwardService.selectInvestAward(investAwardUserDto, pageBounds);
		request.setAttribute("pageBean", pagelist);
		request.setAttribute("page", pagelist.getPaginator());
		request.setAttribute("model", investAwardUserDto);
		}catch (Exception e) {
			// TODO: handle exception
			log.error("查询出错啦", e);
		}
		
		return "admin/invest/tInvestAward-list";
	}
 }