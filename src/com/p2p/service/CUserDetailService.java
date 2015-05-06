package com.p2p.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CUserDetailDao;
import com.p2p.model.CUserDetail;
import com.p2p.mybatis.annotion.SearchOperator;


/** 用户**/
@Service
public class CUserDetailService extends BaseService {

	private static Log log = LogFactory.getLog(CUserDetailService.class);
	@Autowired
	private CUserDetailDao cUserDetailDao;
 
	public long addCUserDetail(CUserDetail cUserDetail) {
		return cUserDetailDao.insert(cUserDetail);
	}
 
	public long updateCUserDetail(CUserDetail cUserDetail) {
		return cUserDetailDao.update(cUserDetail);
	}
 
	public CUserDetail getCUserDetail(CUserDetail cUserDetail) {
		return cUserDetailDao.get(cUserDetail);
	}
 
	public PageList queryCUserDetailPage(CUserDetail cUserDetail,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)cUserDetailDao.getAllBy(cUserDetail, options, pageBounds);
	}

	public CUserDetail getCUserDetailByUserId(Long userId) {
		return cUserDetailDao.getCUserDetailByUserId(userId);
	}
 }