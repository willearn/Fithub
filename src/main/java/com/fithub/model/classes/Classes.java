package com.fithub.model.classes;

import com.fithub.model.classroom.Classroom;
import com.fithub.model.course.Course;

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
	private int classId;
	
	private int courseId;
	private String classDate;
	private String classTimeSince;
	private String classTimeEnd;
	private int employeeId;
	private int applicantsCeil;
	private int applicantsFloor;
	private int price;
	
	private int classroomId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="COURSEID",insertable = false,updatable = false)
	private Course course;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CLASSROOMID",insertable = false,updatable = false)
	private Classroom classroom;
	
	
}
