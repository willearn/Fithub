package com.fithub.model.department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	//XiaoQing
	@Query("from Department where deptname = :name")
	Department findDepartmentByName(@Param("name") String name);

	//XiaoQing
	@Query("select count(*) from Department where :name is null or deptname like %:name%")
	long count(@Param("name")String name);

	
	//XiaoQing
	@Query("select deptid , deptname from Department where :name is null or deptname like %:name%")
	Page<Object[]> searchByName(Pageable pgb,@Param("name") String name);

}
