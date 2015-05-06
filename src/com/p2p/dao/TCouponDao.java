package com.p2p.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.model.TCoupon;
import com.p2p.model.TInvest;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 礼券表**/
@Repository
public interface TCouponDao extends BaseDao<TCoupon>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TCoupon> getAll(TCoupon tCoupon,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TCoupon> getAllBy(TCoupon tCoupon, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TCoupon get(TCoupon tCoupon);

	public List<TInvest> queryUserNotHasRedenvelopes();
	/**
	 * 查询满足赠送首投红包的用户
	 * @param userId
	 * @return
	 */
	public List<TCoupon> queryUserRedenvelopes(Long userId);
	/**
	 * 查询申请首投礼包的客户
	 * @return
	 */
	 public List<TCoupon> querySendOutRedPaperUsers(TCoupon tCoupon);
	/**
	 * 修改红包状态-处理中
	 * @param id
	 * @return
	 */
	public Long updateUserCouponType(TCoupon tCoupon);
	/**
	 * 修改红包状态-成功
	 * @param id
	 * @return
	 */
	public Long updateUserCouponSuccessType(TCoupon tCoupon);

	public long updateUserCouponReback(TCoupon tCoupon);

	public PageList queryTCouponPage(TCoupon tCoupon, PageBounds pageBounds);
}