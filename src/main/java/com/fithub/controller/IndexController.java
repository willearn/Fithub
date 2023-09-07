package com.fithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.javamail.FormData;
import com.fithub.util.JavaMail;

@CrossOrigin
@RestController
@RequestMapping("/index")
public class IndexController {

//	@GetMapping("/")
//	public String home() {
//		return "index";
//	}

	@Autowired
	private JavaMail javaMail;

	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody FormData formdata) {

		String name = formdata.getName();
		String email = formdata.getEmail();
		String phone = formdata.getPhone();
		String message = formdata.getMessage();
		String subject = formdata.getSubject();

		try {
			// 調用emailService的sendEmail方法
			javaMail.SendMail(name, email, phone, message , subject);

			// 如果郵件發送成功，返回HTTP OK狀態碼
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// 如果出現異常，返回HTTP BAD_REQUEST狀態碼
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
