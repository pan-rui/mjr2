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
import com.p2p.dto.TWithdrawCashDto;
import com.p2p.model.TWithdrawCash;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TWithdrawCashService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TWithdrawCashController extends BaseController {

	private static Log log = LogFactory.getLog(TWithdrawCashController.class);
	@Autowired
	private TWithdrawCashService tWithdrawCashService;
 
	@RequestMapping(value = "/queryTWithdrawCashList")
	public String queryTWithdrawCashList (TWithdrawCashDto tWithdrawCashDto) {
		
		PageList pageList = tWithdrawCashService.selectTWithDrawCashDtoList(tWithdrawCashDto, pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tWithdrawCashDto);
		return "admin/finance/twithdrawCash-list";
	}
	
	@RequestMapping(value = "/selectTWithdrawCashDeatil")
	public String selectTWithdrawCashDeatil (TWithdrawCashDto tWithdrawCashDto) {
		
		TWithdrawCashDto twmsg = tWithdrawCashService.selectTWithDrawCashDtoDeatil(tWithdrawCashDto.getId());

		
		request.setAttribute("twmsg", twmsg);
		return "admin/finance/twithdrawCash-deatil";
	}
 
	@RequestMapping(value = "/addTWithdrawCashIndex")
	public String addTWithdrawCashIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTWithdrawCash")
	public String addTWithdrawCash (TWithdrawCash tWithdrawCash) {
		long result = tWithdrawCashService.addTWithdrawCash(tWithdrawCash);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTWithdrawCashIndex")
	public String updateTWithdrawCashIndex  (TWithdrawCash tWithdrawCash) {
		TWithdrawCash tWithdrawCashModel = tWithdrawCashService.getTWithdrawCash(tWithdrawCash);
		request.setAttribute("tWithdrawCashModel", tWithdrawCashModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTWithdrawCash")
	public String updateTWithdrawCash (TWithdrawCash tWithdrawCash) {
		long result = tWithdrawCashService.updateTWithdrawCash(tWithdrawCash);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTWithdrawCashIndex")
	public String queryTWithdrawCashIndex  (TWithdrawCash tWithdrawCash) {
		TWithdrawCash tWithdrawCashModel = tWithdrawCashService.getTWithdrawCash(tWithdrawCash);
		request.setAttribute("tWithdrawCashModel", tWithdrawCashModel);
		return "admin/xx/xx-detail";
	}
 }