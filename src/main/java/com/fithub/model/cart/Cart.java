package com.fithub.model.cart;

import com.fithub.model.classes.Classes;

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
@Table(name="cart")
public class Cart {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cartid")
	private int cartId;
	
	@Column(name="MEMBERID")
	private int memberId;
	
	@Column(name="CLASSID")
	private int classId;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="MEMBERID",insertable = false,updatable = false)
//	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CLASSID",insertable = false,updatable = false)
	private Classes classes;
	

}
