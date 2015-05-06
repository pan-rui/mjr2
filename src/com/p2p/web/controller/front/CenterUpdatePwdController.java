package com.p2p.web.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.constans.SysConstans;
import com.p2p.model.CUser;
import com.p2p.security.MD5;
import com.p2p.service.CUserService;
import com.p2p.web.controller.BaseController;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterUpdatePwdController extends BaseController{
	private static Log log=LogFactory.getLog(CenterUpdatePwdController.class);
	
	@Autowired
	private CUserService cups;
	
	@RequestMapping(value = "center/updatepwd")
	public String updatepwd(CUser cuser){
		String oldpwdc=cups.selectPwd(cuser);
		String oldpwdsr=MD5.encrypt(cuser.getPwd())+SysConstans.MD5_USER_KEY;
		String newpwd=MD5.encrypt(request.getParameter("newpwd"))+SysConstans.MD5_USER_KEY;
		
		if(oldpwdc.equals(oldpwdsr)){
			cuser.setPwd(newpwd);
			cups.updatepwd(cuser);
		}else{
			
		}
		return "";
	}
}
