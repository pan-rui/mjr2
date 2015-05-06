package com.p2p.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.dto.RechargeRecordDto;
import com.p2p.model.TRecharge;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 充值表**/
@Repository
public interface TRechargeDao extends BaseDao<TRecharge>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TRecharge> getAll(TRecharge tRecharge,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TRecharge> getAllBy(TRecharge tRecharge, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TRecharge get(TRecharge tRecharge);

	public List<RechargeRecordDto> queryRechargeRecord(RechargeRecordDto rechargeRecordDto,PageBounds pageBounds);
	
	public RechargeRecordDto selectRechargeRecord(Long id);

	/**
	 * 充值成功回调
	 * @param tRecharge
	 * @return
	 */
	public long updateRechargeSuccess(TRecharge tRecharge);
	
	/**
	 * 个人中心充值记录
	 * @param trecharge
	 * @param pagebounds
	 * @return
	 */
	public List<TRecharge> selectTRechargeList(TRecharge trecharge,PageBounds pagebounds);
	

	
	public TRecharge selectUserTrecharge(String ordId);
	
	public int updateUserTrecharge(TRecharge tRecharge);
	
	public BigDecimal selecttrechargefortime(Long userId);
}