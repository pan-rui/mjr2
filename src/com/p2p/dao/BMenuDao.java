package com.p2p.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.BMenu;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 后台菜单表**/
@Repository
public interface BMenuDao extends BaseDao<BMenu>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<BMenu> getAll(BMenu bMenu,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<BMenu> getAllBy(BMenu bMenu, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public BMenu get(BMenu bMenu);

	public List<BMenu> queryRigthsByAdminId(Long adminId);
	
	public List<BMenu> queryBMenuPage(@Param("bMenu")BMenu bMenu,PageBounds pageBounds);
	
}