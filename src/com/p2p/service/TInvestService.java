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
import com.p2p.dao.TInvestDao;
import com.p2p.dto.AgreementMsgDto;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.InvestAwardRepayDto;
import com.p2p.dto.TInvestDto;
import com.p2p.enums.FundRecordType;
import com.p2p.enums.InfoTemplate;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.CMessage;
import com.p2p.model.TAccount;
import com.p2p.model.TBorrow;
import com.p2p.model.TFundRecord;
import com.p2p.model.TInvest;
import com.p2p.model.TRepay;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.util.DateUtil;
import com.p2p.util.NumberUtil;
import com.p2p.util.OrdIdUtil;

/** 投资表 **/
@Service
public class TInvestService extends BaseService {

	private static Log log = LogFactory.getLog(TInvestService.class);
	@Autowired
	private TInvestDao tInvestDao;
	@Autowired
	private TBorrowDao tBorrowDao;
	@Autowired
	private TAccountService tAccountService;
	@Autowired
	private CMessageService cMessageService;
	
	@Autowired
	private TThridAccountService tThridAccountService; 
	
	@Autowired
	private CUserService cUserService; 

	/**
	 * 立即投资
	 * 
	 * @param tInvest
	 * @return
	 */
	public InfoMsg addTInvest(TInvest tInvest) {
		// 设置或获取投资信息
		String ordId = OrdIdUtil.generateOrdId(OrdIdUtil.OrdType.INVEST_TYPE);
		Long investorId = tInvest.getInvestorId();
		BigDecimal investAmount = tInvest.getInvestAmount();
		Long borrowId = tInvest.getBorrowId();
		Date nowDate = new Date();
		tInvest.setInvestTime(nowDate);
		tInvest.setOrdDate(nowDate);
		tInvest.setOrdId(ordId);

		TBorrow tempBorrow = new TBorrow();
		tempBorrow.setId(borrowId);

		TBorrow tBorrow = tBorrowDao.get(tempBorrow);

		if (tBorrow.getBorrowerId().equals(tInvest.getInvestorId())) {
			InfoMsg msg = new InfoMsg("借款人不能购买自己的借款项目", "n");
			return msg;
		}
		// 可投标金额
		BigDecimal remainBorrowAmount = tBorrow.getBorrowAmount().subtract(
				tBorrow.getHasBorrowAmount());

		TAccount investorUserAccount = tAccountService.getByUserId(investorId);

		if (investorUserAccount.getUsableAmount().compareTo(investAmount) < 0) {
			InfoMsg msg = new InfoMsg("可用余额不足", "n");
			return msg;
		}

		BigDecimal realAmount = null;
		// 判断可投标金额是否大于投资金额，重新设置实际金额
		if (remainBorrowAmount.compareTo(investAmount) < 0) {
			realAmount = remainBorrowAmount;
		} else {
			realAmount = investAmount;
		}

		tInvest.setRealAmount(realAmount);

		if (tBorrow.getMinInvestAmount() != null
				&& investAmount.compareTo(tBorrow.getMinInvestAmount()) < 0) {
			InfoMsg msg = new InfoMsg("投资金额小于最小单笔投标金额", "n");
			return msg;
		}

		if (tBorrow.getMaxInvestAmount() != null
				&& investAmount.compareTo(tBorrow.getMaxInvestAmount()) > 0) {
			InfoMsg msg = new InfoMsg("投资金额大于最大单笔投标金额", "n");
			return msg;
		}

		// 更新借款募集金额
		TBorrow updateBorrow = new TBorrow();
		updateBorrow.setId(borrowId);
		updateBorrow.setHasBorrowAmount(realAmount);
		long result = tBorrowDao.updateHasBorrowAmount(updateBorrow);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("投资金额大于剩余募集金额", "n");
			throw new ZeroSQLException(msg);
		}

		// 添加投资记录
		tInvest.setResult(1);
		result = tInvestDao.insert(tInvest);

		if (result <= 0) {
			InfoMsg msg = new InfoMsg("投资金额大于剩余募集金额", "n");
			throw new ZeroSQLException(msg);
		}

