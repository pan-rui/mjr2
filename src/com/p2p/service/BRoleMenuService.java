package com.p2p.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p2p.dao.BMenuDao;
import com.p2p.dao.BRoleMenuDao;
import com.p2p.model.BRoleMenu;

@Service
public class BRoleMenuService extends BaseService {
	private static Log log = LogFactory.getLog(BRoleMenuService.class);
	@Autowired
	private BRoleMenuDao bRoleMenuDao;
	
	@Autowired
	private BMenuDao bMenuDao;
	
	public long addBRoleMenu(BRoleMenu bRoleMenu){
		return bRoleMenuDao.insert(bRoleMenu);
	}
	
	public long updateBRoleMenu(BRoleMenu bRoleMenu){
		return bRoleMenuDao.update(bRoleMenu);
	}
	
	public BRoleMenu getBRoleMenu(BRoleMenu bRoleMenu){
		return bRoleMenuDao.get(bRoleMenu);
	}
	
	public BRoleMenu queryBRoleMenu(BRoleMenu bRoleMenu){
		return bRoleMenuDao.get(bRoleMenu);
	}
	
	
}
