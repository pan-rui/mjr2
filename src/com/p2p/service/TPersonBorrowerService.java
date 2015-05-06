package com.p2p.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TJobBorrowerDao;
import com.p2p.dao.TPersonBorrowerDao;
import com.p2p.dto.InfoMsg;
import com.p2p.exception.ZeroSQLException;
import com.p2p.model.TJobBorrower;
import com.p2p.model.TPersonBorrower;
import com.p2p.mybatis.annotion.SearchOperator;


/** 借款人信息**/
@Service
public class TPersonBorrowerService extends BaseService {


	@Autowired
	private TPersonBorrowerDao tPersonBorrowerDao;
	
	@Autowired
	private TJobBorrowerDao tJobBorrowerDao;
 
	public long addTPersonBorrower(TPersonBorrower tPersonBorrower,TJobBorrower tJobBorrower) {
		long result = tPersonBorrowerDao.insert(tPersonBorrower);
		if(result <= 0){
			throw new ZeroSQLException();
		}
		tJobBorrower.setPersonBorrowerId(tPersonBorrower.getId());
		result = tJobBorrowerDao.insert(tJobBorrower);
		if(result <= 0){
			throw new ZeroSQLException();
		}
		return result;
	}
 
	public long updateTPersonBorrower(TPersonBorrower tPersonBorrower,TJobBorrower tJobBorrower) {
		long result = tPersonBorrowerDao.update(tPersonBorrower);
		if(result <= 0){
			throw new ZeroSQLException();
		}
		TJobBorrower tJobBorrowerModel = tJobBorrowerDao.getTJobBorrowerByPersonId(tPersonBorrower.getId());
		tJobBorrower.setId(tJobBorrowerModel.getId());
		result = tJobBorrowerDao.update(tJobBorrower);
		if(result <= 0){
			throw new ZeroSQLException();
		}
		return result;
	}
 
	public TPersonBorrower getTPersonBorrower(TPersonBorrower tPersonBorrower) {
		return tPersonBorrowerDao.get(tPersonBorrower);
	}
 
	public PageList queryTPersonBorrowerPage(TPersonBorrower tPersonBorrower,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tPersonBorrowerDao.getAllBy(tPersonBorrower, options, pageBounds);
	}
	
	public int inserttPersonBorrower(TPersonBorrower tPersonBorrower){
		return tPersonBorrowerDao.insert(tPersonBorrower);
	}
 }