package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TNoticeSetting;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 通知类型设置**/
@Repository
public interface TNoticeSettingDao extends BaseDao<TNoticeSetting>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TNoticeSetting> getAll(TNoticeSetting tNoticeSetting,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TNoticeSetting> getAllBy(TNoticeSetting tNoticeSetting, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TNoticeSetting get(TNoticeSetting tNoticeSetting);

	public TNoticeSetting getTNoticeSettingByUserId(Long userId);

}