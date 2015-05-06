package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CLinksDao;
import com.p2p.model.CLinks;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class CLinksService extends BaseService {
	private static Log log = LogFactory.getLog(CLinksService.class);
	@Autowired
	private CLinksDao cLinksDao;
	
	public long addCLinks(CLinks cLinks){
		return cLinksDao.insert(cLinks);
	}
	
	public long updateCLinks(CLinks cLinks){
		return cLinksDao.update(cLinks);
	}
	
	public CLinks getCLinks(CLinks cLinks){
		return cLinksDao.get(cLinks);
	}

	public PageList queryCLinksPage(CLinks cLinks,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		
		List<CLinks> list =cLinksDao.getAllBy(cLinks, options, pageBounds);
		PageList  pageList  =(PageList )list;
		
		return pageList;
	}

	
	public List<CLinks> queryIndexLinks(){
		return cLinksDao.queryIndexLinks();
	}
	
}
