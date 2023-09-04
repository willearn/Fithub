package com.fithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.ecpay.EcpayDetails;
import com.fithub.model.ecpay.EcpayService;
import com.fithub.model.rentorder.IRentOrderService;
import com.fithub.model.rentorder.RentOrder;
import com.fithub.model.rentorder.RentOrderRepository;

import ecpay.payment.integration.ecpayOperator.EcpayFunction;

@CrossOrigin
@RestController
@RequestMapping("/ecpay")
public class EcpayController {

	@Autowired
	private EcpayService ecpayService;

	@Autowired
	IRentOrderService iRentOrderService;
	
	@Autowired
	RentOrderRepository rentOrderRepository;

	@PostMapping("/ecpayCheckout")
	public String ecpayCheckout(@RequestBody RentOrder rentOrder) {
		String aioCheckOutALLForm = ecpayService.ecpayCheckout(rentOrder);

		return aioCheckOutALLForm;
	}

	// 取得綠界回傳資訊並回傳驗證
	@PostMapping("/callback")
	public String handleCallback(@ModelAttribute EcpayDetails ecpayDetails) {
	
		if(ecpayDetails.getRtnCode().equals("1")) {
		RentOrder rentOrder = new RentOrder();
		rentOrder.setRentorderid(41);
		rentOrder.setRentstatus("已付款");
		Boolean updateResult =  iRentOrderService.updateRentstatusById(rentOrder.getRentorderid(),rentOrder.getRentstatus());
		System.out.println(updateResult);
		}

		// 產生驗證碼
		String checkMacValue = EcpayFunction.genCheckMacValue("5294y06JbISpM5x9", "v77hoKGq4kWxNNIS", ecpayDetails);
		// 確認與綠界回傳的驗證碼相同
		if (checkMacValue == ecpayDetails.getCheckMacValue()) {
			return "1|OK";
		}
		return null;
	}
}