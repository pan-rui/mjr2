package com.p2p.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.dto.RepayDto;
import com.p2p.model.TRepay;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 还款表**/
@Repository
public interface TRepayDao extends BaseDao<TRepay>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TRepay> getAll(TRepay tRepay,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TRepay> getAllBy(TRepay tRepay, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TRepay get(TRepay tRepay);

	/**
	 * 根据借款ID和期数查询还款计划
	 * @param borrowId
	 * @param numOfPeriods
	 * @return
	 */
	public List<TRepay> queryTRepayListByBorrowIdAndPeriod(@Param("borrowId")long borrowId,
			@Param("numOfPeriods")int numOfPeriods);

	/**
	 * 查询待还本金，本息，未还本金或本息
	 * @param borrowId
	 * @param numOfPeriods
	 * @return
	 */
	public TRepay queryRepayCapitalProfitAmount(TRepay tRepay);

	public List<RepayDto> queryTRepayBorrowPage(RepayDto repayDto,
			PageBounds pageBounds);

	/**
	 * 查询最后已还款时间
	 * @param investId
	 * @return
	 */
	public Date queryLastRealRepayDate(Long investId);

	/**
	 * 查询开始计息时间
	 * @param investId
	 * @return
	 */
	public Date queryFisrtRepayDate(Long investId);

	/**
	 * 查询最后回款时间
	 * @param investId
	 * @return
	 */
	public Date queryLastRepayDate(Long investId);

	public List<TRepay> queryTRepayList(TRepay tRepay);

	public List<TRepay> queryTRepayAccountListByBorrowIdAndPeriod(
			@Param("borrowId")Long borrowId, @Param("numOfPeriods")Integer numOfPeriods);

	public List<TRepay> queryTRepayListByBorrowIdGroupPeriod(Long id);

	public TRepay queryDueinRepayCapitalProfitAmount(Long userId);


}