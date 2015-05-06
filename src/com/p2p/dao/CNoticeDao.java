package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.CMediaReport;
import com.p2p.model.CNotice;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 公告**/
@Repository
public interface CNoticeDao extends BaseDao<CNotice>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CNotice> getAll(CNotice cNotice,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CNotice> getAllBy(CNotice cNotice, Map<String,SearchOperator> options, PageBounds row);

	public List<CNotice> getAllNotice(CNotice cNotice, PageBounds row);
	
	public List<Long> getAllId();

	@SelectProvider(type = SQLProvider.class,method = "get")
	public CNotice get(CNotice cNotice);

	public List<CNotice> getIndexNotice();

}