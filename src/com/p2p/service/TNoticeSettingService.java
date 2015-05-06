package com.p2p.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TNoticeSettingDao;
import com.p2p.model.TNoticeSetting;
import com.p2p.mybatis.annotion.SearchOperator;


/** 通知类型设置**/
@Service
public class TNoticeSettingService extends BaseService {


	@Autowired
	private TNoticeSettingDao tNoticeSettingDao;
 
	public long addTNoticeSetting(TNoticeSetting tNoticeSetting) {
		return tNoticeSettingDao.insert(tNoticeSetting);
	}
 
	public long updateTNoticeSetting(TNoticeSetting tNoticeSetting) {
		return tNoticeSettingDao.update(tNoticeSetting);
	}
	
	public long updateTNoticeSettingByUserId(TNoticeSetting tNoticeSetting) {
		TNoticeSetting tempTNoticeSetting = tNoticeSettingDao.getTNoticeSettingByUserId(tNoticeSetting.getUserId());
		tNoticeSetting.setId(tempTNoticeSetting.getId());
		return tNoticeSettingDao.update(tNoticeSetting);
	}
 
	public TNoticeSetting getTNoticeSetting(TNoticeSetting tNoticeSetting) {
		return tNoticeSettingDao.get(tNoticeSetting);
	}
 
	public PageList queryTNoticeSettingPage(TNoticeSetting tNoticeSetting,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tNoticeSettingDao.getAllBy(tNoticeSetting, options, pageBounds);
	}
	/**
	 * 根据用户ID获取通知设置
	 * @param userId
	 * @return
	 */
	public TNoticeSetting getTNoticeSettingByUserId(Long userId) {
		return tNoticeSettingDao.getTNoticeSettingByUserId(userId);
	}
 }