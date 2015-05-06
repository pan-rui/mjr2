package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.BAdminActionLogDao;
import com.p2p.model.BAdminActionLog;
import com.p2p.mybatis.annotion.SearchOperator;


/** 管理员操作记录表**/
@Service
public class BAdminActionLogService extends BaseService {


	@Autowired
	private BAdminActionLogDao bAdminActionLogDao;
 
	public long addBAdminActionLog(BAdminActionLog bAdminActionLog) {
		return bAdminActionLogDao.insert(bAdminActionLog);
	}
 
	public long updateBAdminActionLog(BAdminActionLog bAdminActionLog) {
		return bAdminActionLogDao.update(bAdminActionLog);
	}
 
	public BAdminActionLog getBAdminActionLog(BAdminActionLog bAdminActionLog) {
		return bAdminActionLogDao.get(bAdminActionLog);
	}
 
	public PageList queryBAdminActionLogPage(BAdminActionLog bAdminActionLog,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)bAdminActionLogDao.getAllBy(bAdminActionLog, options, pageBounds);
	}
 }