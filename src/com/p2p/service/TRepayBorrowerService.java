package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TRepayBorrowerDao;
import com.p2p.dto.TRepayBorrowerDto;
import com.p2p.model.TRepayBorrower;
import com.p2p.mybatis.annotion.SearchOperator;


/** 借款人总还款表**/
@Service
public class TRepayBorrowerService extends BaseService {


	@Autowired
	private TRepayBorrowerDao tRepayBorrowerDao;
 
	public long addTRepayBorrower(TRepayBorrower tRepayBorrower) {
		return tRepayBorrowerDao.insert(tRepayBorrower);
	}
 
	public long updateTRepayBorrower(TRepayBorrower tRepayBorrower) {
		return tRepayBorrowerDao.update(tRepayBorrower);
	}
	
 
	public TRepayBorrower getTRepayBorrower(TRepayBorrower tRepayBorrower) {
		return tRepayBorrowerDao.get(tRepayBorrower);
	}
 
	public PageList queryTRepayBorrowerPage(TRepayBorrower tRepayBorrower,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tRepayBorrowerDao.getAllBy(tRepayBorrower, options, pageBounds);
	}

	public PageList queryTRepayBorrowerPage(
			TRepayBorrowerDto tRepayBorrowerDto, PageBounds pageBounds) {
		return tRepayBorrowerDao.queryTRepayBorrowerPage(tRepayBorrowerDto,  pageBounds);
	}

	public List<TRepayBorrower> queryTRepayBorrowerList(
			TRepayBorrower tRepayBorrower) {
		return tRepayBorrowerDao.queryTRepayBorrowerList(tRepayBorrower);
	}
 }