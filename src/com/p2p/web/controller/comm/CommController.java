package com.p2p.web.controller.comm;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.p2p.constans.SysConstans;
import com.p2p.dto.InfoMsg;
import com.p2p.enums.InfoTemplate;
import com.p2p.model.BArea;
import com.p2p.model.CSms;
import com.p2p.model.CSmsRecord;
import com.p2p.model.CUser;
import com.p2p.service.BAreaService;
import com.p2p.service.CSmsRecordService;
import com.p2p.service.CSmsService;
import com.p2p.service.CUserService;
import com.p2p.util.RandomUtils;
import com.p2p.util.SMSUtil;
import com.p2p.util.ValidateUtil;
import com.p2p.web.controller.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class CommController extends BaseController {

	private static Log log = LogFactory.getLog(CommController.class);
	@Autowired
	private BAreaService bAreaService;
	@Autowired
	private CSmsRecordService cSmsRecordService;

	@Autowired
	private CSmsService cSmsService;
	@Autowired
	private CUserService cUserService;

	@RequestMapping(value = "/cityList")
	public String queryCityList(BArea bArea) throws IOException {
		List<BArea> areaList = bAreaService.queryBAreaList(bArea);
		this.writeJson(areaList);
		return null;
	}
	/**
	 * 注册验证码
	 * @return
	 */
	@RequestMapping(value = "/getPhoneCode")
	public String getPhoneCode(){

		String sen_phone = (String)session.getAttribute(SysConstans.REG_PHONE);
		Date for_pass_time = (Date)session.getAttribute(SysConstans.REG_USER_COUNTDOWN);
		String phone = request.getParameter("cellPhone");
		CSmsRecord csr = new CSmsRecord();
		csr.setKeyVal(phone);
		CSmsRecord csr2 = cSmsRecordService.getCSmsRecordByPhone(csr); 
		//检查验证码发送次数
		if(csr2!=null){
			if(csr2.getCounts()>=5){
				this.writeJson(new InfoMsg("注册验证码发送超过5次，请联系客服寻求帮助。", "n"));
				return null;
			}else{
				csr2.setCounts((csr2.getCounts()+1));
				cSmsRecordService.updateCSmsRecord(csr2);
			}
		}else{
			CSmsRecord csrAdd = new CSmsRecord();
			csrAdd.setKeyVal(phone);
			csrAdd.setCreateTime(new Date());
			csrAdd.setCounts(1);
			cSmsRecordService.addCSmsRecord(csrAdd);
		}
		if(!ValidateUtil.validateCellPhone(phone)){
			this.writeJson(new InfoMsg("输入手机号码格式有误！", "n"));
			return null;
		}
		if(phone.equals(sen_phone)){
			long timeC =new Date().getTime()-for_pass_time.getTime();
			long timet = timeC/1000;
			if(timet<120){
				long times = 120-timet;
				this.writeJson(new InfoMsg("操作频繁，请稍后重试...", "n",times+""));
				return null;
			}
		}

		String phonecode = RandomUtils.createRadom();

		CSms cSms = new CSms();
		cSms.setCellPhone(phone);
		cSms.setCreateTime(new Date());
		cSms.setSmsContent("注册验证码为："+phonecode);

		boolean rest = SMSUtil.sendSms(phone, InfoTemplate.REG_CODE.getContentTemplate().replace("#regCode", phonecode));
		if(!rest){
			cSms.setSendStatus(1);
			cSmsService.addCSms(cSms);
			this.writeJson(new InfoMsg("短信发送失败请稍后重试", "n"));
			return null;
		}else{
			cSms.setSendStatus(2);
			cSmsService.addCSms(cSms);
			session.setAttribute(SysConstans.REG_CODE, phonecode);
			session.setAttribute(SysConstans.REG_PHONE, phone);
			session.setAttribute(SysConstans.REG_USER_COUNTDOWN, new Date());
			this.writeJson(new InfoMsg("手机验证码发送成功，请及时查收手机短信！", "y"));
			return null;
		}
	}
	/**
	 * 更换手机号码，获取验证码
	 * @return
	 */
	@RequestMapping(value = "/updateTelGetCode")
	public String updateTelGetCode(){
		Date for_pass_time = (Date)session.getAttribute(SysConstans.UPDATE_TEL_COUNTDOWN);
		CUser cUser = this.getCUser();
		String phone = request.getParameter("cellPhone");
		if(!cUser.getCellPhone().equals(phone)){
			this.writeJson(new InfoMsg("手机号码输入有误", "n"));
			return null;
		}
		if(for_pass_time!=null){
			long timeC =new Date().getTime()-for_pass_time.getTime();
			long timet = timeC/1000;
			if(timet<120){
				long times = 120-timet;
				this.writeJson(new InfoMsg("操作频繁，请稍后重试...", "n",times+""));
				return null;
			}
		}
		String phonecode = RandomUtils.createRadom();
		boolean rest = SMSUtil.sendSms(phone, InfoTemplate.REG_CODE.getContentTemplate().replace("#regCode", phonecode));
		if(rest){
			this.writeJson(new InfoMsg("手机验证码发送成功，请及时查收手机短信！", "y"));
			session.setAttribute(SysConstans.UPDATE_TEL_CODE, phonecode);
			session.setAttribute(SysConstans.UPDATE_TEL_COUNTDOWN, new Date());
		}else{
			this.writeJson(new InfoMsg("手机验证码发送失败！", "n"));
		}
		return null;
	}
	/**
	 * 更换手机号码，获取验证码
	 * @return
	 */
	@RequestMapping(value = "/updateTelNewCode")
	public String updateTelNewCode(){
		Date for_pass_time = (Date)session.getAttribute(SysConstans.UPDATE_NEW_TEL_COUNTDOWN);
		CUser cUser = new CUser();
		String phone = request.getParameter("cellPhone");
		cUser.setCellPhone(phone);
		CUser cu= cUserService.verificationNewUserPhone(cUser);
		if(cu!=null){
			this.writeJson(new InfoMsg("该手机号码已注册", "n"));
			return null;
		}
		if(for_pass_time!=null){
			long timeC =new Date().getTime()-for_pass_time.getTime();
			long timet = timeC/1000;
			if(timet<120){
				long times = 120-timet;
				this.writeJson(new InfoMsg("操作频繁，请稍后重试...", "n",times+""));
				return null;
			}
		}
		String phonecode = RandomUtils.createRadom();
		boolean rest = SMSUtil.sendSms(phone, InfoTemplate.REG_CODE.getContentTemplate().replace("#regCode", phonecode));
		if(rest){
			session.setAttribute(SysConstans.UPDATE_NEW_TEL_CODE, phonecode);
			session.setAttribute(SysConstans.UPDATE_NEW_TEL_COUNTDOWN, new Date());
			this.writeJson(new InfoMsg("手机验证码发送成功，请及时查收手机短信！", "y"));
		}else{
			this.writeJson(new InfoMsg("手机验证码发送失败！", "n"));
		}
		return null;
	}


	/**
	 * 忘记密码，获取手机验证码
	 * @return
	 */
	@RequestMapping(value = "/forGetPassCode")
	public String forGetPassCode(){
		Date for_pass_time = (Date)session.getAttribute(SysConstans.FORGET_PASS_TIME);
		String phone = request.getParameter("cellPhone");
		if(for_pass_time!=null){
			long timeC =new Date().getTime()-for_pass_time.getTime();
			long timet = timeC/1000;
			if(timet<120){
				long times = 120-timet;
				this.writeJson(new InfoMsg("操作频繁，请稍后重试...", "n",times+""));
				return null;
			}
		}
		String phonecode = RandomUtils.createRadom();
		boolean rest = SMSUtil.sendSms(phone, InfoTemplate.FORGETPASS_CODE.getContentTemplate().replace("#regCode", phonecode));
		if(rest){
			this.writeJson(new InfoMsg("手机验证码发送成功，请及时查收手机短信！", "y"));
			session.setAttribute(SysConstans.FORGET_PASS_CODE, phonecode);
			session.setAttribute(SysConstans.FORGET_PASS_TIME, new Date());
		}else{
			this.writeJson(new InfoMsg("手机验证码发送失败！", "n"));
		}
		return null;
	}


	/**
	 * 注册验证码
	 * @return
	 */
	@RequestMapping(value = "/getQuickBorrowCode")
	public String getQuickBorrowCode(){

		Date for_pass_time = (Date)session.getAttribute(SysConstans.QUICK_BORROW_COUNTDOWN);
		String phone = request.getParameter("cellPhone");
		if(!ValidateUtil.validateCellPhone(phone)){
			this.writeJson(new InfoMsg("输入手机号码格式有误！", "n"));
			return null;
		}
		if(for_pass_time!=null){
			long timeC =new Date().getTime()-for_pass_time.getTime();
			long timet = timeC/1000;
			if(timet<120){
				long times = 120-timet;
				this.writeJson(new InfoMsg("操作频繁，请稍后重试...", "n",times+""));
				return null;
			}

		}
		String phonecode = RandomUtils.createRadom();

		CSms cSms = new CSms();
		cSms.setCellPhone(phone);
		cSms.setCreateTime(new Date());
		cSms.setSmsContent("您的借款申请验证码为："+phonecode);

		boolean rest = SMSUtil.sendSms(phone, InfoTemplate.QUICK_BORROW_CODE.getContentTemplate().replace("#qbCode", phonecode));
		if(!rest){
			cSms.setSendStatus(1);
			cSmsService.addCSms(cSms);
			this.writeJson(new InfoMsg("短信发送失败请稍后重试", "n"));
			return null;
		}else{
			cSms.setSendStatus(2);
			cSmsService.addCSms(cSms);
			session.setAttribute(SysConstans.QUICK_BORROW_CODE, phonecode);
			session.setAttribute(SysConstans.QUICK_BORROW_COUNTDOWN, new Date());
			this.writeJson(new InfoMsg("手机验证码发送成功，请及时查收手机短信！", "y"));
			return null;
		}
	}
}
