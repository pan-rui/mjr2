package com.p2p.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.constans.BusinessConstans;
import com.p2p.dao.TClaimDao;
import com.p2p.dao.TInvestDao;
import com.p2p.dto.InfoMsg;
import com.p2p.enums.FundRecordType;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.TAccount;
import com.p2p.model.TBorrow;
import com.p2p.model.TClaim;
import com.p2p.model.TFundRecord;
import com.p2p.model.TInvest;
import com.p2p.model.TRepay;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.util.CalculateInterestUtil;
import com.p2p.util.DateUtil;
import com.p2p.util.NumberUtil;
import com.p2p.util.OrdIdUtil;
import com.p2p.util.OrdIdUtil.OrdType;


/** 债权转让表**/
@Service
public class TClaimService extends BaseService {


	@Autowired
	private TClaimDao tClaimDao;
	
	@Autowired
	private TInvestService tInvestService;
	
	@Autowired
	private TInvestDao tInvestDao;
	
	@Autowired
	private TRepayService tRepayService;
	
	@Autowired
	private TAccountService tAccountService;
	
	
	@Autowired
	private TBorrowService tBorrowService;
 
	public InfoMsg addTClaim(TClaim tClaim) {
		Long investId = tClaim.getInvestId();
		TInvest tempInvest = new TInvest();
		tempInvest.setId(investId);
		TInvest tInvest = tInvestService.getTInvest(tempInvest);
		if(!tInvest.getInvestorId().equals(tClaim.getClaimUserId())){
			InfoMsg msg = new InfoMsg("非当前用户的债权，不予转让！", "n");
			return msg;
		}
		TRepay tempRepay = new TRepay();
		tempRepay.setBorrowId(tInvest.getBorrowId());
		tempRepay.setInvestId(investId);
		tempRepay.setRepayStatus(1);
		TRepay sumRepay = tRepayService.queryRepayCapitalProfitAmount(tempRepay);
		BigDecimal havingCapitalAmount = sumRepay.getCapitalAmount();
		BigDecimal havingProfitAmount = sumRepay.getProfitAmount();
		//当前用户应收收益
		BigDecimal claimUserProfitAmount = tRepayService.queryRepayHavingProfitAmount(tInvest.getBorrowId(), investId);
		//债权价值
		BigDecimal claimAmount = havingCapitalAmount.add(claimUserProfitAmount);
		BigDecimal expectProfitAmount   = havingCapitalAmount.add(havingProfitAmount).subtract(tClaim.getClaimPriceAmount());
		
		Date lastRepayDate = tRepayService.queryLastRepayDate(investId);
		
		Date nowDate = new Date();
		Long remainDays = DateUtil.diffDays(nowDate, lastRepayDate);
		
		
		BigDecimal claimAnnualRate = CalculateInterestUtil.calculateAnnualRate(expectProfitAmount, havingCapitalAmount, remainDays.intValue());

		tClaim.setClaimAnnualRate(claimAnnualRate);
		tClaim.setBorrowId(tInvest.getBorrowId());
		tClaim.setClaimAmount(claimAmount);
		tClaim.setClaimCapitalAmount(havingCapitalAmount);
		tClaim.setClaimProfitAmount(havingProfitAmount);
		tClaim.setClaimStatus(2);
		tClaim.setClaimUserProfitAmount(claimUserProfitAmount);
		tClaim.setCreateTime(nowDate);
		tClaim.setExpectProfitAmount(expectProfitAmount);
		tClaim.setOrdDate(nowDate);
		String ordId = OrdIdUtil.generateOrdId(OrdIdUtil.OrdType.CLAIM_TYPE);
		tClaim.setOrdId(ordId);
		tClaim.setRemainClaimAmount(tClaim.getClaimAmount());
		tClaim.setRemainClaimCapitalAmount(tClaim.getClaimCapitalAmount());
		tClaim.setRemainClaimPriceAmount(tClaim.getClaimPriceAmount());
		tClaim.setRemainClaimProfitAmount(tClaim.getClaimProfitAmount());
		tClaim.setRemainExpectProfitAmount(tClaim.getRemainExpectProfitAmount());
		tClaim.setLastRepayDate(lastRepayDate);
		tClaim.setRemainDays(remainDays.intValue());
		long result = tClaimDao.insert(tClaim);
		if(result <= 0){
			InfoMsg msg = new InfoMsg("转让申请失败！", "n");
			throw new ZeroSQLException(msg);
		}
		//
		InfoMsg msg = new InfoMsg("转让申请成功！", "n");
		return msg;
	}
 
