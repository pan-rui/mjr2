package com.p2p.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.BScoreDao;
import com.p2p.model.BScore;
import com.p2p.mybatis.annotion.SearchOperator;


/** 积分**/
@Service
public class BScoreService extends BaseService {

	private static Log log = LogFactory.getLog(BScoreService.class);
	@Autowired
	private BScoreDao bScoreDao;
 
	public long addBScore(BScore bScore) {
		return bScoreDao.insert(bScore);
	}
 
	public long updateBScore(BScore bScore) {
		return bScoreDao.update(bScore);
	}
 
	public BScore getBScore(BScore bScore) {
		return bScoreDao.get(bScore);
	}
 
	public PageList queryBScorePage(BScore bScore,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)bScoreDao.getAllBy(bScore, options, pageBounds);
	}
 }