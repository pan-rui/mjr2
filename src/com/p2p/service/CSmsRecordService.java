package com.p2p.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CSmsRecordDao;
import com.p2p.model.CSmsRecord;
import com.p2p.mybatis.annotion.SearchOperator;


/** 短信发送记录**/
@Service
public class CSmsRecordService extends BaseService {


	@Autowired
	private CSmsRecordDao cSmsRecordDao;
 
	public long addCSmsRecord(CSmsRecord cSmsRecord) {
		return cSmsRecordDao.insert(cSmsRecord);
	}
 
	public long updateCSmsRecord(CSmsRecord cSmsRecord) {
		return cSmsRecordDao.update(cSmsRecord);
	}
 
	public CSmsRecord getCSmsRecord(CSmsRecord cSmsRecord) {
		return cSmsRecordDao.get(cSmsRecord);
	}
 
	public PageList queryCSmsRecordPage(CSmsRecord cSmsRecord,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)cSmsRecordDao.getAllBy(cSmsRecord, options, pageBounds);
	}

	public CSmsRecord getCSmsRecordByPhone(CSmsRecord csr) {
		return cSmsRecordDao.getCSmsRecordByPhone(csr);
	}
 }