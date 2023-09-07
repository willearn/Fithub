package com.fithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> sendEmail(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("context") String context) {

		try {
			// 調用emailService的sendEmail方法，傳遞接收到的name、email和context
			javaMail.SendMail(name, email, context);

			// 如果郵件發送成功，返回HTTP OK狀態碼
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// 如果出現異常，返回HTTP BAD_REQUEST狀態碼
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
