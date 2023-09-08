package com.fithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.ecpay.EcpayDetailDTO;
import com.fithub.model.ecpay.EcpayDetails;
import com.fithub.model.ecpay.EcpayOrderDTO;
import com.fithub.model.ecpay.EcpayService;
import com.fithub.model.order.IOrderService;
import com.fithub.model.order.Order;
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
	
	@Autowired
	IOrderService iOrderService;
	

	@PostMapping("/ecpayCheckout")
	public String ecpayCheckout(@RequestBody EcpayDetailDTO ecpayDetailDTO) {
		String aioCheckOutALLForm = ecpayService.ecpayCheckout(ecpayDetailDTO);

		return aioCheckOutALLForm;
	}
	
	@PostMapping("/ecpayCheckoutOrder")
	public String ecpayCheckout(@RequestBody EcpayOrderDTO ecpayOrderDTO) {
		String aioCheckOutALLForm = ecpayService.ecpayCheckout(ecpayOrderDTO);

		return aioCheckOutALLForm;
	}
	
	

	// 取得綠界回傳資訊並回傳驗證,更新訂單狀態
	@PostMapping("/callback")
	public String handleCallback(@ModelAttribute EcpayDetails ecpayDetails) {
		
		
		String rentOrderId = ecpayDetails.getMerchantTradeNo().substring(15); // 只保留索引15後的字串,去掉UUID

		//判斷是否成功
		if(ecpayDetails.getRtnCode().equals("1")) {
		RentOrder rentOrder = new RentOrder();
		rentOrder.setRentorderid(Integer.parseInt(rentOrderId));
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
	@PostMapping("/OrderCallback")
	public String OrderhandleCallback(@ModelAttribute EcpayDetails ecpayDetails) {
		
		String OrderId = ecpayDetails.getMerchantTradeNo().substring(10); // 只保留索引10後的字串,去掉UUID
		System.out.println(ecpayDetails);
		if(ecpayDetails.getRtnCode().equals("1")) {
			Order order = new Order();
			order.setOrderId(Integer.parseInt(OrderId));
			order.setOrderCondition("已付款");
			System.out.println(order.getOrderId());
			
			
			Boolean updateOrder = iOrderService.updateConditionById(order.getOrderId(),order.getOrderCondition());
			System.out.println(updateOrder);
		}
		
		
		return null;
		
	}
	
}