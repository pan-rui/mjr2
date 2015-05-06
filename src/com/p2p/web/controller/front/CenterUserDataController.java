package com.p2p.web.controller.front;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baofoo.p2p.dto.receive.ResultDto;
import com.p2p.constans.BaofooConstans;
import com.p2p.constans.SysConstans;
import com.p2p.dto.InfoMsg;
import com.p2p.model.CMessage;
import com.p2p.model.CUser;
import com.p2p.model.TPerson;
import com.p2p.model.TThridAccount;
import com.p2p.security.MD5;
import com.p2p.service.CUserService;
import com.p2p.service.TPersonService;
import com.p2p.service.TThridAccountService;
import com.p2p.util.FormUtil;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CenterUserDataController extends BaseController{
	private static Log log = LogFactory.getLog(BorrowController.class);
	@Autowired
	private TPersonService tPersonService;

	@Autowired
	private TThridAccountService tThridAccountService;
	@Autowired 
	private CUserService cUserService;


	@RequestMapping(value="/center/userData")
	public String selectUserIndex(CMessage cMessage){
		CUser sessionUser = this.getCUser();
		TPerson tPerson = tPersonService.getPersonByUserId(sessionUser.getId());
		TThridAccount tThridAccount = tThridAccountService.getThridAccountByUserId(sessionUser.getId());
		request.setAttribute("cuser",sessionUser );
		request.setAttribute("cuserp", tPerson);
		request.setAttribute("userThrid",tThridAccount);
		return "front/usercenter/user-basic-data";
	}
	/**
	 * 修改用户手机号码
	 * @return
	 */

	@RequestMapping(value="/center/updateUserPhone1")
	public String updateUserPhone(){
		String rPhone =request.getParameter("param");
		CUser user = getCUser();
		if(StringUtils.isBlank(rPhone)){
			this.writeJson(new InfoMsg("手机号码输入有误", "n"));
			return null; 
		}
		if(!user.getCellPhone().equals(rPhone)){
			this.writeJson(new InfoMsg("和绑定号码不对应", "n"));
			return null;
		}
		this.writeJson(new InfoMsg("原号码输入正确", "y"));
		return null;
	}
	/**
	 * 修改用户手机号码
	 * @return
	 */
	@RequestMapping(value="/center/updateUserPhone2")
	public String reFormTelCode(){
		String secode = (String)session.getAttribute(SysConstans.UPDATE_TEL_CODE);
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
	 * 
	 * @return
	 */
	@RequestMapping(value="/center/updateUserPhone3")
	public String updateUserNewPhone(){
		String ntel = request.getParameter("newPhone");
		String ncode = request.getParameter("newCode");
		String sNtel = (String)session.getAttribute(SysConstans.UPDATE_NEW_TEL_CODE);
		if(StringUtils.isBlank(ntel)||StringUtils.isBlank(ncode)){
			this.writeJson(new InfoMsg("信息输入有误，请核对后再试", "n"));
			return null;
		}
		if(ntel.length()!=11){
			this.writeJson(new InfoMsg("手机号码输入有误", "n"));
			return null;
		}
		if(ncode.length()!=6 || !ncode.equals(sNtel)){
			this.writeJson(new InfoMsg("验证码输入有误", "n"));
			return null;
		}
		CUser cUser = this.getCUser();
		cUser.setCellPhone(ntel);
		Long ren = -1l;
		try{
			ren = cUserService.updateCUser(cUser);
		}catch (Exception e) {
			log.error("更换用户手机号码异常", e);
		}
		if(ren<=0){
			this.writeJson(new InfoMsg("手机号码修改失败", "n"));
			return null;
		}
		this.writeJson(new InfoMsg("手机号码已经成功修改", "y"));
		return null;
	}
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value="/center/updateUserPass")
	public String updateUserPass(){
		String oldPass = request.getParameter("OldPass");
		String newPass = request.getParameter("NewPass");
		String selectpwd ="";
		CUser cuser=new CUser();
		cuser.setId(this.getCUser().getId());
		try{
			selectpwd=cUserService.selectPwd(cuser);
		}catch (Exception e) {
			log.error("修改密码异常", e);
		}

		if(MD5.encrypt(oldPass+SysConstans.MD5_USER_KEY).equals(selectpwd)){
			cuser.setPwd(MD5.encrypt(newPass+SysConstans.MD5_USER_KEY));

			int i=cUserService.updatepwd(cuser);
			if(i<=0){
				this.writeJson(new InfoMsg("修改密码失败", "n"));
				return null;
			}
		}else{
			this.writeJson(new InfoMsg("旧密码输入错误", "n"));
			return null;
		}


		this.writeJson(new InfoMsg("修改成功", "y"));
		return null;
	}

	/**
	 * 修改邮箱发送邮件
	 * @return
	 */
	@RequestMapping(value="updateUserEmail")
	public String updateUserEmail(String newEmil){
		//Q.send("富田在线", "service@ftzx.com", "jaco1986@163.com", "service@ftzx.com", "ftzx1234", "测试邮件标题", "smtp.exmail.qq.com", "测试邮件内容");
		return "";
	}

	/**
	 * 邮箱验证成功修改邮箱
	 * @return
	 */
	@RequestMapping(value="updateEmail")
	public String updateEmail(String emailmsg){

		return "";
	}

	/**
	 * 宝付授权
	 * @return
	 */
	@RequestMapping(value="/center/baofooAccredit")
	public String baofooAccredit(){
		CUser cUser = this.getCUser();
		TThridAccount tThridAccount = tThridAccountService.getThridAccountByUserId(cUser.getId());
		try {
			BaofooConstans.REQUEST_SERVICE.page_Accredit(response, tThridAccount.getThirdUserId(),BaofooConstans.accreditReturnUrl ,BaofooConstans.accreditPageUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/accreditPageUrl")
	public String accreditPageUrl(){
		return "front/login/reg_result";
	}
	@RequestMapping(value = "/accreditReturnUrl")
	public String accreditReturnUrl(){
		String result = request.getParameter("result");
		String sign = request.getParameter("sign");
		try {
			ResultDto resultDto = BaofooConstans.RECEIVE_SERVICE.page_BindBaoofooAccount(result, sign);
			log.info("宝付授权回调===userId:"+resultDto.getUser_id()+"，Code:"+resultDto.getCode()+"，Msg"+resultDto.getMsg());
			if("CSD000".equals(resultDto.getCode())){
				tThridAccountService.updateThridIsAccredit(1, resultDto.getUser_id());
			}else{
				tThridAccountService.updateThridIsAccredit(2, resultDto.getUser_id());
			}
		} catch (Exception e) {
			log.error("宝付授权，回调异常", e);
		}
		return null;
	}
	/**
	 * 宝付-资金托管商户认证书
	 * @return
	 */
	@RequestMapping(value = "/showCertificate")
	public String showCertificate(){
		try {

			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("merchant_id", BaofooConstans.merchantId);
			sParaTemp.put("terminal_id", BaofooConstans.terminalId);
			String html = FormUtil.buildHtmlForm(sParaTemp,BaofooConstans.baofooCertificate , "post") ;
			this.write(html);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
