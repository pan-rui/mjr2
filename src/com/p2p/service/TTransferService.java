package com.p2p.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TTransferDao;
import com.p2p.dto.InfoMsg;
import com.p2p.enums.FundRecordType;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.CMessage;
import com.p2p.model.TCoupon;
import com.p2p.model.TFundRecord;
import com.p2p.model.TInvestAward;
import com.p2p.model.TTransfer;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.util.NumberUtil;

/** 转账 **/
@Service
public class TTransferService extends BaseService {

	@Autowired
	private TTransferDao tTransferDao;

	@Autowired
	private TAccountService tAccountService;
	
	@Autowired
	private TCouponService tCouponService;
	
	
	@Autowired
	private CMessageService cMessageService;

	@Autowired
	private TInvestAwardService tInvestAwardService;

	public long addTTransfer(TTransfer tTransfer) {
		return tTransferDao.insert(tTransfer);
	}

	public long updateTTransfer(TTransfer tTransfer) {
		return tTransferDao.update(tTransfer);
	}

	public TTransfer getTTransfer(TTransfer tTransfer) {
		return tTransferDao.get(tTransfer);
	}

	public PageList queryTTransferPage(TTransfer tTransfer,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList) tTransferDao.getAllBy(tTransfer, options, pageBounds);
	}

	public InfoMsg updateTTransferFail(TTransfer tTransfer) {
		TTransfer tTransferModel = tTransferDao.getTTransferByOrdId(tTransfer
				.getOrdId());
		
		if(tTransferModel == null || !tTransferModel.getTranStatus().equals(1)){
			InfoMsg msg = new InfoMsg("已处理", "y");
			return msg;
		}
		long result = tTransferDao.updateTTransfer(tTransfer);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("处理中或重复处理", "n");
			throw new ZeroSQLException(msg);
		}
		String transType =  tTransferModel.getTransType();
		if("coupon".equals(transType)){
			TCoupon tCoupon = new TCoupon();
			tCoupon.setId(Long.parseLong(tTransferModel.getSeriesNumber()));
			result = tCouponService.updateUserCouponReback(tCoupon);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("处理中", "n");
				throw new ZeroSQLException(msg);
			}
		}else if("investAward".equals(transType)){
			TInvestAward tInvestAward = new TInvestAward();
			tInvestAward.setId(Long.parseLong(tTransferModel.getSeriesNumber()));
			tInvestAward.setStatusRemarks(tTransferModel.getStatusRemarks());
			result =  tInvestAwardService.updateTInvestAwardReback(tInvestAward);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("处理中", "n");
				throw new ZeroSQLException(msg);
			}
		}
		InfoMsg msg = new InfoMsg("处理成功", "y");
		return msg;

	}

	public InfoMsg updateTTransferSuccess(TTransfer tTransfer) {
		TTransfer tTransferModel = tTransferDao.getTTransferByOrdId(tTransfer
				.getOrdId());
		
		if(tTransferModel == null || !tTransferModel.getTranStatus().equals(1)){
			InfoMsg msg = new InfoMsg("已处理", "n");
			return msg;
		}

		long result = tTransferDao.updateTTransfer(tTransfer);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("处理中或重复处理", "n");
			throw new ZeroSQLException(msg);
		}
		
		CMessage cMessage = new CMessage();
		cMessage.setReceiverId(tTransferModel.getTransInId());
		FundRecordType fundType = null;
		String remarks = "";
		String transType =  tTransferModel.getTransType();
		cMessage.setCreateTime(new Date());
		if("coupon".equals(transType)){
			TCoupon tCoupon = new TCoupon();
			tCoupon.setId(Long.parseLong(tTransferModel.getSeriesNumber()));
			tCoupon.setCouponStatus(3);
			result = tCouponService.updateUserCouponSuccessType(tCoupon);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("处理中", "n");
				throw new ZeroSQLException(msg);
			}
			TCoupon tCouponModel = tCouponService.getTCoupon(tCoupon);
			remarks = tCouponModel.getCouponRemarks();
			fundType = FundRecordType.MER_RED_TRANS;
			
			
			cMessage.setMessageTitle("红包");
			cMessage.setMessageContent(remarks);
			cMessage.setMessageType(2);
		}else if("investAward".equals(transType)){
			TInvestAward tInvestAward = new TInvestAward();
			tInvestAward.setId(Long.parseLong(tTransferModel.getSeriesNumber()));
			tInvestAward.setAwardStatus(3);
			tInvestAward.setAwardTime(new Date());
			tInvestAward.setStatusRemarks("处理成功");
			result =  tInvestAwardService.updateTInvestAwardSuccess(tInvestAward);
			if (result <= 0) {
				InfoMsg msg = new InfoMsg("处理中", "n");
				throw new ZeroSQLException(msg);
			}
			TInvestAward tInvestAwardModel = tInvestAwardService.getTInvestAward(tInvestAward);
			if(tInvestAwardModel.getAwardType().equals(1)){
				fundType = FundRecordType.NEW_INVEST_AWARD;
				remarks = fundType.getRemarks();
				remarks = remarks.replaceAll("#borrowTitle", tInvestAwardModel.getBorrowTitle());
				remarks = remarks.replaceAll("#rate", tInvestAwardModel.getAwardRate()+"");
				remarks = remarks.replaceAll("#amount", NumberUtil.fomatShowAmount(tInvestAwardModel.getAwardAmount()));
				cMessage.setMessageTitle("新手奖励");
				cMessage.setMessageContent(remarks);
				cMessage.setMessageType(2);
			}else if(tInvestAwardModel.getAwardType().equals(2)){
				fundType = FundRecordType.ADD_INVEST_AWARD;
				remarks = fundType.getRemarks();
				remarks = remarks.replaceAll("#borrowTitle", tInvestAwardModel.getBorrowTitle());
				remarks = remarks.replaceAll("#rate", tInvestAwardModel.getAwardRate()+"");
				remarks = remarks.replaceAll("#amount", NumberUtil.fomatShowAmount(tInvestAwardModel.getAwardAmount()));
				cMessage.setMessageTitle("奖励加成");
				cMessage.setMessageContent(remarks);
				cMessage.setMessageType(2);
			}else {
				fundType = FundRecordType.REFEREE_INCOME_AWARD;
				remarks = fundType.getRemarks();
				remarks = remarks.replaceAll("#amount", NumberUtil.fomatShowAmount(tInvestAwardModel.getAwardAmount()));
				cMessage.setMessageTitle("推荐奖励收益");
				cMessage.setMessageContent(remarks);
				cMessage.setMessageType(2);
			}
			
		}
		
		Long inUserId = tTransferModel.getTransInId();

		TFundRecord transFundRecord = new TFundRecord();
		transFundRecord.setOperAmount(tTransferModel.getTransAmount());
		
		transFundRecord.setRemarks(tTransferModel.getRemarks());
		transFundRecord.setFundRecordType(fundType);
		transFundRecord.setUserId(inUserId);
		
		result = tAccountService.updateAddUsableAmount(transFundRecord);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("处理失败", "n");
			throw new ZeroSQLException(msg);
		}
		
		result =  cMessageService.addCMessage(cMessage);
		if (result <= 0) {
			InfoMsg msg = new InfoMsg("处理失败", "n");
			throw new ZeroSQLException(msg);
		}
		InfoMsg msg = new InfoMsg("处理成功", "y");
		return msg;

	}

	public List<TTransfer> queryTTransferList(TTransfer tTransfer) {
		return tTransferDao.queryTTransferList(tTransfer);
	}
}