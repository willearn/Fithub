package com.fithub.model.linepay;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductForm {
	private String id;
	private String name;
	private String imageUrl;
	private BigDecimal quantity;
	private BigDecimal price;
}
