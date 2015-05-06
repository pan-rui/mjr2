package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.BAreaDao;
import com.p2p.model.BArea;
import com.p2p.mybatis.annotion.SearchOperator;


/** 地区表**/
@Service
public class BAreaService extends BaseService {

	private static Log log = LogFactory.getLog(BAreaService.class);
	@Autowired
	private BAreaDao bAreaDao;
 
	public long addBArea(BArea bArea) {
		return bAreaDao.insert(bArea);
	}
 
	public long updateBArea(BArea bArea) {
		return bAreaDao.update(bArea);
	}
 
	public BArea getBArea(BArea bArea) {
		return bAreaDao.get(bArea);
	}
 
	public PageList queryBAreaPage(BArea bArea,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)bAreaDao.getAllBy(bArea, options, pageBounds);
	}

	public List<BArea> queryBAreaList(BArea bArea) {
		return bAreaDao.queryBAreaList(bArea);
	}
 }