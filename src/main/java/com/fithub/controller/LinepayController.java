package com.fithub.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fithub.model.linepay.CheckoutPaymentRequestForm;
import com.fithub.model.linepay.ProductForm;
import com.fithub.model.linepay.ProductPackageForm;
import com.fithub.model.linepay.RedirectUrls;

@CrossOrigin
@RestController
@RequestMapping("/linepay")
public class LinepayController {

	public static String encrypt(final String keys, final String data) {
		return toBase64String(HmacUtils.getHmacSha256(keys.getBytes()).doFinal(data.getBytes()));
	}

	public static String toBase64String(byte[] bytes) {
		byte[] byteArray = Base64.encodeBase64(bytes);
		return new String(byteArray);
	}
	
	public static String toJson(Object object) {
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        return objectMapper.writeValueAsString(object);
	    } catch (Exception e) {
	        // 处理异常
	        e.printStackTrace();
	        return null;
	    }
	}

	@PostMapping("/linepayCheckout")
	public String linepayCheckout() {

		CheckoutPaymentRequestForm form = new CheckoutPaymentRequestForm();
		form.setAmount(new BigDecimal("100"));
		form.setCurrency("TWD");
		form.setOrderId("acb0111222123");

		ProductPackageForm productPackageForm = new ProductPackageForm();
		productPackageForm.setId("abc01112222");
//		productPackageForm.setName("shop_name");
		productPackageForm.setAmount(new BigDecimal("100"));

		ProductForm productForm = new ProductForm();
//		productForm.setId("product_id");
		productForm.setName("bag");
//		productForm.setImageUrl("");
		productForm.setQuantity(new BigDecimal("1"));
		productForm.setPrice(new BigDecimal("100"));

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

		String ChannelSecret = "0dc03aeb8d056d0b9c7624b52428da7a";
		String requestUri = "/v3/payments/request";
		String nonce = UUID.randomUUID().toString();
		String signature = encrypt(ChannelSecret, ChannelSecret + requestUri + toJson(form) + nonce);
		System.out.println(signature);
		System.out.println(nonce);
		return signature;
		
	}
}
