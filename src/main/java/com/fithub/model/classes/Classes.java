package com.fithub.model.classes;

import com.fithub.model.classroom.Classroom;
import com.fithub.model.course.Course;

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
@Table(name="classes")
public class Classes {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="classid")
	private int classId;
	
	@Column(name="COURSEID")
	private int courseId;
	
	@Column(name="classdate")
	private String classDate;

	@Column(name="classtime")
	private String classTime;

	@Column(name="employeeid")
	private int employeeId;

	@Column(name="applicantsceil")
	private int applicantsCeil;
	
	@Column(name="applicantsfloor")
	private int applicantsFloor;
	
	@Column(name="price")
	private int price;
	
	@Column(name="CLASSROOMID")
	private int classroomId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="COURSEID",insertable = false,updatable = false)
	private Course course;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CLASSROOMID",insertable = false,updatable = false)
	private Classroom classroom;
	
	
}
