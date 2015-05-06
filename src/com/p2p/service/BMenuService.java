package com.p2p.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.BMenuDao;
import com.p2p.model.BMenu;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class BMenuService extends BaseService {
	private static Log log = LogFactory.getLog(BMenuService.class);
	@Autowired
	private BMenuDao bMenuDao;

	public long addBMenu(BMenu bMenu) {
		return bMenuDao.insert(bMenu);
	}

	public long updateBMenu(BMenu bMenu) {
		return bMenuDao.update(bMenu);
	}

	public BMenu getBMenu(BMenu bMenu) {
		return bMenuDao.get(bMenu);
	}

	public PageList queryBMenuPage(BMenu bMenu, PageBounds pageBounds) {

		List<BMenu> list = bMenuDao.queryBMenuPage(bMenu, pageBounds);
		PageList pageList = (PageList) list;

		return pageList;
	}
	
	public List<BMenu> queryBMenuList(BMenu bMenu,Map<String, SearchOperator> options, PageBounds pageBounds) {
		pageBounds.setLimit(Integer.MAX_VALUE);
		List<BMenu> list = bMenuDao.getAllBy(bMenu, options, pageBounds);
		
		return list;
	}
	
	public List<BMenu> queryRigthsByAdminId(Long adminId) {
		
		List<BMenu> list = bMenuDao.queryRigthsByAdminId(adminId);
		
		return list;
	}
}
