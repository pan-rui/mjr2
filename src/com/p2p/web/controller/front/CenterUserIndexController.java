package com.p2p.web.controller.front;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.dto.UserDto;
import com.p2p.dto.UserInvestLevelDto;
import com.p2p.model.CUser;
import com.p2p.model.TAccount;
import com.p2p.model.TPerson;
import com.p2p.model.TThridAccount;
import com.p2p.service.CUserDetailService;
import com.p2p.service.CUserService;
import com.p2p.service.TAccountService;
import com.p2p.service.TInvestAwardService;
import com.p2p.service.TPersonService;
import com.p2p.service.TThridAccountService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterUserIndexController extends BaseController{
	private static Log log = LogFactory.getLog(BorrowController.class);
	@Autowired
	private CUserService cUserService;
	@Autowired
	private TPersonService personService;
	@Autowired
	private TThridAccountService accountService;
	@Autowired
	private TInvestAwardService awardService;
	
	/**
	 * 账户中心
	 * @return
	 */
	@RequestMapping(value="center/selectUserIndex")
	public String selectUserIndex(){
		
		try{
			long idd=this.getCUser().getId();
		TAccount tAccount= cUserService.selectUserMoney(idd);//查询用户资金
		
		UserDto userdto=cUserService.getCUser(idd);//查询用户资料
		//查询待收本金
		BigDecimal DueinCorpus=cUserService.selectDueinCorpus(idd);
		//查询待收利息
		BigDecimal DueinInterest=cUserService.selectDueinInterest(idd);
		if(DueinInterest==null){
			DueinInterest=new BigDecimal("0");
		}
		//查询已收利息
		BigDecimal FinishInterest=cUserService.selectFinishInterest(idd);
		if(FinishInterest==null){
			FinishInterest=new BigDecimal("0");
		}
		//总收益
		BigDecimal sumterest=FinishInterest.add(DueinInterest);
		//查询累计投资
		BigDecimal AllInvestMoney=cUserService.selectAllInvestMoney(idd);
		//查询投资笔数
		int InvestSum=cUserService.selectInvestSum(idd);
		//查询已回款的投资数
		int investSumR=cUserService.selectInvestSumReceived(idd);
		//查询待回款的投资数
		int investno=cUserService.selectInvestSumNoReceived(idd);
		
		//查询用户实名认证信息
		TPerson tp=personService.getPersonByUserId(idd);
		TThridAccount tp2=accountService.getThridAccountByUserId(idd);
		if(tp2==null){
			tp2.setIsOpen(0);
		}
		
		//查询是否享受新手奖励
		Long isnew=cUserService.selectisNewforuserid(idd);
		
		if(isnew==null){
			isnew=0l;
		}
		//查询vip等级
		UserInvestLevelDto uild=awardService.getInvestLevel(idd);
		BigDecimal DueinAmount=uild.getDueinAmount();
		
		if(uild.getAwardLevel().equals("V1")){
			uild.setDueinAmount(new BigDecimal("50000").subtract(DueinAmount));
			
		}
		else if(uild.getAwardLevel().equals("V2")){
			uild.setDueinAmount(new BigDecimal("100000").subtract(DueinAmount));
		
		}
		
		request.setAttribute("uild", uild);
		request.setAttribute("isnew", isnew);
		request.setAttribute("tp", tp);
		request.setAttribute("tp2", tp2);
		request.setAttribute("taccount", tAccount);
		request.setAttribute("userdto", userdto);
		request.setAttribute("dsbj", DueinCorpus);
		request.setAttribute("dslx", DueinInterest);
		request.setAttribute("yslx", FinishInterest);
		request.setAttribute("ljtz", AllInvestMoney);
		request.setAttribute("tzbs", InvestSum);
		request.setAttribute("yhktz", investSumR);
		request.setAttribute("nhktz", investno);
		request.setAttribute("zsy", sumterest);
		}catch(Exception e){
			log.error("", e);
		}
		return "front/usercenter/user-index";
	}
	
	
}
