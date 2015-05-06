package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TTransfer;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 转账**/
@Repository
public interface TTransferDao extends BaseDao<TTransfer>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TTransfer> getAll(TTransfer tTransfer,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TTransfer> getAllBy(TTransfer tTransfer, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TTransfer get(TTransfer tTransfer);

	public long updateTTransfer(TTransfer tTransfer);

	public TTransfer getTTransferByOrdId(String ordId);

	public List<TTransfer> queryTTransferList(TTransfer tTransfer);

}