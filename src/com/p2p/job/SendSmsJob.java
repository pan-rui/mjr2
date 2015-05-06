package com.p2p.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.p2p.service.CMessageService;
import com.p2p.service.CSmsService;

@Component
public class SendSmsJob {
	private static Log log = LogFactory.getLog(SendSmsJob.class);


	@Autowired
	private CSmsService cSmsService;

	public void sendSms() {
		log.info("===发送短信开始===");
		try{
		cSmsService.sendSmses();
		}catch(Exception e){
			log.error("发送短信", e);
		}
		log.info("===发送短信结束===");
	}

	

}
