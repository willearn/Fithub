package com.fithub.model.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("from Employee where jobtitleid = :jobtitleid")
	List<Employee> findManagersByJobTitleId(@Param("jobtitleid") String jobtitleid);

}
