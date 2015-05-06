package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TApplyBorrow;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 借款申请表**/
@Repository
public interface TApplyBorrowDao extends BaseDao<TApplyBorrow>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TApplyBorrow> getAll(TApplyBorrow tApplyBorrow,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TApplyBorrow> getAllBy(TApplyBorrow tApplyBorrow, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TApplyBorrow get(TApplyBorrow tApplyBorrow);
	/**
	 * 分页获取借款申请
	 * @param tApplyBorrow
	 * @param row
	 * @return
	 */
	public List<TApplyBorrow> queryTApplyBorrowPage(TApplyBorrow tApplyBorrow, PageBounds row);
}