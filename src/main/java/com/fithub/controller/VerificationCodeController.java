package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fithub.model.member.Member;
import com.fithub.model.verificationcode.VerificationCode;
import com.fithub.model.verificationcode.VerificationCodeService;

@Controller
public class VerificationCodeController {

	@Autowired
	private VerificationCodeService vService;
	
	@GetMapping("/verificationcode/show")
	public String getMembers() {
		return "verificationcode";
	}

	// 所有驗證碼
	@GetMapping("/verificationcode")
	public ResponseEntity<?> getAllVerificationcode() {
			try {
		
		List<VerificationCode> verificationcode = vService.findAll();
				return new ResponseEntity<>(verificationcode, HttpStatus.OK);
		} catch (Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
			
	}

	
	
	// 查詢單一驗證碼
	@GetMapping("/verificationcode/{email}")
	public ResponseEntity<?> getVerificationcode(@PathVariable String email) {

	try {
		VerificationCode resultBean = vService.findByEmail(email);
			return new ResponseEntity<VerificationCode>(resultBean,HttpStatus.OK);
		} catch (Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
} 
	

	// 新增驗證碼
	@PostMapping("/verificationcode")

	public ResponseEntity<?> insertVerificationCode(@RequestBody VerificationCode verificationcode) {
		try {	
			VerificationCode savedVerificationcode = vService.insert(verificationcode);
		return new ResponseEntity<>(HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


	// 修改驗證碼
	@PutMapping("/verificationcode/{email}")
	public ResponseEntity<?> updateVerificationcode(@PathVariable String email, @RequestBody VerificationCode updatedVerificationcode) {
		try {
		VerificationCode resultBean = vService.findByEmail(email);
return new ResponseEntity<>(HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
		
		

	// 刪除驗證碼
	@DeleteMapping("/verificationcode/{email}")
	public ResponseEntity<?> deleteMember(@PathVariable String email) {
		try {
		VerificationCode resultBean = vService.findByEmail(email);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
		