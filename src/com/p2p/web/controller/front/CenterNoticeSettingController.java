package com.p2p.web.controller.front;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.dto.InfoMsg;
import com.p2p.model.TNoticeSetting;
import com.p2p.service.TNoticeSettingService;
import com.p2p.web.controller.BaseController;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;
@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterNoticeSettingController extends BaseController{
	private static Log log=LogFactory.getLog(CenterNoticeSettingController.class);
	
	@Autowired
	private TNoticeSettingService tNoticeSettingService;
	
	@RequestMapping(value="/center/noticeSettingIndex")
	public String queryNoticeSettingIndex(){
		TNoticeSetting tNoticeSetting = tNoticeSettingService.getTNoticeSettingByUserId(this.getCUser().getId());
		request.setAttribute("tNoticeSetting", tNoticeSetting);
		return "front/usercenter/notice-setting";
	}
	
	@RequestMapping(value="/center/updateNoticeSetting")
	public String updateNoticeSetting(TNoticeSetting tNoticeSetting){
		tNoticeSetting.setUserId(this.getCUser().getId());
		tNoticeSetting.setLastUpdateTime(new Date());
		long result = tNoticeSettingService.updateTNoticeSettingByUserId(tNoticeSetting);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
}
