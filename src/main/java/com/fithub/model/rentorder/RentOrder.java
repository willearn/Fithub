package com.fithub.model.rentorder;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import jakarta.persistence.Transient;
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
	
	@Transient							// 不映射資料庫欄位
	@JsonProperty("classroomid")		// 使用自定義的變數名稱
	private List<Integer> classroomids;
	
	private String rentdate;
	private String renttime;
	private String rentstatus;
	private Integer memberid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberid", insertable = false, updatable = false)
	private Member member;

	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinTable(name = "RentOrderClassroom", joinColumns = @JoinColumn(name = "rentorderid"), inverseJoinColumns = @JoinColumn(name = "classroomid"))
	private List<Classroom> classrooms = new ArrayList<Classroom>();
	
}
