package com.fithub.model.classes;

import lombok.Data;

@Data
public class ClassesDto {

	private int classId;

	private int courseId;

	private String classDate;

	private String classTime;

	private int coachSubstitute;

	private int employeeId;

	private int applicantsCeil;

	private int applicantsFloor;

	private int price;

	private int classroomId;
	
	private String courseName;
	
	private String categoryName;
	
	private String employeename ;
	
	private String classroomName;
	
	private int classroomCapacity;

	public ClassesDto() {
	}

//	public ClassesDto(int classId, int courseId, String classDate, String classTime, int coachSubstitute,
//			int employeeId, int applicantsCeil, int applicantsFloor, int price, int classroomId) {
//		this.classId = classId;
//		this.courseId = courseId;
//		this.classDate = classDate;
//		this.classTime = classTime;
//		this.coachSubstitute = coachSubstitute;
//		this.employeeId = employeeId;
//		this.applicantsCeil = applicantsCeil;
//		this.applicantsFloor = applicantsFloor;
//		this.price = price;
//		this.classroomId = classroomId;
//	}

}
