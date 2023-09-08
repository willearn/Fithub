package com.fithub.model.ecpay;

import lombok.Data;

@Data
public class EcpayOrderDTO {
	
	private String orderId;
	private String orderdate;
	private String orderamount;
	private String coursename;
	
}
