package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TThridAccount;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 第三方托管账户**/
@Repository
public interface TThridAccountDao extends BaseDao<TThridAccount>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TThridAccount> getAll(TThridAccount tThridAccount,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TThridAccount> getAllBy(TThridAccount tThridAccount, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TThridAccount get(TThridAccount tThridAccount);

	/**
	 * 根据用户ID获取第三方托管帐号
	 * @param userId
	 * @return
	 */
	public TThridAccount getThridAccountByUserId(Long userId);
	
	/**
	 * 根据用户id，修改托管账号开通结果
	 * @param tThridAccount
	 * @return
	 */
	public Long updateThridOpenOrNo(TThridAccount tThridAccount);
	/**
	 * 根据用户id，修改托管账号授权结果
	 * @param tThridAccount
	 * @return
	 */
	public Long updateThridIsAccredit(TThridAccount tThridAccount);

	public TThridAccount getThridAccountByThirdUserId(String user_id);

}