package com.fithub.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fithub.model.user.ResponseModel;
import com.fithub.model.user.UserModel;

@RestController
@CrossOrigin()
public class LoginController {

	@PostMapping("/login")
	public String login(@RequestBody UserModel userModel) {
		System.out.println("test");
		ResponseModel loginResponse = new ResponseModel();

		boolean flag = userModel.getUsername().equals("root") && userModel.getPassword().equals("root"); // 驗證帳號密碼
		if (flag) {
			String token = "";
			try {
				token = generateToken(userModel.getUsername()); // 生成token其中夾帶使用者帳號
				loginResponse.setStatus(true);
				loginResponse.setToken(token);
				loginResponse.setUsername(userModel.getUsername());
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		return loginResponse.toJSONString();
	}

	@PostMapping("/auth")
	public String auth(@RequestBody Map<String, String> request) {

		ResponseModel response = new ResponseModel();
		String token = request.get("token");
		try {
			String username = verifyToken(token);
			String newToken = generateToken(username);

			response.setStatus(true);
			response.setUsername(username);
			response.setToken(newToken);

		} catch (JWTVerificationException exception) {
			System.out.println("jwt verify fail");
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return response.toJSONString();
	}

	String SECRET_KEY = "secretKey";
	Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

	String generateToken(String username) throws Exception {
		String token = "";
		LocalDateTime dateTime = LocalDateTime.now().plusMinutes(10);
		Date expireTime = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

		token = JWT.create().withClaim("username", username).withExpiresAt(expireTime).sign(algorithm);

		return token;
	}

	String verifyToken(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(algorithm).build();
		String username = "";

		DecodedJWT decodedJWT = verifier.verify(token);
		username = decodedJWT.getClaim("username").asString();

		return username;
	}
}
