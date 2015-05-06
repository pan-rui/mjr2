package com.p2p.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TAccount;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 账户表**/
@Repository
public interface TAccountDao extends BaseDao<TAccount>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TAccount> getAll(TAccount tAccount,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TAccount> getAllBy(TAccount tAccount, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TAccount get(TAccount tAccount);

	public TAccount getByUserId(Long userId);

	public long updateFrozenAmount(TAccount tAccount);

	public long updateUnfreezeAmount(TAccount tAccount);
	
	public long updateReduceUnfreezeAmount(TAccount tAccount);
	
	public long updateAddUsableAmount(TAccount tAccount);

	public long updateReduceUsableAmount(TAccount tAccount);
	
	/**
	 * 根据用户id查询用户可用金额
	 * @param userId
	 * @return
	 */
	public BigDecimal selectAccount(Long userId);
	
	public int updatemoney(TAccount tAccount);
	
	public TAccount getTAccountforuserid(Long userId);
}