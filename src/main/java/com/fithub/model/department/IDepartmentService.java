package com.fithub.model.department;

import java.util.List;

import org.springframework.data.domain.Page;

public interface IDepartmentService {
	public boolean insert(Department dBean);
	public boolean update(Department dBean);
	public void deleteById(Integer id);
	public Department findById(Integer id);
	public List<Department> findAll();
	public boolean findDepartmentByName(String name);
	public long count(String name);
	public long count();
	public Page<Object[]> findPageByName(Integer pageNumber, Integer rows, String name);
	public Page<Department> findByPage(Integer pageNumber, Integer rows);
}
