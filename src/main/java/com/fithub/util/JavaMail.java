package com.fithub.util;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

public class JavaMail {

	private String username = "cc2260112@gmail.com";
	private String password = "wdzzghdrzqfcmdlo";
	private String customer = "cc2260112@gmail.com";
	private String subject = "測試";
	private String txt = "信件內容文字";

	public void SendMail() {
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.port", "465");
		//顯示連線資訊
		prop.put("mail.debug","true");

		Session session = Session.getDefaultInstance(prop, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		MimeMessage message = new MimeMessage(session);
		
		
		try {
			//寄件者
			message.setSender(new InternetAddress(username));
			
			//收件者
			message.setRecipient(RecipientType.TO, new InternetAddress(customer));
			
			//標題
			message.setSubject(subject);
			
			//內容
			message.setContent(txt, "text/html;charset=UTF-8");
			
			//將message傳出
			Transport transport = session.getTransport();
			transport.send(message);
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
