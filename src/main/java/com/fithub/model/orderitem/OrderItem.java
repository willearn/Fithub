package com.fithub.model.orderitem;

import com.fithub.model.classes.Classes;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ORDERID",insertable = false,updatable = false)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CLASSID",insertable = false,updatable = false)
	private Classes classes;
	
	
}
