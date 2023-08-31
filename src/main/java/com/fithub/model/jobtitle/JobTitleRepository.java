package com.fithub.model.jobtitle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {

	@Query("select jobtitleid from JobTitle where jobtitlename = :name")
	Integer findJobTitleByName(@Param("name") String name);
}
