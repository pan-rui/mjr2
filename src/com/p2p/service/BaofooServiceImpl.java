package com.p2p.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baofoo.p2p.dto.receive.ResultDto;
import com.baofoo.p2p.dto.request.TransferDto;
import com.baofoo.p2p.util.XMLBuild;
import com.p2p.constans.BaofooConstans;
import com.p2p.dto.InfoMsg;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.TCoupon;
import com.p2p.model.TInvestAward;
import com.p2p.model.TThridAccount;
import com.p2p.model.TTransfer;
import com.p2p.util.NumberUtil;
import com.p2p.util.OrdIdUtil;
import com.p2p.util.OrdIdUtil.OrdType;

@Service
public class BaofooServiceImpl extends BaseService {
	private static Log log = LogFactory.getLog(BaofooServiceImpl.class);

	@Autowired
	private TTransferService tTransferService;
	
	@Autowired
	private TAccountService tAccountService;
	
	@Autowired
	private TThridAccountService tThridAccountService;
	
	@Autowired
	private TCouponService tCouponService;
	
	@Autowired
	private TInvestAwardService tInvestAwardService;
	
	
	/**
	 * 商户转账
	 * @param tTransfer 需要传的参数transInId，transAmount，remarks(转账备注，用来发送短信或其它),seriesNumber（序列号），transType
	 * @return
	 */
	public InfoMsg  addMerchantTTransUser(TTransfer tTransfer){
		
		TThridAccount tThridAccount = tThridAccountService.getThridAccountByUserId(tTransfer.getTransInId());
	
		Date nowDate = new Date();
		tTransfer.setTransOutId(null);
		tTransfer.setOrdDate(nowDate);
		tTransfer.setCreateTime(nowDate);
		String ordId = OrdIdUtil.generateOrdId(OrdType.TTRANSFER_TYPE);
		tTransfer.setTranStatus(1);
		tTransfer.setOrdId(ordId);
		long result = tTransferService.addTTransfer(tTransfer);
		if(result < 0){
			InfoMsg msg = new InfoMsg("转账插入记录异常","n");
			return msg;
		}
		
		TransferDto transferDto = new TransferDto();
		transferDto.setAmount(NumberUtil.fomatAmount(tTransfer.getTransAmount()));
		transferDto.setFee("0.00");
		transferDto.setFee_taken_on("1");
		transferDto.setMerchant_id(BaofooConstans.merchantId);
		transferDto.setOrder_id(ordId);
		transferDto.setPayer_type("1");
		transferDto.setPayer_user_id(BaofooConstans.merchantId);
		transferDto.setPayee_type("0");
		transferDto.setPayee_user_id(tThridAccount.getThirdUserId());
		
		transferDto.setReq_time(nowDate.getTime()+"");
		
		try {
			log.info("商户转账发送XML:"+XMLBuild.parseEntityToXML(transferDto));
			String xml = BaofooConstans.REQUEST_SERVICE.serv_Transfer(transferDto);
			log.info("商户转账返回XML:"+xml);
			ResultDto resultDto = BaofooConstans.RECEIVE_SERVICE.serv_Transfer(xml);
			if("CSD000".equals(resultDto.getCode())){
				TTransfer updateTTransfer = new TTransfer();
				updateTTransfer.setOrdId(ordId);
				updateTTransfer.setStatusRemarks(resultDto.getMsg());
				updateTTransfer.setTranStatus(2);
				updateTTransfer.setFinishTime(new Date());
				tTransferService.updateTTransferSuccess(updateTTransfer);
				return new InfoMsg("转账成功","y");
				
			}else{
				TTransfer updateTTransfer = new TTransfer();
				updateTTransfer.setOrdId(ordId);
				updateTTransfer.setStatusRemarks(resultDto.getMsg());
				updateTTransfer.setTranStatus(3);
				updateTTransfer.setFinishTime(new Date());
				tTransferService.updateTTransferFail(updateTTransfer);
				return new InfoMsg("转账失败，"+resultDto.getMsg(),"n");
			}
		} catch (Exception e) {
			log.error("转账异常", e);
		}
		return new InfoMsg("处理中","o");
	}

	
	/**
	 * 为申请首投红包的客户，赠送红包
	 */
	public long updateUserRedPapersType(TCoupon tCoupon){
		Long rest = -1l;
		tCoupon.setCouponStatus(6);
		rest = tCouponService.updateUserCouponType(tCoupon);
		if(rest<=0){
			return rest;
		}
			
		TTransfer tTransfer = new  TTransfer();
		tTransfer.setTransInId(tCoupon.getUserId());
		tTransfer.setTransAmount(tCoupon.getCouponAmount());
		tTransfer.setRemarks(tCoupon.getCouponName());
		tTransfer.setSeriesNumber(tCoupon.getId()+"");
		tTransfer.setTransType("coupon");
		InfoMsg msg = addMerchantTTransUser(tTransfer);
		if(!"n".equals(msg.getStatus())){
			rest = 1L;
		}
		return rest;

	}
	
	/**
	 * 为投资用户，发放投资奖励
	 */
	public long updateSetoutUserInvestAward(TInvestAward tInvestAward){
		Long rest = -1l;
		tInvestAward.setAwardStatus(2);
		rest = tInvestAwardService.updateUserAwardType(tInvestAward);
		if(rest<=0){
			return rest;
		}
		TTransfer tTransfer = new  TTransfer();
		tTransfer.setTransInId(tInvestAward.getUserId());
		tTransfer.setTransAmount(tInvestAward.getAwardAmount());
		tTransfer.setRemarks(tInvestAward.getRemarks());

		tTransfer.setSeriesNumber(tInvestAward.getId()+"");
		tTransfer.setTransType("investAward");

		InfoMsg msg = addMerchantTTransUser(tTransfer);
		if(!"n".equals(msg.getStatus())){
			rest = 1L;
		}
		log.info("投资奖励已发放");
		return rest;
	}
}
