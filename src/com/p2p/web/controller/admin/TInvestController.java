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
import com.p2p.dto.TInvestDto;
import com.p2p.model.TInvest;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TInvestService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TInvestController extends BaseController {

	private static Log log = LogFactory.getLog(TInvestController.class);
	@Autowired
	private TInvestService tInvestService;
 
	@RequestMapping(value = "/selectTInvestList")
	public String queryTInvestList (TInvestDto tInvestDto) {
		
		PageList pageList = tInvestService.selectTInvestList(tInvestDto, pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tInvestDto);
		return "admin/invest/tInvest-list";
	}
	
	@RequestMapping(value = "/selectTInvestDeatil")
	public String selectTInvestDeatil (TInvestDto tInvestDto) {
		
		TInvestDto investmsg = tInvestService.selectTInvestDeatil(tInvestDto.getId());

		
		request.setAttribute("investmsg", investmsg);
		return "admin/invest/tInvest-deatil";
	}
 
	@RequestMapping(value = "/addTInvestIndex")
	public String addTInvestIndex () {
		return "admin/xx/xx-add";
	}
 
	
 
	@RequestMapping(value = "/updateTInvestIndex")
	public String updateTInvestIndex  (TInvest tInvest) {
		TInvest tInvestModel = tInvestService.getTInvest(tInvest);
		request.setAttribute("tInvestModel", tInvestModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTInvest")
	public String updateTInvest (TInvest tInvest) {
		long result = tInvestService.updateTInvest(tInvest);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTInvestIndex")
	public String queryTInvestIndex  (TInvest tInvest) {
		TInvest tInvestModel = tInvestService.getTInvest(tInvest);
		request.setAttribute("tInvestModel", tInvestModel);
		return "admin/xx/xx-detail";
	}
	
	/**
	 * 借款详情中的投资查询
	 * @param tInvestDto
	 * @return
	 */
	@RequestMapping(value="/selectInvestListForbid")
	public String selectInvestListForbid(Long borrowId){
		PageList pagelist=tInvestService.selectInvestListforbid(borrowId, pageBounds);
		
		request.setAttribute("pageBean", pagelist);
		request.setAttribute("page", pagelist.getPaginator());
		request.setAttribute("iddd", borrowId);
		return "admin/invest/tInvestforbid-list";
	}
	
 }