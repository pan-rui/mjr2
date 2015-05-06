package com.p2p.web.controller.front;


import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.TAccount;
import com.p2p.model.TRecharge;
import com.p2p.service.CUserService;
import com.p2p.service.TRechargeService;
import com.p2p.util.DateUtil;
import com.p2p.web.controller.BaseController;
@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterRechargeRecordController extends BaseController{
	
	private static Log log = LogFactory.getLog(CenterRechargeRecordController.class);
	@Autowired
	private TRechargeService ctrecrecService;
	@Autowired
	private CUserService cUserService;
	
	@RequestMapping(value= "/ggg" )
	public String ggg(){
		return "front/usercenter/qqq";
	}
	
	@RequestMapping(value= "center/selectRcgRcdList" )
	public String selectRcgRcdList(TRecharge tRecharge){
		tRecharge.setUserId(this.getCUser().getId());
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		int days = tRecharge.getDays();
		if(days!=0){
			tRecharge.setCreateTime(DateUtil.dateAddDay(calendar.getTime(), -days));
		}
		
		PageList pagelist=(PageList)ctrecrecService.selectTRechargeList(tRecharge,pageBounds);
		request.setAttribute("pageBean", pagelist);
		request.setAttribute("aChacked", request.getParameter("achacked"));
		request.setAttribute("page", pagelist.getPaginator());
		request.setAttribute("model", tRecharge);
		
		
		TAccount tAccount= cUserService.selectUserMoney(this.getCUser().getId());//查询用户资金
		request.setAttribute("taccount", tAccount);
		
		return "front/usercenter/recharge-record";
	}
}
