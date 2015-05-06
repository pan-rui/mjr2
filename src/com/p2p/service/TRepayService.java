package com.p2p.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.constans.BorrowStatus;
import com.p2p.dao.TBorrowDao;
import com.p2p.dao.TRepayBorrowerDao;
import com.p2p.dao.TRepayDao;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.RepayDto;
import com.p2p.enums.FundRecordType;
import com.p2p.enums.InfoTemplate;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.CMessage;
import com.p2p.model.TAccount;
import com.p2p.model.TBorrow;
import com.p2p.model.TFundRecord;
import com.p2p.model.TRepay;
import com.p2p.model.TRepayBorrower;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.util.CalculateInterestUtil;
import com.p2p.util.DateUtil;
import com.p2p.util.NumberUtil;

/** 还款表 **/
@Service
public class TRepayService extends BaseService {

	private static Log log = LogFactory.getLog(TRepayService.class);
	@Autowired
	private TRepayDao tRepayDao;

	@Autowired
	private TBorrowDao tBorrowDao;

	@Autowired
	private TAccountService tAccountService;

	@Autowired
	private CMessageService cMessageService;

	@Autowired
	private TRepayBorrowerDao tRepayBorrowerDao;
	
	@Autowired
	private TInvestAwardService tInvestAwardService;

	public long addTRepay(TRepay tRepay) {
		return tRepayDao.insert(tRepay);
	}

	public long updateTRepay(TRepay tRepay) {
		return tRepayDao.update(tRepay);
	}

	public TRepay getTRepay(TRepay tRepay) {
		return tRepayDao.get(tRepay);
	}

