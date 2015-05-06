package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.BRoleMenu;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 角色菜单表**/
@Repository
public interface BRoleMenuDao extends BaseDao<BRoleMenu>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<BRoleMenu> getAll(BRoleMenu bRoleMenu,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<BRoleMenu> getAllBy(BRoleMenu bRoleMenu, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public BRoleMenu get(BRoleMenu bRoleMenu);
	
	

}