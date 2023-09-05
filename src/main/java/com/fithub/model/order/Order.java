package com.fithub.model.order;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fithub.model.member.Member;
import com.fithub.model.orderitem.OrderItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Order {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderid")
	private int orderId;
	
	@Column(name="orderdate")
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
	
	@JsonIgnoreProperties({ "rentOrders" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MEMBERID",insertable = false,updatable = false)
	private Member member;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderItem> orderItem = new ArrayList<>();
	
}