	public InfoMsg updateTClaim(TClaim tClaim,TInvest tInvest) {
		
		TClaim tClaimModel = tClaimDao.get(tClaim);
		
		Long investId = tClaimModel.getInvestId();
		
		if(!tClaimModel.getClaimStatus().equals(2)){
			InfoMsg msg = new InfoMsg("该债权已结束！", "n");
			return msg;
		}
		
		if(tClaimModel.getClaimUserId().equals(tInvest.getInvestorId())){
			InfoMsg msg = new InfoMsg("不能购买自己转让的债权！", "n");
			return msg;
		}
		
		
		BigDecimal realAmount = tInvest.getInvestAmount();
		BigDecimal priceAmount = tClaimModel.getClaimPriceAmount().multiply(realAmount).divide(tClaimModel.getClaimCapitalAmount(), 2, RoundingMode.HALF_UP);
		BigDecimal claimFeeAmount = priceAmount.multiply(BusinessConstans.CLAIM_MANAGE_FEE);
		TRepay tRepay = new TRepay();
		tRepay.setInvestId(investId);
		tRepay.setRepayStatus(1);
		List<TRepay> repayList = tRepayService.queryTRepayList(tRepay);
		if(repayList == null || repayList.size() == 0){
			InfoMsg msg = new InfoMsg("购买债权失败！", "n");
			return msg;
		}
		Date nowDate = new Date();
		
		//判断购买用户是否有足够的余额
		TAccount tAccount = tAccountService.getByUserId(tInvest.getInvestorId());
		if(tAccount.getUsableAmount().compareTo(priceAmount)<0){
			InfoMsg msg = new InfoMsg("可用余额不足！", "n");
			return msg;
		}
		
		TBorrow tempBorrow = new TBorrow();
		tempBorrow.setId(tClaimModel.getBorrowId());
		TBorrow tBorrow = tBorrowService.getTBorrow(tempBorrow);
		
		//受让人扣款
		TFundRecord fundRecord = new TFundRecord();
		fundRecord.setOperAmount(priceAmount);
		String remarks = FundRecordType.CLAIM_BUY_SUCCESS.getRemarks();
		remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(priceAmount));
		fundRecord.setRemarks(remarks);
		fundRecord.setFundRecordType(FundRecordType.CLAIM_BUY_SUCCESS);
		fundRecord.setTraderId(tClaimModel.getClaimUserId());
		fundRecord.setUserId(tInvest.getInvestorId());
		long result = tAccountService.updateReduceUsableAmount(fundRecord);
		if(result <= 0){
			InfoMsg msg = new InfoMsg("债权转让失败！", "n");
			throw new ZeroSQLException(msg);
		}
		//更新投资记录
		TInvest updateInvest = new TInvest();
		updateInvest.setId(investId);
		updateInvest.setRealAmount(realAmount);
		result = tInvestService.updateInvestClaim(updateInvest);
		if(result <= 0){
			InfoMsg msg = new InfoMsg("购买债权金额大于可购债权金额！", "n");
			throw new ZeroSQLException(msg);
		}
		//添加新的投资记录
		tInvest.setBorrowId(tClaimModel.getBorrowId());
		tInvest.setClaimId(tClaimModel.getId());
		tInvest.setRealAmount(realAmount);
		tInvest.setInvestTime(new Date());
		tInvest.setIsClaim(1);
		tInvest.setOrdDate(nowDate);
		tInvest.setOrdId(OrdIdUtil.generateOrdId(OrdIdUtil.OrdType.INVEST_TYPE));
		tInvest.setResult(1);
		
