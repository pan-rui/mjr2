package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.BUserActionLog;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 用户操作记录表**/
@Repository
public interface BUserActionLogDao extends BaseDao<BUserActionLog>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<BUserActionLog> getAll(BUserActionLog bUserActionLog,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<BUserActionLog> getAllBy(BUserActionLog bUserActionLog, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public BUserActionLog get(BUserActionLog bUserActionLog);

}