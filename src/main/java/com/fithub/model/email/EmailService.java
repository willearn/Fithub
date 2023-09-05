package com.fithub.model.email;

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
}
