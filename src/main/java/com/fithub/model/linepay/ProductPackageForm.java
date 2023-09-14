package com.fithub.model.linepay;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ProductPackageForm {

	private String id;
	private String name;
	private BigDecimal amount;
	private List<ProductForm> products;
}
