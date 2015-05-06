package com.p2p.constans;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.baofoo.p2p.service.ReceiveService;
import com.baofoo.p2p.service.RequestService;


/**
 * 宝付配置文件
 * @author Administrator
 *
 */
public class BaofooConstans {
	
	/**
	 * 商户号
	 */
	public static String merchantId = null;
	/**
	 * 终端号
	 */
	public static String terminalId = null;
	/**
	 * 平台回调地址
	 */
	public static String backServerUrl = null;
	/**
	 * 密钥key
	 */
	public static String key = null;
	
	/**
	 * 是否测试
	 */
	public static boolean isTest = true;
	
	/**
	 * 宝付请求类
	 */
	public static  RequestService REQUEST_SERVICE = null;
	
	/**
	 * 宝付接收类
	 */
	public static  ReceiveService RECEIVE_SERVICE;
	
	/**
	 * 授权-页面自动跳转地址(不携带绑定结果信息)
	 */
	public static String accreditPageUrl;
	/**
	 * 授权-绑定成功回调地址，接收返回结果
	 */
	public static String accreditReturnUrl;
	
	/**
	 * 商户认证书，访问地址
	 */
	public static String baofooCertificate;
	
	/**
	 * 页面自动跳转地址(不携带绑定结果信息)
	 */
	public static String regPageUrl;
	/**
	 * 绑定成功回调地址，接收返回结果
	 */
	public static String regReturnUrl;
	
	
	/**
	 * 投标页面回调
	 */
	public static String investPageUrl = null;
	

	
	/**
	 * 投标后端回调
	 */
	public static String investReturnUrl = null;
	
	/**
	 * 充值后端回调
	 */
	public static String rechargeReturnUrl = null;
	
	/**
	 * 充值页面回调
	 */
	public static String rechargePageUrl = null;
	/**
	 * 提现页面回调
	 */
	public static String twithdrawPageUrl = null;
	
	public final static String BORROW_ID = "2015";
	
	public  static String loginBaofooUrl ;
	static{
		try {
			Properties prop = PropertiesLoaderUtils.loadAllProperties("baofoo.properties");
			merchantId = (String)prop.get("merchantId");
			terminalId = (String)prop.get("terminalId");
			backServerUrl = (String)prop.get("backServerUrl");
			key = (String)prop.get("key");
			isTest = "1".equals((String)prop.get("isTest"));
			loginBaofooUrl =  (String)prop.get("loginBaofooUrl");
			baofooCertificate  =  (String)prop.get("baofooCertificate");
			investPageUrl = backServerUrl+"investPageUrl.html";
			investReturnUrl = backServerUrl+"investReturnUrl.html";
			rechargePageUrl = backServerUrl+"rechargePageUrl.html";
			rechargeReturnUrl = backServerUrl+"rechargeReturnUrl.html";
			twithdrawPageUrl=backServerUrl+"twithdrawpageUrl.html";
			regPageUrl = backServerUrl+"regPageUrl.html";
			regReturnUrl = backServerUrl+"regReturnUrl.html";
			accreditPageUrl = backServerUrl+"accreditPageUrl.html";
			accreditReturnUrl = backServerUrl+"accreditReturnUrl.html";
			
			
		
			
			REQUEST_SERVICE = new RequestService(isTest, terminalId, merchantId, key);
			RECEIVE_SERVICE = new ReceiveService();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	
}
