package com.p2p.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.constans.BorrowStatus;
import com.p2p.constans.BusinessConstans;
import com.p2p.constans.RepayType;
import com.p2p.constans.SysConstans;
import com.p2p.dao.TBorrowAttrDao;
import com.p2p.dao.TBorrowDao;
import com.p2p.dao.TInvestDao;
import com.p2p.dao.TRepayBorrowerDao;
import com.p2p.dao.TRepayDao;
import com.p2p.dto.BorrowAttrArrayDto;
import com.p2p.dto.BorrowDto;
import com.p2p.dto.InfoMsg;
import com.p2p.enums.FundRecordType;
import com.p2p.enums.InfoTemplate;
import com.p2p.exception.ZeroSQLException;
import com.p2p.job.StartInvestJobFactory;
import com.p2p.model.CMessage;
import com.p2p.model.TBorrow;
import com.p2p.model.TBorrowAttr;
import com.p2p.model.TFundRecord;
import com.p2p.model.TInvest;
import com.p2p.model.TRepay;
import com.p2p.model.TRepayBorrower;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.util.CalculateInterestUtil;
import com.p2p.util.NumberUtil;
import com.p2p.util.OrdIdUtil;
import com.p2p.util.OrdIdUtil.OrdType;

/** 借款表 **/
@Service
public class TBorrowService extends BaseService {
	private static Log log = LogFactory.getLog(TBorrowService.class);
	@Autowired
	private TBorrowDao tBorrowDao;

	@Autowired
	private TInvestDao tInvestDao;

	@Autowired
	private TAccountService tAccountService;
	
	@Autowired
	private TRepayDao tRepayDao;
	
	@Autowired
	private TBorrowAttrDao tBorrowAttrDao;
	
	@Autowired
	private TRepayBorrowerDao tRepayBorrowerDao;
	
	
	@Autowired
	private StartInvestJobFactory startInvestJobFactory;
	
	@Autowired
	private CMessageService cMessageService;
	
	@Autowired
	private TInvestAwardService tInvestAwardService;
	
	public long addTBorrow(TBorrow tBorrow,TBorrowAttr[] borrowAttrs) {
		long result = tBorrowDao.insert(tBorrow);
		if(result <= 0){
			throw new ZeroSQLException();
		}
		if(borrowAttrs != null){
			for(TBorrowAttr tBorrowAttr : borrowAttrs){
				tBorrowAttr.setCreateTime(new Date());
				tBorrowAttr.setBorrowId(tBorrow.getId());
				result = tBorrowAttrDao.insert(tBorrowAttr);
				if(result <= 0){
					throw new ZeroSQLException();
				}
			}
		}
		return result;
	}

	public long updateTBorrow(TBorrow tBorrow) {
		return tBorrowDao.update(tBorrow);
	}

	public TBorrow getTBorrow(TBorrow tBorrow) {
		return tBorrowDao.get(tBorrow);
	}

