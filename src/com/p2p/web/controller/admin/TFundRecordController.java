package com.p2p.web.controller.admin;

import java.math.BigDecimal;
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
import com.p2p.dto.TFundRecordDto;
import com.p2p.dto.UserMoneyDto;
import com.p2p.model.TAccount;
import com.p2p.model.TFundRecord;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.TAccountService;
import com.p2p.service.TFundRecordService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TFundRecordController extends BaseController {

	private static Log log = LogFactory.getLog(TFundRecordController.class);
	@Autowired
	private TFundRecordService tFundRecordService;
	@Autowired
	private TAccountService accountService;
 
	@RequestMapping(value = "/selectTFundRecordDetail")
	public String selectTFundRecordDetail (TFundRecordDto tFundRecordDto) {
		TFundRecordDto dtomsg=tFundRecordService.selectTFundRecordDetail(tFundRecordDto.getId());
		request.setAttribute("dtomsg", dtomsg);
		return "admin/finance/tfundRecord-detail";
	}
	
	@RequestMapping(value = "/selectTFundRecordList")
	public String selectTFundRecordList (TFundRecordDto tFundRecordDto) {
		
		PageList pageList = (PageList)tFundRecordService.selectTFundRecordlist(tFundRecordDto,pageBounds);
		//查询个人宝付账号余额
		BigDecimal f=tFundRecordService.selectusermoney(tFundRecordDto.getUserId());
		if(f==null){
			f=new BigDecimal(0);
		}
		TAccount tac=accountService.getTAccountforuserid(tFundRecordDto.getUserId());
		request.setAttribute("tac", tac);
		request.setAttribute("baofoomoney", f);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tFundRecordDto);
		request.setAttribute("iddd", tFundRecordDto.getUserId());
		
		return "admin/finance/tfundRecord-list";
	}
 
	@RequestMapping(value = "/addTFundRecordIndex")
	public String addTFundRecordIndex () {
		return "admin/xx/xx-add";
	}
 
	@RequestMapping(value = "/addTFundRecord")
	public String addTFundRecord (TFundRecord tFundRecord) {
		long result = tFundRecordService.addTFundRecord(tFundRecord);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/updateTFundRecordIndex")
	public String updateTFundRecordIndex  (TFundRecord tFundRecord) {
		TFundRecord tFundRecordModel = tFundRecordService.getTFundRecord(tFundRecord);
		request.setAttribute("tFundRecordModel", tFundRecordModel);
		return "admin/xx/xx-update";
	}
 
	@RequestMapping(value = "/updateTFundRecord")
	public String updateTFundRecord (TFundRecord tFundRecord) {
		long result = tFundRecordService.updateTFundRecord(tFundRecord);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
 
	@RequestMapping(value = "/queryTFundRecordIndex")
	public String queryTFundRecordIndex  (TFundRecord tFundRecord) {
		TFundRecord tFundRecordModel = tFundRecordService.getTFundRecord(tFundRecord);
		request.setAttribute("tFundRecordModel", tFundRecordModel);
		return "admin/xx/xx-detail";
	}
	
	@RequestMapping(value="/selectUserList")
	public String selectUserList(UserMoneyDto userMoneyDto){
		PageList pageList = (PageList)tFundRecordService.selectUserList(userMoneyDto, pageBounds);
		
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", userMoneyDto);
		return "admin/finance/user-list";
	}
 }