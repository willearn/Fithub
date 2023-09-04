package com.fithub.model.rentorder;

import com.fithub.model.classroom.Classroom;
import com.fithub.model.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

//資料庫對應的table
@Data
@Entity
@Table(name = "RentOrder")
public class RentOrder {

	// 設定主鍵
	@Id
	@Column(name = "rentorderid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rentorderid;

	private Integer memberid;
	private Integer classroomid;
	private String rentorderdate;
	private String rentdate;
	private String renttime;
	private Integer rentamount;
	private String rentstatus;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberid", insertable = false, updatable = false)
	private Member member;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classroomid",insertable = false, updatable = false)
	private Classroom classroom;
}
