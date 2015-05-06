package com.p2p.web.controller.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.constans.BorrowStatus;
import com.p2p.constans.ParaConstans;
import com.p2p.dto.BorrowAttrArrayDto;
import com.p2p.dto.BorrowDto;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.UserDto;
import com.p2p.model.BArea;
import com.p2p.model.BPara;
import com.p2p.model.TBorrow;
import com.p2p.model.TBorrowAttr;
import com.p2p.model.TJobBorrower;
import com.p2p.model.TPersonBorrower;
import com.p2p.service.BAreaService;
import com.p2p.service.BParaService;
import com.p2p.service.CUserService;
import com.p2p.service.TBorrowAttrService;
import com.p2p.service.TBorrowService;
import com.p2p.service.TJobBorrowerService;
import com.p2p.service.TPersonBorrowerService;
import com.p2p.util.OrdIdUtil;
import com.p2p.util.OrdIdUtil.OrdType;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TBorrowController extends BaseController {

	private static Log log = LogFactory.getLog(TBorrowController.class);
	@Autowired
	private TBorrowService tBorrowService;

	@Autowired
	private BParaService bParaService;

	@Autowired
	private TPersonBorrowerService tPersonBorrowerService;

	@Autowired
	private TJobBorrowerService tJobBorrowerService;

	@Autowired
	private BAreaService bAreaService;

	@Autowired
	private TBorrowAttrService tBorrowAttrService;

	@Autowired
	private CUserService cUserService;

	@RequestMapping(value = "/queryTBorrowList")
	public String queryTBorrowList(BorrowDto borrowDto) {

		PageList pageList = tBorrowService.queryTBorrowPage(borrowDto,
				pageBounds);
		request.setAttribute("borrowStatusList",
				BorrowStatus.BORROW_STATUS_LIST);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", borrowDto);
		return "admin/borrow/borrow-list";
	}

	private void setInitDetailData() {
		BArea bArea = new BArea();
		bArea.setAreaLevel(1);
		List<BArea> bAreaList = bAreaService.queryBAreaList(bArea);
		request.setAttribute("bAreaList", bAreaList);
		List<BPara> companyIndustryList = bParaService
				.queryBparaByCode(ParaConstans.COMPANY_INDUSTRY);
		List<BPara> companyNatureList = bParaService
				.queryBparaByCode(ParaConstans.COMPANY_NATURE);
		List<BPara> jobYearList = bParaService
				.queryBparaByCode(ParaConstans.JOB_YEAR);
		List<BPara> monthlyIncomeLevelList = bParaService
				.queryBparaByCode(ParaConstans.MONTHLY_INCOME_LEVEL);
		request.setAttribute("companyIndustryList", companyIndustryList);
		request.setAttribute("companyNatureList", companyNatureList);
		request.setAttribute("jobYearList", jobYearList);
		request.setAttribute("monthlyIncomeLevelList", monthlyIncomeLevelList);
	}

	@RequestMapping(value = "/addTBorrowIndex")
	public String addTBorrowIndex() {
		setInitData();
		return "admin/borrow/borrow-add";
	}

	private void setInitData() {
		List<BPara> minInvestAmountList = bParaService
				.queryBparaByCode(ParaConstans.MIN_INVEST_AMOUNT);
		request.setAttribute("minInvestAmountList", minInvestAmountList);
		List<BPara> maxInvestAmountList = bParaService
				.queryBparaByCode(ParaConstans.MAX_INVEST_AMOUNT);
		request.setAttribute("maxInvestAmountList", maxInvestAmountList);
	}

	@RequestMapping(value = "/addTBorrow")
	public String addTBorrow(TBorrow tBorrow,
			BorrowAttrArrayDto borrowAttrArrayDto) {
		tBorrow.setCreateTime(new Date());
		tBorrow.setCreateId(this.getBAdmin().getId());
		long result = tBorrowService.addTBorrow(tBorrow,
				borrowAttrArrayDto.getBorrowAttrs());
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/updateTBorrowIndex")
	public String updateTBorrowIndex(TBorrow tBorrow) {
		TBorrow tBorrowModel = tBorrowService.getTBorrow(tBorrow);
		request.setAttribute("tBorrowModel", tBorrowModel);
		return "admin/xx/xx-update";
	}

	@RequestMapping(value = "/updateTBorrow")
	public String updateTBorrow(TBorrow tBorrow) {
		long result = tBorrowService.updateTBorrow(tBorrow);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/queryTBorrowIndex")
	public String queryTBorrowIndex(TBorrow tBorrow) {
		setInitDetailData();

		TBorrow tBorrowModel = tBorrowService.getTBorrow(tBorrow);
		TPersonBorrower tPersonBorrower = new TPersonBorrower();
		tPersonBorrower.setId(tBorrowModel.getPersonBorrowerId());
		TPersonBorrower tPersonBorrowerModel = tPersonBorrowerService
				.getTPersonBorrower(tPersonBorrower);

		TJobBorrower tJobBorrowerModel = tJobBorrowerService
				.getTJobBorrowerByPersonId(tPersonBorrowerModel.getId());
		UserDto userDto = cUserService.getCUser(tBorrowModel.getBorrowerId());
		request.setAttribute("tPersonBorrowerModel", tPersonBorrowerModel);
		request.setAttribute("tJobBorrowerModel", tJobBorrowerModel);
		request.setAttribute("tBorrowModel", tBorrowModel);
		request.setAttribute("userDto", userDto);
		List<TBorrowAttr> borrowAttrList = tBorrowAttrService
				.queryTBorrowAttrListByBorrowId(tBorrowModel.getId());

		request.setAttribute("borrowAttrList", borrowAttrList);
		return "admin/borrow/borrow-detail";
	}

	@RequestMapping(value = "/updateFirstAuditTBorrowIndex")
	public String updateFirstAuditTBorrowIndex(TBorrow tBorrow) {
		setInitDetailData();
		queryTBorrowIndex(tBorrow);
		request.setAttribute("auditStatus", 1);
		return "admin/borrow/borrow-detail";
	}

	@RequestMapping(value = "/updateFirstAuditTBorrow")
	public String updateFirstAuditTBorrow(TBorrow tBorrow) {

		Date nowDate = new Date();
		Long times = tBorrow.getInvestStartTime().getTime() - nowDate.getTime();
		if (times / 60000 < 1) {
			this.writeJson(new InfoMsg("请选择开始投标的时间比当前时间大1分钟", "n"));
			return null;
		}

		TBorrow auditBorrow = new TBorrow();
		auditBorrow.setId(tBorrow.getId());
		auditBorrow.setFirstAuditId(this.getBAdmin().getId());
		auditBorrow.setInvestStartTime(tBorrow.getInvestStartTime());
		auditBorrow.setOrdId(OrdIdUtil.generateOrdId(OrdType.BORROW_TYPE));
		if (tBorrow.getBorrowStatus() == 1) {
			auditBorrow.setBorrowStatus(BorrowStatus.AUDIT_ONE_SUCCESS);
		} else {
			auditBorrow.setBorrowStatus(BorrowStatus.AUDIT_ONE_FAIL);
		}
		long result = -1;
		try {
			auditBorrow.setFirstAuditId(this.getBAdmin().getId());
			result = tBorrowService.updateBorrowFirstAudit(auditBorrow);
		} catch (SchedulerException e) {
			log.error(auditBorrow, e);
		}
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/updateFullAuditTBorrowIndex")
	public String updateFullAuditTBorrowIndex(TBorrow tBorrow) {
		setInitDetailData();
		queryTBorrowIndex(tBorrow);
		request.setAttribute("auditStatus", 2);
		return "admin/borrow/borrow-detail";
	}

	@RequestMapping(value = "/updateFullAuditTBorrow")
	public String updateFullAuditTBorrow(TBorrow tBorrow) {

		TBorrow auditBorrow = new TBorrow();
		auditBorrow.setId(tBorrow.getId());
		auditBorrow.setFullAuditId(this.getBAdmin().getId());
		auditBorrow.setAuditTime(new Date());

		if (tBorrow.getBorrowStatus() == 1) {
			auditBorrow.setBorrowStatus(BorrowStatus.REPAYING);
		} else {
			auditBorrow.setBorrowStatus(BorrowStatus.AUDIT_TWO_FAIL);
		}

		InfoMsg msg = tBorrowService.updateBorrowFullAudit(auditBorrow);

		this.writeJson(msg);

		return null;
	}
	
	/**
	 * 添加项目资料信息
	 * @param tBorrowAttr
	 * @return
	 */
	@RequestMapping(value="/insertTBorrowAttr")
	public String insertTBorrowAttr(TBorrowAttr tBorrowAttr){
		tBorrowAttr.setCreateTime(new Date());
		Long result=tBorrowService.insertTBorrowAttr(tBorrowAttr);
		if(result>0){
			InfoMsg msg =new InfoMsg("修改成功", "y");
			this.writeJson(msg);
		}else{
			InfoMsg msg =new InfoMsg("修改失败", "n");
			this.writeJson(msg);
		}
		return null;
	}
	
	/**
	 * 删除项目资料信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteTBorrowAttr")
	public String deleteTBorrowAttr(String id){
		Long result=tBorrowAttrService.deleteTBorrowAttr(Long.parseLong(id));
		if(result>0){
			InfoMsg msg =new InfoMsg("操作成功", "y");
			this.writeJson(msg);
		}else{
			
		}
		return null;
	}

}