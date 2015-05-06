package com.p2p.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.p2p.service.TUserDayGrowthStatisticsService;

@Component
public class UserStatisticsJob {
	private static Log log = LogFactory.getLog(UserStatisticsJob.class);

	@Autowired
	private TUserDayGrowthStatisticsService tUserDayGrowthStatisticsService;


	/**
	 * 添加首投红包
	 */
	public void setUserDayStatistics() {
		log.info("===每日新用户注册，实名，投资统计开始===");
		tUserDayGrowthStatisticsService.addDayUserStatistics();
		log.info("===每日新用户注册，实名，投资统计结束===");
	}
}
