package com.p2p.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.model.CMessage;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.CMessageService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CMessageController extends BaseController {

	private static Log log = LogFactory.getLog(CMessageController.class);
	@Autowired
	private CMessageService cMessageService;
 
	@RequestMapping(value = "/queryCMessageList")
	public String queryCMessageList (CMessage cMessage) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>(); 
		PageList pageList = cMessageService.queryCMessagePage(cMessage, options,pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", cMessage);
		return "admin/xx/xx-list";
	}
 
	@RequestMapping(value = "/addCMessageIndex")
	public String addCMessageIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addCMessage")
	public String addCMessage (CMessage cMessage) {
		long result = cMessageService.addCMessage(cMessage);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateCMessageIndex")
	public String updateCMessageIndex  (CMessage cMessage) {
		CMessage cMessageModel = cMessageService.getCMessage(cMessage);
		request.setAttribute("cMessageModel", cMessageModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateCMessage")
	public String updateCMessage (CMessage cMessage) {
		long result = cMessageService.updateCMessage(cMessage);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryCMessageIndex")
	public String queryCMessageIndex  (CMessage cMessage) {
		CMessage cMessageModel = cMessageService.getCMessage(cMessage);
		request.setAttribute("cMessageModel", cMessageModel);
		return "admin/xx/xx-detail";
	}
 }