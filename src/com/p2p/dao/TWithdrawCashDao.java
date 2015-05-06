package com.p2p.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.dto.TWithdrawCashDto;
import com.p2p.model.TWithdrawCash;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 提现金额**/
@Repository
public interface TWithdrawCashDao extends BaseDao<TWithdrawCash>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TWithdrawCash> getAll(TWithdrawCash tWithdrawCash,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TWithdrawCash> getAllBy(TWithdrawCash tWithdrawCash, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TWithdrawCash get(TWithdrawCash tWithdrawCash);
	
	public List<TWithdrawCashDto> selectTWithDrawCashDtoList(TWithdrawCashDto twithdrawCashDto,PageBounds pageBounds);
	
	public TWithdrawCashDto selectTWithDrawCashDtoDeatil(Long id);
	
	/**
	 * 个人中心提现记录
	 * @param twithdrawcash
	 * @param pagebounds
	 * @return
	 */
	public List<TWithdrawCash> selectWithdraw(TWithdrawCash twithdrawcash,PageBounds pagebounds);
	
	public TWithdrawCash insertWithdraw(TWithdrawCash tWithdrawCash);
	
	public TWithdrawCash selectwithdrawforordid(String ordId);
	
	public int updatewithdrawtwo(String ordId);
	public int updatewithdrawthree(String ordId);
	public int updatewithdrawfour(String ordId);
	
	public List<TWithdrawCash> selectWithdrawList(TWithdrawCash tWithdrawCash);
	
	public BigDecimal selecttwithdrwafortime(Long userId);
	public TWithdrawCash selectUserIdforOrdid(String ordId);
	
	public int updateonetofour(String ordId);
}