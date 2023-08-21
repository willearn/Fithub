package com.fithub.model.rentorder;

import java.util.ArrayList;
import java.util.List;

import com.fithub.model.classroom.Classroom;
import com.fithub.model.member.Member;

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

//資料庫對應的table
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberid",insertable=false, updatable=false)
	private Member member;
	
	private Integer memberid;
	private Integer classroomid;
	
	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	public Integer getClassroomid() {
		return classroomid;
	}

	public void setClassroomid(Integer classroomid) {
		this.classroomid = classroomid;
	}

	@ManyToMany
	@JoinTable(name = "RentOrderClassroom", joinColumns = @JoinColumn(name = "rentorderid"), inverseJoinColumns = @JoinColumn(name = "classroomid"))
	private List<Classroom> classrooms = new ArrayList<>();;

	public RentOrder() {
	}

	public Integer getRentorderid() {
		return rentorderid;
	}

	public void setRentorderid(Integer rentorderid) {
		this.rentorderid = rentorderid;
	}


	public String getRentdate() {
		return rentdate;
	}

	public void setRentdate(String rentdate) {
		this.rentdate = rentdate;
	}

	public String getRenttime() {
		return renttime;
	}

	public void setRenttime(String renttime) {
		this.renttime = renttime;
	}

	public String getRentstatus() {
		return rentstatus;
	}

	public void setRentstatus(String rentstatus) {
		this.rentstatus = rentstatus;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

}
