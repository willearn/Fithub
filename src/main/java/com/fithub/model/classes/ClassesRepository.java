package com.fithub.model.classes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassesRepository extends JpaRepository<Classes, Integer> {

	 // william 自定查詢，返回 classroomName 和 classroomId
    @Query("SELECT c.classDate, c.classTime FROM Classes c")
    List<Object[]> findAllclassDateAndclassTime();
}
