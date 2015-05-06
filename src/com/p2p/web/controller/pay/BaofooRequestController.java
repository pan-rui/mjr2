package com.p2p.web.controller.pay;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baofoo.p2p.dto.receive.ResultDto;
import com.baofoo.p2p.dto.request.BalanceDto;
import com.baofoo.p2p.dto.request.FoChargePage;
import com.baofoo.p2p.dto.request.RechargeDto;
import com.baofoo.p2p.dto.request.p2p.Action;
import com.baofoo.p2p.dto.request.p2p.P2pRequestDto;
import com.baofoo.p2p.util.XMLBuild;
import com.p2p.constans.BaofooConstans;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.TInvestDto;
import com.p2p.enums.FundRecordType;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.BAdmin;
import com.p2p.model.CUser;
import com.p2p.model.TAccount;
import com.p2p.model.TBorrow;
import com.p2p.model.TFundRecord;
import com.p2p.model.TInvest;
import com.p2p.model.TRecharge;
import com.p2p.model.TRepay;
import com.p2p.model.TRepayBorrower;
import com.p2p.model.TThridAccount;
import com.p2p.model.TWithdrawCash;
import com.p2p.service.TAccountService;
import com.p2p.service.TBorrowService;
import com.p2p.service.TInvestService;
import com.p2p.service.TRechargeService;
import com.p2p.service.TRepayBorrowerService;
import com.p2p.service.TRepayService;
import com.p2p.service.TThridAccountService;
import com.p2p.service.TWithdrawCashService;
import com.p2p.util.DateUtil;
import com.p2p.util.NumberUtil;
import com.p2p.util.OrdIdUtil;
import com.p2p.util.OrdIdUtil.OrdType;
import com.p2p.web.controller.BaseController;

