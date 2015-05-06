package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.BAdminActionLog;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 管理员操作记录表**/
@Repository
public interface BAdminActionLogDao extends BaseDao<BAdminActionLog>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<BAdminActionLog> getAll(BAdminActionLog bAdminActionLog,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<BAdminActionLog> getAllBy(BAdminActionLog bAdminActionLog, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public BAdminActionLog get(BAdminActionLog bAdminActionLog);

}