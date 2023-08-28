package com.fithub.model.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {

	@Query("from Employee where employeetitle = :employeetitle")
	public List<Employee> findEmployeeByEmployeetitle(@Param(value="employeetitle") String employeetitle);
	
	
}
