package com.p2p.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TCouponDao;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.TCoupon;
import com.p2p.model.TInvest;


/** 礼券表**/
@Service
public class TCouponService extends BaseService {


	@Autowired
	private TCouponDao tCouponDao;

	@Autowired 
	private BaofooServiceImpl baofooService;

	public long addTCoupon(TCoupon tCoupon) {
		return tCouponDao.insert(tCoupon);
	}

	public long updateTCoupon(TCoupon tCoupon) {
		return tCouponDao.update(tCoupon);
	}

	public TCoupon getTCoupon(TCoupon tCoupon) {
		return tCouponDao.get(tCoupon);
	}

	public PageList queryTCouponPage(TCoupon tCoupon, PageBounds pageBounds) {
		return (PageList)tCouponDao.queryTCouponPage(tCoupon, pageBounds);
	}

	public List<TCoupon> queryUserRedenvelopes(Long userId){
		return tCouponDao.queryUserRedenvelopes(userId);
	}

	/**
	 * 检查没有领取首投红包的客户，为其添加
	 * @return
	 */
	public void updateUserRedenvelopesType(){
		List<TInvest> tlist = tCouponDao.queryUserNotHasRedenvelopes();
		Long rest = -1l;
		Date date = new Date();
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(calendar.DATE,30);//把日期往后增加一天.整数往后推,负数往前移动 
		Date exdate=calendar.getTime();   //这个时间就是日期往后推一天的结果 
		BigDecimal hundred = new BigDecimal("100");
		BigDecimal thousand = new BigDecimal("1000");
		BigDecimal million = new BigDecimal("10000");
		for (TInvest tInvest : tlist) {
			BigDecimal firstInvest = tInvest.getRealAmount();
			BigDecimal bonus = new BigDecimal("0");
			if(firstInvest.compareTo(hundred)>=0 && firstInvest.compareTo(thousand)<0){
				bonus=new BigDecimal("20");
			}else if(firstInvest.compareTo(thousand)>=0 && firstInvest.compareTo(million)<0){
				bonus=new BigDecimal("38");
			}else if(firstInvest.compareTo(million)>=0){
				bonus=new BigDecimal("88");
			}
			TCoupon tCoupon = new TCoupon();
			tCoupon.setCreateTime(date);
			tCoupon.setUserId(tInvest.getInvestorId());
			tCoupon.setCouponName("首投红包");
			tCoupon.setCouponRemarks("恭喜您首次投资成功，平台特奖励您"+bonus+"元红包，以示鼓励。");
			tCoupon.setCouponAmount(bonus);
			tCoupon.setCouponType(1);
			tCoupon.setCouponStatus(1);
			tCoupon.setExpirationDate(exdate);
			rest = this.addTCoupon(tCoupon);
			if(rest<=0){
				throw new ZeroSQLException();
			}
		}
	}

	
	/**
	 * 插入推荐奖励 领用状态为2
	 * @param userId
	 * @return
	 */
	public long insertcouponforuserid(Long userId){
		Long rest = -1l;
		TCoupon tCoupon = new TCoupon();
		tCoupon.setCreateTime(new Date());
		tCoupon.setUserId(userId);
		tCoupon.setCouponName("推荐奖励");
		tCoupon.setCouponRemarks("恭喜您的推荐人首次投资成功，平台特奖励您8元红包，请查收。");
		tCoupon.setCouponAmount(new BigDecimal("8"));
		tCoupon.setCouponType(2);
		tCoupon.setCouponStatus(2);
		rest = this.addTCoupon(tCoupon);
		if(rest<=0){
			throw new ZeroSQLException();
			
		}
		
		
		return rest;
	}
	
 
	

	public long updateUserCouponSuccessType(TCoupon tCoupon) {
		return tCouponDao.updateUserCouponSuccessType(tCoupon);
	}

	public long updateUserCouponReback(TCoupon tCoupon) {
		return tCouponDao.updateUserCouponReback(tCoupon);
	}

	public Long updateUserCouponType(TCoupon tCoupon) {
		return tCouponDao.updateUserCouponType(tCoupon);
	}

	public List<TCoupon> querySendOutRedPaperUsers(TCoupon coupon) {
		return tCouponDao.querySendOutRedPaperUsers(coupon);
	}

}
