package com.fithub.model.classroom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fithub.model.classes.Classes;
import com.fithub.model.rentorder.RentOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//import lombok.Data;


@Entity
@Table(name="classroom")
public class Classroom {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="classroomid")
	private int classroomId;
	
	@Column(name="classroomname")
	private String classroomname;
	
	@Column(name="classroomcapacity")
	private int classroomCapacity;
	
	@Column(name="classroomdescription")
	private String classroomdescription;
	
	@Column(name="classroomprice")
	private int classroomprice;
	
	@Column(name="classroomstatus")
	private String classroomstatus;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classroom")
	private Set<Classes> classes=new HashSet<Classes>();

	@ManyToMany(mappedBy = "classrooms")
	private List<RentOrder> rentOrders = new ArrayList<>();
	
	
	public List<RentOrder> getRentOrders() {
		return rentOrders;
	}

	public void setRentOrders(List<RentOrder> rentOrders) {
		this.rentOrders = rentOrders;
	}

	public Classroom() {
	}

	public int getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}

	public String getClassroomname() {
		return classroomname;
	}

	public void setClassroomname(String classroomname) {
		this.classroomname = classroomname;
	}

	public int getClassroomCapacity() {
		return classroomCapacity;
	}

	public void setClassroomCapacity(int classroomCapacity) {
		this.classroomCapacity = classroomCapacity;
	}

	public String getClassroomdescription() {
		return classroomdescription;
	}

	public void setClassroomdescription(String classroomdescription) {
		this.classroomdescription = classroomdescription;
	}

	public int getClassroomprice() {
		return getClassroomprice();
	}

	public void setClassroompirce(int classroompirce) {
		this.classroomprice = classroompirce;
	}

	public String getClassroomstatus() {
		return classroomstatus;
	}

	public void setClassroomstatus(String classroomstatus) {
		this.classroomstatus = classroomstatus;
	}

	public Set<Classes> getClasses() {
		return classes;
	}

	public void setClasses(Set<Classes> classes) {
		this.classes = classes;
	}
	
	
}
