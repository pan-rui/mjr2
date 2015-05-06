package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.BArea;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 地区表**/
@Repository
public interface BAreaDao extends BaseDao<BArea>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<BArea> getAll(BArea bArea,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<BArea> getAllBy(BArea bArea, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public BArea get(BArea bArea);

	public List<BArea> queryBAreaList(BArea bArea);

}