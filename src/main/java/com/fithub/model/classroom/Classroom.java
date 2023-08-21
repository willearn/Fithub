package com.fithub.model.classroom;

import java.util.HashSet;
import java.util.Set;

import com.fithub.model.classes.Classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@Column(name="classroomlocation")
	private String classroomLocation;
	
	@Column(name="classroomcapacity")
	private int classroomCapacity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classroom")
	private Set<Classes> classes=new HashSet<Classes>();
	
	
}
