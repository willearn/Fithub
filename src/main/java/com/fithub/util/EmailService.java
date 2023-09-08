package com.fithub.util;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

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
		mHelper.setFrom("Fithub <iSpanFithub@gmail.com>");
		mHelper.setTo(email);
		mHelper.setSubject("Fithub信箱驗證碼");
		mHelper.setText("這是您的信箱驗證碼:" + verificationcode,true);
		
		javaMailSender.send(mimeMessage);
	}
	
	public void sendForgotPasswordEmail(String email) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mHelper = new MimeMessageHelper(mimeMessage,true);
		mHelper.setFrom("Fithub <iSpanFithub@gmail.com>");
		mHelper.setTo(email);
		mHelper.setSubject("Fithub密碼重設");
		mHelper.setText("請點選連結重設密碼:" + "",true);
		
		javaMailSender.send(mimeMessage);
	}
	
	String SECRET_KEY = "nO,4o*Czn|9Tj8P[.j,JT%tFY=|=A{";
	Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

	String generateToken(String email) throws Exception {
		String token = "";
		LocalDateTime dateTime = LocalDateTime.now().plusMinutes(10);
		Date expireTime = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

		token = JWT.create().withClaim("memberemail", email).withExpiresAt(expireTime).sign(algorithm);

		return token;
	}

	String verifyToken(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(algorithm).build();
		String username = "";

		DecodedJWT decodedJWT = verifier.verify(token);
		username = decodedJWT.getClaim("memberemail").asString();

		return username;
	}
}



