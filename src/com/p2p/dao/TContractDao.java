package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TContract;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** **/
@Repository
public interface TContractDao extends BaseDao<TContract>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TContract> getAll(TContract tContract,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TContract> getAllBy(TContract tContract, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TContract get(TContract tContract);
	
	public List<TContract> selectcontracttitle();
}