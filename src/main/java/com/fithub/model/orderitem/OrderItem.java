package com.fithub.model.orderitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fithub.model.classes.Classes;
import com.fithub.model.coupon.Coupon;
import com.fithub.model.order.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="orderitem")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="itemid")
	private int itemId;

	@Column(name="ORDERID")
	private int orderId;
	
	@Column(name="CLASSID")
	private int classId;
		
	@Column(name="couponid")
	private int couponId; 
	
	@JsonIgnore //等下刪
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ORDERID",insertable = false,updatable = false)
	private Order order;
	
	@JsonIgnoreProperties({ "epmloyee","cart","classesSet","order","wishlist" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CLASSID",insertable = false,updatable = false)
	private Classes classes;
	
//	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="couponid",insertable = false,updatable = false)
	private Coupon coupon;
	
}
