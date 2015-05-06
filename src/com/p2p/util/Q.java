package com.p2p.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Q
{
    /**
     * @param mailFromName 接收到的邮件显示的名字
     * @param mailFrom 发送邮件的地址，已经创建公共邮箱commonfornewspace@163.com
     * @param mailTo 接收邮件的地址
     * @param userName 用户名，commonfornewspace
     * @param password 邮箱密码：common123
     * @param subject 邮件主题
     * @param message 邮件具体信息
     * @param serverName 邮件服务器地址：163邮箱为"smtp.163.com"
     * @return
     */
   
	 public static boolean send(String mailFromName, String mailFrom, String mailTo, String userName, 
	            String password, String subject, String serverName,Long id){
	        HtmlEmail email = new HtmlEmail(); // 不要使用SimpleEmail,会出现乱码问题
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			Date date=new Date();
	        String str="<div style='font:14px/1.8 arial,simsun;color:#333;border:solid 8px #E7E7E7;padding:16px;" +
					"margin:10px auto;background-color:#FFF;text-align:left;border-radius:8px;width:660px;'>"+
		"<p style='margin:8px 0px;padding:0px;'>亲爱的用户</p>"+
		"<p style='margin:8px 0px;padding:0px;padding-left:36px;'>"+
		"欢迎来到富田在线!</p><p style='margin:8px 0px;padding:0px;padding-left:36px;word-wrap:break-word;'>"+
		"您的新邮箱为："+mailTo+"，为了您的帐号安全，请点击如下链接完成安全邮箱激活。<br/>"+
		"<a href='http://www.ftzx.com/bankemailkh.html?yx="+mailTo+"&id="+id+"'  target='_blank'>亲 点我验证啦</a></p><p style='text-align:right;padding-right:10px;'>"+
		""+ 
		"</p><p style='color:#999;font-size:12px;'>"+
		"详情请登录<a href='www.ftzx.com'>富田在线</a>账号查询 。<br/>"+
		"如有任何疑问，请拨打我们的客服热线<span style='color:red;'>400 856 3899</span>或联系网站客服人员。<br/>"+
		"<b style='color:blue'>富田在线是国内首家央企合作的千亿资产交易平台，让您的投资不再是冒险。</b></p></div>";
	        try
	        {
	        	email.setSSL(Boolean.TRUE); 
	        	email.setSslSmtpPort("465");
	            email.setHostName(serverName); // 这里是SMTP发送服务器的名字：，163的如下：
	            email.setCharset("utf-8");     // 字符编码集的设置
	            email.addTo(mailTo);           // 收件人的邮箱
	            email.setFrom(mailFrom, mailFromName);        // 发送人的邮箱,以及发件人显示的名字
	            email.setAuthentication(userName, password);  // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
	            email.setSubject(subject);
	            email.setMsg(str);    // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
	            email.send();             // 发送
	            System.out.println("邮件发送成功");
	            return true;
	        }
	        catch (EmailException e)
	        {
	            e.printStackTrace();
	            return false;
	        }
	    }
    /*public static void main(String[] args) {
    	//send("富田在线邮箱变更验证", "guyue_king@126.com", "a374162595@qq.com", "guyue_king", "guyue3659123.0", "测试主题", "000", "smtp.126.com");
    	send("富田在线邮箱变更验证", "service@ftzx.com", "a374162595@qq.com", "service@ftzx.com", "ftzx1234", " 富田在线邮箱变更验证", "smtp.exmail.qq.com",8);
	}*/
	 
	
	 /**	资金变动邮箱提示
	     * @param mailFromName 接收到的邮件显示的名字
	     * @param mailFrom 发送邮件的地址，已经创建公共邮箱commonfornewspace@163.com
	     * @param mailTo 接收邮件的地址
	     * @param userName 用户名，commonfornewspace
	     * @param password 邮箱密码：common123
	     * @param subject 邮件主题
	     * @param serverName 邮件服务器地址：163邮箱为"smtp.163.com"
	     * @param whysend 为何发这条邮件（比如您成功投资了多少元）
	     * @param noticeMode reciveRepayEnable:收到还款通知  showSucEnable:提现通知  loanSucEnable:借款成功  rechargeSucEnable:充值成功  capitalChangeEnable:投资资金变化 【预留】
	     * @param investType 投资类型（1投标成功，2投资成功，3流标） 非投资类置为null【预留】
	     * @return
	     */
	 public static boolean sendfundchangmsg(String mailFromName, String mailFrom, String mailTo, String userName,String password, 
			                                String subject, String serverName,String whysend)
	 {
	        HtmlEmail email = new HtmlEmail(); // 不要使用SimpleEmail,会出现乱码问题
	        StringBuffer content=new StringBuffer();
	        content.append("<div style='font:14px/1.8 arial,simsun;color:#333;border:solid 8px #E7E7E7;padding:16px;");
	        content.append("margin:10px auto;background-color:#FFF;text-align:left;border-radius:8px;width:660px;'>");
	        content.append("<p style='margin:8px 0px;padding:0px;'>亲爱的用户,您好！</p>");
	        content.append("<p style='margin:8px 0px;padding:0px;padding-left:36px;'>您的资金有变动喔!</p>");
	        content.append("<p style='margin:8px 0px;padding:0px;padding-left:36px;word-wrap:break-word;'>");
	        content.append(whysend);
	        content.append("</p><p style='text-align:right;padding-right:10px;'></p>");
	        content.append("<p style='color:#999;font-size:12px;'>详情请登录<a href='http://www.ftzx.com'>富田在线</a>账号查询 <br/>");
	        content.append("如有任何疑问，请拨打我们的客服热线<span style='color:red;'>400 856 3899</span>或联系网站客服人员。<br/>");
	        content.append("富田在线是国内首家央企合作的千亿资产交易平台，让您的投资不再是冒险。</p></div>");
	        try
	        {
	        	email.setSSL(Boolean.TRUE); 
	        	email.setSslSmtpPort("465");
	            email.setHostName(serverName); // 这里是SMTP发送服务器的名字：，163的如下：
	            email.setCharset("utf-8");     // 字符编码集的设置
	            email.addTo(mailTo);           // 收件人的邮箱
	            email.setFrom(mailFrom, mailFromName);        // 发送人的邮箱,以及发件人显示的名字
	            email.setAuthentication(userName, password);  // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
	            email.setSubject(subject);
	            email.setMsg(content.toString());// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
	            String result=email.send();             // 发送
	            System.out.println("邮件发送返回："+result);
	            return true;
	        }
	        catch (EmailException e)
	        {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public static boolean test(String mailFromName, String mailFrom, String mailTo, String userName, 
	            String password, String subject, String serverName){
	        HtmlEmail email = new HtmlEmail(); // 不要使用SimpleEmail,会出现乱码问题
	        
	        String str="<div style='font:14px/1.8 arial,simsun;color:#333;border:solid 8px #E7E7E7;padding:16px;" +
					"margin:10px auto;background-color:#FFF;text-align:left;border-radius:8px;width:660px;'>"+
		"<p style='margin:8px 0px;padding:0px;'>亲爱的用户</p>"+
		"<p style='margin:8px 0px;padding:0px;padding-left:36px;'>"+
		"欢迎来到富田在线!</p><p style='margin:8px 0px;padding:0px;padding-left:36px;word-wrap:break-word;'>"+
		"测试测试 我在测试<br/>"+
		"鸭梨测试而已</p><p style='text-align:right;padding-right:10px;'>"+
		""+ 
		"</p><p style='color:#999;font-size:12px;'>本邮件由富田在线平台自动发出，请勿直接回复！ <br/>"+
		"为了能够正常收到我们的邮件，请将我们加入白名单,或添加为您的联系人。<br/>"+
		"富田在线 - 全球最棒的、最有价值的网站--想发财、想投资、找朋友、找知己、找客户就来富田在线。</p></div>";
	        try
	        {
	        	email.setSSL(Boolean.TRUE); 
	        	email.setSslSmtpPort("465");
	            email.setHostName(serverName); // 这里是SMTP发送服务器的名字：，163的如下：
	            email.setCharset("utf-8");     // 字符编码集的设置
	            email.addTo(mailTo);           // 收件人的邮箱
	            email.setFrom(mailFrom, mailFromName);        // 发送人的邮箱,以及发件人显示的名字
	            email.setAuthentication(userName, password);  // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
	            email.setSubject(subject);
	            email.setMsg(str);    // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
	            email.send();             // 发送
	            System.out.println("邮件发送成功");
	            return true;
	        }
	        catch (EmailException e)
	        {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public static void main(String[] args) {
	    	//send("富田在线邮箱变更验证", "guyue_king@126.com", "a374162595@qq.com", "guyue_king", "guyue3659123.0", "测试主题", "000", "smtp.126.com");
	    	
	    	/*for(int i=0;i<1000;i++){
	    		test("富田在线邮箱变更验证", "service@ftzx.com", "295446524@qq.com", "service@ftzx.com", "ftzx1234", " 富田在线邮箱变更验证", "smtp.exmail.qq.com");
	    		test("富田在线邮箱变更验证", "service@ftzx.com", "407357996@qq.com", "service@ftzx.com", "ftzx1234", " 富田在线邮箱变更验证", "smtp.exmail.qq.com");
	    		test("富田在线邮箱变更验证", "service@ftzx.com", "772364562@qq.com", "service@ftzx.com", "ftzx1234", " 富田在线邮箱变更验证", "smtp.exmail.qq.com");
	    	}*/
		 
		/* mailFromName 接收到的邮件显示的名字
		 mailFrom 发送邮件的地址，已经创建公共邮箱commonfornewspace@163.com
		 mailTo 接收邮件的地址
		 userName 用户名，commonfornewspace
		 password 邮箱密码：common123
		 subject 邮件主题
		 serverName 邮件服务器地址：163邮箱为"smtp.163.com"
		 whysend 为何发这条邮件（比如您成功投资了多少元）
		 noticeMode reciveRepayEnable:收到还款通知 showSucEnable:提现通知 loanSucEnable:借款成功 rechargeSucEnable:充值成功 capitalChangeEnable:投资资金变化
		 investType 投资类型（1投标成功，2投资成功，3流标） 非投资类置为null*/

		 //Q.sendfundchangmsg("富田在线", "ftzx_server@126.com", "305007595@qq.com", "ftzx_server@126.com", "ftzx_server001", "测试邮件标题", "smtp.126.com", "测试邮件内容");
		 
		 Q.sendfundchangmsg("富田在线", "service@ftzx.com", "jaco1986@163.com", "service@ftzx.com", "ftzx1234", "测试邮件标题", "smtp.exmail.qq.com", "测试邮件内容");
	 }
	 
	 
	 
	 
	 
}