		result = tInvestDao.insert(tInvest);
		if(result <= 0){
			InfoMsg msg = new InfoMsg("购买债权失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		//
		BigDecimal remainClaimAmount = BigDecimal.ZERO;
		BigDecimal remainClaimProfitAmount = BigDecimal.ZERO;
		BigDecimal remainClaimCapitalAmount = BigDecimal.ZERO;
		for(TRepay tempRepay : repayList){
			TRepay newRepay = new TRepay();
			newRepay.setBorrowId(tempRepay.getBorrowId());
			BigDecimal capitalAmount = tempRepay.getCapitalAmount().multiply(realAmount).divide(tClaimModel.getClaimCapitalAmount(), 2, RoundingMode.HALF_UP);
			BigDecimal profitAmount = tempRepay.getProfitAmount().multiply(realAmount).divide(tClaimModel.getClaimCapitalAmount(), 2, RoundingMode.HALF_UP);
			BigDecimal feeAmount = tempRepay.getFeeAmount().multiply(realAmount).divide(tClaimModel.getClaimCapitalAmount(), 2, RoundingMode.HALF_UP);
			BigDecimal remainCapitalAmount = tempRepay.getRemainCapitalAmount().multiply(realAmount).divide(tClaimModel.getClaimCapitalAmount(), 2, RoundingMode.HALF_UP);
			BigDecimal remainProfitAmount = tempRepay.getRemainProfitAmount().multiply(realAmount).divide(tClaimModel.getClaimCapitalAmount(), 2, RoundingMode.HALF_UP);
			
			remainClaimAmount = remainClaimAmount.add(capitalAmount).add(profitAmount);
			remainClaimProfitAmount = remainClaimProfitAmount.add(profitAmount);
			remainClaimCapitalAmount = remainClaimCapitalAmount.add(capitalAmount);
			
			newRepay.setCapitalAmount(capitalAmount);
			newRepay.setCreateTime(nowDate);
			newRepay.setFeeAmount(feeAmount);
			newRepay.setInvestId(tInvest.getId());
			newRepay.setInvestorId(tInvest.getInvestorId());
			newRepay.setNumOfPeriods(tempRepay.getNumOfPeriods());
			newRepay.setOrdId(OrdIdUtil.generateOrdId(OrdIdUtil.OrdType.REPAY_TYPE));
			newRepay.setPeroids(tempRepay.getPeroids());
			newRepay.setProfitAmount(profitAmount);
			newRepay.setRemainCapitalAmount(remainCapitalAmount);
			newRepay.setRemainProfitAmount(remainProfitAmount);
			newRepay.setRepayDate(tempRepay.getRepayDate());
			newRepay.setRepayStatus(tempRepay.getRepayStatus());
			result = tRepayService.addTRepay(newRepay);
			if(result <= 0){
				InfoMsg msg = new InfoMsg("购买债权失败！", "n");
				throw new ZeroSQLException(msg);
			}
			
			result = tRepayService.updateRepayByClaim(newRepay);
			if(result <= 0){
				InfoMsg msg = new InfoMsg("购买债权失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}

		//转让人收款
		TFundRecord claimfundRecord = new TFundRecord();
		claimfundRecord.setOperAmount(priceAmount);
		remarks = FundRecordType.CLAIM_SELL_SUCCESS.getRemarks();
		remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(priceAmount));
		claimfundRecord.setRemarks(remarks);
		claimfundRecord.setFundRecordType(FundRecordType.CLAIM_SELL_SUCCESS);
		claimfundRecord.setTraderId(tInvest.getInvestorId());
		claimfundRecord.setUserId(tClaimModel.getClaimUserId());
		result = tAccountService.updateAddUsableAmount(claimfundRecord);
		if(result <= 0){
			InfoMsg msg = new InfoMsg("债权转让失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		TFundRecord claimFeefundRecord = new TFundRecord();
		claimFeefundRecord.setOperAmount(claimFeeAmount);
		remarks = FundRecordType.CLAIM_MANAGE.getRemarks();
		remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(claimFeeAmount));
		claimFeefundRecord.setRemarks(remarks);
		claimFeefundRecord.setFundRecordType(FundRecordType.CLAIM_MANAGE);
		claimFeefundRecord.setTraderId(tInvest.getInvestorId());
		claimFeefundRecord.setUserId(tClaimModel.getClaimUserId());
		result = tAccountService.updateReduceUsableAmount(claimFeefundRecord);
		if(result <= 0){
			InfoMsg msg = new InfoMsg("债权转让失败！", "n");
			throw new ZeroSQLException(msg);
		}
		//更新债权
		
		BigDecimal remainClaimPriceAmount = priceAmount;
		
		TRepay queryRepay = new TRepay();
		queryRepay.setInvestId(tClaimModel.getInvestId());
		queryRepay.setBorrowId(tClaimModel.getBorrowId());
		queryRepay.setRepayStatus(1);
		TRepay remainRepay = tRepayService.queryRepayCapitalProfitAmount(queryRepay);
		BigDecimal remainExpectProfitAmount = remainRepay.getProfitAmount();
		TClaim updateClaim = new TClaim();
		updateClaim.setId(tClaimModel.getId());
		updateClaim.setRemainClaimAmount(remainClaimAmount);
		updateClaim.setRemainClaimCapitalAmount(remainClaimCapitalAmount);
		updateClaim.setRemainClaimPriceAmount(remainClaimPriceAmount);
		updateClaim.setRemainClaimProfitAmount(remainClaimProfitAmount);
		updateClaim.setRemainExpectProfitAmount(remainExpectProfitAmount);
		result = tClaimDao.updateBuyClaim(updateClaim);
		if(result <= 0){
			InfoMsg msg = new InfoMsg("债权转让失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		if(tClaimModel.getRemainClaimCapitalAmount().compareTo(realAmount)==0){
			//更新转让完债权
			 updateClaim = new TClaim();
				updateClaim.setId(tClaimModel.getId());
				updateClaim.setFinishTime(new Date());
			result = tClaimDao.updateFinishClaim(updateClaim);
			if(result <= 0){
				InfoMsg msg = new InfoMsg("债权转让失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}
		
		InfoMsg msg = new InfoMsg("债权转让成功！", "n");
		return msg;
	}
 
	public TClaim getTClaim(TClaim tClaim) {
		return tClaimDao.get(tClaim);
	}
 
	public PageList queryTClaimPage(TClaim tClaim,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tClaimDao.getAllBy(tClaim, options, pageBounds);
	}
 }