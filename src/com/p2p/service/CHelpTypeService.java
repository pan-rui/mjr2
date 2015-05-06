package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CHelpTypeDao;
import com.p2p.model.CHelpType;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class CHelpTypeService extends BaseService {
	private static Log log = LogFactory.getLog(CHelpTypeService.class);
	@Autowired
	private CHelpTypeDao cHelpTypeDao;
	
	public long addCHelpType(CHelpType cHelpType){
		return cHelpTypeDao.insert(cHelpType);
	}
	
	public long updateCHelpType(CHelpType cHelpType){
		return cHelpTypeDao.update(cHelpType);
	}
	
	public CHelpType getCHelpType(CHelpType cHelpType){
		return cHelpTypeDao.get(cHelpType);
	}

	public PageList queryCHelpTypePage(CHelpType cHelpType,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		
		List<CHelpType> list =cHelpTypeDao.getAllBy(cHelpType, options, pageBounds);
		PageList  pageList  =(PageList )list;
		
		return pageList;
	}

	public List<CHelpType> queryCHelpTypeList(CHelpType cHelpType) {
		return cHelpTypeDao.getAllByCondition( cHelpType);
	}
	/**
	 * 顺序获取所有帮助类型
	 * @return
	 */
	public List<CHelpType> queryAllType(){
		return cHelpTypeDao.queryAllType();
	};
	
	
	
}