/**
 * 宝付请求
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class BaofooRequestController extends BaseController {
	private static Log log = LogFactory.getLog(BaofooRequestController.class);
	@Autowired
	private TThridAccountService tThridAccountService;

	@Autowired
	private TInvestService tInvestService;

	@Autowired
	private TBorrowService tBorrowService;

	@Autowired
	private TRechargeService rechargeService;
	
	@Autowired
	private TRepayBorrowerService tRepayBorrowerService;
	
	@Autowired
	private TRepayService tRepayService;


	@Autowired
	private TWithdrawCashService tWithdrawCashService;
	
	@Autowired
	private TAccountService accountService;
	
	/**
	 * 用户充值
	 * 
	 * @param tRecharge
	 * @return
	 */
	@RequestMapping(value = "bfpay/userRecharge")
	public String userRecharge(TRecharge tRecharge) {
		try {
			Long userId = this.getCUser().getId();
			String Orderid = OrdIdUtil.generateOrdId(OrdType.RECHAGER_TYPE);

			// 插入充值表用户充值记录 状态为0
			tRecharge.setUserId(userId);
			tRecharge.setOrdId(Orderid);

			tRecharge.setCreateTime(new Date());
			tRecharge.setOrdDate(new Date());
			rechargeService.insertUserRecharge(tRecharge);

			TThridAccount tthrid = tThridAccountService
					.getThridAccountByUserId(userId);

			RechargeDto entity = new RechargeDto();

			entity.setMerchant_id(BaofooConstans.merchantId);
			entity.setUser_id(tthrid.getThirdUserId());
			entity.setOrder_id(Orderid);
			entity.setAmount(NumberUtil.fomatAmount(tRecharge
					.getRechargeAmount()));
			entity.setFee("0.00");
			entity.setFee_taken_on("1");
			entity.setPage_url(BaofooConstans.rechargePageUrl);
			entity.setReturn_url(BaofooConstans.rechargeReturnUrl);
			log.info("充值请求参数："+XMLBuild.parseEntityToXML(entity));
			BaofooConstans.REQUEST_SERVICE.page_Recharge(entity, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("充值请求异常", e);
			InfoMsg msg = ((ZeroSQLException) e).getInfoMsg();
			this.writeJson(msg);
		}
		return null;
	}
	
	/**
	 * 提现审核
	 * @return
	 */
	@RequestMapping(value="bfpay/userWithdraw")
	public String userWithdraw(TWithdrawCash tWithdrawCash){
		
		
		
		BigDecimal drawmoney=tWithdrawCash.getWithdrawAmount();//用户输入的提现金额
		TAccount tAcount=accountService.getByUserId(this.getCUser().getId());//用户实际金额
		BigDecimal mrpoundage =new BigDecimal("2");//默认手续费
		BigDecimal poundage =null;//实际手续费
		int xqj=DateUtil.getday();//得到今天星期几
		if(xqj==1||xqj==7){
			mrpoundage=new BigDecimal("5");
		}
		//手续费计算
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
			poundage=new BigDecimal("0");
		}
		
		if(tzje.compareTo(ysxfje)<0){
			BigDecimal yssxfje=ysxfje.subtract(tzje).subtract(shiwudaytxe);//需要手续费的金额
			BigDecimal txje=drawmoney;//提现金额
			
			TAccount tAccount2=accountService.getTAccountforuserid(this.getCUser().getId());
			BigDecimal userbleamount=tAccount2.getUsableAmount();
			if(txje.add(yssxfje).compareTo(userbleamount)<1){
				poundage=new BigDecimal("0");
			}else{
				if(yssxfje.compareTo(userbleamount)>-1){
					poundage=txje.multiply(new BigDecimal("0.005")).setScale(2, BigDecimal.ROUND_HALF_UP);
				}else if(userbleamount.subtract(yssxfje).compareTo(txje)>-1){
					poundage=new BigDecimal("0");
				}else if(userbleamount.subtract(yssxfje).compareTo(txje)<1){
					if(yssxfje.compareTo(txje)>-1){
						poundage=txje.multiply(new BigDecimal("0.005")).setScale(2, BigDecimal.ROUND_HALF_UP);
					}else{
						poundage=yssxfje.multiply(new BigDecimal("0.005")).setScale(2, BigDecimal.ROUND_HALF_UP);  
						// txje.subtract(userbleamount.subtract(yssxfje)).multiply(new BigDecimal("0.005")).setScale(2, BigDecimal.ROUND_HALF_UP).add(mrpoundage);
					}
				}
				
								
			}
			
		}
		long result = -1;
		
		if(drawmoney.compareTo(tAcount.getUsableAmount())>0){
			InfoMsg msg = new InfoMsg("用户输入金额高于可提现金额", "n");
			
			this.writeJson(msg);
			return "errors/404";
		}else if(drawmoney.compareTo(new BigDecimal("0"))<1){
			InfoMsg msg = new InfoMsg("用户输入金额错误", "n");
			this.writeJson(msg);
			return "errors/404";
		}else{
			tWithdrawCash.setUserId(this.getCUser().getId());
			tWithdrawCash.setOrdId(OrdIdUtil.generateOrdId(OrdIdUtil.OrdType.WITHDRAW_TYPE));
			tWithdrawCash.setOrdDate(new Date());
			tWithdrawCash.setWithdrawAmount(drawmoney);
			tWithdrawCash.setAccountAmount(drawmoney.subtract(poundage).subtract(mrpoundage));
			tWithdrawCash.setFeeAmount(poundage.add(mrpoundage));
			tWithdrawCash.setCreateTime(new Date());
			tWithdrawCash.setWithdrawStatus(1);
			tWithdrawCash.setRemarks("暂无");
			tWithdrawCash.setPlatformCollect(poundage);
			
			
			result = tWithdrawCashService.inserttwithdraw(tWithdrawCash);//添加提现表记录
			if(result<=0){
				InfoMsg msg = new InfoMsg("提现操作失败","n");
				throw new ZeroSQLException(msg);
			}
			
			
				
				
		}
		
		TWithdrawCash twc=tWithdrawCashService.getTWithdrawCash(tWithdrawCash);
		if(twc==null){
			InfoMsg msg=new InfoMsg("未查出该提现记录","n");
			this.writeJson(msg);
		}
		
		try {
			
			TThridAccount tthrid=tThridAccountService.getThridAccountByUserId(twc.getUserId());
			FoChargePage entity=new FoChargePage();
			entity.setOrder_id(twc.getOrdId());
			entity.setMerchant_id(BaofooConstans.merchantId);
			entity.setUser_id(tthrid.getThirdUserId());
			
			entity.setFee(poundage+"");
			
			
			entity.setAmount(twc.getWithdrawAmount().subtract(poundage).subtract(mrpoundage)+"");
			entity.setFee_taken_on("2");
			entity.setReturn_url(BaofooConstans.twithdrawPageUrl);
			
			log.info("提现请求参数："+XMLBuild.parseEntityToXML(entity));
			
			BaofooConstans.REQUEST_SERVICE.page_foCharge(entity, response);
		} catch (Exception e) {
			log.error("提现异常", e);
		}
				
						
				
		return null;
	}

	/**
	 * 立即投资
	 * 
	 * @return
	 */
	@RequestMapping(value = "bfpay/investBorrow")
	public String addInvest(TInvest tInvest) {
		try {
			CUser cUser = this.getCUser();
			tInvest.setInvestorId(cUser.getId());
			if (tInvest.getInvestAmount() == null) {
				this.writeJson(new InfoMsg("投资金额不能为空", "n"));
				return null;
			}
			tInvest.setInvestorId(this.getCUser().getId());

			InfoMsg msg = tInvestService.addThirdTInvest(tInvest);

			if (!"y".equals(msg.getStatus())) {
				request.setAttribute("msg", msg);
				return "front/comm/back-status";
			}

			TThridAccount tThridAccount = tThridAccountService
					.getThridAccountByUserId(cUser.getId());
			List<Action> actionList = new ArrayList<Action>();
			Action action = new Action(tThridAccount.getThirdUserId(),
					NumberUtil.fomatAmount(tInvest.getInvestAmount()), null,null,
					null);
			TBorrow tempBorrow = new TBorrow();
			tempBorrow.setId(tInvest.getBorrowId());
			TBorrow tBorrow = tBorrowService.getTBorrow(tempBorrow);
			actionList.add(action);

			TThridAccount borrowerThridAccount = tThridAccountService
					.getThridAccountByUserId(tBorrow.getBorrowerId());

			P2pRequestDto p2pRequestDto = new P2pRequestDto(
					BaofooConstans.merchantId, "1", tInvest.getOrdId(),
					tBorrow.getId() + BaofooConstans.BORROW_ID, tBorrow.getBorrowTitle(),
					borrowerThridAccount.getThirdUserId(), tInvest
							.getInvestTime().getTime() + "", actionList, "0.00");

			log.info("立即投标发送参数：" + XMLBuild.parseEntityToXML(p2pRequestDto));

			BaofooConstans.REQUEST_SERVICE.page_p2pRequest(p2pRequestDto,
					BaofooConstans.investReturnUrl,
					BaofooConstans.investPageUrl, response);
		} catch (ZeroSQLException e) {
			log.error("投标异常", e);
			InfoMsg msg = e.getInfoMsg();
			this.writeJson(msg);
		} catch (Exception e) {
			log.error("投标异常", e);
			InfoMsg msg = new InfoMsg("投标异常，请联系客服", "n");
			this.writeJson(msg);
		}
		return null;
	}
	
	/**
	 * 立即投资
	 * 
	 * @return
	 */
	@RequestMapping(value = "bfpay/investAjaxBorrow")
	public String addAjaxInvest(TInvest tInvest) {
		try {
			CUser cUser = this.getCUser();
			tInvest.setInvestorId(cUser.getId());
			if (tInvest.getInvestAmount() == null) {
				this.writeJson(new InfoMsg("投资金额不能为空", "n"));
				return null;
			}
			tInvest.setInvestorId(this.getCUser().getId());

			InfoMsg msg = tInvestService.addThirdTInvest(tInvest);

			if (!"y".equals(msg.getStatus())) {
				this.writeJson(msg);
				return null;
			}

			TThridAccount tThridAccount = tThridAccountService
					.getThridAccountByUserId(cUser.getId());
			List<Action> actionList = new ArrayList<Action>();
			Action action = new Action(tThridAccount.getThirdUserId(),
					NumberUtil.fomatAmount(tInvest.getRealAmount()), null,null,
					null);
			TBorrow tempBorrow = new TBorrow();
			tempBorrow.setId(tInvest.getBorrowId());
			TBorrow tBorrow = tBorrowService.getTBorrow(tempBorrow);
			actionList.add(action);

			TThridAccount borrowerThridAccount = tThridAccountService
					.getThridAccountByUserId(tBorrow.getBorrowerId());

			P2pRequestDto p2pRequestDto = new P2pRequestDto(
					BaofooConstans.merchantId, "1", tInvest.getOrdId(),
					tBorrow.getId() + BaofooConstans.BORROW_ID, tBorrow.getBorrowTitle(),
					borrowerThridAccount.getThirdUserId(), tInvest
							.getInvestTime().getTime() + "", actionList, "0.00");

			log.info("立即投标发送参数：" + XMLBuild.parseEntityToXML(p2pRequestDto));

			String xml = BaofooConstans.REQUEST_SERVICE.serv_p2pRequest(p2pRequestDto);
			log.info("立即投标回传参数：" + xml);
			ResultDto resultDto = BaofooConstans.RECEIVE_SERVICE.serv_p2pRequest(xml);
			if(!"CSD000".equals(resultDto.getCode())){
				msg = tInvestService.updateThirdFailInvest(tInvest.getOrdId(),resultDto.getMsg());
				msg = new InfoMsg(resultDto.getMsg(), "n");
				return null;
			}
			msg = tInvestService.updateThirdSuccessInvest(tInvest.getOrdId(),resultDto.getMsg());
			this.writeJson(msg);
		} catch (ZeroSQLException e) {
			log.error("投标异常", e);
			InfoMsg msg = e.getInfoMsg();
			this.writeJson(msg);
		} catch (Exception e) {
			log.error("投标异常", e);
			InfoMsg msg = new InfoMsg("投标异常，请联系客服", "n");
			this.writeJson(msg);
		}
		return null;
	}
	
	/**
	 * 满标
	 * 
	 * @return
	 */
	@RequestMapping(value = "admin/auditBorrow")
	public String auditBorrow(TBorrow auditBorrow) {
		try {
			BAdmin bAdmin = this.getBAdmin();
			auditBorrow.setFullAuditId(bAdmin.getId());
			
			int count = tInvestService.queryTInvestUndealCountByBorrowId(auditBorrow.getId());
			
			//审核通过
			if("1".equals(auditBorrow.getBorrowStatus()+"")){
				
				
				InfoMsg msg = tBorrowService.updateBorrowThirdFullAudit(auditBorrow);

				if (!"y".equals(msg.getStatus())) {
					this.writeJson(msg);
					return null;
				}
				
				TBorrow tBorrow = tBorrowService.getTBorrow(auditBorrow);
				
				
				
				List<Action> actionList = new ArrayList<Action>();
				TThridAccount borrowerThridAccount = tThridAccountService
						.getThridAccountByUserId(tBorrow.getBorrowerId());
					
				Action action = new Action(borrowerThridAccount.getThirdUserId(),
						NumberUtil.fomatAmount(tBorrow.getBorrowAmount()), "0.00","0",
						null);
				actionList.add(action);
				

				P2pRequestDto p2pRequestDto = new P2pRequestDto(
						BaofooConstans.merchantId, "2",tBorrow.getOrdId() ,
						tBorrow.getId() + BaofooConstans.BORROW_ID, tBorrow.getBorrowTitle(),
						borrowerThridAccount.getThirdUserId(), tBorrow.getAuditTime().getTime() + "", actionList, "0.00");
				
			

				
				log.info("满标发送参数：" + XMLBuild.parseEntityToXML(p2pRequestDto));

				String xml = BaofooConstans.REQUEST_SERVICE.serv_p2pRequest(p2pRequestDto);
				
				log.info("满标回传参数：" + xml);
				ResultDto resultDto = BaofooConstans.RECEIVE_SERVICE.serv_p2pRequest(xml);
				
				if(!"CSD000".equals(resultDto.getCode())){
					msg = new InfoMsg(resultDto.getMsg(), "n");
					this.writeJson(msg);
					return null;
				}
				msg = tBorrowService.updateBorrowThirdFullBack(tBorrow.getOrdId());
				this.writeJson(msg);
			}else{
				InfoMsg msg = tBorrowService.updateBorrowThirdFlowAudit(auditBorrow);

				if (!"y".equals(msg.getStatus())) {
					this.writeJson(msg);
					return null;
				}
				
				TBorrow tBorrow = tBorrowService.getTBorrow(auditBorrow);
				
				List<TInvestDto> investList = tInvestService.queryTInvestAndThirdAccountsListByBorrowId(auditBorrow.getId());
				List<Action> actionList = new ArrayList<Action>();
				if(investList != null){
					for(TInvestDto tInvestDto :investList){
						Action action = new Action(tInvestDto.getThirdUserId(),
								NumberUtil.fomatAmount(tInvestDto.getRealAmount()), "0.00","0",
								null);
						actionList.add(action);
					}
				}
				TThridAccount borrowerThridAccount = tThridAccountService
						.getThridAccountByUserId(tBorrow.getBorrowerId());

				P2pRequestDto p2pRequestDto = new P2pRequestDto(
						BaofooConstans.merchantId, "3", tBorrow.getOrdId(),
						tBorrow.getId() + BaofooConstans.BORROW_ID, tBorrow.getBorrowTitle(),
						borrowerThridAccount.getThirdUserId(), tBorrow.getAuditTime().getTime() + "", actionList, "0.00");

				
				log.info("流标发送参数：" + XMLBuild.parseEntityToXML(p2pRequestDto));

				String xml = BaofooConstans.REQUEST_SERVICE.serv_p2pRequest(p2pRequestDto);
				
				log.info("流标回传参数：" + xml);
				ResultDto resultDto = BaofooConstans.RECEIVE_SERVICE.serv_p2pRequest(xml);
				
				if(!"CSD000".equals(resultDto.getCode())){
					msg = new InfoMsg(resultDto.getMsg(), "n");
					this.writeJson(msg);
					return null;
				}
				msg = tBorrowService.updateBorrowThirdFlowBack( tBorrow.getOrdId());
				this.writeJson(msg);
			}
			
		
		} catch (ZeroSQLException e) {
			log.error("审核异常", e);
			InfoMsg msg = e.getInfoMsg();
			this.writeJson(msg);
		} catch (Exception e) {
			log.error("审核异常", e);
			InfoMsg msg = new InfoMsg("审核异常，请联系技术", "n");
			this.writeJson(msg);
		}
		return null;
	}
	
	/**
	 * 还款
	 * 
	 * @return
	 */
	@RequestMapping(value = "admin/repayBorrow")
	public String repayBorrow(TRepayBorrower tRepayBorrower) {
		try {
			
			InfoMsg msg = tRepayService.updateThirdRepay(tRepayBorrower.getId());
			
			if(!"y".equals(msg.getStatus())){
				this.writeJson(msg);
				return null;
			}
			
			TRepayBorrower tRepayBorrowerModel = tRepayBorrowerService.getTRepayBorrower(tRepayBorrower);

			
			List<TRepay> tRepayList = tRepayService.queryTRepayAccountListByBorrowIdAndPeriod(tRepayBorrowerModel.getBorrowId(),tRepayBorrowerModel.getNumOfPeriods());
			TThridAccount borrowerThridAccount = tThridAccountService.getThridAccountByUserId(tRepayBorrowerModel.getBorrwerId());
			List<Action> actionList = new ArrayList<Action>();
			if(tRepayList != null){
				for(TRepay tRepay : tRepayList){
					BigDecimal capitalProfitAmount = tRepay.getCapitalAmount().add(tRepay.getProfitAmount());
					Action action = new Action(tRepay.getThirdUserId(),
							NumberUtil.fomatAmount(capitalProfitAmount), NumberUtil.fomatAmount(tRepay.getFeeAmount()),null,
							null);
					actionList.add(action);
				}
			}
			
			BigDecimal  capitalProfitAmount = tRepayBorrowerModel.getCapitalAmount().add(tRepayBorrowerModel.getProfitAmount());
			Action borrowerAction = new Action(borrowerThridAccount.getThirdUserId(),
					NumberUtil.fomatAmount(capitalProfitAmount), "0.00",null,
					"1");
			
			actionList.add(borrowerAction);
			TBorrow tempBorrow = new TBorrow();
			tempBorrow.setId(tRepayBorrowerModel.getBorrowId());
			TBorrow tBorrow = tBorrowService.getTBorrow(tempBorrow);

			P2pRequestDto p2pRequestDto = new P2pRequestDto(
					BaofooConstans.merchantId, "4", tRepayBorrowerModel.getOrdId(),
					tBorrow.getId() + BaofooConstans.BORROW_ID, tBorrow.getBorrowTitle(),
					borrowerThridAccount.getThirdUserId(), new Date().getTime() + "", actionList, "0.00");

			
			log.info("还款发送参数：" + XMLBuild.parseEntityToXML(p2pRequestDto));

			String xml = BaofooConstans.REQUEST_SERVICE.serv_p2pRequest(p2pRequestDto);
			
			log.info("还款回传参数：" + xml);
			ResultDto resultDto = BaofooConstans.RECEIVE_SERVICE.serv_p2pRequest(xml);
			
			if(!"CSD000".equals(resultDto.getCode())){
				msg = new InfoMsg(resultDto.getMsg(), "n");
				this.writeJson(msg);
				return null;
			}
			msg = tRepayService.updateThirdRepayBack(tRepayBorrowerModel.getOrdId());
			this.writeJson(msg);
		} catch (ZeroSQLException e) {
			log.error("还款异常", e);
			InfoMsg msg = e.getInfoMsg();
			this.writeJson(msg);
		} catch (Exception e) {
			log.error("还款异常", e);
			InfoMsg msg = new InfoMsg("还款异常，请联系技术", "n");
			this.writeJson(msg);
		}
		return null;
	}
	
	/**
	 * 跳转到宝付界面
	 * @return
	 */
	@RequestMapping(value = "bfpay/loginBaofooUrl")
	public String loginBaofooUrl() {
		try {
			response.sendRedirect(BaofooConstans.loginBaofooUrl);
		} catch (IOException e) {
			log.error("跳转宝付", e);
		}
		return null;
	}

	
}
