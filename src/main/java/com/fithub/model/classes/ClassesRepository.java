package com.fithub.model.classes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassesRepository extends JpaRepository<Classes, Integer> {

	// william，返回指定教室的全部 classDate 和 classTime
	@Query("SELECT c.classDate, c.classTime FROM Classes c WHERE c.classroomId = :classroomId")
	List<Object[]> findAllclassDateAndclassTimeByClassroomId(@Param("classroomId") Integer classroomId);

	// william，查找是否有使用此日期和時段的課堂
	@Query("SELECT c FROM Classes c WHERE c.classroomId = :classroomId AND c.classDate = :classDate AND c.classTime = :classTime")
	Classes checkClass(@Param("classroomId") int classroomId, @Param("classDate") String classDate,
			@Param("classTime") String classTime);

	// Chrislafolia，返回指定course在指定時間内的所有classes資訊
	@Query("SELECT cl.classId, cl.courseId, cl.classDate, cl.classTime, cl.coachSubstitute, "
			+ "cl.employeeId, cl.applicantsCeil,cl.applicantsFloor, cl.price, cl.classroomId, "
			+ "co.courseName, coc.categoryName, e.employeename, r.classroomName "
			+ "FROM Classes cl JOIN cl.course co JOIN co.courseCategories coc "
			+ "JOIN cl.employee e JOIN cl.classroom r "
			+ "WHERE cl.courseId = :courseId AND "
			+ "(cl.classDate >= :startDate AND cl.classDate <= :endDate) "
			+ "ORDER BY cl.classDate")
	List<Object[]> findAllByCourseIdAndDateRange(@Param("courseId") Integer courseId,
			@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	// Chrislafolia，返回在指定時間内的所有classes資訊
	@Query("SELECT cl.classId, cl.courseId, cl.classDate, cl.classTime, cl.coachSubstitute, "
			+ "cl.employeeId, cl.applicantsCeil,cl.applicantsFloor, cl.price, cl.classroomId, "
			+ "co.courseName, coc.categoryName, e.employeename, r.classroomName, r.classroomCapacity "
			+ "FROM Classes cl JOIN cl.course co JOIN co.courseCategories coc "
			+ "JOIN cl.employee e JOIN cl.classroom r "
			+ "WHERE cl.classDate >= :startDate AND cl.classDate <= :endDate "
			+ "ORDER BY cl.classDate")
	List<Object[]> findAllByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
