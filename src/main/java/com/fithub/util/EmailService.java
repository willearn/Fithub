package com.fithub.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
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
	
	
	public void sendEmail(String name,String email ,String text) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mHelper = new MimeMessageHelper(mimeMessage,true);
		// 發件人
		mHelper.setFrom(name);
		// 收件人
		mHelper.setTo("iSpanFithub@gmail.com");
		// 消息頭
		mHelper.setSubject("信箱:" + email);
		// 消息體
		mHelper.setText("內容:" + text );
		javaMailSender.send(mimeMessage);
	}
}
