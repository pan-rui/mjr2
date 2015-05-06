package com.p2p.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TThridAccountDao;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.TThridAccount;
import com.p2p.mybatis.annotion.SearchOperator;


/** 第三方托管账户**/
@Service
public class TThridAccountService extends BaseService {
	private static Log log = LogFactory.getLog(TPersonService.class);

	@Autowired
	private TThridAccountDao tThridAccountDao;
 
	public long addTThridAccount(TThridAccount tThridAccount) {
		return tThridAccountDao.insert(tThridAccount);
	}
 
	public long updateTThridAccount(TThridAccount tThridAccount) {
		return tThridAccountDao.update(tThridAccount);
	}
 
	/**
	 * 根据用户ID获取第三方托管帐号
	 * @param userId
	 * @return
	 */
	public TThridAccount getThridAccountByUserId(Long userId) {
		return tThridAccountDao.getThridAccountByUserId(userId);
	}
	/**
	 * 根据用户id，修改托管账号开通结果
	 * @param tThridAccount
	 * @return
	 */
	public Long updateThridOpenOrNo(TThridAccount tThridAccount){
		return tThridAccountDao.updateThridOpenOrNo(tThridAccount);
	};
	/**
	 * 根据第三方授权结果，对应修改
	 * @param yesOrNo
	 * @param thirdUserId
	 * @return
	 */
	public long updateThridIsAccredit(int isAccredit,String thirdUserId){
		long rest =-1;
		TThridAccount tta = new TThridAccount();
		TThridAccount tThridAccount = tThridAccountDao.getThridAccountByThirdUserId(thirdUserId);
		if(tThridAccount==null){
			log.error("用户id不存在："+thirdUserId);
			return rest;
		}
		tta.setUserId(tThridAccount.getUserId());
		tta.setIsAccredit(isAccredit);
		rest = tThridAccountDao.updateThridIsAccredit(tta);
		if(rest <=0){
			throw new ZeroSQLException();
		}
		return rest;
	}
	
	public TThridAccount getTThridAccount(TThridAccount tThridAccount) {
		return tThridAccountDao.get(tThridAccount);
	}
 
	public PageList queryTThridAccountPage(TThridAccount tThridAccount,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tThridAccountDao.getAllBy(tThridAccount, options, pageBounds);
	}
 }