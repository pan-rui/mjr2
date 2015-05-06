package com.p2p.web.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.TFundRecord;
import com.p2p.service.TFundRecordService;
import com.p2p.web.controller.BaseController;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterFundRecordController extends BaseController{
	private static Log log=LogFactory.getLog(CenterFundRecordController.class);
	
	@Autowired
	private TFundRecordService centerFundRecordService;
	
	@RequestMapping(value="center/selectFundRecord")
	public String selectFundRecord(TFundRecord tFundRecord){
		PageList pagelist=centerFundRecordService.selectFundRecord(tFundRecord, pageBounds);
		request.setAttribute("bean", pagelist);
		request.setAttribute("page", pagelist.getPaginator());
		request.setAttribute("model", tFundRecord);
		return "front/usercenter/fundrecord-list";
	}
}
