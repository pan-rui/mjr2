package com.p2p.constans;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 系统常量
 * @author Administrator
 *
 */
public final class SysConstans {
	public final static String ADMIN_SESSION ="admin";
	
	public final static String USER_SESSION ="user";
	
	public final static String REG_CODE = "phonecode";
	
	public final static String REG_PHONE = "regphone";
	
	public final static String REG_USER_SCORE = "userScore";
	
	public final static String USER_LOGIN_URL="userLoginURL";
	
	public final static String REG_USER_JL="regUserRecord";
	
	public final static String REG_USER_COUNTDOWN ="regUserCountdown";
	/**
	 * 申请借款-验证码
	 */
	public final static String QUICK_BORROW_CODE="quickBorrowCode";
	/**
	 * 申请借款短信CD时间
	 */
	public final static String QUICK_BORROW_COUNTDOWN ="quickBorrowCountdown";
	/**
	 * 更换手机，验证码CD时间
	 */
	public final static String UPDATE_TEL_COUNTDOWN ="updateTelCountdown";
	/**
	 * 原号码解绑验证码
	 */
	public final static String UPDATE_TEL_CODE ="updateTelCode";
	/**
	 * 更换手机，新号码验证码CD时间
	 */
	public final static String UPDATE_NEW_TEL_COUNTDOWN ="updateNewTelCountdown";
	/**
	 * 绑定新号码
	 */
	public final static String UPDATE_NEW_TEL_CODE ="updateNewTelCode";
	/**
	 * 忘记密码，验证码CD时间
	 */
	public final static String FORGET_PASS_TIME="forGetPassTime";
	/**
	 * 忘记密码，验证码内容
	 */
	public final static String FORGET_PASS_CODE="forGetPassCode";
	/**
	 * 忘记密码，记录操作id
	 */
	public final static String FORGET_PASS_WORD_ID="forGetPassWordId";
	
	/**
	 * 菜单列表名称
	 */
	public final static String MENU_LIST ="menuList";
	
	public final static String NO_LOGIN ="noLogin";
	
	
	public  static String MD5_KEY ;
	
	/**
	 * 用户密码MD5值
	 */
	public  static String MD5_USER_KEY ;
	
	/**
	 * 计算结果保留值
	 */
	public static final int VALUE_SCALE = 2;

	/**
	 * 短信商账号
	 */
	public static  String SMS_USER_NAME;
	
	public static  String SMS_USER_PASS;
	
	public static  String SMS_SIREVICE_URL;

	static{
		try {
			Properties prop = PropertiesLoaderUtils.loadAllProperties("sys.properties");
			MD5_USER_KEY = (String)prop.get("MD5_USER_KEY");
			MD5_KEY = (String)prop.get("MD5_KEY");
			SMS_USER_NAME = (String)prop.get("SMS_USER_NAME");
			SMS_USER_PASS = (String)prop.get("SMS_USER_PASS");
			SMS_SIREVICE_URL = (String)prop.get("SMS_SIREVICE_URL");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
