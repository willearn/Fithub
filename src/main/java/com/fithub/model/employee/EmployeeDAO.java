package com.fithub.model.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
	
 // Author: ChrislaFolia
	@Query("SELECT e.employeeid, e.employeename  FROM Employee e where e.employeetitle = :employeetitle")
	List<Object[]> findNameAndIdByEmployeeTitle(@Param(value="employeetitle") String employeeTitle);
	
	
}
