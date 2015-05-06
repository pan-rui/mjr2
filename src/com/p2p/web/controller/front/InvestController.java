package com.p2p.web.controller.front;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.service.TInvestService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class InvestController extends BaseController {
	private static Log log = LogFactory.getLog(InvestController.class);
	@Autowired
	private TInvestService tInvestService;

//	@RequestMapping(value = "/investBorrow")
//	public String addInvest(TInvest tInvest) {
//		CUser cUser = this.getCUser();
//		tInvest.setInvestorId(cUser.getId());
//		if(tInvest.getInvestAmount()==null){
//			this.writeJson(new InfoMsg("投资金额不能为空", "n"));
//			return null;
//		}
//		tInvest.setInvestorId(this.getCUser().getId());
//		try{
//			InfoMsg msg = tInvestService.addTInvest(tInvest);
//			this.writeJson(msg);
//		}catch(Exception e){
//			log.error("投标异常",e);
//			InfoMsg msg = ((ZeroSQLException)e).getInfoMsg();
//			this.writeJson(msg);
//		}
//		
//		
//		return null;
//	}


}
