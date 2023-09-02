package com.fithub.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.util.EcpayService;

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

	@PostMapping("/callback")
	public void handleCallback(@RequestParam(value = "CustomField1", required = false) String customField1,
								@RequestParam(value = "CustomField2", required = false) String customField2,
								@RequestParam(value = "CustomField3", required = false) String customField3,
								@RequestParam(value = "CustomField4", required = false) String customField4,
								@RequestParam(value = "MerchantID", required = false) String merchantID,
								@RequestParam(value = "MerchantTradeNo", required = false) String merchantTradeNo,
								@RequestParam(value = "PaymentDate", required = false) String paymentDate,
								@RequestParam(value = "PaymentType", required = false) String paymentType,
								@RequestParam(value = "PaymentTypeChargeFee", required = false) String paymentTypeChargeFee,
								@RequestParam(value = "RtnCode", required = false) String rtnCode,
								@RequestParam(value = "RtnMsg", required = false) String rtnMsg,
								@RequestParam(value = "SimulatePaid", required = false) String simulatePaid,
								@RequestParam(value = "StoreID", required = false) String storeID,
								@RequestParam(value = "TradeAmt", required = false) String tradeAmt,
								@RequestParam(value = "TradeDate", required = false) String tradeDate,
								@RequestParam(value = "TradeNo", required = false) String tradeNo,
								@RequestParam(value = "CheckMacValue", required = false) String checkMacValue) 
	{
		System.out.println("CustomField1: " + customField1);
		System.out.println("CustomField2: " + customField2);
		System.out.println("CustomField3: " + customField3);
		System.out.println("CustomField4: " + customField4);
		System.out.println("MerchantID: " + merchantID);
		System.out.println("MerchantTradeNo: " + merchantTradeNo);
		System.out.println("PaymentDate: " + paymentDate);
		System.out.println("PaymentType: " + paymentType);
		System.out.println("PaymentTypeChargeFee: " + paymentTypeChargeFee);
		System.out.println("RtnCode: " + rtnCode);
		System.out.println("RtnMsg: " + rtnMsg);
		System.out.println("SimulatePaid: " + simulatePaid);
		System.out.println("StoreID: " + storeID);
		System.out.println("TradeAmt: " + tradeAmt);
		System.out.println("TradeDate: " + tradeDate);
		System.out.println("TradeNo: " + tradeNo);
		System.out.println("CheckMacValue: " + checkMacValue);
	}
}