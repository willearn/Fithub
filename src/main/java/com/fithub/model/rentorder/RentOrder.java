package com.fithub.model.rentorder;

import java.util.ArrayList;
import java.util.List;

import com.fithub.model.classroom.Classroom;
import com.fithub.model.member.Member;

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
import jakarta.persistence.ManyToOne;
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

	private String rentdate;
	private String renttime;
	private String rentstatus;
	private Integer memberid;
	private Integer classroomid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberid", insertable = false, updatable = false)
	private Member member;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RentOrderClassroom", joinColumns = @JoinColumn(name = "rentorderid"), inverseJoinColumns = @JoinColumn(name = "classroomid"))
	private List<Classroom> classroom = new ArrayList<>();
	
}
