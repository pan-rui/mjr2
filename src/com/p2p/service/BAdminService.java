package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.BAdminDao;
import com.p2p.model.BAdmin;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class BAdminService extends BaseService {
	private static Log log = LogFactory.getLog(BAdminService.class);
	@Autowired
	private BAdminDao bAdminDao;
	
	public long addBAdmin(BAdmin bAdmin){
		return bAdminDao.insert(bAdmin);
	}
	
	public long updateBAdmin(BAdmin bAdmin){
		return bAdminDao.update(bAdmin);
	}
	
	public BAdmin getBAdmin(BAdmin bAdmin){
		return bAdminDao.get(bAdmin);
	}

	
	
	public PageList queryBAdminPage(BAdmin bAdmin,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		
		List<BAdmin> list =bAdminDao.getAllBy(bAdmin, options, pageBounds);
		PageList  pageList  =(PageList )list;
		
		return pageList;
	}

	public List<BAdmin> queryBAdminList(BAdmin bAdmin) {
		return bAdminDao.getAllByCondition(bAdmin);
		
	}
	
	
}
