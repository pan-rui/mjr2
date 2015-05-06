package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.CUserDetail;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 用户**/
@Repository
public interface CUserDetailDao extends BaseDao<CUserDetail>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CUserDetail> getAll(CUserDetail cUserDetail,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CUserDetail> getAllBy(CUserDetail cUserDetail, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public CUserDetail get(CUserDetail cUserDetail);

	public CUserDetail getCUserDetailByUserId(Long userId);

}