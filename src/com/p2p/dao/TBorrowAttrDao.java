package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TBorrowAttr;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 借款附件**/
@Repository
public interface TBorrowAttrDao extends BaseDao<TBorrowAttr>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TBorrowAttr> getAll(TBorrowAttr tBorrowAttr,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TBorrowAttr> getAllBy(TBorrowAttr tBorrowAttr, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TBorrowAttr get(TBorrowAttr tBorrowAttr);

	public List<TBorrowAttr> queryTBorrowAttrListByBorrowId(Long borrowId);
	public long deleteTBorrowAttr(Long id);
}