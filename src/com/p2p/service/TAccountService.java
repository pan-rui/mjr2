package com.p2p.service;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TAccountDao;
import com.p2p.dto.InfoMsg;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.TAccount;
import com.p2p.model.TFundRecord;
import com.p2p.mybatis.annotion.SearchOperator;


/** 账户表**/
@Service
public class TAccountService extends BaseService {

	private static Log log = LogFactory.getLog(TAccountService.class);
	@Autowired
	private TAccountDao tAccountDao;
	@Autowired
	private TFundRecordService tFundRecordService;
	
	public long addTAccount(TAccount tAccount) {
		return tAccountDao.insert(tAccount);
	}
 
	public long updateTAccount(TAccount tAccount) {
		return tAccountDao.update(tAccount);
	}
 
	public TAccount getTAccount(TAccount tAccount) {
		return tAccountDao.get(tAccount);
	}
	
	public TAccount getTAccountforuserid(Long userId){
		return tAccountDao.getTAccountforuserid(userId);
	}
 
	public PageList queryTAccountPage(TAccount tAccount,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tAccountDao.getAllBy(tAccount, options, pageBounds);
	}

	public TAccount getByUserId(Long userId) {
		return tAccountDao.getByUserId(userId);
	}
	
	/**
	 * 冻结金额
	 * @param tAccount
	 * @param tFundRecord
	 * @return
	 */
	public long updateFrozenAmount(TFundRecord tFundRecord){
		long result = -1;
		if(BigDecimal.ZERO.compareTo(tFundRecord.getOperAmount())==0){
			return 1;
		}
		TAccount tAccount = new TAccount();
		tAccount.setFrozenAmount(tFundRecord.getOperAmount());
		tAccount.setUserId(tFundRecord.getUserId());
		result = tAccountDao.updateFrozenAmount(tAccount);
		if(result<=0){
			InfoMsg msg = new InfoMsg("可用余额不足", "n");
			throw new ZeroSQLException(msg);
		}
		
		result = tFundRecordService.addTFundRecord(tFundRecord);
		if(result<=0){
			throw new ZeroSQLException();
		}
		return result;
	}
	
	/**
	 * 解冻金额（并添加可用余额）
	 * @param tAccount
	 * @param tFundRecord
	 * @return
	 */
	public long updateUnfreezeAmount(TFundRecord tFundRecord){
		long result = -1;
		if(BigDecimal.ZERO.compareTo(tFundRecord.getOperAmount())==0){
			return 1;
		}
		TAccount tAccount = new TAccount();
		tAccount.setFrozenAmount(tFundRecord.getOperAmount());
		tAccount.setUserId(tFundRecord.getUserId());
		result = tAccountDao.updateUnfreezeAmount(tAccount);
		if(result<=0){
			InfoMsg msg = new InfoMsg("可用余额不足", "n");
			throw new ZeroSQLException(msg);
		}
		
		result = tFundRecordService.addTFundRecord(tFundRecord);
		if(result<=0){
			throw new ZeroSQLException();
		}
		return result;
	}
	
	/**
	 * 减少冻结金额（不添加可用余额）
	 * @param tAccount
	 * @param tFundRecord
	 * @return
	 */
	public long updateReduceUnfreezeAmount(TFundRecord tFundRecord){
		long result = -1;
		if(BigDecimal.ZERO.compareTo(tFundRecord.getOperAmount())==0){
			return 1;
		}
		TAccount tAccount = new TAccount();
		tAccount.setFrozenAmount(tFundRecord.getOperAmount());
		tAccount.setUserId(tFundRecord.getUserId());
		result = tAccountDao.updateReduceUnfreezeAmount(tAccount);
		if(result<=0){
			InfoMsg msg = new InfoMsg("可用余额不足", "n");
			throw new ZeroSQLException(msg);
		}
		
		result = tFundRecordService.addTFundRecord(tFundRecord);
		if(result<=0){
			throw new ZeroSQLException();
		}
		return result;
	}
	
	/**
	 * 添加可用金额
	 * @param tAccount
	 * @param tFundRecord
	 * @return
	 */
	public long updateAddUsableAmount(TFundRecord tFundRecord){
		long result = -1;
		if(BigDecimal.ZERO.compareTo(tFundRecord.getOperAmount())==0){
			return 1;
		}
		TAccount tAccount = new TAccount();
		tAccount.setUsableAmount(tFundRecord.getOperAmount());
		tAccount.setUserId(tFundRecord.getUserId());
		result = tAccountDao.updateAddUsableAmount(tAccount);
		if(result<=0){
			InfoMsg msg = new InfoMsg("可用余额不足", "n");
			throw new ZeroSQLException(msg);
		}
		
		result = tFundRecordService.addTFundRecord(tFundRecord);
		if(result<=0){
			throw new ZeroSQLException();
		}
		return result;
	}
	
	/**
	 * 减少可用金额
	 * @param tAccount
	 * @param tFundRecord
	 * @return
	 */
	public long updateReduceUsableAmount(TFundRecord tFundRecord){
		long result = -1;
		if(BigDecimal.ZERO.compareTo(tFundRecord.getOperAmount())==0){
			return 1;
		}
		TAccount tAccount = new TAccount();
		tAccount.setUsableAmount(tFundRecord.getOperAmount());
		tAccount.setUserId(tFundRecord.getUserId());
		result = tAccountDao.updateReduceUsableAmount(tAccount);
		if(result<=0){
			InfoMsg msg = new InfoMsg("可用余额不足", "n");
			throw new ZeroSQLException(msg);
		}
		
		result = tFundRecordService.addTFundRecord(tFundRecord);
		if(result<=0){
			throw new ZeroSQLException();
		}
		return result;
	}
 }