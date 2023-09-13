package com.fithub.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
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

import com.fithub.model.member.IMemberService;
import com.fithub.model.member.Member;


//@RequestMapping("/members")
//@RestController
@Controller
@CrossOrigin
public class MemberController {

	@Autowired
	private IMemberService mService;

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

	@GetMapping("/members/byemail/{email}")
	public ResponseEntity<?> getMemberByEmail(@PathVariable String email) {
		try {
			Member resultBean = mService.findByEmail(email);
			resultBean.setMemberpassword(null);
			return new ResponseEntity<Member>(resultBean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
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

	public ResponseEntity<Object> insert(@RequestBody Member mBean) {
		return null;

	}

//修改會員資料
	@PutMapping("/members/{id}")
	public ResponseEntity<?> updateMember(@PathVariable Integer id, @RequestBody Member mBean) {
		try {
			System.out.println(mBean);
			mService.update(mBean);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

	@PostMapping("/members/findPageByName")
	public ResponseEntity<?> findPageByName(@RequestBody String json) {
		System.out.println("JSON");
		System.out.println(json.toString());
		try {
			JSONObject obj = new JSONObject(json);

			JSONObject responseJson = new JSONObject();
			JSONArray array = new JSONArray();

			String name = obj.isNull("name") ? null : obj.getString("name");

			long count;

			// 有的話 依照name去搜尋有幾筆資料，沒有則搜尋全部
			if (name != null) {
				Page<Member> page;
				count = mService.count(obj.getString("name"));
				page = mService.findPageByName(obj.getInt("start"), obj.getInt("rows"), obj.getString("name"));

				responseJson.put("count", count);

				for (Member member : page) {
					JSONObject item = new JSONObject().put("memberid", member.getMemberid())
							.put("memberphoneno", member.getMemberphoneno()).put("membername", member.getMembername())
							.put("membergender", member.getMembergender()).put("memberemail", member.getMemberemail())
							.put("membercity", member.getMembercity()).put("memberzone", member.getMemberzone())
							.put("memberaddress", member.getMemberaddress())
							.put("memberbirthday", member.getMemberbirthday())
							.put("memberaccountsince", member.getMemberaccountsince());
					array = array.put(item);
				}
			} else {
				Page<Member> page;
				count = mService.count();
				page = mService.findByPage(obj.getInt("start"), obj.getInt("rows"));

				responseJson.put("count", count);

				for (Member member : page) {
					JSONObject item = new JSONObject().put("memberid", member.getMemberid())
							.put("memberphoneno", member.getMemberphoneno()).put("membername", member.getMembername())
							.put("membergender", member.getMembergender()).put("memberemail", member.getMemberemail())
							.put("membercity", member.getMembercity()).put("memberzone", member.getMemberzone())
							.put("memberaddress", member.getMemberaddress())
							.put("memberbirthday", member.getMemberbirthday())
							.put("memberaccountsince", member.getMemberaccountsince());
					array = array.put(item);
				}
			}

			responseJson.put("list", array);
			return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/members/changepassword/{id}")
	public ResponseEntity<?> changePassword(@PathVariable Integer id,@RequestBody Map<String, String> checkPassword){
		try {
			boolean result = mService.changePassword(id, checkPassword);

			if(result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/members/forgotpassword/{email}")
	public ResponseEntity<?> forgotPassword(@PathVariable String email){
		try {
			boolean result = mService.forgotPassword(email);
			
			if(result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/members/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody Map<String, Object> checkPassword){
		try {
			System.out.println(checkPassword);
			boolean resetPassword = mService.resetPassword(checkPassword);
			if(resetPassword) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}