package com.fithub.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

// william google寄信
@Service
public class JavaMail {

	private String username = "test@gmail.com";
	private String password = "test";
//	private String customer = "test@gmail.com";
//	private String subject = "客戶EAMIL";
//	private String txt = "信件內容文字";

	public void SendMail(String name, String email, String phone, String message, String subject)
			throws UnsupportedEncodingException {
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.port", "465");
		// 顯示連線資訊
//		prop.put("mail.debug", "true");

		Session session = Session.getDefaultInstance(prop, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		MimeMessage mimemessage = new MimeMessage(session);

		try {
			// 寄件者
			mimemessage.setSender(new InternetAddress(username));

			// 收件者
			mimemessage.setRecipient(RecipientType.TO, new InternetAddress("test@gmail.com"));

			// 標題
			mimemessage.setSubject(subject);

			// 內容
			mimemessage.setContent("姓名:" + name + "<br>信箱:" + email + "<br>聯絡電話:" + phone + "<br>內容:" + message,
					"text/html;charset=UTF-8");

			mimemessage.setFrom(new InternetAddress("test@gmail.com", subject, "UTF-8"));

			// 將message傳出
			Transport transport = session.getTransport();
			transport.send(mimemessage);
			transport.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
