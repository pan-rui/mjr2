package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.CHelpType;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 帮助类型**/
@Repository
public interface CHelpTypeDao extends BaseDao<CHelpType>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CHelpType> getAll(CHelpType cHelpType,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CHelpType> getAllBy(CHelpType cHelpType, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public CHelpType get(CHelpType cHelpType);

	@SelectProvider(type = SQLProvider.class,method = "getAllByCondition")
	public List<CHelpType> getAllByCondition(CHelpType cHelpType);
	/**
	 * 顺序获取所有帮助类型
	 * @return
	 */
	public List<CHelpType> queryAllType();
}