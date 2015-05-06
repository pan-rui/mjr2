package com.p2p.web.controller.pay;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baofoo.p2p.dto.receive.FoChargePageDto;
import com.baofoo.p2p.dto.receive.RechargeDto;
import com.baofoo.p2p.dto.receive.ResultDto;
import com.p2p.constans.BaofooConstans;
import com.p2p.dto.InfoMsg;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.TRecharge;
import com.p2p.service.TAccountService;
import com.p2p.service.TInvestService;
import com.p2p.service.TRechargeService;
import com.p2p.service.TWithdrawCashService;
import com.p2p.util.DateUtil;
import com.p2p.web.controller.BaseController;

/**
 * 宝付接收
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class BaofooReceiveController extends BaseController {
	private static Log log = LogFactory.getLog(BaofooReceiveController.class);
	
	@Autowired
	private TInvestService tInvestService;
	@Autowired
	private TRechargeService rechargeService;
	@Autowired
	private TWithdrawCashService tWithdrawCashService;
	@Autowired
	private TAccountService accountService;
	/**
	 * 立即投资后端回调地址
	 * @return
	 */
	@RequestMapping(value="investReturnUrl")
	public String investReturnUrl(){
		try{
			log.info("=====investReturnUrl=====");
			String result = request.getParameter("result");
			
			
			log.info("result==>"+result);

			ResultDto resultDto = BaofooConstans.RECEIVE_SERVICE.page_p2pRequest(result);
			String orderId = resultDto.getOrder_id();
			log.info("投标回调===ordId:"+resultDto.getOrder_id()+"，Code:"+resultDto.getCode()+"，Msg"+resultDto.getMsg());
			if(!"CSD000".equals(resultDto.getCode())){
				InfoMsg msg = tInvestService.updateThirdFailInvest(orderId,resultDto.getMsg());
				msg = new InfoMsg(resultDto.getMsg(), "n");
				request.setAttribute("msg", msg);
				return null;
			}
			
			InfoMsg msg = tInvestService.updateThirdSuccessInvest(orderId,resultDto.getMsg());
			request.setAttribute("msg", msg);
			
		}catch(ZeroSQLException e){
			log.error("回调投标异常",e);
			InfoMsg msg = ((ZeroSQLException)e).getInfoMsg();
			request.setAttribute("msg", msg);
			this.writeJson(msg);
		}catch(Exception e){
			log.error("回调投标异常",e);
		}
		return null;
	}
	
	/**
	 * 立即投资页面回调地址
	 * @return
	 */
	@RequestMapping(value="investPageUrl")
	public String investPageUrl(){
		log.info("=====investPageUrl=====");
		String result = request.getParameter("result");
		log.info("result==>"+result);
		InfoMsg msg = new InfoMsg("投标成功！", "y");
		request.setAttribute("msg", msg);
//		investReturnUrl();
		return "forward:center/selectInvestList.html";
	}
	
	/**
	 * 充值页面回调地址
	 * @return
	 */
	@RequestMapping(value="rechargePageUrl")
	public String rechargePageUrl(){
		rechargeReturnUrl();
		return "front/comm/back-status";
	}
	
	/**
	 * 充值后台回调
	 * @return
	 */
	@RequestMapping(value="rechargeReturnUrl")
	public String rechargeReturnUrl(){
		
		String result = request.getParameter("result");
		log.info("result==>"+result);
		String sign = request.getParameter("sign");
		try {
			RechargeDto rechargeDto =BaofooConstans.RECEIVE_SERVICE.page_Recharge(result, sign);
			if(!"CSD000".equals(rechargeDto.getCode())){
				InfoMsg msg = new InfoMsg("充值失败", "n");
				request.setAttribute("msg", msg);
				return null;
			}
			
			TRecharge trecharge=new TRecharge();
			trecharge.setRechargeAmount(new BigDecimal(rechargeDto.getIncash_money()));
			trecharge.setRechargeRealAmount(new BigDecimal(rechargeDto.getIncash_money()));
			trecharge.setFeeAmount(new BigDecimal(rechargeDto.getFee()));
			trecharge.setFinishTime(DateUtil.YYYYMMDDHHMMSS.parse(rechargeDto.getSucc_time()));
			trecharge.setOrdId(rechargeDto.getOrder_id());
			InfoMsg msg=rechargeService.updateUserRecharge(trecharge);
			request.setAttribute("msg", msg);
			
		} catch (Exception e) {
			log.error("充值回调错误", e);
			
		}
		
		
		return "front/comm/back-status";
	}
	
	
	/**
	 * 提现回调
	 * @return
	 */
	@RequestMapping(value="twithdrawpageUrl")
	public String twithdrawpageUrl(){
		String result = request.getParameter("result");
		String sign = request.getParameter("sign");
		
		try {
			log.info("=========callbackcash==========");
			log.info("result==>"+result);
			log.info("sign==>"+sign);
			FoChargePageDto fochargepagedto=BaofooConstans.RECEIVE_SERVICE.page_foCharge(result, sign);
			if(!"CSD000".equals(fochargepagedto.getCode())){
				InfoMsg msg = new InfoMsg("提现处理失败", "n");
				request.setAttribute("msg", msg);
				return null;
			}
			
			tWithdrawCashService.insertAllUserWithdrawHandle(fochargepagedto.getOrder_id(),fochargepagedto.getAmount());
			
		} catch (Exception e) {
			log.error("提现回调错误", e);
		}
		return null;
	}
	
	
	
}
