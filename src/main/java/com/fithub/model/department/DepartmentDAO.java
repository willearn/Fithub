package com.fithub.model.department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentDAO extends JpaRepository<Department, Integer> {
	
	@Query("from Department where deptname = :name")
	List<Department> findDepartmentByName(@Param("name") String name);

}
