package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CBannerDao;
import com.p2p.model.CBanner;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class CBannerService extends BaseService {
	private static Log log = LogFactory.getLog(CBannerService.class);
	@Autowired
	private CBannerDao cBannerDao;
	
	public long addCBanner(CBanner cBanner){
		return cBannerDao.insert(cBanner);
	}
	
	public long updateCBanner(CBanner cBanner){
		return cBannerDao.update(cBanner);
	}
	
	public CBanner getCBanner(CBanner cBanner){
		return cBannerDao.get(cBanner);
	}

	public PageList queryCBannerPage(CBanner cBanner,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		
		List<CBanner> list =cBannerDao.getAllBy(cBanner, options, pageBounds);
		PageList  pageList  =(PageList )list;
		
		return pageList;
	}

	public List<CBanner> queryCBannerList() {
		List<CBanner> list =cBannerDao.queryIndexBanner();
		return list;
	}

	
	
	
}
