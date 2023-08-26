package com.fithub.model.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
	
	 // 自定查詢，返回全部 employeename 和 employeeid
    @Query("SELECT e.employeename, e.employeeid FROM Employee e")
    List<Object[]> findAllemployeenameAndemployeeid();
}
