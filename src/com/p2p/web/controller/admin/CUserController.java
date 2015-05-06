package com.p2p.web.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.UserDto;
import com.p2p.model.CUser;
import com.p2p.model.TPerson;
import com.p2p.service.CUserService;
import com.p2p.service.TPersonService;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class CUserController extends BaseController{
	private static Log log = LogFactory.getLog(CUserController.class);

	@Autowired
	private CUserService cUserService;
	@Autowired
	private TPersonService tPersonService;
	
	@RequestMapping(value = "/queryCUserList")
	public String queryCUserList (UserDto cUser) {
		PageList pageList = cUserService.queryCUserPage(cUser, pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", cUser);
		return "admin/user/user-list";
	}

	
	@RequestMapping(value = "/queryCommCUserList")
	public String queryCommCUserList (UserDto cUser) {
		cUser.setIsOpen(1);		
		PageList pageList = cUserService.queryCUserPage(cUser, pageBounds);
		request.setAttribute("pageBean", pageList);
		request.setAttribute("page", pageList.getPaginator());
		request.setAttribute("model", cUser);
		return "admin/comm/comm-user-list";
	}
 

	/**
	 * 用户详情
	 * @param cUser
	 * @return
	 */
	@RequestMapping(value = "/queryCUserIndex")
	public String queryCUserIndex  (UserDto cUser) {
		UserDto cUserModel = cUserService.getCUser(cUser.getId());
		request.setAttribute("cUserModel", cUserModel);
		return "admin/user/user-detail";
	}
	/**
	 * 用户欲修改
	 * @param cUser
	 * @return
	 */
	@RequestMapping(value = "/updateCUserInfo")
	public String updateCUserInfo(UserDto cUser){
		UserDto cUserModel = cUserService.getCUser(cUser.getId());
		request.setAttribute("cUserModel", cUserModel);
		return "admin/user/user-update";
	}
	/**
	 * 用户修改
	 * @param userDto
	 * @return
	 */
	@RequestMapping(value = "/updateCUser")
	public String updateCUser(UserDto userDto){
		UserDto ud = cUserService.getCUser(userDto.getId());
		CUser cuser = new CUser();
		BeanUtils.copyProperties(ud, cuser);
		cuser.setIsEnable(userDto.getIsEnable());
		cuser.setSource(userDto.getSource());
		TPerson tperson = new TPerson();
		tperson = tPersonService.getPersonByUserId(userDto.getId());
		if(tperson!=null){
			tperson.setSex(userDto.getSex());
			long result =cUserService.updateUserAndPerson(cuser, tperson);
			if(result > 0){
				this.writeJson(new InfoMsg("保存成功", "y"));
			}else{
				this.writeJson(new InfoMsg("保存失败", "n"));
			}
			return null;	
		}else{
			long result = cUserService.updateCUser(cuser);
			if(result > 0){
				this.writeJson(new InfoMsg("保存成功", "y"));
			}else{
				this.writeJson(new InfoMsg("保存失败", "n"));
			}
			return null;	
		}
	}
 }