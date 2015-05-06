package com.p2p.web.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.model.TBankCard;
import com.p2p.service.TBankCardService;
import com.p2p.web.controller.BaseController;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterBankCardController extends BaseController{
	private static Log log=LogFactory.getLog(CenterBankCardController.class);
	@Autowired
	private TBankCardService cbcs;
	
	@RequestMapping(value = "center/selectBankCard")
	public String selectBankCard(TBankCard tBankCard){
		tBankCard.setUserId(this.getCUser().getId());
		List<TBankCard> bankcardlist=cbcs.selectBankCardlist(tBankCard);
		request.setAttribute("bankcardlist", bankcardlist);
		return "front/usercenter/user-bankcard";
	}
	
	@RequestMapping(value = "center/insertBankCard")
	public String insertBankCard(TBankCard tBankCard){
		int i=cbcs.insertBankCard(tBankCard);
		if(i>0){
			return "";
		}else{
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
	
	@RequestMapping(value="center/updateBankCard")
	public String updateBankCard(TBankCard tBankCard){
		int i=cbcs.updateBankCard(tBankCard);
		if(i>0){
			return "";
		}else{
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
	
	@RequestMapping(value="center/deleteBankCard")
	public String deleteBankCard(TBankCard tBankCard){
		tBankCard.setId(Long.parseLong(request.getParameter("id")));
		tBankCard.setUserId(this.getCUser().getId());
		int i=cbcs.deleteBankCardforid(tBankCard);
		if(i>0){
			this.writeJson(new InfoMsg("保存成功", "y"));
		}else{
			//this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
}
