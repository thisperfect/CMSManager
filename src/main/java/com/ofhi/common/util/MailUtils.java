package com.ofhi.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {

	private static final String username = "vip@family-zh.cn";
	private static final String password = "Vip123456";

	
	/*
	 * 初始化方法
	 */
	private MailUtils() {}

	/**
	 * 发送邮件
	 * 
	 * @param subject
	 *            邮件主题
	 * @param sendHtml
	 *            邮件内容
	 * @param receiveUser
	 *            收件人地址
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void doSendHtmlEmail(String subject, String sendHtml, String receiveUser) throws MessagingException, UnsupportedEncodingException, GeneralSecurityException {
		Properties prop = new Properties();
		// 协议
		prop.setProperty("mail.transport.protocol", "smtp");
		// 服务器
		prop.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
		// 端口
		prop.setProperty("mail.smtp.port", "465");
		// 使用smtp身份验证
		prop.setProperty("mail.smtp.auth", "true");
        //使用SSL，企业邮箱必需！
        //开启安全协议
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);

        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        //
        //获取Session对象
        Session s = Session.getDefaultInstance(prop,new Authenticator() {
            //此访求返回用户和密码的对象
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                PasswordAuthentication pa = new PasswordAuthentication(username, password);
                return pa;
            }
        });
        //设置session的调试模式，发布时取消
        s.setDebug(false);
		MimeMessage mimeMessage = new MimeMessage(s);
		mimeMessage.setFrom(new InternetAddress(username));
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveUser));
		//设置主题
		mimeMessage.setSubject(subject);
		mimeMessage.setSentDate(new Date());
		//设置内容
		mimeMessage.saveChanges();
		mimeMessage.setContent(sendHtml, "text/html;charset=gb2312");
		//发送
		Transport.send(mimeMessage);
	}
	public static void main(String[] args) throws IOException {
		try {
			MailUtils.doSendHtmlEmail("欢迎使用聚合数据提供的数据服务", "欢迎使用聚合数据提供的数据服务！请点击以下的链接验证您的邮箱，验证成功后就可以使用聚合提供的所有服务了。", "136166087@qq.com");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
	}
}
