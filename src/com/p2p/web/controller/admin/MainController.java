package com.p2p.web.controller.admin;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.constans.SysConstans;
import com.p2p.dto.InfoMsg;
import com.p2p.model.BAdmin;
import com.p2p.model.BMenu;
import com.p2p.security.MD5;
import com.p2p.service.BAdminService;
import com.p2p.service.BMenuService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class MainController extends BaseController{
	private static Log log = LogFactory.getLog(MainController.class);
	@Autowired
	private BMenuService bMenuService;
	
	@Autowired
	private BAdminService bAdminService;
	
	@RequestMapping(value = "/toMain")
	public String toMain(){
		BAdmin bAdmin = this.getBAdmin();
		Long adminId = bAdmin.getId();
		List<BMenu> menuList = bMenuService.queryRigthsByAdminId(adminId);
		session.setAttribute(SysConstans.MENU_LIST, menuList);
		return "admin/frame/main";
	}
	
	@RequestMapping(value = "/loginIndex")
	public String loginIndex(){
		return "admin/frame/login";
	}
	
	@RequestMapping(value = "/adminLogin")
	public String adminLogin(BAdmin bAdmin){
		bAdmin.setPwd(MD5.encrypt(bAdmin.getPwd()+SysConstans.MD5_KEY));
		List<BAdmin> adminList =bAdminService.queryBAdminList(bAdmin);
		int result = 0;
		if(adminList != null && adminList.size() > 0) {
			session.setAttribute(SysConstans.ADMIN_SESSION, adminList.get(0));
			result = 1;
		}
		//ckfinder 权限
		session.setAttribute("CKFinder_UserRole", "admin"); 
		if (result > 0) {
			this.writeJson(new InfoMsg("登录成功", "y"));
		} else {
			this.writeJson(new InfoMsg("用户名或密码输入错误", "n"));
		}
		return null;
	}
	
	@RequestMapping(value = "/loginOut")
	public String loginOut(){
		session.removeAttribute(SysConstans.ADMIN_SESSION);
		return "admin/frame/login";
	}
	
}
