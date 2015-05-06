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
import com.p2p.mybatis.annotion.SearchOperator;
import com.p2p.service.BMenuService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value="/admin")
public class BMenuController extends BaseController{
	private static Log log = LogFactory.getLog(BMenuController.class);
	@Autowired
	private BMenuService bMenuService;
	
	@RequestMapping(value = "/queryBMenuList")
	public String queryBMenuList(BMenu bMenu){
	
		PageList pageList =bMenuService.queryBMenuPage(bMenu, pageBounds);
		
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		setParentMenuList();
		request.setAttribute("model", bMenu);
		return "admin/rights/menu-list";
	}
	
	@RequestMapping(value = "/addBMenuIndex")
	public String addBMenuIndex(){
		setParentMenuList();
		return "admin/rights/menu-add";
	}
	
	private void setParentMenuList(){
		BMenu bMenu = new BMenu();
		Map<String, SearchOperator>  options = new HashMap<String, SearchOperator>();
		options.put("parentId", SearchOperator.EQ);
		bMenu.setMenuLevel(1);
		bMenu.setParentId(-1L);
		List<BMenu> list = bMenuService.queryBMenuList(bMenu,options, pageBounds);
		request.setAttribute("parentMenuList", list);
	}
	
	@RequestMapping(value = "/addBMenu")
	public String addBMenu(BMenu bMenu){
		
		long result = bMenuService.addBMenu(bMenu);
		if(result > 0){
			this.writeJson(new InfoMsg("保存成功", "y"));
		}else{
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
	/**
	 * 管理菜单欲修改
	 * @param bMenu
	 * @return
	 */
	@RequestMapping(value = "/updateBMenuIndex")
	public String updateBMenuIndex(BMenu bMenu){
		setParentMenuList();
		BMenu bMenuModel = bMenuService.getBMenu(bMenu);
		request.setAttribute("bMenuModel", bMenuModel);
		return "admin/rights/menu-update";
	}
	/**
	 * 管理菜单修改
	 * @param bMenu
	 * @return
	 */
	@RequestMapping(value = "/updateBMenu")
	public String updateBMenu(BMenu bMenu){
		long result = bMenuService.updateBMenu(bMenu);
		if(result > 0){
			this.writeJson(new InfoMsg("保存成功", "y"));
		}else{
			this.writeJson(new InfoMsg("保存失败", "n"));
		}
		return null;
	}
	
	@RequestMapping(value = "/queryBMenuIndex")
	public String queryBMenuIndex(BMenu bMenu){
		setParentMenuList();
		BMenu bMenuModel = bMenuService.getBMenu(bMenu);
		request.setAttribute("bMenuModel", bMenuModel);
		return "admin/rights/menu-detail";
	}
	
	
}
