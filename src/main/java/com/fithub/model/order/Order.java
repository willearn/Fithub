package com.fithub.model.order;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fithub.model.orderitem.OrderItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="order")
public class Order {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderid")
	private int orderId;
	
	@Column(name="orderdeate")
	private String orderDate;
	
	@Column(name="ordercondition")
	private String orderCondition;
	
	@Column(name="MEMBERID")
	private int memberId;
	
	@Column(name="ordertotalamount")
	private int orderTotalAmount;
	
	@Column(name="orderpaymentmethod")
	private String orderPaymentMethod;
	
	@Column(name="orderState")
	private int orderstate;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="MEMBERID",insertable = false,updatable = false)
//	private Member member;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	@JsonIgnore
	private Set<OrderItem> orderItem = new HashSet<OrderItem>();
	
}
