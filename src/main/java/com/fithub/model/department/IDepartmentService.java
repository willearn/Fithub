package com.fithub.model.department;

import java.util.List;

public interface IDepartmentService {
	public boolean insert(Department dBean);
	public boolean update(Department dBean);
	public void deleteById(Integer id);
	public Department findById(Integer id);
	public List<Department> findAll();
	public boolean findDepartmentByName(String name);
}
