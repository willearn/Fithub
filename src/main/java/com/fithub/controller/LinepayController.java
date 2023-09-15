package com.fithub.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fithub.model.linepay.CheckoutPaymentRequestForm;
import com.fithub.model.linepay.LinePayService;
import com.fithub.model.linepay.ProductForm;
import com.fithub.model.linepay.ProductPackageForm;
import com.fithub.model.linepay.RedirectUrls;
import com.fithub.model.rentorder.IRentOrderService;
import com.fithub.model.rentorder.RentOrder;

@CrossOrigin
@RestController
@RequestMapping("/linepay")
public class LinepayController {
	
	@Autowired
	private LinePayService linePayService;
	
	@Autowired
	private IRentOrderService iRentOrderService;

	// 將ChannelSecret, ChannelSecret + requestUri + mapper.writeValueAsString(form)
	// + nonce加密
	public static String encrypt(final String keys, final String data) {
		return toBase64String(
				HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, keys.getBytes()).doFinal(data.getBytes()));
	}

	// 轉成BASE64
	public static String toBase64String(byte[] bytes) {
		byte[] byteArray = Base64.encodeBase64(bytes);
		return new String(byteArray);
	}

	@PostMapping("/linepayCheckout")
	public ResponseEntity<String> linepayCheckout(@RequestBody Map<String, Object> order) {

		// 接收前端訂單資訊
		String orderId = ((Integer) order.get("rentorderid")).toString();
		String rentAmount = ((Integer) order.get("rentamount")).toString();
		String classroomName = (String) order.get("classroomname");

		// 將訂單資訊轉為Line Pay需要的訂單格式
		// 由form內包package package在包product
		CheckoutPaymentRequestForm form = new CheckoutPaymentRequestForm();
		// 總價錢(計算要正確)
		form.setAmount(new BigDecimal(rentAmount));
		// 設定幣種
		form.setCurrency("TWD");
		// 商家提供訂單編號
		form.setOrderId(orderId);

		ProductPackageForm productPackageForm = new ProductPackageForm();
		productPackageForm.setId(orderId);
//		productPackageForm.setName("shop_name");
		productPackageForm.setAmount(new BigDecimal(rentAmount));

		ProductForm productForm = new ProductForm();
//		productForm.setId("product_id");
		// 商品名稱
		productForm.setName(classroomName);
//		productForm.setImageUrl("");
		// 商品數量
		productForm.setQuantity(new BigDecimal("1"));
		// 商品價格
		productForm.setPrice(new BigDecimal(rentAmount));

		List<ProductForm> productList = new ArrayList<>();
		productList.add(productForm);
		productPackageForm.setProducts(productList);

		List<ProductPackageForm> productPackageForms = new ArrayList<>();
		productPackageForms.add(productPackageForm);

		form.setPackages(productPackageForms);

		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setConfirmUrl("http://localhost:5173/");
		redirectUrls.setCancelUrl("http://localhost:5173/");
		form.setRedirectUrls(redirectUrls);

		ObjectMapper mapper = new ObjectMapper();
		String ChannelSecret = "0dc03aeb8d056d0b9c7624b52428da7a";
		String requestUri = "/v3/payments/request";
		String nonce = UUID.randomUUID().toString();
		String signature;
		try {
			signature = encrypt(ChannelSecret, ChannelSecret + requestUri + mapper.writeValueAsString(form) + nonce);
			
			// LinePay回傳成立訂單和付款頁面
			ResponseEntity<String> response =  linePayService.createLinePayOrder(signature, nonce, mapper.writeValueAsString(form));
			
			if(response != null) {
				RentOrder rentOrder = new RentOrder();
				rentOrder.setRentorderid(Integer.parseInt(orderId));
				rentOrder.setRentstatus("已付款");
				Boolean updateResult =  iRentOrderService.updateRentstatusById(rentOrder.getRentorderid(),rentOrder.getRentstatus());
				System.out.println(updateResult);
			}
			
			
			//從後端linePayService發送http請求
			return response;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
