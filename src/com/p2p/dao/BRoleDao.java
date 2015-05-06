package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.BRole;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 角色表**/
@Repository
public interface BRoleDao extends BaseDao<BRole>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<BRole> getAll(BRole bRole,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<BRole> getAllBy(BRole bRole, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public BRole get(BRole bRole);

}