package com.p2p.web.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.model.BMenu;
import com.p2p.model.BRole;
import com.p2p.model.BRoleMenu;
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.BMenuService;
import com.p2p.service.BRoleService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class BRoleController extends BaseController {
	private static Log log = LogFactory.getLog(BRoleController.class);
	@Autowired
	private BRoleService bRoleService;

	@Autowired
	private BMenuService bMenuService;

	@RequestMapping(value = "/queryBRoleList")
	public String queryBRoleList(BRole bRole) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		options.put("roleName", SearchOperator.LIKE);
		PageList pageList = bRoleService.queryBRolePage(bRole, options,
				pageBounds);

		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());

		request.setAttribute("model", bRole);
		return "admin/rights/role-list";
	}

	@RequestMapping(value = "/addBRoleIndex")
	public String addBRoleIndex() {
		setMenuList();
		return "admin/rights/role-add";
	}

	private void setMenuList() {
		BMenu bMenu = new BMenu();
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();

		List<BMenu> list = bMenuService.queryBMenuList(bMenu, options,
				pageBounds);
		request.setAttribute("menuList", list);
	}

	@RequestMapping(value = "/addBRole")
	public String addBRole(BRole bRole, Long[] menuId) {

		long result = bRoleService.addBRole(bRole, menuId);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/updateBRoleIndex")
	public String updateBRoleIndex(BRole bRole) {
		setMenuList();
		BRole bRoleModel = bRoleService.getBRole(bRole);
		request.setAttribute("bRoleModel", bRoleModel);
		return "admin/rights/role-update";
	}

	@RequestMapping(value = "/queryBRoleMenuById")
	public String queryBRoleMenuById(BRoleMenu bRoleMenu) {
		Map<String, SearchOperator> options = new HashMap<String, SearchOperator>();
		options.put("roleId", SearchOperator.EQ);
		List<BRoleMenu> bRoleMenuList = bRoleService.queryBRoleMenuList(
				bRoleMenu, options, pageBounds);
		this.writeJson(bRoleMenuList);
		return null;
	}

	@RequestMapping(value = "/updateBRole")
	public String updateBRole(BRole bRole, Long[] menuId) {

		long result = bRoleService.updateBRole(bRole, menuId);
		if (result > 0) {
			this.writeJson(new InfoMsg("保存成功", "y"));
		} else {
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}

	@RequestMapping(value = "/queryBRoleIndex")
	public String queryBRoleIndex(BRole bRole) {
		BRole bRoleModel = bRoleService.getBRole(bRole);
		request.setAttribute("bRoleModel", bRoleModel);
		return "admin/rights/role-detail";
	}
}
