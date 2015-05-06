package com.p2p.web.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.TBankCardDto;
import com.p2p.model.TBankCard;
import com.p2p.service.TBankCardService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TBankCardController extends BaseController {
	private static Log log = LogFactory.getLog(TBankCardController.class);

	@Autowired
	private TBankCardService tBankCardService;
 
	@RequestMapping(value = "/queryTBankCardList")
	public String queryTBankCardList (TBankCardDto tbankCardDto) {
		
		PageList pageList = tBankCardService.selectTBankCardList(tbankCardDto, pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tbankCardDto);
		return "admin/finance/tBankCard-list";
	}
	
	@RequestMapping(value = "/selectTBankCardDeatil")
	public String selectTBankCardDeatil (TBankCardDto tbankCardDto) {
		
		TBankCardDto tbankcardmsg = tBankCardService.selectTBankCardDeatil(tbankCardDto.getId());

		request.setAttribute("tbankcardmsg", tbankcardmsg);
		
		return "admin/finance/tBankCard-deatil";
	}
 
	@RequestMapping(value = "/addTBankCardIndex")
	public String addTBankCardIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTBankCard")
	public String addTBankCard (TBankCard tBankCard) {
		long result = tBankCardService.addTBankCard(tBankCard);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTBankCardIndex")
	public String updateTBankCardIndex  (TBankCard tBankCard) {
		TBankCard tBankCardModel = tBankCardService.getTBankCard(tBankCard);
		request.setAttribute("tBankCardModel", tBankCardModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTBankCard")
	public String updateTBankCard (TBankCard tBankCard) {
		long result = tBankCardService.updateTBankCard(tBankCard);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTBankCardIndex")
	public String queryTBankCardIndex  (TBankCard tBankCard) {
		TBankCard tBankCardModel = tBankCardService.getTBankCard(tBankCard);
		request.setAttribute("tBankCardModel", tBankCardModel);
		return "admin/xx/xx-detail";
	}
 }