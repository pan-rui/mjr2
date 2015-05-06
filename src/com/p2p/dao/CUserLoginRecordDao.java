package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.CUserLoginRecord;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 用户登录记录表**/
@Repository
public interface CUserLoginRecordDao extends BaseDao<CUserLoginRecord>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CUserLoginRecord> getAll(CUserLoginRecord cUserLoginRecord,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CUserLoginRecord> getAllBy(CUserLoginRecord cUserLoginRecord, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public CUserLoginRecord get(CUserLoginRecord cUserLoginRecord);

}