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
	
	public void sendVerificationCode() throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mHelper = new MimeMessageHelper(mimeMessage,true);
		mHelper.setFrom("Wayne's Talk <waynestalk@gmail.com>");
		mHelper.setTo("tw3555488@gmail.com");
		mHelper.setSubject("這是主旨");
		mHelper.setText("測試用",true);
		
		javaMailSender.send(mimeMessage);
	}
}
