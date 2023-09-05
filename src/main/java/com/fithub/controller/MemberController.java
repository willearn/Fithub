package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fithub.model.backstageaccount.BackStageAccount;
import com.fithub.model.email.EmailService;
import com.fithub.model.member.Member;
import com.fithub.model.member.MemberService;

import jakarta.mail.MessagingException;

//@RequestMapping("/members")
//@RestController
@Controller
@CrossOrigin
public class MemberController {

	@Autowired
	private MemberService mService;

	@GetMapping("/members/showMembers")
	public String getMembers() {
		return "member/memberHome";
	}

	// 所有會員資料
	@GetMapping("/members")
	public ResponseEntity<?> getAllMembers() {
		try {
			List<Member> members = mService.findAll();
			return new ResponseEntity<>(members, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 單一會員資料
	@GetMapping("/members/{id}")
	public ResponseEntity<?> getMember(@PathVariable Integer id) {
		try {
			Member resultBean = mService.findById(id);
			return new ResponseEntity<Member>(resultBean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


//	// 新增會員資料
	@PostMapping("/members")
	public ResponseEntity<Object> insertMember(@RequestBody Member mBean) {
		try {
			mService.insert(mBean);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Object> insert(@RequestBody Member mBean){
		return null;
		
	}

//修改會員資料
	@PutMapping("/members/{id}")
	public ResponseEntity<?> updateMember(@PathVariable Integer id, @RequestBody Member mBean) {
		try {
			mService.update(mBean);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 刪除會員資料
	@DeleteMapping("/members/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable Integer id) {
		try {
			mService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}