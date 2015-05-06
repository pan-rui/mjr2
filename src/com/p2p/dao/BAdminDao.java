package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.BAdmin;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 管理员表**/
@Repository
public interface BAdminDao extends BaseDao<BAdmin> {

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<BAdmin> getAll(BAdmin bAdmin,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<BAdmin> getAllBy(BAdmin bAdmin, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public BAdmin get(BAdmin bAdmin);
	
	@SelectProvider(type = SQLProvider.class,method = "getAllByCondition")
	public List<BAdmin> getAllByCondition(BAdmin bAdmin);


}