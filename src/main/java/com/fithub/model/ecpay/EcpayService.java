package com.fithub.model.ecpay;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fithub.model.classes.Classes;
import com.fithub.model.rentorder.RentOrder;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Service
public class EcpayService {

	public String ecpayCheckout(RentOrder rentOrder) {

		System.out.println("test:"+rentOrder.getRentorderdate());
		
		AllInOne all = new AllInOne("");
		// 產生隨機編號
		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
		
		AioCheckOutALL obj = new AioCheckOutALL();
		// 特店訂單編號
		obj.setMerchantTradeNo(uuId);
		// 特店交易時間 綠界後台查詢以這為基準
		obj.setMerchantTradeDate(rentOrder.getRentorderdate());
		// 如果商品名稱有多筆，需在金流選擇頁一行一行顯示商品名稱的話，商品名稱請以符號#分隔
		obj.setItemName("攀岩教室");
		// 總金額
		obj.setTotalAmount(Integer.toString(rentOrder.getRentamount()));
		// 交易描述
		obj.setTradeDesc("場地租借");
		// 交易結果回傳網址，只接受 https 開頭的網站，可以使用 ngrok
		obj.setReturnURL("https://e18a-2001-b011-c009-cc5d-182c-398-34ef-8dbe.ngrok-free.app/fithub/ecpay/callback");
		// 是否需要額外的付款資訊 Y|N
		obj.setNeedExtraPaidInfo("N");
		// 消費者點選此按鈕後，會將頁面導回到此設定的網址
		obj.setClientBackURL("http://localhost:5173/");
		
		// 有別於ReturnURL(server端的網址)，OrderResultURL為特店的client端(前端)網址。消費者付款完成後，綠界會將付款結果參數以POST方式回傳到到該網址。
		// obj.getOrderResultURL()
		
		String form = all.aioCheckOut(obj, null);
		return form;
	}
	
//	// 調用多載
//	public String ecpayCheckout(Classes classes) {
//
//		AllInOne all = new AllInOne("");
//		// 產生隨機編號
//		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
//		
//		AioCheckOutALL obj = new AioCheckOutALL();
//		// 特店訂單編號
//		obj.setMerchantTradeNo(uuId);
//		// 特店交易時間 綠界後台查詢以這為基準
//		obj.setMerchantTradeDate("");
//		// 如果商品名稱有多筆，需在金流選擇頁一行一行顯示商品名稱的話，商品名稱請以符號#分隔
//		obj.setItemName("TestItem");
//		// 總金額
//		obj.setTotalAmount("50");
//		// 交易描述
//		obj.setTradeDesc("test Description");
//		// 交易結果回傳網址，只接受 https 開頭的網站，可以使用 ngrok
//		obj.setReturnURL("https://8196-2001-b011-c009-4676-182c-398-34ef-8dbe.ngrok-free.app/fithub/ecpay/callback");
//		// 是否需要額外的付款資訊 Y|N
//		obj.setNeedExtraPaidInfo("N");
//		// 消費者點選此按鈕後，會將頁面導回到此設定的網址
//		obj.setClientBackURL("http://localhost:5173/");
//		
//		// 有別於ReturnURL(server端的網址)，OrderResultURL為特店的client端(前端)網址。消費者付款完成後，綠界會將付款結果參數以POST方式回傳到到該網址。
//		// obj.getOrderResultURL()
//		
//		
//		String form = all.aioCheckOut(obj, null);
//		
//		return form;
//	}
}
