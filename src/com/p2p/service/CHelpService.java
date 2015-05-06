package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.CHelpDao;
import com.p2p.model.CHelp;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class CHelpService extends BaseService {
	private static Log log = LogFactory.getLog(CHelpService.class);
	@Autowired
	private CHelpDao cHelpDao;
	
	public long addCHelp(CHelp cHelp){
		return cHelpDao.insert(cHelp);
	}
	
	public long updateCHelp(CHelp cHelp){
		return cHelpDao.update(cHelp);
	}
	
	public CHelp getCHelp(CHelp cHelp){
		return cHelpDao.get(cHelp);
	}

	public PageList queryCHelpPage(CHelp cHelp,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		
		List<CHelp> list =cHelpDao.getAllBy(cHelp, options, pageBounds);
		PageList  pageList  =(PageList )list;
		
		return pageList;
	}

	/**
	 * 根据帮助中心类型，或许类型下所有帮助列表
	 * @param helpTypeId
	 * @param pageBounds 
	 * @return
	 */
	public PageList queryHelpByType(long helpTypeId, PageBounds pageBounds){
		return cHelpDao.queryHelpByType(helpTypeId,pageBounds);
	};
	
	
}
