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
import com.fithub.model.member.MemberService;


//@RequestMapping("/members")
//@RestController
@Controller
public class MemberController {

	@Autowired
	private MemberService mService;
	
	@GetMapping("/members/showMembers")
	public String getMembers() {
		return "member/memberHome";
	}

	// 所有會員資料
	@GetMapping("/members")
	public ResponseEntity<List<Member>> getAllMembers() {
		List<Member> members = mService.findAll();
		return ResponseEntity.ok(members);
	}

	// 單一會員資料
	@GetMapping("/members/{id}")
	public ResponseEntity<Member> getMember(@PathVariable Integer id) {
//	        Boolean exists = mService.findById(id);

		Member resultBean = mService.findById(id);

		if (resultBean != null) {
			// Assuming you have a method to get a single member by ID
			// Member member = mService.findOne(id);
			// return ResponseEntity.ok(member);
//	            return ResponseEntity.ok().build();
			return new ResponseEntity<Member>(resultBean,HttpStatus.OK);
		} else {
//	            return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Member>(resultBean,HttpStatus.NOT_FOUND);
	}

	// 新增會員資料

	@PostMapping("/members")
	public ResponseEntity<Member> insertMember(@RequestBody Member member) {
		Member savedMember = mService.insert(member);
		return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
	}

	@PutMapping("/members/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable Integer id, @RequestBody Member updatedMember) {
//	        Boolean exists = mService.findById(id);
//	        if (exists) {
		
		Member resultBean = mService.findById(id);

		if (resultBean != null) {
			mService.updateById(updatedMember);
			return ResponseEntity.ok(updatedMember);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// 刪除會員資料
	@DeleteMapping("/members/{id}")
	public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
//	        Boolean exists = mService.findById(id);
//	        if (exists) {

		Member resultBean = mService.findById(id);

		if (resultBean != null) {
			mService.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}