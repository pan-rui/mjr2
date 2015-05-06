package com.p2p.job;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.p2p.model.TCoupon;
import com.p2p.model.TInvestAward;
import com.p2p.service.BaofooServiceImpl;
import com.p2p.service.TCouponService;
import com.p2p.service.TInvestAwardService;

@Component
public class SetUserRedenvelopesJob {
	private static Log log = LogFactory.getLog(SetUserRedenvelopesJob.class);

	@Autowired
	private TCouponService couponService;

	@Autowired
	private TInvestAwardService tInvestAwardService;

	@Autowired
	private BaofooServiceImpl baofooServiceImpl;

	/**
	 * 添加首投红包
	 */
	public void setUserRedenvelops() {
		log.info("===添加首投礼包开始===");
		try {
			couponService.updateUserRedenvelopesType();
		} catch (Exception e) {
			log.error("首投礼包异常", e);
		}
		log.info("===添加首投礼包结束===");
	}

	/**
	 * 为申请首投红包的客户发放红包
	 */
	public void sendOutUserRedPapers() {
		TCoupon coupon = new TCoupon();
		coupon.setCouponStatus(2);
		log.info("===赠送首投礼包开始===");

		List<TCoupon> tlist = couponService.querySendOutRedPaperUsers(coupon);
		for (TCoupon tCoupon : tlist) {
			try {
				baofooServiceImpl.updateUserRedPapersType(tCoupon);
			} catch (Exception e) {
				log.error("=赠送首投礼包异常", e);
			}
		}

		log.info("===赠送首投礼包结束===");
	}

	/**
	 * 发放投资奖励
	 */
	public void setOutUsersAward() {
		log.info("===发放投资奖励开始===");

		List<TInvestAward> tlist = tInvestAwardService.querySetoutUsersAward();
		for (TInvestAward tInvestAward : tlist) {
			try {
				baofooServiceImpl.updateSetoutUserInvestAward(tInvestAward);
			} catch (Exception e) {
				log.error("发放投资奖励异常", e);
			}
		}

		log.info("===发放投资奖励结束===");
	}

}
