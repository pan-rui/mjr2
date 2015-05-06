package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.TBorrowAttrDao;
import com.p2p.model.TBorrowAttr;
import com.p2p.mybatis.annotion.SearchOperator;


/** 借款附件**/
@Service
public class TBorrowAttrService extends BaseService {
	private static Log log = LogFactory.getLog(TBorrowAttrService.class);

	@Autowired
	private TBorrowAttrDao tBorrowAttrDao;
 
	public long addTBorrowAttr(TBorrowAttr tBorrowAttr) {
		return tBorrowAttrDao.insert(tBorrowAttr);
	}
 
	public long updateTBorrowAttr(TBorrowAttr tBorrowAttr) {
		return tBorrowAttrDao.update(tBorrowAttr);
	}
 
	public TBorrowAttr getTBorrowAttr(TBorrowAttr tBorrowAttr) {
		return tBorrowAttrDao.get(tBorrowAttr);
	}
 
	public PageList queryTBorrowAttrPage(TBorrowAttr tBorrowAttr,Map<String, SearchOperator> options, PageBounds pageBounds) {
		return (PageList)tBorrowAttrDao.getAllBy(tBorrowAttr, options, pageBounds);
	}

	/**
	 * 根据借款ID获取项目资料
	 * @param borrowId
	 * @return
	 */
	public List<TBorrowAttr> queryTBorrowAttrListByBorrowId(Long borrowId) {
		return tBorrowAttrDao.queryTBorrowAttrListByBorrowId(borrowId);
	}
	
	/**
	 * 删除借款资料图片
	 * @param id
	 * @return
	 */
	public long deleteTBorrowAttr(Long id){
		return tBorrowAttrDao.deleteTBorrowAttr(id);
	}
 }