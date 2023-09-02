package com.fithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.ecpay.EcpayDetails;
import com.fithub.model.ecpay.EcpayService;

import ecpay.payment.integration.ecpayOperator.EcpayFunction;

@RestController
@RequestMapping("/ecpay")
public class EcpayController {

	@Autowired
	private EcpayService ecpayService;

	@PostMapping("/ecpayCheckout")
	public String ecpayCheckout() {
		String aioCheckOutALLForm = ecpayService.ecpayCheckout();
		
		return aioCheckOutALLForm;
	}

	// 取得綠界回傳資訊並回傳驗證
	@PostMapping("/callback")
	public String handleCallback(@ModelAttribute EcpayDetails ecpayDetails) {

		System.out.println(ecpayDetails);
		
		// 產生驗證碼
		String checkMacValue = EcpayFunction.genCheckMacValue("5294y06JbISpM5x9", "v77hoKGq4kWxNNIS", ecpayDetails);
		// 確認與綠界回傳的驗證碼相同
		if (checkMacValue == ecpayDetails.getCheckMacValue()) {
			return "1|OK";
		}
		return null;
	}
}