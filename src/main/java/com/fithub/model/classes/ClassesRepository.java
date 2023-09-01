package com.fithub.model.classes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassesRepository extends JpaRepository<Classes, Integer> {

	 // william ，返回指定教室的全部 classDate 和 classTime
	@Query("SELECT c.classDate, c.classTime FROM Classes c WHERE c.classroomId = :classroomId")
	List<Object[]> findAllclassDateAndclassTimeByClassroomId(@Param("classroomId") Integer classroomId);
	
}
