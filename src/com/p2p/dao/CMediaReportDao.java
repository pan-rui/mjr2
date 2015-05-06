package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.CMediaReport;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 媒体报道**/
@Repository
public interface CMediaReportDao extends BaseDao<CMediaReport>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<CMediaReport> getAll(CMediaReport cMediaReport,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<CMediaReport> getAllBy(CMediaReport cMediaReport, Map<String,SearchOperator> options, PageBounds row);

	public List<CMediaReport> getAllMedia(CMediaReport cMediaReport, PageBounds row);
	
	public List<Long> getAllId();
	
	@SelectProvider(type = SQLProvider.class,method = "get")
	public CMediaReport get(CMediaReport cMediaReport);

	public List<CMediaReport> queryIndexNews();

}