package com.p2p.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CUserLoginRecordDao;
import com.p2p.model.CUserLoginRecord;
import com.p2p.mybatis.annotion.SearchOperator;


/** 用户登录记录表**/
@Service
public class CUserLoginRecordService extends BaseService {

	private static Log log = LogFactory.getLog(CUserLoginRecordService.class);
	@Autowired
	private CUserLoginRecordDao cUserLoginRecordDao;
 
	public long addCUserLoginRecord(CUserLoginRecord cUserLoginRecord) {
		return cUserLoginRecordDao.insert(cUserLoginRecord);
	}
 
	public long updateCUserLoginRecord(CUserLoginRecord cUserLoginRecord) {
		return cUserLoginRecordDao.update(cUserLoginRecord);
	}
 
	public CUserLoginRecord getCUserLoginRecord(CUserLoginRecord cUserLoginRecord) {
		return cUserLoginRecordDao.get(cUserLoginRecord);
	}
 
	public PageList queryCUserLoginRecordPage(CUserLoginRecord cUserLoginRecord,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)cUserLoginRecordDao.getAllBy(cUserLoginRecord, options, pageBounds);
	}
 }