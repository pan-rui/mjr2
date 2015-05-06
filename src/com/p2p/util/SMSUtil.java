package com.p2p.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.p2p.constans.SysConstans;

public class SMSUtil {
	private static Log log = LogFactory.getLog(SMSUtil.class);
	public static boolean sendSms(String phone,String content){
		
		String serviceUrl = SysConstans.SMS_SIREVICE_URL;
		String sms_hz="【P2P】";
		Map<String, String> map = new HashMap<>();
		map.put("account", SysConstans.SMS_USER_NAME);//用户名
		map.put("password", SysConstans.SMS_USER_PASS);//密码
		map.put("destmobile", phone);//发送手机号码
		map.put("msgText", (content+sms_hz));//内容+签名
		String rest = null;
		//map.put("sendDateTime", "");//定时日期	格式如20130201120000,14位长度,无需传值
		try {
			rest = HttpClientUtil.doPost(serviceUrl, map, null);
		} catch (IOException e) {
			log.error("そそそそそそ短信发送失败そそそそそそ",e);
		}
		log.info("短信发送情况："+rest+"，phone："+phone+"，content："+content);
		int restInt= NumberUtils.toInt(rest, -9);
		if(restInt>0){
			log.info("短信发送成功："+restInt+"，phone："+phone+"，content："+content);
			return true;
		}else{
			switch (restInt) {
			case -1:
				log.info("短信发送失败-余额不足"+restInt+"，phone："+phone+"，content："+content);
				break;
			case -2:
				log.info("短信发送失败-帐号或密码错误"+restInt+"，phone："+phone+"，content："+content);
				break;
			case -3:
				log.info("短信发送失败-连接服务商失败"+restInt+"，phone："+phone+"，content："+content);
				break;
			case -4:
				log.info("短信发送失败-超时"+restInt+"，phone："+phone+"，content："+content);
				break;
			case -5:
				log.info("短信发送失败-其他错误，一般为网络问题，IP受限等"+restInt+"，phone："+phone+"，content："+content);
				break;
			case -6:
				log.info("短信发送失败-短信内容为空"+restInt+"，phone："+phone+"，content："+content);
				break;
			case -7:
				log.info("短信发送失败-目标号码为空"+restInt+"，phone："+phone+"，content："+content);
				break;
			case -11:
				log.info("短信发送失败-目标号码在黑名单里"+restInt+"，phone："+phone+"，content："+content);
				break;
			case -12:
				log.info("短信发送失败-消息内容包含禁用词语"+restInt+"，phone："+phone+"，content："+content);
				break;
			case -13:
				log.info("短信发送失败-没有权限使用该网关"+restInt+"，phone："+phone+"，content："+content);
				break;
			case -20:
				log.info("短信发送失败-超速提交(一般为每秒一次提交)"+restInt+"，phone："+phone+"，content："+content);
				break;
			default:
				log.info("短信发送失败-反正是错了，别问我为什么"+restInt+"，phone："+phone+"，content："+content);
				break;
			}	
			return false;
		}
		
	}
	
	public static void main(String[] args) {
		sendSms("13715096137", "注册验证码为:476652，请及时验证。");
	}
}
