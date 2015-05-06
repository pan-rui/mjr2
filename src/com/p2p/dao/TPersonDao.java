package com.p2p.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.model.TPerson;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.mybatis.util.SQLProvider;


/** 实名认证**/
@Repository
public interface TPersonDao extends BaseDao<TPerson>{

	@SelectProvider(type = SQLProvider.class,method = "getAll")
	public List<TPerson> getAll(TPerson tPerson,PageBounds row);

	@SelectProvider(type = SQLProvider.class,method = "getAllBy")
	public List<TPerson> getAllBy(TPerson tPerson, Map<String,SearchOperator> options, PageBounds row);


	@SelectProvider(type = SQLProvider.class,method = "get")
	public TPerson get(TPerson tPerson);
	public TPerson  getPersonByUserId(Long userId);
	
	public TPerson verificationNewPersonIdcard(TPerson tPerson);
	
	public Long updatePersonAuth(TPerson tPerson);
	
}