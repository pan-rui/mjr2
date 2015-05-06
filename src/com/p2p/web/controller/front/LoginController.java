package com.p2p.web.controller.front;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baofoo.p2p.dto.receive.ResultDto;
import com.baofoo.p2p.dto.request.BindBaofooPage;
import com.p2p.constans.BaofooConstans;
import com.p2p.constans.SysConstans;
import com.p2p.dto.InfoMsg;
import com.p2p.dto.UserDto;
import com.p2p.model.CUser;
import com.p2p.model.TPerson;
import com.p2p.model.TThridAccount;
import com.p2p.security.MD5;
import com.p2p.service.CUserService;
import com.p2p.service.TPersonService;
import com.p2p.service.TThridAccountService;
import com.p2p.util.IdcardValidator;
import com.p2p.util.ServletUtil;
import com.p2p.util.StringUtil;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class LoginController extends BaseController {
	private static Log log = LogFactory.getLog(LoginController.class);
	@Autowired
	private CUserService cUserService;
	@Autowired
	private TPersonService tPersonService;

	@Autowired
	private TThridAccountService tThridAccountService;
	/**
	 * 用户登录初始化
	 * @return
	 */
	@RequestMapping(value = "/loginIndex")
	public String loginIndex() {
		CUser cUser = this.getCUser();
		if (cUser!=null)
			try {
				response.sendRedirect(ServletUtil.getServerPath(request)+"/center/selectUserIndex.html");
			} catch (IOException e) {
				log.error("用户已登录，请求登陆页异常", e);
			}
		return "front/login/login";
	}
	/**
	 * AJAX验证登陆验证码
	 * @return
	 */
	@RequestMapping(value = "/chackLoginCode")
	public String chackLoginCode(){
		String sysCode = (String)session.getAttribute("userlogin_code");
		String rCode = request.getParameter("param");
		if(StringUtils.isBlank(sysCode)||StringUtils.isBlank(rCode)){
			this.writeJson(new InfoMsg("验证码不能为空", "n"));
			return null;
		}
		if(rCode.equals(sysCode)){
			this.writeJson(new InfoMsg("验证码输入有误，请重新获取", "n"));
			return null;
		}
		this.writeJson(new InfoMsg("ok", "y"));
		return null;
	}

	@RequestMapping(value = "/login")
	public String login(UserDto ud) {
		CUser cUser = new CUser();
		String userUrl = (String)session.getAttribute(SysConstans.USER_LOGIN_URL);
		session.removeAttribute(SysConstans.USER_LOGIN_URL);
		if(this.getCUser()!=null){//用户已登陆
			return "forward:selectUserIndex";
		}
		String sysCode = (String)session.getAttribute("userlogin_code");
		//验证验证码
		if(StringUtils.isBlank(ud.getLoginCode())){
			this.writeJson(new InfoMsg("验证码不能为空", "n"));
			return null;
		}
		if(!ud.getLoginCode().equals(sysCode)){
			this.writeJson(new InfoMsg("验证码输入错误", "n"));
			return null;
		}
		//用户名为空验证
		if(StringUtils.isBlank(ud.getUserName())){
			this.writeJson(new InfoMsg("用户名不能为空", "n"));
			return null;
		}
		//密码为空验证
		if(StringUtils.isBlank(ud.getPwd())){
			this.writeJson(new InfoMsg("密码不能为空", "n"));
			return null;
		}
		//用户名长度验证
		if(4>ud.getUserName().length() || 16<ud.getUserName().length()){
			this.writeJson(new InfoMsg("用户名输入长度有误", "n"));
			return null;
		}
		if(6>ud.getPwd().length() || 16<ud.getPwd().length()){
			this.writeJson(new InfoMsg("密码输入长度有误", "n"));
			return null;
		}
		cUser.setUserName(ud.getUserName());
		cUser.setPwd(ud.getPwd());
		cUser.setPwd(MD5.encrypt(ud.getPwd()+SysConstans.MD5_USER_KEY));
		cUser = cUserService.queryLoginUser(cUser);
		//验证该账号密码是否匹配到用户
		if(cUser==null){
			this.writeJson(new InfoMsg("登陆失败，请检查用户名和密码是否正确", "n"));
			return null;
		}
		//验证用户是否被禁用
		if(cUser.getIsEnable()==1){
			this.writeJson(new InfoMsg("账号已经被禁用，请联系客服", "n"));
			return null;
		}
		/*全部验证通过，更新、记录登陆*/
		cUser.setLastLoginTime(new Date());
		cUser.setLastLoginIp(ServletUtil.getIpAddress(request));

		Long result = cUserService.updateLoginCUser(cUser);
		if (result > 0) {
			session.setAttribute(SysConstans.USER_SESSION, cUser);
			this.writeJson(new InfoMsg(userUrl, "y"));
			return null;
		} else {
			this.writeJson(new InfoMsg("登陆失败，请稍后重试", "n"));
			return null;
		}
	}

	/**
	 * 用户登出
	 * @return
	 */
	@RequestMapping(value = "/loginOut")
	public String loginOut(){
		session.removeAttribute(SysConstans.USER_SESSION);
		return "forward:index";
	}
	
	@RequestMapping(value = "/showWebService")
	public String showWebService(){

		return "front/login/web-serivce";
	}

	/*注册方法*/
	@RequestMapping(value = "/regIndex")
	public String regIndex() {
		String tel=request.getParameter("tjr");
		request.setAttribute("tel", tel);
		CUser cUser = this.getCUser();
		if (cUser!=null)
			try {
				response.sendRedirect(ServletUtil.getServerPath(request)+"/center/selectUserIndex.html");
			} catch (IOException e) {
				log.error("用户已登录，请求注册页异常", e);
			}
		return "front/login/reg";
	}
	/**
	 * 验证手机号码存在
	 * @param cUser
	 * @return
	 */
	@RequestMapping(value = "/verificationNewUserPhone")
	public String verificationNewUserPhone(CUser cUser){
		cUser.setCellPhone(request.getParameter("param"));
		CUser cu= cUserService.verificationNewUserPhone(cUser);
		if(cu==null){
			this.writeJson(new InfoMsg("可用的手机号码", "y"));
			return null;
		}else{
			this.writeJson(new InfoMsg("手机号码已存在", "n"));
			return null;
		}
	}


	@RequestMapping(value = "/veriUserName")
	public String veriUserName(){
		String userName = request.getParameter("param");
		boolean isExist= cUserService.isExistUserName(userName);
		if(isExist){
			this.writeJson(new InfoMsg("用户名已存在", "n"));
			return null;
		}else{
			this.writeJson(new InfoMsg("用户名可用", "y"));
			return null;
		}
	}
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/reg")
	public String addUser(UserDto user){
		CUser cUser = new CUser();
		Integer userc = (Integer)session.getAttribute(SysConstans.REG_USER_SCORE);
		String pwd = user.getPassword(),repwd = user.getPassword2();
		cUser.setCellPhone(user.getCellPhone());
		String code = user.getRegCode();
		String scode = (String)session.getAttribute(SysConstans.REG_CODE);
		if(code ==null ){
			this.writeJson(new InfoMsg("验证码输入有误", "n"));
			return null;
		}
		if(!code.equals(scode)){
			this.writeJson(new InfoMsg("验证码输入有误", "n"));
			return null;
		}
		if(StringUtils.isBlank(user.getUserName())){
			this.writeJson(new InfoMsg("用户名不能为空", "n"));
			return null;
		}
		if(StringUtil.chackUserName(user.getUserName())){
			this.writeJson(new InfoMsg("禁止使用该用户名", "n"));
			return null;
		}
		boolean isExist= cUserService.isExistUserName(user.getUserName());
		if(isExist){
			this.writeJson(new InfoMsg("用户名已存在", "n"));
			return null;
		}
		if (cUserService.verificationNewUserPhone(cUser) != null){
			this.writeJson(new InfoMsg("该手机号码已存在", "n"));
			return null;
		}
		if(pwd==null || pwd.length()<6 || pwd.length()>16){
			this.writeJson(new InfoMsg("密码输入有误", "n"));
			return null;
		}
		if(!pwd.equals(repwd)){
			this.writeJson(new InfoMsg("请确认两次密码输入一致", "n"));
			return null;
		}
		cUser.setPwd(MD5.encrypt(pwd+SysConstans.MD5_USER_KEY));
		cUser.setRegIp(ServletUtil.getIpAddress(request));
		cUser.setUserName(user.getUserName());
		cUser.setCreateTime(new Date());
		if(userc!=null){
			cUser.setSource(userc);
		}else{
			cUser.setSource(0);
		}
		cUser.setReferee(user.getRefereeUser());
		Long result =  cUserService.addCUser(cUser);
		if (result > 0) {
			
			session.setAttribute(SysConstans.USER_SESSION, cUser);
			this.writeJson(new InfoMsg("注册成功", "y"));
			return null;
		} 

		this.writeJson(new InfoMsg("注册失败，请稍后重试", "n"));
		return null;
	}
	/**
	 * 用户注册，进入实名认证
	 * @return
	 */
	@RequestMapping(value = "/regPersonInfo")
	public String regPersonInfo(){
		CUser cUser = this.getCUser();
		if (cUser==null)
			try {
				response.sendRedirect(ServletUtil.getServerPath(request)+"/loginIndex.html");
			} catch (IOException e) {
				log.error("没有用户登录，实名", e);
			}
		TPerson person = tPersonService.getPersonByUserId(cUser.getId());
		if(person!=null){
			if(1==person.getIsAuth()){
				try {
					response.sendRedirect(ServletUtil.getServerPath(request)+"/center/selectUserIndex.html");
				} catch (IOException e) {
					log.error("用户已实名，访问实名认证页面异常", e);
				}
			}
		}
		request.setAttribute("isPerson", person);
		return "front/login/reg_person";
	}
	/**
	 * 用户注册，实名认证
	 * @param tPerson
	 * @return
	 */
	@RequestMapping(value = "/regPerson")
	public String regPerson(TPerson tPerson){
		CUser cu = this.getCUser();
		TPerson person = tPersonService.getPersonByUserId(cu.getId());
		if(person!=null && person.getIsAuth()==1){
			request.setAttribute("msg", new InfoMsg("用户已经实名成功", "n"));
			return "front/login/reg_person";
		}
		IdcardValidator idc = new IdcardValidator();
		if(tPerson == null){
			request.setAttribute("msg", new InfoMsg("请填写正确信息之后重试", "n"));
			return "front/login/reg_person";
		}
		if(StringUtils.isBlank(tPerson.getRealName())){
			request.setAttribute("msg", new InfoMsg("姓名不能为空", "n"));
			return "front/login/reg_person";
		}
		if(StringUtils.isBlank(tPerson.getCardNo())){
			request.setAttribute("msg", new InfoMsg("身份证号码不能为空", "n"));
			return "front/login/reg_person";
		}
		if(!idc.isValidatedAllIdcard(tPerson.getCardNo())){
			request.setAttribute("msg", new InfoMsg("身份证号码输入有误", "n"));
			return "front/login/reg_person";
		}
		tPerson.setCreateTime(new Date());
		tPerson.setUserId(cu.getId());
		InfoMsg msg = tPersonService.addOrUpdatePerson(tPerson);
		if("n".equals(msg.getStatus())){
			request.setAttribute("msg", msg);
			return "front/login/reg_person";
		}
		TThridAccount tThridAccount = tThridAccountService.getThridAccountByUserId(cu.getId());
		BindBaofooPage bf = new BindBaofooPage();
		bf.setBf_account(cu.getCellPhone());
		bf.setName(tPerson.getRealName());
		bf.setId_card(tPerson.getCardNo());
		bf.setUser_id(tThridAccount.getThirdUserId());
		bf.setPage_url(BaofooConstans.regPageUrl);
		bf.setReturn_url(BaofooConstans.regReturnUrl);
		try {
			BaofooConstans.REQUEST_SERVICE.page_BindBaoofooAccount(bf, response);
		} catch (Exception e) {
			log.error("调用宝付出错", e);
		}
		return null;
	}
	@RequestMapping(value = "/regPageUrl")
	public String regPageUrl(){
		//regReturnUrl();
		return "front/login/reg_result";
	}
	@RequestMapping(value = "/regReturnUrl")
	public String regReturnUrl(){
		String result = request.getParameter("result");
		String sign = request.getParameter("sign");
		try {
			ResultDto resultDto = BaofooConstans.RECEIVE_SERVICE.page_BindBaoofooAccount(result, sign);
			log.info("绑定宝付回调===userId:"+resultDto.getUser_id()+"，Code:"+resultDto.getCode()+"，Msg"+resultDto.getMsg());
			if(!"CSD000".equals(resultDto.getCode())){
				tPersonService.updateYesOrNo(2, resultDto);
				/*InfoMsg msg = new InfoMsg(resultDto.getMsg(), "n");
				request.setAttribute("msg", msg);
				return "front/login/reg_result";*/
			}else{
				tPersonService.updateYesOrNo(1, resultDto);
				/*InfoMsg msg = new InfoMsg("恭喜你，开户成功。", "y");
				request.setAttribute("msg", msg);
				return "front/login/reg_result";*/
			}
		} catch (Exception e) {
			log.error("绑定宝付账号，回调异常", e);
		}
		return null;
	}
	/**
	 * 忘记密码-初始化
	 * @return
	 */
	@RequestMapping(value = "/forGetPassInfo")
	public String forGetPassInfo(){
		return "front/login/forgetpassword";
	}
	/**
	 * 忘记密码-验证手机号码
	 * @return 
	 */
	@RequestMapping(value="/forGetPassPhone")
	public String forGetPassPhone(){
		String rPhone =request.getParameter("param");
		CUser user = new CUser();
		user.setCellPhone(rPhone);
		CUser cu= cUserService.verificationNewUserPhone(user);
		if(StringUtils.isBlank(rPhone)){
			this.writeJson(new InfoMsg("手机号码输入有误", "n"));
			return null; 
		}
		if(cu==null){
			this.writeJson(new InfoMsg("输入手机号码有误", "n"));
			return null;
		}
		session.setAttribute(SysConstans.FORGET_PASS_WORD_ID, cu.getId());
		this.writeJson(new InfoMsg("原号码输入正确", "y"));
		return null;
	}
	/**
	 * 验证手机号码
	 * @return
	 */
	@RequestMapping(value="/reFormforGetPassCode")
	public String reFormforGetPassCode(){
		String secode = (String)session.getAttribute(SysConstans.FORGET_PASS_CODE);
		String hlcode = request.getParameter("telCode");
		if(StringUtils.isBlank(hlcode)){
			this.writeJson(new InfoMsg("验证码输入有误", "n"));
			return null;
		}
		if(!hlcode.equals(secode)){
			this.writeJson(new InfoMsg("验证码验证失败，请稍后重试", "n"));
			return null;
		}
		this.writeJson(new InfoMsg("ok", "y"));
		return null;
	}

	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value="/updateforGetPass")
	public String updateforGetPass(){
		String newPass = request.getParameter("forGetpassword");
		Long uid = (Long)session.getAttribute(SysConstans.FORGET_PASS_WORD_ID);
		if(StringUtils.isBlank(newPass)||uid==null){
			this.writeJson(new InfoMsg("密码输入有误", "n"));
			return null;
		}
		CUser cuser=new CUser();
		cuser.setId(uid);
		cuser.setPwd(MD5.encrypt(newPass+SysConstans.MD5_USER_KEY));
		int i=-1;
		try {
			i=cUserService.updatepwd(cuser);
		} catch (Exception e) {
			log.error("忘记密码异常：", e);
		}
		if(i<=0){
			this.writeJson(new InfoMsg("修改密码失败", "n"));
			return null;
		}
		this.writeJson(new InfoMsg("修改成功", "y"));
		return null;
	}

}
