package com.fithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fithub.model.member.Member;
import com.fithub.model.member.MemberService;
import com.fithub.model.user.ResponseModel;

public class MemberLoginController {

	@Autowired
	private MemberService mService;
	
	@PostMapping("/memberlogin")
	public String login(@RequestBody Member mBean) {
		ResponseModel loginResponse = new ResponseModel();
		
		Member resultBean = mService.checkLogin(mBean); // 驗證帳號密碼
		if (resultBean != null) {
			String token = "";
			try {
//				token = generateToken(resultBean.getEmployeeaccount()); // 生成token其中夾帶使用者帳號
				loginResponse.setStatus(true);
				loginResponse.setToken(token);
//				loginResponse.setUsername(resultBean.getEmployeeaccount());
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		return loginResponse.toJSONString();
	}
}
