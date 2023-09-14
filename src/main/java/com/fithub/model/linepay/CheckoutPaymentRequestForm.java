package com.fithub.model.linepay;

import java.math.BigDecimal;
import java.util.List;


import lombok.Data;

@Data
public class CheckoutPaymentRequestForm {

	private BigDecimal amount;
	private String currency;
	private String orderId;
	private List<ProductPackageForm> packages;
	private RedirectUrls redirectUrls;
}

