package com.p2p.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CMessageDao;
import com.p2p.dao.CUserDao;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.CMessage;
import com.p2p.model.CSms;
import com.p2p.model.CUser;
import com.p2p.model.TNoticeSetting;
import com.p2p.mybatis.annotion.SearchOperator;


/** 消息表**/
@Service
public class CMessageService extends BaseService {

	private static Log log = LogFactory.getLog(CMessageService.class);
	
	@Autowired
	private CMessageDao cMessageDao;
	
	@Autowired
	private CSmsService cSmsService;
	
	@Autowired
	private TNoticeSettingService tNoticeSettingService;
	
	@Autowired
	private CUserDao cUserDao;
 
	/**
	 * 发送通知
	 * @param cMessage
	 * @return
	 */
	public long addCMessage(CMessage cMessage) {
		Long userId = cMessage.getReceiverId();
		CUser tempUser = new CUser();
		tempUser.setId(userId);
		CUser cUser = cUserDao.get(tempUser);
		TNoticeSetting tNoticeSetting = tNoticeSettingService.getTNoticeSettingByUserId(userId);
		long result = -1;
		if(tNoticeSetting.getIsMessage()==1){
			result =cMessageDao.insert(cMessage);
			if(result<=0){
				throw new ZeroSQLException();
			}
		}
		if(tNoticeSetting.getIsSms()==1){
			CSms cSms = new CSms();
			cSms.setCellPhone(cUser.getCellPhone());
			cSms.setCreateTime(cMessage.getCreateTime());
			cSms.setSendStatus(1);
			cSms.setSmsType(cMessage.getMessageTitle());
			cSms.setUserId(userId);
			cSms.setUserName(cUser.getUserName());
			cSms.setSmsContent(cMessage.getMessageContent());
			result =cSmsService.addCSms(cSms);
			if(result<=0){
				throw new ZeroSQLException();
			}
		}
		if(tNoticeSetting.getIsEmail()==1){
			
		}
		result = 1;
		return result;
	}
 
	public long updateCMessage(CMessage cMessage) {
		return cMessageDao.update(cMessage);
	}
 
	public CMessage getCMessage(CMessage cMessage) {
		return cMessageDao.get(cMessage);
	}
 
	public PageList queryCMessagePage(CMessage cMessage,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)cMessageDao.getAllBy(cMessage, options, pageBounds);
	}
	/**
	 * 分页获取单个用户的站内信
	 * @param cMessage
	 * @param pageBounds
	 * @return
	 */
	public PageList queryOneUserMessage(CMessage cMessage, PageBounds pageBounds){
		return (PageList)cMessageDao.queryOneUserMessage(cMessage, pageBounds);
	}
	
	/**
	 * 根据用户id查询要显示的站内信
	 */
	public CMessage selectAllMsg(CMessage cMessage){
		return cMessageDao.selectAllMsg(cMessage);
	}

 }