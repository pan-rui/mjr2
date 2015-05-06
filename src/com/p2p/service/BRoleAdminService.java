package com.p2p.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.p2p.dao.BMenuDao;
import com.p2p.dao.BRoleAdminDao;
import com.p2p.model.BRoleAdmin;
import com.p2p.mybatis.annotion.SearchOperator;

@Service
public class BRoleAdminService extends BaseService {
	private static Log log = LogFactory.getLog(BRoleAdminService.class);
	@Autowired
	private BRoleAdminDao bRoleAdminDao;
	
	@Autowired
	private BMenuDao bMenuDao;
	
	public long addBRoleAdmin(BRoleAdmin bRoleAdmin,Long[] roleIds){
		BRoleAdmin tempBRoleAdmin = new BRoleAdmin();
		long adminId = bRoleAdmin.getAdminId();
		tempBRoleAdmin.setAdminId(adminId);
		Date nowDate = new Date();
		long count = bRoleAdminDao.delete(tempBRoleAdmin);
		if(roleIds != null){
			for(Long roleId :roleIds){
				BRoleAdmin bRoleAdminModel = new BRoleAdmin();
				bRoleAdminModel.setCreateTime(nowDate);
				bRoleAdminModel.setAdminId(adminId);
				bRoleAdminModel.setRoleId(roleId);
				count = bRoleAdminDao.insert(bRoleAdminModel);
			}
			
		}
		return count;
	}
	
	public long updateBRoleAdmin(BRoleAdmin bRoleAdmin){
		return bRoleAdminDao.update(bRoleAdmin);
	}
	
	public BRoleAdmin getBRoleAdmin(BRoleAdmin bRoleAdmin){
		return bRoleAdminDao.get(bRoleAdmin);
	}
	
	public BRoleAdmin queryBRoleAdmin(BRoleAdmin bRoleAdmin){
		return bRoleAdminDao.get(bRoleAdmin);
	}
	public List<BRoleAdmin> queryBRoleAdminList(BRoleAdmin bRoleAdmin,
			Map<String, SearchOperator> options, PageBounds pageBounds) {
		pageBounds.setLimit(Integer.MAX_VALUE);
		List<BRoleAdmin> list = bRoleAdminDao.getAllBy(bRoleAdmin, options, pageBounds);

		return list;
	}
	
}
