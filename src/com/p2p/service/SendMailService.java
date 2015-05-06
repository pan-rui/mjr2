package com.p2p.service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMailService {

	private static Log log = LogFactory.getLog(SendMailService.class);
	@Resource(name = "javaMailSender")
	private JavaMailSender javaMailSender;

	public boolean sendHtmlMail(String toMail, String subject, String text) {
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		// 设置utf-8或GBK编码，否则邮件会有乱码
		boolean result = false;
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(
					mailMessage, true, "utf-8");
			messageHelper.setTo(toMail);// 接受者
			messageHelper.setFrom("xxx@163.com");// 发送者
			messageHelper.setSubject(subject);// 主题
			// 邮件内容，注意加参数true，表示启用html格式
			messageHelper.setText(text, true);
			javaMailSender.send(mailMessage);
			result = true;
		} catch (Exception e) {
			log.error("发送邮件失败", e);
		}
		return result;
	}
}
