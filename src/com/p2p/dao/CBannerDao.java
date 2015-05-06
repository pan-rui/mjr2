package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.CBanner;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** banner表**/
@Repository
public interface CBannerDao extends BaseDao<CBanner>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CBanner> getAll(CBanner cBanner,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CBanner> getAllBy(CBanner cBanner, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public CBanner get(CBanner cBanner);
	/**
	 * 获取首页的banner
	 * @return
	 */
	public List<CBanner> queryIndexBanner();
	
}