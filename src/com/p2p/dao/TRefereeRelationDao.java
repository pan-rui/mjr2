package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.dto.RefereeInvestDto;
import com.p2p.dto.TRefereeDto;
import com.p2p.dto.TRefereeRelationDto;
import com.p2p.model.TRefereeRelation;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 推荐关系表**/
@Repository
public interface TRefereeRelationDao extends BaseDao<TRefereeRelation>{
	
	/**
	 * 分页取值，推荐关系
	 * @param cSms
	 * @param pageBounds
	 * @return
	 */
	public List<TRefereeRelationDto> queryRefereeUserAndTUser(TRefereeRelationDto trr, PageBounds pageBounds);
	public List<TRefereeRelationDto> queryRefereeUserAndTUser2(TRefereeRelationDto trr, PageBounds pageBounds);
	
	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TRefereeRelation> getAll(TRefereeRelation tRefereeRelation,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TRefereeRelation> getAllBy(TRefereeRelation tRefereeRelation, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TRefereeRelation get(TRefereeRelation tRefereeRelation);
	
	public List<TRefereeDto> selectRefereelist();
	
	public int updateReferee(Long userId);
	
	public Long selectrefereeIdforuserId(Long userId);
	public List<RefereeInvestDto> queryRefereeInvestListByBorrowId(Long borrowId);
}