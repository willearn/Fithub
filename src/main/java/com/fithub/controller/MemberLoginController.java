package com.fithub.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fithub.model.member.IMemberService;
import com.fithub.model.member.Member;
import com.fithub.model.user.ResponseModelMember;

@RestController
@CrossOrigin()
public class MemberLoginController {

	@Autowired
	private IMemberService mService;
	
	@PostMapping("/memberlogin")
	public String login(@RequestBody Member mBean) {
		ResponseModelMember loginResponse = new ResponseModelMember();
		
		Member resultBean = mService.checkLogin(mBean); // 驗證帳號密碼
		if (resultBean != null) {
			String token = "";
			try {
				token = generateToken(resultBean.getMemberemail()); // 生成token其中夾帶使用者帳號
				loginResponse.setStatus(true);
				loginResponse.setToken(token);
				loginResponse.setMembername(resultBean.getMembername());
				loginResponse.setMemberid(resultBean.getMemberid());
				loginResponse.setMemberemail(resultBean.getMemberemail());
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		return loginResponse.toJSONString();
	}
	
	@PostMapping("/memberGooglelogin")
	public String googleLogin(@RequestBody Member mBean) {
		System.out.println(mBean);
		ResponseModelMember loginResponse = new ResponseModelMember();
		
		Member resultBean = mService.checkGoogleLogin(mBean); // 驗證帳號密碼
		if (resultBean != null) {
			String token = "";
			try {
				token = generateToken(resultBean.getMemberemail()); // 生成token其中夾帶使用者帳號
				loginResponse.setStatus(true);
				loginResponse.setToken(token);
				loginResponse.setMembername(resultBean.getMembername());
				loginResponse.setMemberid(resultBean.getMemberid());
				loginResponse.setMemberemail(resultBean.getMemberemail());
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		return loginResponse.toJSONString();
	}
	
	@PostMapping("/memberauth")
	public String auth(@RequestBody Map<String, String> request) {
		ResponseModelMember response = new ResponseModelMember();
		String token = request.get("token");
		
		
		try {
			String memberemail = verifyToken(token);
			String newToken = generateToken(memberemail);
			
			Member resultBean = mService.findByEmail(memberemail);
			response.setStatus(true);
			response.setMemberemail(memberemail);
			response.setToken(newToken);
			response.setMembername(resultBean.getMembername());
			response.setMemberid(resultBean.getMemberid());

		} catch (JWTVerificationException exception) {
			System.out.println("jwt verify fail");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println(response.toJSONString());

		return response.toJSONString();
	}
	
	String SECRET_KEY = ".*L(MKxk{'OaN{!4^\"L@5!1p1^!8:S";
	Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
	
	String generateToken(String memberemail) throws Exception {
		String token = "";
		LocalDateTime dateTime = LocalDateTime.now().plusMinutes(10);
		Date expireTime = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

		token = JWT.create().withClaim("memberemail", memberemail).withExpiresAt(expireTime).sign(algorithm);

		return token;
	}

	String verifyToken(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(algorithm).build();
		String memberemail = "";

		DecodedJWT decodedJWT = verifier.verify(token);
		memberemail = decodedJWT.getClaim("memberemail").asString();

		return memberemail;
	}
}
