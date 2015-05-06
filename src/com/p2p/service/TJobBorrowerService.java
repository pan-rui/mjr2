package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TJobBorrowerDao;
import com.p2p.model.TJobBorrower;
import com.p2p.mybatis.annotion.SearchOperator;


/** 借款人工作情况**/
@Service
public class TJobBorrowerService extends BaseService {


	@Autowired
	private TJobBorrowerDao tJobBorrowerDao;
 
	public long addTJobBorrower(TJobBorrower tJobBorrower) {
		return tJobBorrowerDao.insert(tJobBorrower);
	}
 
	public long updateTJobBorrower(TJobBorrower tJobBorrower) {
		return tJobBorrowerDao.update(tJobBorrower);
	}
 
	public TJobBorrower getTJobBorrower(TJobBorrower tJobBorrower) {
		return tJobBorrowerDao.get(tJobBorrower);
	}
 
	public PageList queryTJobBorrowerPage(TJobBorrower tJobBorrower,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tJobBorrowerDao.getAllBy(tJobBorrower, options, pageBounds);
	}

	/**
	 * 根据个人信息Id获取工作信息
	 * @param personBorrowerId
	 * @return
	 */
	public TJobBorrower getTJobBorrowerByPersonId(Long personBorrowerId) {
		return tJobBorrowerDao.getTJobBorrowerByPersonId(personBorrowerId);
	}
 }