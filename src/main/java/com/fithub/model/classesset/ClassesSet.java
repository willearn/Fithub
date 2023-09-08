package com.fithub.model.classesset;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fithub.model.order.Order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="classesset")
public class ClassesSet {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="classessetid")
	private int classesSetId;
	
	@Column(name="COUPONID")
	private int couponId;
	
	@Column(name="classessetname")
	private String classesSetName;
	
	@Column(name="classessetdescription")
	private String classessetDescription;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="COUPONID",insertable = false,updatable = false)
//	private Coupon coupon;
//	
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "OrderItem", joinColumns = 
	@JoinColumn(name = "classid"), inverseJoinColumns = @JoinColumn(name = "orderid"))
	private List<Order> order = new ArrayList<Order>();
}
