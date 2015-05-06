package com.p2p.web.controller.front;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.model.TAccount;
import com.p2p.model.TRecharge;
import com.p2p.service.CUserService;
import com.p2p.service.TRechargeService;
import com.p2p.util.OrdIdUtil;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class RechargeController extends BaseController {
	private static Log log = LogFactory.getLog(RechargeController.class);
	@Autowired
	private TRechargeService tRechargeService;
	@Autowired
	private CUserService cUserService;

	@RequestMapping(value = "/recharge")
	public String addRecharge(TRecharge tRecharge) {
		
		
	
		tRecharge.setCreateTime(new Date());
		tRecharge.setOrdId(OrdIdUtil.generateOrdId(OrdIdUtil.OrdType.RECHAGER_TYPE));
		long result = tRechargeService.addTRecharge(tRecharge);
		return "admin/news/links-list";
	}

	
	@RequestMapping(value="bfpay/readyRecharge")
	public String readyRecharge(){
		TAccount tAccount= cUserService.selectUserMoney(this.getCUser().getId());
		request.setAttribute("taccount", tAccount);
		return "front/usercenter/user-recharge";
	}
}
