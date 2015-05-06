package com.p2p.web.controller.front;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.CMessage;
import com.p2p.model.CUser;
import com.p2p.service.CMessageService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterMessageController extends BaseController{
	private static Log log = LogFactory.getLog(BorrowController.class);
	@Autowired
	private CMessageService cMessageService ;
	
	@RequestMapping(value="/center/letterStation")
	public String selectUserIndex(CMessage cMessage){
		CUser sessionUser = this.getCUser();
		cMessage.setReceiverId(sessionUser.getId());
		PageList pageList = cMessageService.queryOneUserMessage(cMessage,pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		return "front/usercenter/user-message";
	}
}
