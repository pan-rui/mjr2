package com.p2p.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.AgreementMsgDto;
import com.p2p.dto.InvestAwardRepayDto;
import com.p2p.dto.TInvestDto;
import com.p2p.model.TInvest;
import com.p2p.model.TRepay;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 投资表**/
@Repository
public interface TInvestDao extends BaseDao<TInvest>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TInvest> getAll(TInvest tInvest,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TInvest> getAllBy(TInvest tInvest, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TInvest get(TInvest tInvest);

	public List<TInvest> queryTInvestByBorrowId(Long id);
	
	public List<TInvestDto> selectTInvestList(TInvestDto tInvestDto,PageBounds pageBounds);
	public List<TInvestDto> selectTInvestListfortj(TInvestDto tInvestDto,PageBounds pageBounds);
	
	public TInvestDto selectTInvestDeatil(Long id);

	public long updateInvestClaim(TInvest updateInvest);

	public TInvest getTInvestByOrdId(String ordId);

	public long updateInvestResultByOrderId(@Param("result")Integer result,@Param("ordId")String ordId,@Param("remarks")String remarks);
	
	/**
	 * 查询已投标的还款明细
	 * @param id
	 * @return
	 */
	public List<TRepay> selectInvestList(Long id);

	public List<TInvestDto> queryTInvestAndThirdAccountsListByBorrowId(Long borrowId);

	public TInvest queryTInvestByOrdId(String ordId);

	public List<TInvest> queryTInvestList(TInvest tInvest);
	/**
	 * 根据用户id 统计用户投资次数
	 * @param userId
	 * @return
	 */
	public Long getInvestCounts(long userId);
	/**
	 * 获取第一条投资额度
	 * @param userId
	 * @return
	 */
	public BigDecimal getFristInvest(long userId); 
	
	/**
	 * 查询15天内的投资额
	 * @param investorId
	 * @return
	 */
	public BigDecimal selectinvestfortime(Long investorId);

	public PageList<TInvestDto> queryTInvestListPage(TInvestDto tInvestDto,
			PageBounds pageBounds);
	
	public TRepay selectTrepayMoney(TRepay tRepay);

	public int queryTInvestUndealCountByBorrowId(Long id);
	
	/**
	 * 查询合同模板里面的内容
	 */
	public AgreementMsgDto selectAgreementMsg(TInvestDto tInvestDto);

	public List<InvestAwardRepayDto> queryTInvestAwardListByBorrowId(Long borrowId);
	
	public List<TInvestDto> selectInvestListforbid(Long borrowId,PageBounds pageBounds);
}