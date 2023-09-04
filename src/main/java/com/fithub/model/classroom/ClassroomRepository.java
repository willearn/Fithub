package com.fithub.model.classroom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
	
	 // william 自定查詢，返回 classroomName,classroomId
    @Query("SELECT c.classroomName, c.classroomId FROM Classroom c")
    List<Object[]> findAllClassroomNameAndId();
    
	 // william 返回指定教室的全部 classroomId,classroomName,classroomstatus,classroomPrice,classroomPic
    @Query("SELECT new com.fithub.model.classroom.ClassroomDTO(c.classroomId, c.classroomName, c.classroomStatus ,c.classroomPrice,c.classroomPic) FROM Classroom c")
    List<ClassroomDTO> getClassroomInfo();
}
