package com.fithub.model.classes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fithub.model.rentorder.RentOrder;

public interface ClassesRepository extends JpaRepository<Classes, Integer> {

	// william，返回指定教室的全部 classDate 和 classTime
	@Query("SELECT c.classDate, c.classTime FROM Classes c WHERE c.classroomId = :classroomId")
	List<Object[]> findAllclassDateAndclassTimeByClassroomId(@Param("classroomId") Integer classroomId);

	// william，查找是否有使用此日期和時段的課堂 
	@Query("SELECT c FROM Classes c WHERE c.classroomId = :classroomId AND c.classDate = :classDate AND c.classTime = :classTime")
	Classes checkClass(@Param("classroomId") int classroomId, @Param("classDate") String classDate,
			@Param("classTime") String classTime);
}
