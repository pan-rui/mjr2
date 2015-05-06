package com.p2p.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.CSms;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 短信信息表**/
@Repository
public interface CSmsDao extends BaseDao<CSms>{
	/**
	 * 获取短信分页
	 * @param cSms
	 * @param pageBounds
	 * @return
	 */
	public List<CSms> queryCSmsPage(CSms cSms, PageBounds pageBounds);
	
	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CSms> getAll(CSms cSms,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CSms> getAllBy(CSms cSms, Map<String,SearchOperator> options, PageBounds row);
	

	@SelectProvider(type = SQLProvider.class,method = "get")
	public CSms get(CSms cSms);

}