	public PageList queryTBorrowPage(TBorrow tBorrow,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList) tBorrowDao.getAllBy(tBorrow, options, pageBounds);
	}

	public int selectidfortitle(String borrowTitle) {
		return tBorrowDao.selectidfortitle(borrowTitle);
	}

	/**
	 * 满标复审
	 * 
	 * @param borrowId
	 * @return
	 */
	public InfoMsg updateBorrowFullAudit(TBorrow auditBorrow) {
		TBorrow tBorrow = tBorrowDao.get(auditBorrow);
		auditBorrow.setAuditTime(new Date());
		auditBorrow.setBorrowStatus(BorrowStatus.REPAYING);
		if (tBorrow.getBorrowStatus().equals(5)) {
			InfoMsg msg = new InfoMsg("重复审核！", "n");
			return msg;
		}

		// 更新借款状态
		long result = tBorrowDao.updateBorrowAuditTwo(auditBorrow);

		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		String dealineStr = null;
		if(tBorrow.getDeadlineType().equals(1)){
			dealineStr = tBorrow.getDeadline() + "天"	;
		}else{
			dealineStr = tBorrow.getDeadline() + "个月"	;
		}
		
		List<CMessage> messageList = new ArrayList<CMessage>();
		//处理投资人业务
		
		// 扣除投资人投资金额
		List<TInvest> investList = tInvestDao.queryTInvestByBorrowId(tBorrow
				.getId());
		for (TInvest tInvest : investList) {
			//解冻投资金额
			TFundRecord unfreezeFundRecord = new TFundRecord();
			unfreezeFundRecord.setOperAmount(tInvest.getRealAmount());
			String remarks = FundRecordType.INVEST_SUCCESS_UNFREEZE.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#amount",NumberUtil.fomatAmount(tInvest.getRealAmount()));
			unfreezeFundRecord.setRemarks(remarks);
			unfreezeFundRecord.setFundRecordType(FundRecordType.INVEST_SUCCESS_UNFREEZE);
			unfreezeFundRecord.setUserId(tInvest.getInvestorId());
			result = tAccountService.updateUnfreezeAmount(unfreezeFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
			
			//扣除投资金额
			TFundRecord investFundRecord = new TFundRecord();
			investFundRecord.setOperAmount(tInvest.getRealAmount());
			remarks = FundRecordType.INVEST_SUCCESS.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#amount",NumberUtil.fomatAmount(tInvest.getRealAmount()));
			investFundRecord.setRemarks(remarks);
			investFundRecord.setFundRecordType(FundRecordType.INVEST_SUCCESS);
			investFundRecord.setTraderId(tBorrow.getBorrowerId());
			investFundRecord.setUserId(tInvest.getInvestorId());
			result = tAccountService.updateReduceUsableAmount(investFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
			
			//添加还款记录
			List<Map<String,Object>> repayList = null;
			if(tBorrow.getRepayType().equals(RepayType.ONE_REPAY)){
				boolean isDay = tBorrow.getDeadlineType().equals(1);
				repayList = CalculateInterestUtil.calculateOneRepayList(tInvest.getRealAmount(), tBorrow.getDeadline(), tBorrow.getAnnualRate(),isDay );
			}else if(tBorrow.getRepayType().equals(RepayType.EQUAL_MONTHLY)){
				repayList = CalculateInterestUtil.calculateEqualMonthlyList(tInvest.getRealAmount(), tBorrow.getDeadline(), tBorrow.getAnnualRate());
			}else if(tBorrow.getRepayType().equals(RepayType.MONTH_REPAY_INTEREST)){
				repayList = CalculateInterestUtil.calculateMonthRepayInterestList(tInvest.getRealAmount(), tBorrow.getDeadline(), tBorrow.getAnnualRate());
			}else{
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
			Date nowDate = new Date();
			int peroids = repayList.size();
			BigDecimal allProfitAmount = BigDecimal.ZERO;
			for(Map<String,Object> repayMap : repayList){
				TRepay tRepay = new TRepay();
				BigDecimal profitAmount = (BigDecimal)repayMap.get("profitAmount");
				allProfitAmount = allProfitAmount.add(profitAmount);
				BigDecimal feeAmount = profitAmount.multiply(BusinessConstans.INVEST_MANAGE_FEE).setScale(SysConstans.VALUE_SCALE, RoundingMode.HALF_UP);
				tRepay.setBorrowId(tBorrow.getId());
				tRepay.setCapitalAmount((BigDecimal)repayMap.get("capitalAmount"));
				tRepay.setCreateTime(nowDate);
				tRepay.setInvestId(tInvest.getId());
				tRepay.setFeeAmount(feeAmount);
				tRepay.setInvestorId(tInvest.getInvestorId());
				tRepay.setNumOfPeriods((Integer)repayMap.get("period"));
				tRepay.setOrdId(OrdIdUtil.generateOrdId(OrdIdUtil.OrdType.REPAY_TYPE));
				tRepay.setPeroids(peroids);
				tRepay.setProfitAmount(profitAmount);
				tRepay.setRemainCapitalAmount((BigDecimal)repayMap.get("remainCapitalAmount"));
				tRepay.setRemainProfitAmount((BigDecimal)repayMap.get("remainProfitAmount"));
				tRepay.setRepayDate((Date)repayMap.get("repayDate"));
				tRepay.setRepayStatus(1);
				result = tRepayDao.insert(tRepay);
				if (result <= 0) {
					InfoMsg msg = new InfoMsg("复审借款失败！", "n");
					throw new ZeroSQLException(msg);
				}
				
			
				
			}
			//投资人投资成功信息
			CMessage cMessage = new CMessage();
			cMessage.setCreateTime(new Date());
			cMessage.setReceiverId(tInvest.getInvestorId());
			InfoTemplate infoTemplate = InfoTemplate.INVEST_SUCCESS;
			cMessage.setMessageTitle(infoTemplate.getSmsType());
			String messageContent = infoTemplate.getContentTemplate();
			messageContent = messageContent.replace("#borrowTitle", tBorrow.getBorrowTitle());
			messageContent = messageContent.replace("#investAmount", NumberUtil.fomatAmount(tInvest.getRealAmount()));
			messageContent = messageContent.replace("#dealine", dealineStr);
			messageContent = messageContent.replace("#profitAmount", NumberUtil.fomatAmount(allProfitAmount));
			
			cMessage.setMessageContent(messageContent);
			result = cMessageService.addCMessage(cMessage);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}
		
		//处理借款人业务
		//添加借款人还款表
		
		List<TRepay> investRepayList = tRepayDao.queryTRepayListByBorrowIdGroupPeriod(tBorrow.getId());
		
		for(TRepay tempTRepay : investRepayList){
			TRepayBorrower tRepayBorrower = new TRepayBorrower();
			tRepayBorrower.setOrdId(OrdIdUtil.generateOrdId(OrdType.REPAY_BORROWER_TYPE));
			tRepayBorrower.setBorrowId(tBorrow.getId());
			tRepayBorrower.setBorrwerId(tBorrow.getBorrowerId());
			tRepayBorrower.setCapitalAmount(tempTRepay.getCapitalAmount());
			tRepayBorrower.setProfitAmount(tempTRepay.getProfitAmount());
			tRepayBorrower.setPeroids(tempTRepay.getPeroids());
			tRepayBorrower.setRemainCapitalAmount(tempTRepay.getRemainCapitalAmount());
			tRepayBorrower.setRemainProfitAmount(tempTRepay.getRemainProfitAmount());
			tRepayBorrower.setRepayDate(tempTRepay.getRepayDate());
			tRepayBorrower.setRepayStatus(tempTRepay.getRepayStatus());
			result = tRepayBorrowerDao.insert(tRepayBorrower);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}
		
		
		
		TFundRecord borrowerFundRecord = new TFundRecord();
		borrowerFundRecord.setOperAmount(tBorrow.getBorrowAmount());
		String remarks = FundRecordType.BORROW_SUCCESS.getRemarks();
		remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(tBorrow.getBorrowAmount()));
		borrowerFundRecord.setRemarks(remarks);
		
		borrowerFundRecord.setFundRecordType(FundRecordType.BORROW_SUCCESS);
		borrowerFundRecord.setUserId(tBorrow.getBorrowerId());
		result = tAccountService.updateAddUsableAmount(borrowerFundRecord);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		//借款人借款成功信息
		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(tBorrow.getBorrowerId());
		InfoTemplate infoTemplate = InfoTemplate.BORROW_SUCCESS;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#borrowTitle", tBorrow.getBorrowTitle());
		messageContent = messageContent.replace("#borrowAmount", NumberUtil.fomatAmount(tBorrow.getBorrowAmount()));
		messageContent = messageContent.replace("#dealine", dealineStr);
		
		cMessage.setMessageContent(messageContent);
		result = cMessageService.addCMessage(cMessage);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		InfoMsg msg = new InfoMsg("复审借款成功！", "y");
		return msg;
	}
	/**
	 * 满标复审
	 * 
	 * @param borrowId
	 * @return
	 */
	public InfoMsg updateBorrowThirdFullAudit(TBorrow auditBorrow) {
		TBorrow tBorrow = tBorrowDao.get(auditBorrow);
		auditBorrow.setAuditTime(new Date());
		auditBorrow.setBorrowStatus(BorrowStatus.AUDIT_TWO_DEALING);
		if (tBorrow.getBorrowStatus().equals(BorrowStatus.AUDIT_TWO_DEALING)) {
			InfoMsg msg = new InfoMsg("重复审核！", "n");
			return msg;
		}
		if (tBorrow.getBorrowStatus().equals(BorrowStatus.REPAYING)) {
			InfoMsg msg = new InfoMsg("重复审核！", "n");
			return msg;
		}

		// 更新借款状态
		long result = tBorrowDao.updateBorrowAuditTwo(auditBorrow);

		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		InfoMsg msg = new InfoMsg("复审借款成功！", "y");
		return msg;
	}
	
	/**
	 * 流标
	 * 
	 * @param borrowId
	 * @return
	 */
	public InfoMsg updateBorrowThirdFlowAudit(TBorrow auditBorrow) {
		TBorrow tBorrow = tBorrowDao.get(auditBorrow);
		auditBorrow.setAuditTime(new Date());
		auditBorrow.setBorrowStatus(BorrowStatus.FLOW_DEALING);
		if (tBorrow.getBorrowStatus().equals(BorrowStatus.FLOW_DEALING)) {
			InfoMsg msg = new InfoMsg("重复审核！", "n");
			return msg;
		}
		if (tBorrow.getBorrowStatus().equals(BorrowStatus.AUDIT_TWO_FAIL)) {
			InfoMsg msg = new InfoMsg("重复审核！", "n");
			return msg;
		}
		if (tBorrow.getBorrowStatus().equals(BorrowStatus.FLOW)) {
			InfoMsg msg = new InfoMsg("重复审核！", "n");
			return msg;
		}

		// 更新借款状态
		long result = tBorrowDao.updateBorrowAuditTwo(auditBorrow);

		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		InfoMsg msg = new InfoMsg("复审借款成功！", "y");
		return msg;
	}
	
	

	
	/**
	 * 满标复审回调操作
	 * 
	 * @param borrowId
	 * @return
	 */
	public InfoMsg updateBorrowThirdFullBack(String ordId) {
		
		TBorrow tBorrow = tBorrowDao.getTBorrowByOrdId(ordId);
		if(!tBorrow.getBorrowStatus().equals(BorrowStatus.AUDIT_TWO_DEALING)){
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		TBorrow auditBorrow = new TBorrow();
		auditBorrow.setId(tBorrow.getId());
		
		auditBorrow.setAuditTime(new Date());
		auditBorrow.setBorrowStatus(BorrowStatus.REPAYING);
		// 更新借款状态
		long result = tBorrowDao.updateBorrowFullAuditBack(auditBorrow);

		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		String dealineStr = null;
		if(tBorrow.getDeadlineType().equals(1)){
			dealineStr = tBorrow.getDeadline() + "天"	;
		}else{
			dealineStr = tBorrow.getDeadline() + "个月"	;
		}
		
		List<CMessage> messageList = new ArrayList<CMessage>();
		//处理投资人业务
		Date nowDate = new Date();
		// 扣除投资人投资金额
		List<TInvest> investList = tInvestDao.queryTInvestByBorrowId(tBorrow
				.getId());
		for (TInvest tInvest : investList) {
			//解冻投资金额
			TFundRecord unfreezeFundRecord = new TFundRecord();
			unfreezeFundRecord.setOperAmount(tInvest.getRealAmount());
			String remarks = FundRecordType.INVEST_SUCCESS_UNFREEZE.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#amount",NumberUtil.fomatAmount(tInvest.getRealAmount()));
			unfreezeFundRecord.setRemarks(remarks);
			unfreezeFundRecord.setFundRecordType(FundRecordType.INVEST_SUCCESS_UNFREEZE);
			unfreezeFundRecord.setUserId(tInvest.getInvestorId());
			result = tAccountService.updateUnfreezeAmount(unfreezeFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
			
			//扣除投资金额
			TFundRecord investFundRecord = new TFundRecord();
			investFundRecord.setOperAmount(tInvest.getRealAmount());
			remarks = FundRecordType.INVEST_SUCCESS.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#amount",NumberUtil.fomatAmount(tInvest.getRealAmount()));
			investFundRecord.setRemarks(remarks);
			investFundRecord.setFundRecordType(FundRecordType.INVEST_SUCCESS);
			investFundRecord.setTraderId(tBorrow.getBorrowerId());
			investFundRecord.setUserId(tInvest.getInvestorId());
			result = tAccountService.updateReduceUsableAmount(investFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
			
			//添加还款记录
			List<Map<String,Object>> repayList = null;
			if(tBorrow.getRepayType().equals(RepayType.ONE_REPAY)){
				boolean isDay = tBorrow.getDeadlineType().equals(1);
				repayList = CalculateInterestUtil.calculateOneRepayList(tInvest.getRealAmount(), tBorrow.getDeadline(), tBorrow.getAnnualRate(),isDay );
			}else if(tBorrow.getRepayType().equals(RepayType.EQUAL_MONTHLY)){
				repayList = CalculateInterestUtil.calculateEqualMonthlyList(tInvest.getRealAmount(), tBorrow.getDeadline(), tBorrow.getAnnualRate());
			}else if(tBorrow.getRepayType().equals(RepayType.MONTH_REPAY_INTEREST)){
				repayList = CalculateInterestUtil.calculateMonthRepayInterestList(tInvest.getRealAmount(), tBorrow.getDeadline(), tBorrow.getAnnualRate());
			}else{
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
		
			int peroids = repayList.size();
			BigDecimal allProfitAmount = BigDecimal.ZERO;
			for(Map<String,Object> repayMap : repayList){
				TRepay tRepay = new TRepay();
				BigDecimal profitAmount = (BigDecimal)repayMap.get("profitAmount");
				allProfitAmount = allProfitAmount.add(profitAmount);
				BigDecimal feeAmount = profitAmount.multiply(BusinessConstans.INVEST_MANAGE_FEE).setScale(SysConstans.VALUE_SCALE, RoundingMode.HALF_UP);
				tRepay.setBorrowId(tBorrow.getId());
				tRepay.setCapitalAmount((BigDecimal)repayMap.get("capitalAmount"));
				tRepay.setCreateTime(nowDate);
				tRepay.setInvestId(tInvest.getId());
				tRepay.setFeeAmount(feeAmount);
				tRepay.setInvestorId(tInvest.getInvestorId());
				tRepay.setNumOfPeriods((Integer)repayMap.get("period"));
				tRepay.setOrdId(OrdIdUtil.generateOrdId(OrdIdUtil.OrdType.REPAY_TYPE));
				tRepay.setPeroids(peroids);
				tRepay.setProfitAmount(profitAmount);
				tRepay.setRemainCapitalAmount((BigDecimal)repayMap.get("remainCapitalAmount"));
				tRepay.setRemainProfitAmount((BigDecimal)repayMap.get("remainProfitAmount"));
				tRepay.setRepayDate((Date)repayMap.get("repayDate"));
				tRepay.setRepayStatus(1);
				result = tRepayDao.insert(tRepay);
				if (result <= 0) {
					InfoMsg msg = new InfoMsg("复审借款失败！", "n");
					throw new ZeroSQLException(msg);
				}
				
			
				
			}

			//投资人投资成功信息
			CMessage cMessage = new CMessage();
			cMessage.setCreateTime(new Date());
			cMessage.setReceiverId(tInvest.getInvestorId());
			InfoTemplate infoTemplate = InfoTemplate.INVEST_SUCCESS;
			cMessage.setMessageTitle(infoTemplate.getSmsType());
			String messageContent = infoTemplate.getContentTemplate();
			messageContent = messageContent.replace("#borrowTitle", tBorrow.getBorrowTitle());
			messageContent = messageContent.replace("#investAmount", NumberUtil.fomatAmount(tInvest.getRealAmount()));
			messageContent = messageContent.replace("#dealine", dealineStr);
			messageContent = messageContent.replace("#profitAmount", NumberUtil.fomatAmount(allProfitAmount));
			
			cMessage.setMessageContent(messageContent);
			result = cMessageService.addCMessage(cMessage);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}
		
		//处理借款人业务
		//借款人还款记录
		List<TRepay> queryRepayList = tRepayDao.queryTRepayListByBorrowIdGroupPeriod(tBorrow.getId());
		for(TRepay tempRepay :queryRepayList ){
			TRepayBorrower tRepayBorrower = new TRepayBorrower();
			tRepayBorrower.setBorrowId(tBorrow.getId());
			tRepayBorrower.setBorrwerId(tBorrow.getBorrowerId());
			tRepayBorrower.setCapitalAmount(tempRepay.getCapitalAmount());
			tRepayBorrower.setCreateTime(nowDate);
			tRepayBorrower.setNumOfPeriods(tempRepay.getNumOfPeriods());
			tRepayBorrower.setOrdId(OrdIdUtil.generateOrdId(OrdType.REPAY_BORROWER_TYPE));
			tRepayBorrower.setPeroids(tempRepay.getPeroids());
			tRepayBorrower.setProfitAmount(tempRepay.getProfitAmount());
			tRepayBorrower.setRemainCapitalAmount(tempRepay.getRemainCapitalAmount());
			tRepayBorrower.setRemainProfitAmount(tempRepay.getRemainProfitAmount());
			tRepayBorrower.setRepayDate(tempRepay.getRepayDate());
			tRepayBorrower.setRepayStatus(tempRepay.getRepayStatus());
			log.info("insert===="+tRepayBorrower);
			result = tRepayBorrowerDao.insert(tRepayBorrower);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}
		
		//操作资金
		TFundRecord borrowerFundRecord = new TFundRecord();
		borrowerFundRecord.setOperAmount(tBorrow.getBorrowAmount());
		String remarks = FundRecordType.BORROW_SUCCESS.getRemarks();
		remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
		remarks = remarks.replace("#amount",NumberUtil.fomatAmount(tBorrow.getBorrowAmount()));
		borrowerFundRecord.setRemarks(remarks);
		
		borrowerFundRecord.setFundRecordType(FundRecordType.BORROW_SUCCESS);
		borrowerFundRecord.setUserId(tBorrow.getBorrowerId());
		result = tAccountService.updateAddUsableAmount(borrowerFundRecord);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		//借款人借款成功信息
		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(tBorrow.getBorrowerId());
		InfoTemplate infoTemplate = InfoTemplate.BORROW_SUCCESS;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#borrowTitle", tBorrow.getBorrowTitle());
		messageContent = messageContent.replace("#borrowAmount", NumberUtil.fomatAmount(tBorrow.getBorrowAmount()));
		messageContent = messageContent.replace("#dealine", dealineStr);
		
		cMessage.setMessageContent(messageContent);
		result = cMessageService.addCMessage(cMessage);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		//更新还款时间
		Date lastRepayDate = tRepayBorrowerDao.queryLastRepayDate(tBorrow.getId());
		if(lastRepayDate == null){
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		TBorrow updateBorrow = new TBorrow();
		updateBorrow.setId(tBorrow.getId());
		updateBorrow.setRepayDate(lastRepayDate);
		result = tBorrowDao.update(updateBorrow);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		//添加新手投资奖励和投资奖励
		result = tInvestAwardService.addTInvestAwards(tBorrow.getId());
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		InfoMsg msg = new InfoMsg("复审借款成功！", "y");
		return msg;
	}


	/**
	 * 分页查询借款
	 * @param borrowDto
	 * @param pageBounds
	 * @return
	 */
	public PageList queryTBorrowPage(BorrowDto borrowDto, PageBounds pageBounds) {
		return tBorrowDao.queryBorrowDtoPage(borrowDto,pageBounds);
	}

	/**
	 * 发标审核
	 * @param auditBorrow
	 * @return
	 * @throws SchedulerException 
	 */
	public long updateBorrowFirstAudit(TBorrow auditBorrow) throws SchedulerException {
		startInvestJobFactory.startInvestJob(auditBorrow.getId(), auditBorrow.getInvestStartTime());
		long result = tBorrowDao.updateBorrowFirstAudit(auditBorrow);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("初审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		return result;
	}

	public long updateBorrowStartInvest(Long borrowId) {
		return tBorrowDao.updateBorrowStartInvest(borrowId);
		
	}
	
	/**
	 * 初始化定时发标
	 */
	public void initStartInvestBorrow() {
		List<TBorrow> borrowList = tBorrowDao.queryTBorrowListByInvestDate();
		if(borrowList != null){
			for(TBorrow tBorrow : borrowList){
				try {
					startInvestJobFactory.startInvestJob(tBorrow.getId(), tBorrow.getInvestStartTime());
				} catch (SchedulerException e) {
					log.error("初始化定时发标失败",e);
				}
			}
		}
		
	}


	public PageList<TBorrow> queryTBorrowListPage(BorrowDto borrowDto,
			PageBounds pageBounds) {
		
		return tBorrowDao.queryTBorrowListPage(borrowDto,pageBounds);
	}
	
	public List<TBorrow> queryIndexTBorrowListPage(BorrowDto borrowDto){
		return tBorrowDao.queryIndexTBorrowListPage(borrowDto);
	};

	public List<TBorrow> queryTBorrowList(TBorrow tBorrow) {
		return tBorrowDao.queryTBorrowList(tBorrow);
	}

	public InfoMsg updateBorrowThirdFlowBack(String ordId) {
		TBorrow tBorrow = tBorrowDao.getTBorrowByOrdId(ordId);
		if(!tBorrow.getBorrowStatus().equals(BorrowStatus.FLOW_DEALING)){
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		TBorrow auditBorrow = new TBorrow();
		auditBorrow.setId(tBorrow.getId());
		
		auditBorrow.setAuditTime(new Date());
		auditBorrow.setBorrowStatus(BorrowStatus.FLOW);
		// 更新借款状态
		long result = tBorrowDao.updateBorrowFlowAuditBack(auditBorrow);

		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		String dealineStr = null;
		if(tBorrow.getDeadlineType().equals(1)){
			dealineStr = tBorrow.getDeadline() + "天"	;
		}else{
			dealineStr = tBorrow.getDeadline() + "个月"	;
		}
		
		List<CMessage> messageList = new ArrayList<CMessage>();
		//处理投资人业务
		Date nowDate = new Date();
		// 扣除投资人投资金额
		List<TInvest> investList = tInvestDao.queryTInvestByBorrowId(tBorrow
				.getId());
		for (TInvest tInvest : investList) {
			//解冻投资金额
			TFundRecord unfreezeFundRecord = new TFundRecord();
			unfreezeFundRecord.setOperAmount(tInvest.getRealAmount());
			String remarks = FundRecordType.INVEST_UNFREEZE.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#amount",NumberUtil.fomatAmount(tInvest.getRealAmount()));
			unfreezeFundRecord.setRemarks(remarks);
			unfreezeFundRecord.setFundRecordType(FundRecordType.INVEST_UNFREEZE);
			unfreezeFundRecord.setUserId(tInvest.getInvestorId());
			result = tAccountService.updateUnfreezeAmount(unfreezeFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
	
			//投资人投资成功信息
			CMessage cMessage = new CMessage();
			cMessage.setCreateTime(new Date());
			cMessage.setReceiverId(tInvest.getInvestorId());
			InfoTemplate infoTemplate = InfoTemplate.INVEST_FAIL;
			cMessage.setMessageTitle(infoTemplate.getSmsType());
			String messageContent = infoTemplate.getContentTemplate();
			messageContent = messageContent.replace("#borrowTitle", tBorrow.getBorrowTitle());
			messageContent = messageContent.replace("#investAmount", NumberUtil.fomatAmount(tInvest.getRealAmount()));
			
			cMessage.setMessageContent(messageContent);
			result = cMessageService.addCMessage(cMessage);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("复审借款失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}
		
		
		
		
		//借款人借款借款失败信息
		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(tBorrow.getBorrowerId());
		InfoTemplate infoTemplate = InfoTemplate.BORROW_FAIL;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#borrowTitle", tBorrow.getBorrowTitle());

		cMessage.setMessageContent(messageContent);
		result = cMessageService.addCMessage(cMessage);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		InfoMsg msg = new InfoMsg("复审借款成功！", "y");
		return msg;
	}

	public long updateBorrowThirdFullResetBack(String ordId) {
		TBorrow tBorrow = tBorrowDao.getTBorrowByOrdId(ordId);
		if(!tBorrow.getBorrowStatus().equals(BorrowStatus.AUDIT_TWO_DEALING)){
			InfoMsg msg = new InfoMsg("复审借款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		return tBorrowDao.updateBorrowResetBackFullStatus(tBorrow.getId());
		
	}

	
	/**
	 * 添加项目资料信息
	 * @param tBorrowAttr
	 * @return
	 */
	public long insertTBorrowAttr(TBorrowAttr tBorrowAttr){
		return tBorrowAttrDao.insert(tBorrowAttr);
	}
}