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
	public ResponseEntity<List<VerificationCode>> getAllVerificationcode() {
		List<VerificationCode> verificationcode = vService.findAll();
		return ResponseEntity.ok(verificationcode);
	}

	// 查詢單一驗證碼
	@GetMapping("/verificationcode/{email}")
	public ResponseEntity<VerificationCode> getVerificationcode(@PathVariable String email) {
//	        Boolean exists = mService.findById(id);

		VerificationCode resultBean = vService.findByEmail(email);

		if (resultBean != null) {

			return new ResponseEntity<VerificationCode>(resultBean,HttpStatus.OK);
		} else {
		}
		return new ResponseEntity<VerificationCode>(resultBean,HttpStatus.NOT_FOUND);
	}

	// 新增驗證碼
	@PostMapping("/verificationcode")
	public ResponseEntity<VerificationCode> insertVerificationCode(@RequestBody VerificationCode verificationcode) {
		VerificationCode savedVerificationcode = vService.insert(verificationcode);
		return new ResponseEntity<>(savedVerificationcode, HttpStatus.CREATED);
	}

	@PutMapping("/verificationcode/{email}")
	public ResponseEntity<VerificationCode> updateVerificationcode(@PathVariable String email, @RequestBody VerificationCode updatedVerificationcode) {
//	        Boolean exists = mService.findById(id);
//	        if (exists) {
		
		VerificationCode resultBean = vService.findByEmail(email);

		if (resultBean != null) {
			vService.updateByEmail(updatedVerificationcode.getEmail());
			return ResponseEntity.ok(updatedVerificationcode);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// 刪除驗證碼
	@DeleteMapping("/verificationcode/{email}")
	public ResponseEntity<Void> deleteMember(@PathVariable String email) {
//	        Boolean exists = mService.findById(id);
//	        if (exists) {

		VerificationCode resultBean = vService.findByEmail(email);

		if (resultBean != null) {
			vService.deleteByEmail(email);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}