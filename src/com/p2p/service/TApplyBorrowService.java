package com.p2p.service;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TApplyBorrowDao;
import com.p2p.model.TApplyBorrow;


/** 借款申请表**/
@Service
public class TApplyBorrowService extends BaseService {


	@Autowired
	private TApplyBorrowDao tApplyBorrowDao;
 
	public long addTApplyBorrow(TApplyBorrow tApplyBorrow) {
		tApplyBorrow.setBorrowerName(StringEscapeUtils.escapeSql(tApplyBorrow.getBorrowerName()));
		tApplyBorrow.setBorrowUse(StringEscapeUtils.escapeSql(tApplyBorrow.getBorrowUse()));
		return tApplyBorrowDao.insert(tApplyBorrow);
	}
 
	public long updateTApplyBorrow(TApplyBorrow tApplyBorrow) {
		return tApplyBorrowDao.update(tApplyBorrow);
	}
 
	public TApplyBorrow getTApplyBorrow(TApplyBorrow tApplyBorrow) {
		return tApplyBorrowDao.get(tApplyBorrow);
	}
 
	public PageList queryTApplyBorrowPage(TApplyBorrow tApplyBorrow,PageBounds pageBounds) {
		return (PageList)tApplyBorrowDao.queryTApplyBorrowPage(tApplyBorrow, pageBounds);
	}
 }