package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.BScore;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 积分**/
@Repository
public interface BScoreDao extends BaseDao<BScore>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<BScore> getAll(BScore bScore,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<BScore> getAllBy(BScore bScore, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public BScore get(BScore bScore);

}