	public PageList queryTRepayPage(TRepay tRepay,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList) tRepayDao.getAllBy(tRepay, options, pageBounds);
	}

	public List<TRepay> queryTRepayListByBorrowIdAndPeriod(Long borrowId,
			Integer numOfPeriods) {
		return tRepayDao.queryTRepayListByBorrowIdAndPeriod(borrowId,
				numOfPeriods);
	}

	public List<TRepay> queryTRepayList(TRepay tRepay) {
		return tRepayDao.queryTRepayList(tRepay);
	}

	/**
	 * 还款
	 * 
	 * @param borrowId
	 * @param numOfPeriods
	 * @return
	 */
	public InfoMsg updateRepay(Long borrowId, Integer numOfPeriods) {

		// 判断是否到期可还款

		List<TRepay> repayList = tRepayDao.queryTRepayListByBorrowIdAndPeriod(
				borrowId, numOfPeriods);
		if (repayList == null || repayList.size() == 0) {
			InfoMsg msg = new InfoMsg("还款失败", "n");
			return msg;
		}

		// 判断借款人是否有足够金额
		TBorrow tempBorrow = new TBorrow();
		tempBorrow.setId(borrowId);

		// 本期还款金额

		TBorrow tBorrow = tBorrowDao.get(tempBorrow);

		if (!tBorrow.getBorrowStatus().equals(BorrowStatus.REPAYING)) {
			InfoMsg msg = new InfoMsg("非还款状态，还款失败", "n");
			return msg;
		}
		// 查询将要还款的金额和利息金额
		TRepayBorrower tRepayBorrower = tRepayBorrowerDao
				.queryTRepayBorrowerByBorrowIdAndPeriods(borrowId, numOfPeriods);

		BigDecimal allCapitalAmount = tRepayBorrower.getCapitalAmount();
		BigDecimal allProfitAmount = tRepayBorrower.getProfitAmount();

		TAccount borrowerAccount = tAccountService.getByUserId(tBorrow
				.getBorrowerId());

		BigDecimal allRepayAmount = allCapitalAmount.add(allProfitAmount);
		if (borrowerAccount.getUsableAmount().compareTo(allRepayAmount) < 0) {
			InfoMsg msg = new InfoMsg("可用余额不足，还款失败", "n");
			return msg;
		}
		// 更新借款人还款
		TRepayBorrower updateRepayBorrower = new TRepayBorrower();
		updateRepayBorrower.setRepayStatus(2);
		updateRepayBorrower.setRealRepayTime(new Date());
		updateRepayBorrower.setId(tRepayBorrower.getId());
		long result = tRepayBorrowerDao
				.updateSuccessRepayBorrower(updateRepayBorrower);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("还款失败！", "n");
			throw new ZeroSQLException(msg);
		}

		TFundRecord borrowRepayFundRecord = new TFundRecord();
		borrowRepayFundRecord.setOperAmount(allRepayAmount);
		String remarks = FundRecordType.BORROW_REPAY.getRemarks();
		remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
		remarks = remarks.replace("#peroid", numOfPeriods.toString());
		remarks = remarks.replace("#allAmount",
				NumberUtil.fomatAmount(allRepayAmount));
		borrowRepayFundRecord.setRemarks(remarks);
		borrowRepayFundRecord.setFundRecordType(FundRecordType.BORROW_REPAY);
		borrowRepayFundRecord.setUserId(tBorrow.getBorrowerId());
		result = tAccountService
				.updateReduceUsableAmount(borrowRepayFundRecord);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("扣除金额失败，还款失败！", "n");
			throw new ZeroSQLException(msg);
		}

		Date nowDate = new Date();
		// 向投资人还款
		for (TRepay tRepay : repayList) {
			BigDecimal capitalAmount = tRepay.getCapitalAmount();
			BigDecimal profitAmount = tRepay.getProfitAmount();
			BigDecimal feeAmount = tRepay.getFeeAmount();
			// 还本金
			TFundRecord capitalFundRecord = new TFundRecord();
			capitalFundRecord.setOperAmount(capitalAmount);
			remarks = FundRecordType.REPAY_CAPITAL.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#peroid", numOfPeriods.toString());
			remarks = remarks.replace("#amount",
					NumberUtil.fomatAmount(capitalAmount));
			capitalFundRecord.setRemarks(remarks);
			capitalFundRecord.setFundRecordType(FundRecordType.REPAY_CAPITAL);
			capitalFundRecord.setTraderId(tBorrow.getBorrowerId());
			capitalFundRecord.setUserId(tRepay.getInvestorId());
			result = tAccountService.updateAddUsableAmount(capitalFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}

			// 还利息
			TFundRecord profitFundRecord = new TFundRecord();
			profitFundRecord.setOperAmount(profitAmount);
			remarks = FundRecordType.REPAY_PROFIT.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#peroid", numOfPeriods.toString());
			remarks = remarks.replace("#amount",
					NumberUtil.fomatAmount(profitAmount));
			profitFundRecord.setRemarks(remarks);
			profitFundRecord.setFundRecordType(FundRecordType.REPAY_PROFIT);
			profitFundRecord.setTraderId(tBorrow.getBorrowerId());
			profitFundRecord.setUserId(tRepay.getInvestorId());
			result = tAccountService.updateAddUsableAmount(profitFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}

			// 扣除投资管理费
			TFundRecord feeFundRecord = new TFundRecord();
			feeFundRecord.setOperAmount(feeAmount);
			remarks = FundRecordType.INVEST_MANAGER_FEE.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#peroid", numOfPeriods.toString());
			remarks = remarks.replace("#amount",
					NumberUtil.fomatAmount(feeAmount));
			feeFundRecord.setRemarks(remarks);
			feeFundRecord.setFundRecordType(FundRecordType.INVEST_MANAGER_FEE);
			feeFundRecord.setUserId(tRepay.getInvestorId());
			result = tAccountService.updateReduceUsableAmount(feeFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}

			// 更新还款状态
			TRepay updateRepay = new TRepay();
			updateRepay.setId(tRepay.getId());
			updateRepay.setRealRepayTime(nowDate);
			updateRepay.setRepayStatus(2);
			result = tRepayDao.update(updateRepay);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}

			// 投资人还款信息
			CMessage cMessage = new CMessage();
			cMessage.setCreateTime(new Date());
			cMessage.setReceiverId(tRepay.getInvestorId());
			InfoTemplate infoTemplate = InfoTemplate.REPAY_SUCCESS;
			cMessage.setMessageTitle(infoTemplate.getSmsType());
			String messageContent = infoTemplate.getContentTemplate();
			messageContent = messageContent.replace("#borrowTitle",
					tBorrow.getBorrowTitle());
			messageContent = messageContent.replace("#period", tRepay
					.getNumOfPeriods().toString());
			messageContent = messageContent.replace("#allAmount",
					NumberUtil.fomatAmount(capitalAmount.add(profitAmount)));

			cMessage.setMessageContent(messageContent);
			result = cMessageService.addCMessage(cMessage);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}
		TRepay queryRepay = new TRepay();
		queryRepay.setBorrowId(borrowId);
		queryRepay.setRepayStatus(2);
		// 统计已还款金额
		TRepay repayed = tRepayDao.queryRepayCapitalProfitAmount(queryRepay);
		if (repayed.getCapitalAmount().compareTo(tBorrow.getBorrowAmount()) == 0) {
			result = tBorrowDao.updateRepayAllBorrow(borrowId);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}

		// 借款人信息通知
		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(tBorrow.getBorrowerId());
		InfoTemplate infoTemplate = InfoTemplate.BORROW_REPAY_SUCCESS;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#borrowTitle",
				tBorrow.getBorrowTitle());
		messageContent = messageContent.replace("#period", numOfPeriods + "");
		messageContent = messageContent.replace("#allAmount",
				NumberUtil.fomatAmount(allRepayAmount));
		cMessage.setMessageContent(messageContent);
		result = cMessageService.addCMessage(cMessage);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("还款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		InfoMsg msg = new InfoMsg("还款成功！", "y");

		return msg;
	}

	/**
	 * 宝付还款
	 * 
	 * @param borrowId
	 * @param numOfPeriods
	 * @return
	 */
	public InfoMsg updateThirdRepay(Long id) {

		TRepayBorrower queryRepayBorrower = new TRepayBorrower();
		queryRepayBorrower.setId(id);
		// 查询将要还款的金额和利息金额
		TRepayBorrower tRepayBorrower = tRepayBorrowerDao
				.get(queryRepayBorrower);

		if (!tRepayBorrower.getRepayStatus().equals(1)) {
			InfoMsg msg = new InfoMsg("重复操作还款", "n");
			return msg;
		}

		// 判断是否到期可还款

		// 判断借款人是否有足够金额
		TBorrow tempBorrow = new TBorrow();
		tempBorrow.setId(tRepayBorrower.getBorrowId());

		// 本期还款金额

		TBorrow tBorrow = tBorrowDao.get(tempBorrow);

		if (!tBorrow.getBorrowStatus().equals(BorrowStatus.REPAYING)) {
			InfoMsg msg = new InfoMsg("非还款状态，还款失败", "n");
			return msg;
		}

		BigDecimal allCapitalAmount = tRepayBorrower.getCapitalAmount();
		BigDecimal allProfitAmount = tRepayBorrower.getProfitAmount();

		TAccount borrowerAccount = tAccountService.getByUserId(tBorrow
				.getBorrowerId());

		BigDecimal allRepayAmount = allCapitalAmount.add(allProfitAmount);
		if (borrowerAccount.getUsableAmount().compareTo(allRepayAmount) < 0) {
			InfoMsg msg = new InfoMsg("可用余额不足，还款失败", "n");
			return msg;
		}

		long result = tRepayBorrowerDao.updateClockRepayBorrower(tRepayBorrower
				.getId());
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("重复操作", "n");
			throw new ZeroSQLException(msg);
		}
		InfoMsg msg = new InfoMsg("还款成功！", "y");

		return msg;
	}

	/**
	 * 宝付还款回调处理
	 * 
	 * @param borrowId
	 * @param numOfPeriods
	 * @return
	 */
	public InfoMsg updateThirdRepayBack(String ordId) {

		// 判断是否到期可还款

		TRepayBorrower tRepayBorrower = tRepayBorrowerDao
				.queryTRepayBorrowerByOrdId(ordId);

		if (!tRepayBorrower.getRepayStatus().equals(3)) {
			InfoMsg msg = new InfoMsg("重复处理", "n");
			return msg;
		}
		Integer numOfPeriods = tRepayBorrower.getNumOfPeriods();
		Long borrowId = tRepayBorrower.getBorrowId();
		// 判断借款人是否有足够金额
		TBorrow tempBorrow = new TBorrow();
		tempBorrow.setId(tRepayBorrower.getBorrowId());

		// 本期还款金额

		TBorrow tBorrow = tBorrowDao.get(tempBorrow);

		if (!tBorrow.getBorrowStatus().equals(BorrowStatus.REPAYING)) {
			InfoMsg msg = new InfoMsg("非还款状态，还款失败", "n");
			return msg;
		}

		// 更新借款人还款
		TRepayBorrower updateRepayBorrower = new TRepayBorrower();
		updateRepayBorrower.setRepayStatus(2);
		updateRepayBorrower.setRealRepayTime(new Date());
		updateRepayBorrower.setId(tRepayBorrower.getId());
		long result = tRepayBorrowerDao
				.updateSuccessRepayBorrower(updateRepayBorrower);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("还款失败！", "n");
			throw new ZeroSQLException(msg);
		}

		// 查询将要还款的金额和利息金额

		BigDecimal allCapitalAmount = tRepayBorrower.getCapitalAmount();
		BigDecimal allProfitAmount = tRepayBorrower.getProfitAmount();

		// TAccount borrowerAccount =
		// tAccountService.getByUserId(tBorrow.getBorrowerId());

		BigDecimal allRepayAmount = allCapitalAmount.add(allProfitAmount);

		// 借款人操作业务
		TFundRecord borrowRepayFundRecord = new TFundRecord();
		borrowRepayFundRecord.setOperAmount(allRepayAmount);
		String remarks = FundRecordType.BORROW_REPAY.getRemarks();
		remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
		remarks = remarks.replace("#peroid", numOfPeriods.toString());
		remarks = remarks.replace("#allAmount",
				NumberUtil.fomatAmount(allRepayAmount));
		borrowRepayFundRecord.setRemarks(remarks);
		borrowRepayFundRecord.setFundRecordType(FundRecordType.BORROW_REPAY);
		borrowRepayFundRecord.setUserId(tBorrow.getBorrowerId());
		result = tAccountService
				.updateReduceUsableAmount(borrowRepayFundRecord);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("扣除金额失败，还款失败！", "n");
			throw new ZeroSQLException(msg);
		}

		Date nowDate = new Date();
		// 向投资人还款

		List<TRepay> repayList = tRepayDao.queryTRepayListByBorrowIdAndPeriod(
				borrowId, numOfPeriods);
		if (repayList == null || repayList.size() == 0) {
			InfoMsg msg = new InfoMsg("还款失败", "n");
			return msg;
		}
		for (TRepay tRepay : repayList) {
			BigDecimal capitalAmount = tRepay.getCapitalAmount();
			BigDecimal profitAmount = tRepay.getProfitAmount();
			BigDecimal feeAmount = tRepay.getFeeAmount();
			// 还本金
			TFundRecord capitalFundRecord = new TFundRecord();
			capitalFundRecord.setOperAmount(capitalAmount);
			remarks = FundRecordType.REPAY_CAPITAL.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#peroid", numOfPeriods.toString());
			remarks = remarks.replace("#amount",
					NumberUtil.fomatAmount(capitalAmount));
			capitalFundRecord.setRemarks(remarks);
			capitalFundRecord.setFundRecordType(FundRecordType.REPAY_CAPITAL);
			capitalFundRecord.setTraderId(tBorrow.getBorrowerId());
			capitalFundRecord.setUserId(tRepay.getInvestorId());
			result = tAccountService.updateAddUsableAmount(capitalFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}

			// 还利息
			TFundRecord profitFundRecord = new TFundRecord();
			profitFundRecord.setOperAmount(profitAmount);
			remarks = FundRecordType.REPAY_PROFIT.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#peroid", numOfPeriods.toString());
			remarks = remarks.replace("#amount",
					NumberUtil.fomatAmount(profitAmount));
			profitFundRecord.setRemarks(remarks);
			profitFundRecord.setFundRecordType(FundRecordType.REPAY_PROFIT);
			profitFundRecord.setTraderId(tBorrow.getBorrowerId());
			profitFundRecord.setUserId(tRepay.getInvestorId());
			result = tAccountService.updateAddUsableAmount(profitFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}

			// 扣除投资管理费
			TFundRecord feeFundRecord = new TFundRecord();
			feeFundRecord.setOperAmount(feeAmount);
			remarks = FundRecordType.INVEST_MANAGER_FEE.getRemarks();
			remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
			remarks = remarks.replace("#peroid", numOfPeriods.toString());
			remarks = remarks.replace("#amount",
					NumberUtil.fomatAmount(feeAmount));
			feeFundRecord.setRemarks(remarks);
			feeFundRecord.setFundRecordType(FundRecordType.INVEST_MANAGER_FEE);
			feeFundRecord.setUserId(tRepay.getInvestorId());
			result = tAccountService.updateReduceUsableAmount(feeFundRecord);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}

			// 更新还款状态
			TRepay updateRepay = new TRepay();
			updateRepay.setId(tRepay.getId());
			updateRepay.setRealRepayTime(nowDate);
			updateRepay.setRepayStatus(2);
			result = tRepayDao.update(updateRepay);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}

			// 投资人还款信息
			CMessage cMessage = new CMessage();
			cMessage.setCreateTime(new Date());
			cMessage.setReceiverId(tRepay.getInvestorId());
			InfoTemplate infoTemplate = InfoTemplate.REPAY_SUCCESS;
			cMessage.setMessageTitle(infoTemplate.getSmsType());
			String messageContent = infoTemplate.getContentTemplate();
			messageContent = messageContent.replace("#borrowTitle",
					tBorrow.getBorrowTitle());
			messageContent = messageContent.replace("#period", tRepay
					.getNumOfPeriods().toString());
			messageContent = messageContent.replace("#allAmount",
					NumberUtil.fomatAmount(capitalAmount.add(profitAmount)));

			cMessage.setMessageContent(messageContent);
			result = cMessageService.addCMessage(cMessage);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}
		TRepayBorrower tRepayBorrowerModel = tRepayBorrowerDao
				.queryTRepayBorrowerByBorrowId(borrowId, 2);

		if (tRepayBorrowerModel.getCapitalAmount().compareTo(
				tBorrow.getBorrowAmount()) == 0) {
			result = tBorrowDao.updateRepayAllBorrow(borrowId);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("还款失败！", "n");
				throw new ZeroSQLException(msg);
			}
		}

		// 借款人信息通知
		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(tBorrow.getBorrowerId());
		InfoTemplate infoTemplate = InfoTemplate.BORROW_REPAY_SUCCESS;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#borrowTitle",
				tBorrow.getBorrowTitle());
		messageContent = messageContent.replace("#period", numOfPeriods + "");
		messageContent = messageContent.replace("#allAmount",
				NumberUtil.fomatAmount(allRepayAmount));
		cMessage.setMessageContent(messageContent);
		result = cMessageService.addCMessage(cMessage);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("还款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		
		//处理奖励
		result = tInvestAwardService.updateTInvestAwardByBorrowId(tRepayBorrower.getBorrowId(),tRepayBorrower.getNumOfPeriods());
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("还款失败！", "n");
			throw new ZeroSQLException(msg);
		}
		InfoMsg msg = new InfoMsg("还款成功！", "y");

		return msg;
	}

	public PageList queryTRepayBorrowPage(RepayDto repayDto,
			PageBounds pageBounds) {
		return (PageList) tRepayDao.queryTRepayBorrowPage(repayDto, pageBounds);
	}

	/**
	 * 查询待还或已还的本息
	 * 
	 * @param investId
	 * @param i
	 */
	public TRepay queryRepayCapitalProfitAmount(TRepay tRepay) {
		return tRepayDao.queryRepayCapitalProfitAmount(tRepay);
	}

	/**
	 * 查询并计算上一个还款日到今天的收益
	 * 
	 * @param investId
	 * @return
	 */
	public BigDecimal queryRepayHavingProfitAmount(Long borrowId, Long investId) {
		Date lastRepayDate = tRepayDao.queryLastRealRepayDate(investId);
		if (lastRepayDate == null) {
			lastRepayDate = tRepayDao.queryFisrtRepayDate(investId);
		}
		if (lastRepayDate == null) {
			return BigDecimal.ZERO;
		}

		TRepay tempRepay = new TRepay();
		tempRepay.setBorrowId(borrowId);
		tempRepay.setInvestId(investId);
		tempRepay.setRepayStatus(1);
		TRepay tRepay = tRepayDao.queryRepayCapitalProfitAmount(tempRepay);
		BigDecimal capitalAmount = tRepay.getCapitalAmount();
		Long days = DateUtil.diffDays(lastRepayDate, new Date());
		if (days <= 0) {
			return BigDecimal.ZERO;
		}
		TBorrow tempBorrow = new TBorrow();
		tempBorrow.setId(borrowId);
		TBorrow tBorrow = tBorrowDao.get(tempBorrow);
		BigDecimal profitAmount = CalculateInterestUtil.calculateOneRepay(
				capitalAmount, days.intValue(), tBorrow.getAnnualRate(), true);
		return profitAmount;
	}

	/**
	 * 
	 * @param investId
	 * @return
	 */
	public Date queryLastRepayDate(Long investId) {
		return tRepayDao.queryLastRepayDate(investId);
	}

	public long updateRepayByClaim(TRepay newRepay) {
		return 0;
	}

	public List<TRepay> queryTRepayAccountListByBorrowIdAndPeriod(
			Long borrowId, Integer numOfPeriods) {
		return tRepayDao.queryTRepayAccountListByBorrowIdAndPeriod(borrowId,
				numOfPeriods);
	}

	public InfoMsg updateThirdRepayResetStatusBack(String ordId) {
		TRepayBorrower tRepayBorrower = tRepayBorrowerDao
				.queryTRepayBorrowerByOrdId(ordId);
		if (!tRepayBorrower.getRepayStatus().equals(3)) {
			InfoMsg msg = new InfoMsg("重复处理", "n");
			return msg;
		}

		// 更新借款人还款
		TRepayBorrower updateRepayBorrower = new TRepayBorrower();
		updateRepayBorrower.setRepayStatus(2);
		updateRepayBorrower.setId(tRepayBorrower.getId());
		long result = tRepayBorrowerDao
				.updateRebackRepayBorrower(updateRepayBorrower);
		
		if (result <=0) {
			InfoMsg msg = new InfoMsg("处理失败", "n");
			return msg;
		}
		InfoMsg msg = new InfoMsg("处理成功", "y");
		return msg;
	}

	public TRepay queryDueinRepayCapitalProfitAmount(Long userId) {
		
		return tRepayDao.queryDueinRepayCapitalProfitAmount(userId);
	}

}