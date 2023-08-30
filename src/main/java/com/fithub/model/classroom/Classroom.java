package com.fithub.model.classroom;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fithub.model.classes.Classes;
import com.fithub.model.rentorder.RentOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "classroom")
public class Classroom {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "classroomid")
	private int classroomId;

	@Column(name = "classroomname")
	private String classroomName;

	@Column(name = "classroomcapacity")
	private int classroomCapacity;

	@Column(name = "classroomdescription")
	private String classroomDescription;

	@Column(name = "classroomprice")
	private int classroomPrice;

	@Column(name = "classroomstatus")
	private String classroomStatus;

	@Column(name = "classroompic")
	private String classroomPic;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classroom")
	private Set<Classes> classes = new HashSet<Classes>();

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "classroom")
	private RentOrder rentOrder;
}
