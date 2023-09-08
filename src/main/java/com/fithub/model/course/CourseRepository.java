package com.fithub.model.course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	// Chrislafolia
	Page<Course> findByCategoryId(@Param("categoryId") int categoryId,PageRequest pgb);
}
