package com.p2p.web.controller.front;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.TFundRecord;
import com.p2p.service.TFundRecordService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterMoneyFlowController extends BaseController{
	private static Log log = LogFactory.getLog(BorrowController.class);
	
	@Autowired
	private TFundRecordService tFundRecordService;
	
	@RequestMapping(value="/center/moneyFlow")
	public String moneyFlow(){
		TFundRecord tFundRecord = new TFundRecord();
		tFundRecord.setUserId(this.getCUser().getId());
		PageList pageList = tFundRecordService.selectFundRecord(tFundRecord,pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		return "front/usercenter/user-money-flow";
	}
}
