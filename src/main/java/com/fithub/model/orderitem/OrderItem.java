package com.fithub.model.orderitem;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.persistence.OneToMany;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ORDERID",insertable = false,updatable = false)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CLASSID",insertable = false,updatable = false)
	private Classes classes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderItem")
	@JsonIgnore
	private Set<OrderItem> orderItem = new HashSet<OrderItem>();
	
}
