package com.p2p.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.dto.InvestAwardUserDto;
import com.p2p.model.TInvestAward;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 奖励推荐**/
@Repository
public interface TInvestAwardDao extends BaseDao<TInvestAward>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TInvestAward> getAll(TInvestAward tInvestAward,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TInvestAward> getAllBy(TInvestAward tInvestAward, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TInvestAward get(TInvestAward tInvestAward);

	public Integer queryTInvestAwardByBorrowIdAndPeroidCount(@Param("borrowId")Long borrowId,
			@Param("numOfPeroids")Integer numOfPeroids);

	public Long updateTInvestAwardByBorrowIdAndPeroid(@Param("borrowId")Long borrowId,
			@Param("numOfPeroids")Integer numOfPeroids);
	
	public List<TInvestAward> querySetoutUsersAward();
	
	public Long updateUserAwardType(TInvestAward tInvestAward);
	
	public Long updateUserAwardSuccessType(TInvestAward tInvestAward);
	public long updateTInvestAwardSuccess(TInvestAward tInvestAward);

	public long updateTInvestAwardReback(TInvestAward tInvestAward);
	
	public List<InvestAwardUserDto> selectInvestAward(InvestAwardUserDto investAwardUserDto,PageBounds pageBounds);

}