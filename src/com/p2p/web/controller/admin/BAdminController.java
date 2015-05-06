package com.p2p.web.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.constans.SysConstans;
import com.p2p.dto.InfoMsg;
import com.p2p.model.BAdmin;
import com.p2p.model.BRole;
import com.p2p.model.BRoleAdmin;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.security.MD5;
import com.p2p.service.BAdminService;
import com.p2p.service.BRoleAdminService;
import com.p2p.service.BRoleService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class BAdminController extends BaseController {
	private static Log log = LogFactory.getLog(BAdminController.class);
	@Autowired
	private BAdminService bAdminService;
	@Autowired
	private BRoleAdminService bRoleAdminService;
	
	@Autowired
	private BRoleService bRoleService;
	

	
	@RequestMapping(value = "/queryBAdminList")
	public String queryBAdminList(BAdmin bAdmin) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();

		options.put("adminName", SearchOperator.LIKE);
		options.put("isEnable", SearchOperator.EQ);
		PageList pageList = bAdminService.queryBAdminPage(bAdmin, options,
				pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());

		request.setAttribute("model", bAdmin);
		return "admin/rights/admin-list";
	}

	@RequestMapping(value = "/addBAdminIndex")
	public String addBAdminIndex() {
		return "admin/rights/admin-add";
	}

	@RequestMapping(value = "/addBAdmin")
	public String addBAdmin(BAdmin bAdmin) {
		bAdmin.setCreateTime(new Date());
		bAdmin.setPwd(MD5.encrypt(bAdmin.getPwd() + SysConstans.MD5_KEY));
		long result = bAdminService.addBAdmin(bAdmin);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/updateBAdminIndex")
	public String updateBAdminIndex(BAdmin bAdmin) {
		BAdmin bAdminModel = bAdminService.getBAdmin(bAdmin);
		request.setAttribute("bAdminModel", bAdminModel);
		return "admin/rights/admin-update";
	}

	@RequestMapping(value = "/updateBAdmin")
	public String updateBAdmin(BAdmin bAdmin) {

		if (StringUtils.isNotBlank(bAdmin.getPwd())) {
			bAdmin.setPwd(MD5.encrypt(bAdmin.getPwd() + SysConstans.MD5_KEY));
		}

		long result = bAdminService.updateBAdmin(bAdmin);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
	
	@RequestMapping(value = "/updateBAdminPwdIndex")
	public String updateBAdminPwdIndex() {
		return "admin/rights/admin-pwd-update";
	}

	@RequestMapping(value = "/updateBAdminPwd")
	public String updateBAdminPwd(BAdmin bAdmin) {
		bAdmin.setId(this.getBAdmin().getId());
		if (StringUtils.isNotBlank(bAdmin.getPwd())) {
			bAdmin.setPwd(MD5.encrypt(bAdmin.getPwd() + SysConstans.MD5_KEY));
		}

		long result = bAdminService.updateBAdmin(bAdmin);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/queryBAdminIndex")
	public String queryBAdminIndex(BAdmin bAdmin) {
		BAdmin bAdminModel = bAdminService.getBAdmin(bAdmin);
		request.setAttribute("bAdminModel", bAdminModel);
		return "admin/rights/admin-detail";
	}

	@RequestMapping(value = "/updateBRoleAdminIndex")
	public String updateBRoleAdminIndex(BAdmin bAdmin) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		BRole bRole = new BRole();
		List<BRole> roleList = bRoleService.queryBRoleList(bRole, options, pageBounds);
		request.setAttribute("roleList", roleList);
		request.setAttribute("bAdminModel", bAdmin);
		return "admin/rights/role-admin-update";
	}
	
	@RequestMapping(value = "/queryBRoleAdminByAdminId")
	public String queryBRoleAdminByAdminId(BRoleAdmin bRoleAdmin) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		options.put("adminId", SearchOperator.EQ);
		List<BRoleAdmin> roleAdminList = bRoleAdminService.queryBRoleAdminList(bRoleAdmin, options, pageBounds);
		this.writeJson(roleAdminList);
		return null;
	}

	@RequestMapping(value = "/updateBRoleAdmin")
	public String updateBRoleAdmin(BRoleAdmin bRoleAdmin, Long[] roleIds) {

		long result = bRoleAdminService.addBRoleAdmin(bRoleAdmin, roleIds);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}

		return null;
	}
}
