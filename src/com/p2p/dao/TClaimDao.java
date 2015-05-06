package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TClaim;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 债权转让表**/
@Repository
public interface TClaimDao extends BaseDao<TClaim>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TClaim> getAll(TClaim tClaim,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TClaim> getAllBy(TClaim tClaim, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TClaim get(TClaim tClaim);

	/**
	 * 购买债权更新
	 * @param updateClaim
	 * @return
	 */
	public long updateBuyClaim(TClaim updateClaim);

	/**
	 * 债权转让完
	 * @param id
	 * @return
	 */
	public long updateFinishClaim(TClaim updateClaim);

}