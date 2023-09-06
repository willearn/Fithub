package com.fithub.model.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
	@Query("from Department where deptname = :name")
	Department findDepartmentByName(@Param("name") String name);

}
