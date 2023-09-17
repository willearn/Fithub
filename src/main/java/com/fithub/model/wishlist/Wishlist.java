package com.fithub.model.wishlist;

import com.fithub.model.classes.Classes;
import com.fithub.model.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "wishlist")
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "listid")
	private int listId;

	@Column(name = "MEMBERID")
	private int memberId;

	@Column(name = "CLASSID")
	private int classId;

	@Column(name = "wishaddsince")
	@Temporal(TemporalType.TIMESTAMP) //指定DB->server的時間資料格式"yyyy-MM-dd HH:mm:ss.SSS"
	private String wishAddSince;

	@Column(name = "wishremovedate")
	@Temporal(TemporalType.TIMESTAMP) //指定DB->server的時間資料格式"yyyy-MM-dd HH:mm:ss.SSS"
	private String wishRemoveDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBERID", insertable = false, updatable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASSID", insertable = false, updatable = false)
	private Classes classes;

}
