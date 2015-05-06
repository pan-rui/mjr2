package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.CHelp;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 帮助**/
@Repository
public interface CHelpDao extends BaseDao<CHelp>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CHelp> getAll(CHelp cHelp,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CHelp> getAllBy(CHelp cHelp, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public CHelp get(CHelp cHelp);
	/**
	 * 根据帮助中心类型，或许类型下所有帮助列表
	 * @param helpTypeId
	 * @param pageBounds 
	 * @return
	 */
	public PageList queryHelpByType(long helpTypeId, PageBounds pageBounds);
}