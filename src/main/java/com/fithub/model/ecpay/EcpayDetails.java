package com.fithub.model.ecpay;

import lombok.Data;

@Data
public class EcpayDetails {

	private String CustomField1;
	private String CustomField2;
	private String CustomField3;
	private String CustomField4;
	private String MerchantID;
	private String MerchantTradeNo;
	private String PaymentDate;
	private String PaymentType;
	private String PaymentTypeChargeFee;
	private String RtnCode;
	private String RtnMsg;
	private String SimulatePaid;
	private String StoreID;
	private String TradeAmt;
	private String TradeDate;
	private String TradeNo;
	private String CheckMacValue;
}


//4311-9522-2222-2222 安全碼 : 222	測試用信用卡
//https://vendor-stage.ecpay.com.tw/User/LogOn_Step1#	綠界測試後台
//綠界回傳格式form-data
//CustomField1: 				提供合作廠商使用記錄用客製化使用欄位
//CustomField2: 
//CustomField3: 
//CustomField4: 
//MerchantID: 2000132 特店編號
//MerchantTradeNo: 1530833465cc4165864a 	特店交易編號
//PaymentDate: 2023/09/02 18:30:14 	付款時間
//PaymentType: Credit_CreditCard  	特店選擇的付款方式
//PaymentTypeChargeFee: 5 		交易手續費金額
//RtnCode: 1 				若回傳值為1時，為付款成功，其餘代碼皆為交易異常，請至廠商管理後台確認後再出貨
//RtnMsg: 交易成功
//SimulatePaid: 0				是否為模擬付款 0非模擬 1模擬
//StoreID: 				特店旗下店舖代號
//TradeAmt: 50				交易金額
//TradeDate: 2023/09/02 18:29:19		訂單成立時間
//TradeNo: 2309021829195928		綠界的交易編號，請保存綠界的交易編號與特店交易編號[MerchantTradeNo]的關連。
//CheckMacValue: 3F9B08139CBB13EE0894BF8E5A5D4BC1B790853BEFF1630A5BAC7CBB0A8CDB29	特店必須檢查檢查碼 [CheckMacValue] 來驗證