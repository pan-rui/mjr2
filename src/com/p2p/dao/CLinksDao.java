package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.CLinks;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 合作伙伴或链接**/
@Repository
public interface CLinksDao extends BaseDao<CLinks>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CLinks> getAll(CLinks cLinks,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CLinks> getAllBy(CLinks cLinks, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public CLinks get(CLinks cLinks);

	public List<CLinks> queryIndexLinks();

}