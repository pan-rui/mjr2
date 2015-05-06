package com.p2p.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.TUserDayGrowthStatistics;
import com.p2p.service.TUserDayGrowthStatisticsService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class TUserDayGrowthStatisticsController extends BaseController {


	@Autowired
	private TUserDayGrowthStatisticsService tUserDayGrowthStatisticsService;
 
	@RequestMapping(value = "/queryTUserDayGrowthStatisticsList")
	public String queryTUserDayGrowthStatisticsList (TUserDayGrowthStatistics tUserDayGrowthStatistics) {
		PageList pageList = tUserDayGrowthStatisticsService.queryTUserDayGrowthStatisticsPage(tUserDayGrowthStatistics,pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", tUserDayGrowthStatistics);
		return "admin/statistics/dayGrowthStatistics-list";
	}

 }