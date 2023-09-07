package com.fithub.util;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendVerificationCode(String email , String verificationcode) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mHelper = new MimeMessageHelper(mimeMessage,true);
		mHelper.setFrom("Fithub");
		mHelper.setTo(email);
		mHelper.setSubject("Fithub信箱驗證碼");
		mHelper.setText("這是您的信箱驗證碼:" + verificationcode,true);
		
		javaMailSender.send(mimeMessage);
	}
	
	
	public void sendEmail(String name,String email ,String text) throws MessagingException, UnsupportedEncodingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mHelper = new MimeMessageHelper(mimeMessage,true);
		// 發件人 參考建構子
		mHelper.setFrom(new InternetAddress(email,name,"UTF-8"));
		// 收件人
		mHelper.setTo("cc2260112@gmail.com");
		// 消息頭
		mHelper.setSubject(email);
		// 消息體
		mHelper.setText(text);
		
		javaMailSender.send(mimeMessage);
	}
}



