package com.p2p.web.controller.front;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.constans.BorrowStatus;
import com.p2p.dto.BorrowDto;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.TInvestDto;
import com.p2p.model.CUser;
import com.p2p.model.TAccount;
import com.p2p.model.TBorrow;
import com.p2p.model.TBorrowAttr;
import com.p2p.model.TJobBorrower;
import com.p2p.model.TPersonBorrower;
import com.p2p.service.TAccountService;
import com.p2p.service.TBorrowAttrService;
import com.p2p.service.TBorrowService;
import com.p2p.service.TInvestService;
import com.p2p.service.TJobBorrowerService;
import com.p2p.service.TPersonBorrowerService;
import com.p2p.util.CalculateInterestUtil;
import com.p2p.util.DateUtil;
import com.p2p.util.NumberUtil;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class BorrowController extends BaseController {
	private static Log log = LogFactory.getLog(BorrowController.class);
	@Autowired
	private TInvestService tInvestService;

	@Autowired
	private TBorrowService tBorrowService;

	@Autowired
	private TBorrowAttrService tBorrowAttrService;

	@Autowired
	private TPersonBorrowerService tPersonBorrowerService;

	@Autowired
	private TJobBorrowerService tJobBorrowerService;

	@Autowired
	private TAccountService tAccountService;

	@RequestMapping(value = "/queryBorrowDetail")
	public String queryBorrowDetail(Long id) {
		try{
		TBorrow tBorrow = new TBorrow();
		tBorrow.setId(id);
		// 查询借款详情
		TBorrow tBorrowModel = tBorrowService.getTBorrow(tBorrow);
		request.setAttribute("tBorrowModel", tBorrowModel);
		BigDecimal progress = tBorrowModel.getHasBorrowAmount().divide(tBorrowModel.getBorrowAmount(),4,RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
		
		tBorrowModel.setProgress(progress.setScale(2));
		// 查询项目资料
		List<TBorrowAttr> borrowAttrList = tBorrowAttrService
				.queryTBorrowAttrListByBorrowId(id);
		request.setAttribute("borrowAttrList", borrowAttrList);
		
		request.setAttribute("remainAmount", tBorrowModel.getBorrowAmount().subtract(tBorrowModel.getHasBorrowAmount()));
	
		if(tBorrowModel.getBorrowStatus().equals(BorrowStatus.TENDERING)){
			long remainTime = DateUtil.dateAddDay(tBorrowModel.getInvestStartTime(), tBorrowModel.getRaisingPeriod().intValue()).getTime()-new Date().getTime();
			remainTime = remainTime/1000;
			request.setAttribute("remainTime", remainTime);
		}else if(tBorrowModel.getBorrowStatus().equals(BorrowStatus.AUDIT_ONE_SUCCESS)){
			long remainTime = tBorrowModel.getInvestStartTime().getTime()-new Date().getTime();
			remainTime = remainTime/1000;
			request.setAttribute("remainTime", remainTime);
		}else{
			request.setAttribute("remainTime", "0");
		}
		
		
		
		// 借款人信息
		TPersonBorrower tPersonBorrower = new TPersonBorrower();
		tPersonBorrower.setId(tBorrowModel.getPersonBorrowerId());
		TPersonBorrower tPersonBorrowerModel = tPersonBorrowerService
				.getTPersonBorrower(tPersonBorrower);
		request.setAttribute("tPersonBorrowerModel", tPersonBorrowerModel);

		TJobBorrower tJobBorrowerModel = tJobBorrowerService
				.getTJobBorrowerByPersonId(tPersonBorrowerModel.getId());
		request.setAttribute("tJobBorrowerModel", tJobBorrowerModel);
		
		// 当前账户余额
		CUser cUser = this.getCUser();
		if (cUser != null) {
			TAccount tAccountModel = tAccountService.getByUserId(cUser.getId());
			request.setAttribute("tAccountModel", tAccountModel);
		}
		}catch(Exception e){
			log.error("", e);
		}
		return "front/borrow/borrow-detail";
	}

	@RequestMapping(value = "/borrowInvestList")
	public String queryInvestList(TInvestDto tInvestDto) {
		tInvestDto.setResult(1);
		// 查询投资记录
		PageList<TInvestDto> pageList = tInvestService.queryTInvestListPage(tInvestDto, pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		return "front/borrow/borrow-detail-invest-list";
	}

	@RequestMapping(value = "/borrowList")
	public String queryBorrowList(BorrowDto borrowDto) {
		if (borrowDto.getDeadline() != null) {
			if (borrowDto.getDeadline() == 1) {
				borrowDto.setStartDeadline(1);
				borrowDto.setEndDeadline(1);
			} else if (borrowDto.getDeadline() == 2) {
				borrowDto.setStartDeadline(3);
				borrowDto.setEndDeadline(3);
			} else if (borrowDto.getDeadline() == 3) {
				borrowDto.setStartDeadline(6);
				borrowDto.setEndDeadline(6);
			} else if (borrowDto.getDeadline() == 4) {
				borrowDto.setStartDeadline(12);
			}
		}
		try {
			PageList<TBorrow> pageList = tBorrowService.queryTBorrowListPage(
					borrowDto, pageBounds);
			request.setAttribute("pageBean", pageList);
			request.setAttribute("page", pageList.getPaginator());
			request.setAttribute("borrowDto", borrowDto);
		} catch (Exception e) {
			log.error("理财投资异常", e);
		}
		return "front/borrow/borrow-list";
	}
	
	@RequestMapping(value = "/showAgreement")
	public String showAgreement() {

		return "front/borrow/agreement";
	}
	
	/**
	 * 根据标id获取剩余投资额度
	 * @return
	 */
	@RequestMapping(value = "/chackBrrowMayAmount")
	public String chackBrrowMayAmount(){
		long id = Long.parseLong(request.getParameter("bId"));
		TBorrow tBorrow = new TBorrow();
		tBorrow.setId(id);
		// 查询借款详情
		TBorrow tBorrowModel = tBorrowService.getTBorrow(tBorrow);
		if(tBorrowModel==null){
			this.writeJson(new InfoMsg("此产品不存在", "n"));
			return null; 
		}
		BigDecimal mayCast = tBorrowModel.getBorrowAmount().subtract(tBorrowModel.getHasBorrowAmount());
		this.writeJson(new InfoMsg("ok", "y", mayCast+""));
		return null; 
	}

	/**
	 * 根据标id获取剩余投资额度
	 * @return
	 */
	@RequestMapping(value = "/calculateInterest")
	public String calculateInterest(BigDecimal amount,BigDecimal annualRate,Integer month,Integer repayType,int dayType ){
		boolean isDay = (dayType==1?true:false);
		BigDecimal interestAmount = BigDecimal.ZERO;
		BigDecimal capitalInterestAmount = BigDecimal.ZERO;
	
		if(amount == null ){
			Map<String,Object> map = new HashMap<>();
			map.put("code", "-1");
			map.put("msg", "投资金额不能为空");
			this.writeJson(map);
			return null;
		}
		if(amount.compareTo(BigDecimal.ZERO) <=0){
			Map<String,Object> map = new HashMap<>();
			map.put("code", "-1");
			map.put("msg", "投资金额应该大于0");
			this.writeJson(map);
			return null;
		}
		if(annualRate == null ){
			Map<String,Object> map = new HashMap<>();
			map.put("code", "-1");
			map.put("msg", "年化利率不能为空");
			this.writeJson(map);
			return null;
		}
		if(annualRate.compareTo(BigDecimal.ZERO) <=0){
			Map<String,Object> map = new HashMap<>();
			map.put("code", "-1");
			map.put("msg", "年化利率应该大于0");
			this.writeJson(map);
			return null;
		}
		
		if(month == null ){
			Map<String,Object> map = new HashMap<>();
			map.put("code", "-1");
			map.put("msg", "期限不能为空");
			this.writeJson(map);
			return null;
		}
		if(month <=0){
			Map<String,Object> map = new HashMap<>();
			map.put("code", "-1");
			map.put("msg", "期限应该大于0");
			this.writeJson(map);
			return null;
		}
		if(repayType == null ){
			Map<String,Object> map = new HashMap<>();
			map.put("code", "-1");
			map.put("msg", "还款方式不能为空");
			this.writeJson(map);
			return null;
		}
		if(repayType < 1 || repayType >3){
			Map<String,Object> map = new HashMap<>();
			map.put("code", "-1");
			map.put("msg", "还款方式不对");
			this.writeJson(map);
			return null;
		}
	
		/** 还款方式（1，一次性还款，2，为按月付息，到期还本,3等额本息） **/
		if(repayType==1){
			interestAmount = CalculateInterestUtil.calculateOneRepay(amount, month, annualRate, isDay);
		}else if(repayType==2){
			interestAmount = CalculateInterestUtil.calculateOneRepay(amount, month, annualRate, false);
		}else if(repayType==3){
			interestAmount = CalculateInterestUtil.calculateEqualMonthlyInterest(amount, month, annualRate);
		}
		capitalInterestAmount = amount.add(interestAmount);
		Map<String,Object> map = new HashMap<>();
		map.put("interestAmount", NumberUtil.fomatShowAmount(interestAmount));
		map.put("capitalInterestAmount", NumberUtil.fomatShowAmount(capitalInterestAmount));
		map.put("code", "1");
		map.put("msg", "");
		this.writeJson(map);
		return null; 
	}

}
