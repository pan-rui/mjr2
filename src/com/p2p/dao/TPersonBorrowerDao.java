package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TPersonBorrower;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 借款人信息**/
@Repository
public interface TPersonBorrowerDao extends BaseDao<TPersonBorrower>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TPersonBorrower> getAll(TPersonBorrower tPersonBorrower,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TPersonBorrower> getAllBy(TPersonBorrower tPersonBorrower, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TPersonBorrower get(TPersonBorrower tPersonBorrower);

}