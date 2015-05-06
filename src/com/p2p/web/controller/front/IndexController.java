package com.p2p.web.controller.front;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.BorrowDto;
import com.p2p.model.CLinks;
import com.p2p.model.CUser;
import com.p2p.model.TBorrow;
import com.p2p.service.CBannerService;
import com.p2p.service.CLinksService;
import com.p2p.service.CMediaReportService;
import com.p2p.service.CNoticeService;
import com.p2p.service.TAccountService;
import com.p2p.service.TBorrowService;
import com.p2p.util.CalculateInterestUtil;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class IndexController extends BaseController {
	private static Log log = LogFactory.getLog(IndexController.class);
	@Autowired
	private CBannerService bannerService;
	@Autowired
	private CNoticeService cNoticeService;
	@Autowired
	private TBorrowService tBorrowService;
	@Autowired
	private TAccountService tAccountService;
	@Autowired
	private CLinksService cLinksService;
	@Autowired
	private CMediaReportService cMediaReportService;
	
	/**
	 * 首页初始化，取值
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		//banner取值
		request.setAttribute("banners", bannerService.queryCBannerList());
		request.setAttribute("notices", cNoticeService.getIndexNotice());
		BorrowDto borrowDto = new BorrowDto();
		borrowDto.setBorrowStatus(2);
		pageBounds.setLimit(3);
		PageList<TBorrow> pageList = tBorrowService.queryTBorrowListPage(borrowDto, pageBounds);
		request.setAttribute("preSaleBrrows", pageList);
		List<TBorrow> pageListing = tBorrowService.queryIndexTBorrowListPage(borrowDto);
		request.setAttribute("IngBrrows", pageListing);
		CUser cUser = this.getCUser();
		if(cUser!=null){
			request.setAttribute("userAccount", tAccountService.getByUserId(cUser.getId()));
	
		}
		return "front/index";
	}
	/**
	 * 首页合作伙伴
	 * @return
	 */
	@RequestMapping(value = "/indexLinks")
	public String indexLinks(){
		List<CLinks> indexLinks = new ArrayList<CLinks>();
		try{
			indexLinks= cLinksService.queryIndexLinks();
		}
		catch (Exception e) {
			log.error("合作伙伴", e);
		}
		request.setAttribute("indexLinks",indexLinks );
		return "front/supporting/indexLinks";
	}
	
	/**
	 * 首页新闻
	 * @return
	 */
	@RequestMapping(value = "/indexNews")
	public String indexNews(){
		request.setAttribute("indexNews",cMediaReportService.queryIndexNews());
		return "front/supporting/index-news"; 
	}
	/**
	 * 首页收益计算器
	 * @return
	 */
	@RequestMapping(value = "/indexCalculator")
	public String indexCalculator(){
		BigDecimal investAmount=new BigDecimal(request.getParameter("investAmount"));//金额
		BigDecimal annualRate=new BigDecimal(request.getParameter("annualRate"));//利率
		int investLine=Integer.parseInt(request.getParameter("investLine"));//期限
		String deadlineType=request.getParameter("deadlineType");//天OR月
		String repayType = request.getParameter("repayType");//还款方式
		BigDecimal rest=null;
		if("3".equals(repayType)){
			rest = CalculateInterestUtil.calculateEqualMonthlyInterest(investAmount, investLine, annualRate);
		}else{
			if("1".equals(deadlineType)){
				rest = CalculateInterestUtil.calculateOneRepay(investAmount, investLine, annualRate, true);
			}else{
				rest = CalculateInterestUtil.calculateOneRepay(investAmount, investLine, annualRate, false);
			}
		}
		this.writeJson(""+rest);
		return null;
	}
	
}
