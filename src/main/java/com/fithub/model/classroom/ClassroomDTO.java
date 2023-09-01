package com.fithub.model.classroom;

import lombok.Data;

@Data
public class ClassroomDTO {

	private int classroomId;

	private String classroomName;

	private String classroomStatus;


	public ClassroomDTO(int classroomId, String classroomName, String classroomStatus) {
		this.classroomId = classroomId;
		this.classroomName = classroomName;
		this.classroomStatus = classroomStatus;
	}
}