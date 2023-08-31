package com.fithub.model.classroom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
	
	 // william 自定查詢，返回 classroomName 和 classroomId
    @Query("SELECT c.classroomName, c.classroomId FROM Classroom c")
    List<Object[]> findAllClassroomNamesAndIds();
}
