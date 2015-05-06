package com.p2p.web.controller.front;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.TWithdrawCashDto;
import com.p2p.dto.UserDto;
import com.p2p.model.CUser;
import com.p2p.model.TAccount;
import com.p2p.model.TBankCard;
import com.p2p.model.TWithdrawCash;
import com.p2p.service.CUserService;
import com.p2p.service.TAccountService;
import com.p2p.service.TBankCardService;
import com.p2p.service.TInvestService;
import com.p2p.service.TRechargeService;
import com.p2p.service.TWithdrawCashService;
import com.p2p.util.DateUtil;
import com.p2p.web.controller.BaseController;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;
@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterWithdrawController extends BaseController{
	private static Log log=LogFactory.getLog(CenterWithdrawController.class);
	
	@Autowired
	private TWithdrawCashService centerws;
	@Autowired
	private CUserService cUserService;
	@Autowired
	private TBankCardService tBankCardService;
	@Autowired
	private TWithdrawCashService tWithdrawCashService;
	@Autowired
	private TRechargeService rechargeService;
	@Autowired
	private TInvestService tInvestService;
	@Autowired
	private TAccountService accountService;
	
	/**
	 * 提现记录
	 * @param tWithdrawCash
	 * @return
	 */
	@RequestMapping(value="center/selectWithdraw")
	public String selectWithdraw(TWithdrawCash tWithdrawCash){
		tWithdrawCash.setUserId(this.getCUser().getId());
			
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		int days = tWithdrawCash.getDays();
		if(days!=0){
			tWithdrawCash.setCreateTime(DateUtil.dateAddDay(calendar.getTime(), -days));
		}
		System.out.println(tWithdrawCash.getCreateTime()+"-------------------------------------");
		PageList pagelist=centerws.selectWithdraw(tWithdrawCash, pageBounds);
		request.setAttribute("pageBean", pagelist);
		request.setAttribute("aChacked", request.getParameter("achacked"));
		request.setAttribute("page", pagelist.getPaginator());
		request.setAttribute("model", tWithdrawCash);
		
		TAccount tAccount= cUserService.selectUserMoney(this.getCUser().getId());//查询用户资金
		request.setAttribute("taccount", tAccount);
		return "front/usercenter/withdraw-record";
	}
	
	/**
	 * 提现准备页面
	 * @return
	 */
	@RequestMapping(value="center/userWithdraw")
	public String userWithdraw(TBankCard tBankCard){
		tBankCard.setUserId(this.getCUser().getId());
		UserDto userdto=cUserService.getCUser(this.getCUser().getId());
		TAccount taccount=cUserService.selectUserMoney(this.getCUser().getId());
		List<TBankCard> tBankCardlist=tBankCardService.selectBankCardlist(tBankCard);
		
		request.setAttribute("userdto", userdto);
		request.setAttribute("taccount", taccount);
		request.setAttribute("tbankcard", tBankCardlist);
		return "front/usercenter/user-withdraw";
	}
	/**
	 * 计算手续费
	 * @return
	 */
	@RequestMapping(value="/countpoundage")
	public String countpoundage(){
		BigDecimal cgltxje=new BigDecimal(request.getParameter("tzje"));
		BigDecimal mrpoundage =new BigDecimal("2");//默认手续费
		BigDecimal poundage =null;//实际手续费
		int xqj=DateUtil.getday();//得到今天星期几
		if(xqj==1||xqj==7){
			mrpoundage=new BigDecimal("5");
		}
		
		BigDecimal ysxfje=rechargeService.selecttrechargefortime(this.getCUser().getId());//15天内充值的金额
		if(ysxfje==null){
			ysxfje=new BigDecimal("0");
		}
		BigDecimal tzje=tInvestService.selectinvestfortime(this.getCUser().getId());//15天内的投资额
		if(tzje==null){
			tzje=new BigDecimal("0");
		}
		BigDecimal shiwudaytxe=tWithdrawCashService.selecttwithdrwafortime(this.getCUser().getId());//15天内的提现金额
		if(shiwudaytxe==null){
			shiwudaytxe=new BigDecimal("0");
		}
		
		if(tzje.compareTo(ysxfje)>-1){
			poundage=mrpoundage;
		}
		
		if(tzje.compareTo(ysxfje)<0){
			BigDecimal yssxfje=ysxfje.subtract(tzje).subtract(shiwudaytxe);//需要手续费的金额
			BigDecimal txje=cgltxje;//提现金额
			
			TAccount tAccount2=accountService.getTAccountforuserid(this.getCUser().getId());
			BigDecimal userbleamount=tAccount2.getUsableAmount();
			if(txje.add(yssxfje).compareTo(userbleamount)<1){
				poundage=mrpoundage;
			}else{
				if(yssxfje.compareTo(userbleamount)>-1){
					poundage=txje.multiply(new BigDecimal("0.005")).setScale(2, BigDecimal.ROUND_HALF_UP).add(mrpoundage);
				}else if(userbleamount.subtract(yssxfje).compareTo(txje)>-1){
					poundage=mrpoundage;
				}else if(userbleamount.subtract(yssxfje).compareTo(txje)<1){
					if(yssxfje.compareTo(txje)>-1){
						poundage=txje.multiply(new BigDecimal("0.005")).setScale(2, BigDecimal.ROUND_HALF_UP).add(mrpoundage);
					}else{
						poundage=yssxfje.multiply(new BigDecimal("0.005")).setScale(2, BigDecimal.ROUND_HALF_UP).add(mrpoundage);  
						// txje.subtract(userbleamount.subtract(yssxfje)).multiply(new BigDecimal("0.005")).setScale(2, BigDecimal.ROUND_HALF_UP).add(mrpoundage);
					}
				}
				
								
			}
			
		}
		
		
		
		request.setAttribute("jg", poundage);
		return "front/usercenter/countpoundage";
	}
	
	
}
