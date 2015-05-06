package com.p2p.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.TRepayBorrowerDto;
import com.p2p.model.TRepayBorrower;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 借款人总还款表**/
@Repository
public interface TRepayBorrowerDao extends BaseDao<TRepayBorrower>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TRepayBorrower> getAll(TRepayBorrower tRepayBorrower,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TRepayBorrower> getAllBy(TRepayBorrower tRepayBorrower, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TRepayBorrower get(TRepayBorrower tRepayBorrower);

	public TRepayBorrower queryTRepayBorrowerByBorrowIdAndPeriods(
			@Param("borrowId")Long borrowId, @Param("numOfPeriods")Integer numOfPeriods);

	public long updateSuccessRepayBorrower(TRepayBorrower updateRepayBorrower);

	public TRepayBorrower queryTRepayBorrowerByBorrowId(@Param("borrowId")Long borrowId, @Param("repayStatus")int repayStatus);
	
	public long updateClockRepayBorrower(Long id);

	public TRepayBorrower queryTRepayBorrowerByOrdId(String ordId);

	public PageList queryTRepayBorrowerPage(
			TRepayBorrowerDto tRepayBorrowerDto, PageBounds pageBounds);

	public List<TRepayBorrower> queryTRepayBorrowerList(
			TRepayBorrower tRepayBorrower);

	public long updateRebackRepayBorrower(TRepayBorrower updateRepayBorrower);

	public Date queryLastRepayDate(Long id);


}