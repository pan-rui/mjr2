package com.p2p.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.BUserActionLogDao;
import com.p2p.model.BUserActionLog;
import com.p2p.mybatis.annotion.SearchOperator;


/** 用户操作记录表**/
@Service
public class BUserActionLogService extends BaseService {

	private static Log log = LogFactory.getLog(BUserActionLogService.class);
	@Autowired
	private BUserActionLogDao bUserActionLogDao;
 
	public long addBUserActionLog(BUserActionLog bUserActionLog) {
		return bUserActionLogDao.insert(bUserActionLog);
	}
 
	public long updateBUserActionLog(BUserActionLog bUserActionLog) {
		return bUserActionLogDao.update(bUserActionLog);
	}
 
	public BUserActionLog getBUserActionLog(BUserActionLog bUserActionLog) {
		return bUserActionLogDao.get(bUserActionLog);
	}
 
	public PageList queryBUserActionLogPage(BUserActionLog bUserActionLog,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)bUserActionLogDao.getAllBy(bUserActionLog, options, pageBounds);
	}
 }