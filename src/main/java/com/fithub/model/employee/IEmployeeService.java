package com.fithub.model.employee;

import java.util.List;

public interface IEmployeeService {
	public boolean insert(Employee eBean);
	public boolean update(Employee eBean);
	public void deleteById(Integer id);
	public Employee findById(Integer id);
	public List<Employee> findAll();
	public List<Employee> findManagerByJobTitleId(Integer jobtitleid);
}
