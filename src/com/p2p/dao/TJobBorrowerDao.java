package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TJobBorrower;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 借款人工作情况**/
@Repository
public interface TJobBorrowerDao extends BaseDao<TJobBorrower>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TJobBorrower> getAll(TJobBorrower tJobBorrower,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TJobBorrower> getAllBy(TJobBorrower tJobBorrower, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TJobBorrower get(TJobBorrower tJobBorrower);

	public TJobBorrower getTJobBorrowerByPersonId(Long personBorrowerId);

}