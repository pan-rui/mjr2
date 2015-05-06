package com.p2p.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.BPara;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 参数值**/
@Repository
public interface BParaDao extends BaseDao<BPara>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<BPara> getAll(BPara bPara,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<BPara> getAllBy(BPara bPara, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public BPara get(BPara bPara);

	public List<BPara> queryBparaByCode(String paraCode);

}