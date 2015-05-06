package com.p2p.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.p2p.service.CUserService;
import com.p2p.service.TRefereeRelationService;

@Component
public class RefereeCouponJob {
	private static Log log = LogFactory.getLog(RefereeCouponJob.class);
	
	@Autowired
	private TRefereeRelationService refereeRelationService;
	@Autowired
	private CUserService cUserService;
	
	public void refereecoupon(){
		log.info("===定时发放推荐奖励开始===");
		refereeRelationService.selectRefereelist();
	}
	
	public void updatevipdown(){
		log.info("===定时清除过期vip开始===");
		cUserService.updatevipdown();
	}
	
	public void updateisNew(){
		log.info("===定时清除过期新手奖励开始===");
		cUserService.deleteNovicesReward();
	}
}
