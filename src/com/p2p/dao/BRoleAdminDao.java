package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.BRoleAdmin;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 角色用户表**/
@Repository
public interface BRoleAdminDao extends BaseDao<BRoleAdmin>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<BRoleAdmin> getAll(BRoleAdmin bRoleAdmin,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<BRoleAdmin> getAllBy(BRoleAdmin bRoleAdmin, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public BRoleAdmin get(BRoleAdmin bRoleAdmin);

}