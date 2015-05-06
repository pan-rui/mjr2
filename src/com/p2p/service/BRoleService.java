package com.p2p.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dao.BRoleDao;
import com.p2p.dao.BRoleMenuDao;
import com.p2p.model.BRole;
import com.p2p.model.BRoleMenu;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class BRoleService extends BaseService {
	private static Log log = LogFactory.getLog(BRoleService.class);
	@Autowired
	private BRoleDao bRoleDao;

	@Autowired
	private BRoleMenuDao bRoleMenuDao;

	public long addBRole(BRole bRole, Long[] menuId) {
		long count = bRoleDao.insert(bRole);
		if (count <= 0) {

		}
		Date nowDate = new Date();
		if (menuId != null) {
			for (Long mId : menuId) {
				BRoleMenu bRoleMenu = new BRoleMenu();
				bRoleMenu.setMenuId(mId);
				bRoleMenu.setRoleId(bRole.getId());
				bRoleMenu.setCreateTime(nowDate);
				bRoleMenuDao.insert(bRoleMenu);
			}
		}
		return count;
	}

	public long updateBRole(BRole bRole,Long[] menuId) {
		long count = bRoleDao.update(bRole);
		BRoleMenu tempBRoleMenu = new BRoleMenu();
		tempBRoleMenu.setRoleId(bRole.getId());
		bRoleMenuDao.delete(tempBRoleMenu);
		Date nowDate = new Date();
		if (menuId != null) {
			for (Long mId : menuId) {
				BRoleMenu bRoleMenu = new BRoleMenu();
				bRoleMenu.setMenuId(mId);
				bRoleMenu.setRoleId(bRole.getId());
				bRoleMenu.setCreateTime(nowDate);
				bRoleMenuDao.insert(bRoleMenu);
			}
		}
		return count;
	}

	public BRole getBRole(BRole bRole) {
		return bRoleDao.get(bRole);
	}

	public PageList queryBRolePage(BRole bRole,
			Map<String, SearchOperator> options, PageBounds pageBounds) {

		List<BRole> list = bRoleDao.getAllBy(bRole, options, pageBounds);
		PageList pageList = (PageList) list;

		return pageList;
	}

	public List<BRole> queryBRoleList(BRole bRole,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		pageBounds.setLimit(Integer.MAX_VALUE);
		List<BRole> list = bRoleDao.getAllBy(bRole, options, pageBounds);

		return list;
	}
	
	public List<BRoleMenu> queryBRoleMenuList(BRoleMenu bRoleMenu,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		pageBounds.setLimit(Integer.MAX_VALUE);
		List<BRoleMenu> list = bRoleMenuDao.getAllBy(bRoleMenu, options, pageBounds);

		return list;
	}

}
