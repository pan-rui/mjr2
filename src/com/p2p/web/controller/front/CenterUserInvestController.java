package com.p2p.web.controller.front;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.AgreementMsgDto;
import com.p2p.dto.TInvestDto;
import com.p2p.model.TInvest;
import com.p2p.model.TRepay;
import com.p2p.service.TInvestService;
import com.p2p.service.TRepayService;
import com.p2p.web.controller.BaseController;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;
@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterUserInvestController extends BaseController{
	private static Log log=LogFactory.getLog(CenterUserInvestController.class);
	@Autowired
	private TInvestService tis;
	
	@Autowired
	private TRepayService tRepayService;
	/**
	 * 查询投标中的借款
	 * @param tInvestDto
	 * @return
	 */
	@RequestMapping(value="center/selectInvestList")
	public String selectInvestList(TInvestDto tInvestDto){
		tInvestDto.setInvestorId(this.getCUser().getId());
		tInvestDto.setBorrowStatus(3);
		System.out.println(tInvestDto.getInvestTimeup()+"-----------"+tInvestDto.getInvestTimeend());
		PageList pageList=tis.selectTInvestListfortj(tInvestDto, pageBounds);
		
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tInvestDto);
		return "front/usercenter/user-invest";
	}
	
	/**
	 * 查询回收中的借款
	 * @param tInvestDto
	 * @return
	 */
	@RequestMapping(value="center/selectInvestListforhsz")
	public String selectInvestListforhsz(TInvestDto tInvestDto){
		tInvestDto.setInvestorId(this.getCUser().getId());
		tInvestDto.setBorrowStatus(5);
		PageList pageList=tis.selectTInvestListfortj(tInvestDto, pageBounds);
		
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tInvestDto);
		return "front/usercenter/user-invest2";
	}
	
	/**
	 * 查询已回收的借款
	 * @param tInvestDto
	 * @return
	 */
	@RequestMapping(value="center/selectInvestListforyhs")
	public String selectInvestListforyhs(TInvestDto tInvestDto){
		tInvestDto.setInvestorId(this.getCUser().getId());
		tInvestDto.setBorrowStatus(6);
		PageList pageList=tis.selectTInvestListfortj(tInvestDto, pageBounds);
		
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tInvestDto);
		return "front/usercenter/user-invest3";
	}
	
	/**
	 * 根据投标id查询详细信息
	 * @param tInvestDto
	 * @return
	 */
	@RequestMapping(value="center/selectInvestDeatil")
	public String selectInvestDeatil(TInvestDto tInvestDto){
		TRepay tRepay=new TRepay();
		tRepay.setInvestId(tInvestDto.getId());
		TRepay tRepay2=tis.selectTrepayMoney(tRepay);
		
 		TInvestDto tinvestdto=tis.selectTInvestDeatil(tInvestDto.getId());
		tinvestdto.setCapitalAmount(tRepay2.getCapitalAmount());
		tinvestdto.setProfitAmount(tRepay2.getProfitAmount());
		tinvestdto.setRepayStatus(tRepay2.getRepayStatus());
		List<TRepay> pagelist=tis.selectInvestList(tinvestdto.getId());
		
		request.setAttribute("pageBean", pagelist);
		
		
		request.setAttribute("investdto", tinvestdto);
		
		return "front/usercenter/user-investdeatil";
	}
	
	/**
	 * 加载合同详细信息
	 * @param tInvestDto
	 * @return
	 */
	@RequestMapping(value="center/selectAgreementMsg")
	public String selectAgreementMsg(TInvestDto tInvestDto){
		tInvestDto.setId(Long.parseLong(request.getParameter("id")));
		//TRepay tRepay=new TRepay();
		//tRepay.setInvestId(tInvestDto.getId());
		//TRepay tRepay2=tis.selectTrepayMoney(tRepay);
		AgreementMsgDto amdto=tis.selectAgreementMsg(tInvestDto);
		//amdto.setProfitAmount(tRepay2.getProfitAmount());
		Date date = amdto.getAuditTime();
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
		Date exdate=calendar.getTime();   //这个时间就是日期往后推一天的结果 
		amdto.setAvaryMout(exdate);
		request.setAttribute("amdto", amdto);
		return "front/usercenter/agreement";
	}
}
