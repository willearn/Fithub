package com.fithub.model.classroom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fithub.model.classes.Classes;
import com.fithub.model.rentorder.RentOrder;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="classroom")
public class Classroom {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="classroomid")
	private int classroomId;
	
	@Column(name="classroomname")
	private String classroomName;
	
	@Column(name="classroomcapacity")
	private int classroomCapacity;
	
	@Column(name="classroomdescription")
	private String classroomDescription;
	
	@Column(name="classroomprice")
	private int classroomPrice;
	
	@Column(name="classroomstatus")
	private String classroomStatus;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classroom")
	private Set<Classes> classes=new HashSet<Classes>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "RentOrderClassroom", joinColumns = 
	@JoinColumn(name = "classroomid"), inverseJoinColumns = @JoinColumn(name = "rentorderid"))
	private List<RentOrder> rentOrders = new ArrayList<RentOrder>();
	
}
