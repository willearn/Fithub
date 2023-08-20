package com.fithub.model.department;

import java.util.List;

public interface IDepartmentDAO {
	public Department insert(Department dBean);
	public Department selectById(int deptid);
	public List<Department> selectAll();
	public boolean deleteById(int deptid);
	public Department update(Department dBean);
}
