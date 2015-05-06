package com.p2p.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.p2p.dao.CSmsDao;
import com.p2p.model.CSms;
import com.p2p.util.SMSUtil;


/** 短信信息表**/
@Service
public class CSmsService extends BaseService {

	private static Log log = LogFactory.getLog(CSmsService.class);
	@Autowired
	private CSmsDao cSmsDao;
 
	public long addCSms(CSms cSms) {
		cSms.setCreateTime(new Date());
		return cSmsDao.insert(cSms);
	}
 
	public long updateCSms(CSms cSms) {
		return cSmsDao.update(cSms);
	}
 
	public CSms getCSms(CSms cSms) {
		return cSmsDao.get(cSms);
	}
 
	public PageList queryCSmsPage(CSms cSms, PageBounds pageBounds) {
		return (PageList)cSmsDao.queryCSmsPage(cSms, pageBounds);
	}

	public void sendSmses() {
		CSms cSms = new CSms();
		cSms.setSendStatus(1);
		PageBounds pageBounds = new PageBounds(1, 50);
		PageList<CSms> smsList = (PageList<CSms>)cSmsDao.queryCSmsPage(cSms, pageBounds);
		Paginator page = smsList.getPaginator();
		
		while(page.getNextPage() >= page.getPage()){
			for(CSms tempSms : smsList){
				boolean isSend = SMSUtil.sendSms(tempSms.getCellPhone(), tempSms.getSmsContent());
				if(isSend){
					CSms updateCsms = new CSms();
					updateCsms.setId(tempSms.getId());
					updateCsms.setSendStatus(2);
					cSmsDao.update(updateCsms);
				}
			}
			if(page.getNextPage()==page.getPage()){
				break;
			}
			pageBounds = new PageBounds(page.getNextPage(), 50);
			smsList = (PageList<CSms>)cSmsDao.queryCSmsPage(cSms, pageBounds);
			page = smsList.getPaginator();
		}
		log.info("page========"+page);
	}

	
 }