		// 冻结投资人金额
		TFundRecord investorFundRecord = new TFundRecord();
		investorFundRecord.setOperAmount(realAmount);
		String remarks = FundRecordType.INVEST_FROZEN.getRemarks();
		remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
		remarks = remarks
				.replace("#amount", NumberUtil.fomatAmount(realAmount));
		investorFundRecord.setRemarks(remarks);
		investorFundRecord.setFundRecordType(FundRecordType.INVEST_FROZEN);
		investorFundRecord.setUserId(tInvest.getInvestorId());
		result = tAccountService.updateFrozenAmount(investorFundRecord);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("冻结金额失败", "n");
			throw new ZeroSQLException(msg);
		}

		// 发送投资信息

		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(tInvest.getInvestorId());
		InfoTemplate infoTemplate = InfoTemplate.TENDER_SUCCESS;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#time",
				DateUtil.dateToString(new Date()));
		messageContent = messageContent.replace("#investAmount",
				NumberUtil.fomatAmount(tInvest.getRealAmount()));
		cMessage.setMessageContent(messageContent);
		result = cMessageService.addCMessage(cMessage);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("投标失败！", "n");
			throw new ZeroSQLException(msg);
		}

		// 更新满标状态
		if (tBorrow.getHasBorrowAmount().add(realAmount)
				.compareTo(tBorrow.getBorrowAmount()) == 0) {
			tBorrow.setFullTime(new Date());
			tBorrow.setBorrowStatus(BorrowStatus.AUDITING_TWO);
			result = tBorrowDao.updateFullBorrow(tBorrow);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("投标不成功", "n");
				throw new ZeroSQLException(msg);
			}
		}
	
		
		InfoMsg msg = new InfoMsg("投资成功", "y");
		return msg;
	}

	/**
	 * 立即投资
	 * 
	 * @param tInvest
	 * @return
	 */
	public InfoMsg addThirdTInvest(TInvest tInvest) {
		// 设置或获取投资信息
		String ordId = OrdIdUtil.generateOrdId(OrdIdUtil.OrdType.INVEST_TYPE);
		Date nowDate = new Date();
		tInvest.setInvestTime(nowDate);
		tInvest.setOrdDate(nowDate);
		tInvest.setOrdId(ordId);

		InfoMsg msg = checkTInvest(tInvest);
		if(!"y".equals(msg.getStatus())){
			return msg;
		}

		// 添加投资记录
		tInvest.setResult(0);
		long result = tInvestDao.insert(tInvest);

		if (result <= 0) {
			msg = new InfoMsg("投标失败", "n");
			throw new ZeroSQLException(msg);
		}
		result = tBorrowDao.updateClockAmount(tInvest.getBorrowId(),tInvest.getRealAmount());
		if (result <= 0) {
			msg = new InfoMsg("锁定投标金额失败，请稍后再试", "n");
			throw new ZeroSQLException(msg);
		}
		msg = new InfoMsg("投资成功", "y");
		return msg;
	}
	
	/**
	 * 立即投资检查
	 * 
	 * @param tInvest
	 * @return
	 */
	public InfoMsg checkTInvest(TInvest tInvest) {
		// 设置或获取投资信息
		Long investorId = tInvest.getInvestorId();
		BigDecimal investAmount = tInvest.getInvestAmount();
		Long borrowId = tInvest.getBorrowId();
	

		TBorrow tempBorrow = new TBorrow();
		tempBorrow.setId(borrowId);

		TBorrow tBorrow = tBorrowDao.get(tempBorrow);

		if (tBorrow.getBorrowerId().equals(tInvest.getInvestorId())) {
			InfoMsg msg = new InfoMsg("借款人不能购买自己的借款项目", "n");
			return msg;
		}
		// 可投标金额
		BigDecimal remainBorrowAmount = tBorrow.getBorrowAmount().subtract(
				tBorrow.getHasBorrowAmount());

		TAccount investorUserAccount = tAccountService.getByUserId(investorId);


		BigDecimal realAmount = null;
		// 判断可投标金额是否大于投资金额，重新设置实际金额
		if (remainBorrowAmount.compareTo(investAmount) < 0) {
			realAmount = remainBorrowAmount;
		} else {
			realAmount = investAmount;
		}

		tInvest.setRealAmount(realAmount);
		
		if(tBorrow.getMinInvestAmount() != null && remainBorrowAmount.compareTo(tBorrow.getMinInvestAmount()) <0){
			if(realAmount.compareTo(remainBorrowAmount) != 0){
				InfoMsg msg = new InfoMsg("投资金额只能为"+NumberUtil.fomatAmount(remainBorrowAmount)+"元", "n");
				return msg;
			}
		}else if (tBorrow.getMinInvestAmount() != null
				&& realAmount.compareTo(tBorrow.getMinInvestAmount()) < 0) {
			InfoMsg msg = new InfoMsg("投资金额小于最小单笔投标金额", "n");
			return msg;
		}
		

		if (tBorrow.getMaxInvestAmount() != null && tBorrow.getMaxInvestAmount().compareTo(BigDecimal.ZERO) >0
				&& realAmount.compareTo(tBorrow.getMaxInvestAmount()) > 0) {
			InfoMsg msg = new InfoMsg("投资金额大于最大单笔投标金额", "n");
			return msg;
		}
		

		if (investorUserAccount.getUsableAmount().compareTo(realAmount) < 0) {
			InfoMsg msg = new InfoMsg("可用余额不足", "n");
			return msg;
		}
		InfoMsg msg = new InfoMsg("投资检查通过", "y");
		return msg;
	}

	/**
	 * 投标成功调用
	 * @param ordId
	 * @return
	 */
	public InfoMsg updateThirdSuccessInvest(String ordId,String resultRemarks) {
		TInvest tInvest = tInvestDao.getTInvestByOrdId(ordId);
		if(tInvest==null){
			InfoMsg msg = new InfoMsg("该投资订单ID不存在", "n");
			return msg;
		}
		if(tInvest.getResult().equals(1)){
			InfoMsg msg = new InfoMsg("投标成功", "y");
			return msg;
		}
		if(tInvest.getResult().equals(2)){
			InfoMsg msg = new InfoMsg("投标失败", "n");
			return msg;
		}
		
		long result = tInvestDao.updateInvestResultByOrderId(1,ordId,resultRemarks);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("投标处理失败，请稍后再试", "n");
			throw new ZeroSQLException(msg);
		}
		
		Long borrowId = tInvest.getBorrowId();
		TBorrow tempBorrow = new TBorrow();
		tempBorrow.setId(borrowId);
		BigDecimal realAmount = tInvest.getRealAmount();
		TBorrow tBorrow = tBorrowDao.get(tempBorrow);
		// 更新借款募集金额
		TBorrow updateBorrow = new TBorrow();
		updateBorrow.setId(borrowId);
		updateBorrow.setHasBorrowAmount(realAmount);
		result = tBorrowDao.updateHasBorrowAmount(updateBorrow);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("投资金额大于剩余募集金额", "n");
			throw new ZeroSQLException(msg);
		}
		// 冻结投资人金额
		TFundRecord investorFundRecord = new TFundRecord();
		investorFundRecord.setOperAmount(realAmount);
		String remarks = FundRecordType.INVEST_FROZEN.getRemarks();
		remarks = remarks.replace("#borrowTitle", tBorrow.getBorrowTitle());
		remarks = remarks
				.replace("#amount", NumberUtil.fomatAmount(realAmount));
		investorFundRecord.setRemarks(remarks);
		investorFundRecord.setFundRecordType(FundRecordType.INVEST_FROZEN);
		investorFundRecord.setUserId(tInvest.getInvestorId());
		result = tAccountService.updateFrozenAmount(investorFundRecord);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("冻结金额失败", "n");
			throw new ZeroSQLException(msg);
		}

		// 发送投资信息

		CMessage cMessage = new CMessage();
		cMessage.setCreateTime(new Date());
		cMessage.setReceiverId(tInvest.getInvestorId());
		InfoTemplate infoTemplate = InfoTemplate.TENDER_SUCCESS;
		cMessage.setMessageTitle(infoTemplate.getSmsType());
		String messageContent = infoTemplate.getContentTemplate();
		messageContent = messageContent.replace("#time",
				DateUtil.dateToString(new Date()));
		messageContent = messageContent.replace("#investAmount",
				NumberUtil.fomatAmount(tInvest.getRealAmount()));
		cMessage.setMessageContent(messageContent);
		result = cMessageService.addCMessage(cMessage);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("投标失败！", "n");
			throw new ZeroSQLException(msg);
		}

		// 更新满标状态
		if (tBorrow.getHasBorrowAmount().add(realAmount)
				.compareTo(tBorrow.getBorrowAmount()) == 0) {
			tBorrow.setFullTime(new Date());
			tBorrow.setBorrowStatus(BorrowStatus.AUDITING_TWO);
			result = tBorrowDao.updateFullBorrow(tBorrow);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("投标不成功", "n");
				throw new ZeroSQLException(msg);
			}
		}

		InfoMsg msg = cUserService.updateAwardTimeLevel(tInvest.getInvestorId());
		if(!"y".equals(msg.getStatus())){
			throw new ZeroSQLException(msg);
		}
		msg = new InfoMsg("投资成功，投资金额为"+NumberUtil.fomatShowAmount(tInvest.getRealAmount())+"元。", "y");
		return msg;
	}

	public long updateTInvest(TInvest tInvest) {
		return tInvestDao.update(tInvest);
	}

	public TInvest getTInvest(TInvest tInvest) {
		return tInvestDao.get(tInvest);
	}

	public PageList queryTInvestPage(TInvest tInvest,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList) tInvestDao.getAllBy(tInvest, options, pageBounds);
	}

	public PageList selectTInvestList(TInvestDto tInvestDto,
			PageBounds pageBounds) {
		return (PageList) tInvestDao.selectTInvestList(tInvestDto, pageBounds);
	}



	public PageList selectTInvestListfortj(TInvestDto tInvestDto,PageBounds pageBounds){
		return (PageList)tInvestDao.selectTInvestListfortj(tInvestDto, pageBounds);
	}
	
	public TInvestDto selectTInvestDeatil(Long id){

		return tInvestDao.selectTInvestDeatil(id);
	}
	
	public TRepay selectTrepayMoney(TRepay tRepay){
		return tInvestDao.selectTrepayMoney(tRepay);
	}
	
	public long updateInvestClaim(TInvest updateInvest) {
		return tInvestDao.updateInvestClaim(updateInvest);
	}

	/**
	 * 查询已投标的还款明细
	 * @param id
	 * @return
	 */
	public List<TRepay> selectInvestList(Long id){
		return tInvestDao.selectInvestList(id);
	}

	public List<TInvestDto> queryTInvestAndThirdAccountsListByBorrowId(Long borrowId) {
		return tInvestDao.queryTInvestAndThirdAccountsListByBorrowId(borrowId);
	}

	public TInvest queryTInvestByOrdId(String ordId) {
		return tInvestDao.getTInvestByOrdId(ordId);
	}

	public List<TInvest> queryTInvestList(TInvest tInvest) {
		return tInvestDao.queryTInvestList(tInvest);
	}

	

	/**
	 * 查询15天内的投资额
	 * @param investorId
	 * @return
	 */
	public BigDecimal selectinvestfortime(Long investorId){
		return tInvestDao.selectinvestfortime(investorId);
	}



	public PageList<TInvestDto> queryTInvestListPage(TInvestDto tInvestDto,
			PageBounds pageBounds) {
		return tInvestDao.queryTInvestListPage(tInvestDto,pageBounds);
	}

	public InfoMsg updateThirdFailInvest(String ordId, String remarks) {
		TInvest tInvest = tInvestDao.getTInvestByOrdId(ordId);
		if(tInvest==null){
			InfoMsg msg = new InfoMsg("该投资订单ID不存在", "n");
			return msg;
		}
		if(tInvest.getResult().equals(1)){
			InfoMsg msg = new InfoMsg("投标成功", "y");
			return msg;
		}
		if(tInvest.getResult().equals(2)){
			InfoMsg msg = new InfoMsg("投标失败", "n");
			return msg;
		}
		
		long result = tInvestDao.updateInvestResultByOrderId(2,ordId,remarks);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("投标处理失败，请稍后再试", "n");
			throw new ZeroSQLException(msg);
		}
		InfoMsg msg = new InfoMsg(remarks, "n");
		return msg;
	}
	
	/**
	 * 获取第一条投资额度
	 * @param userId
	 * @return
	 */
	public BigDecimal getFristInvest(long userId){
		return  tInvestDao.getFristInvest(userId);
	}

	public int queryTInvestUndealCountByBorrowId(Long id) {
		// TODO Auto-generated method stub
		return  tInvestDao.queryTInvestUndealCountByBorrowId(id);
	}; 
	
	/**
	 * 查询合同模板里面的内容
	 */
	public AgreementMsgDto selectAgreementMsg(TInvestDto tInvestDto){
		return tInvestDao.selectAgreementMsg(tInvestDto);
	}
	
	public List<InvestAwardRepayDto> queryTInvestAwardListByBorrowId(Long borrowId) {
		return tInvestDao.queryTInvestAwardListByBorrowId(borrowId);
	}; 
	
	public PageList selectInvestListforbid(Long id,PageBounds pageBounds){
		return (PageList) tInvestDao.selectInvestListforbid(id,pageBounds);
	}
 }
