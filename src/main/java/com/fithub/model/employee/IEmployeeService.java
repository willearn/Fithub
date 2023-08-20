package com.fithub.model.employee;

import java.util.List;

public interface IEmployeeService {
	public Employee insert(Employee eBean);
	public Employee selectById(int employeeid);
	public List<Employee> selectAll();
	public Employee update(int employeeid,String employeename,String employeeemail,String employeephone,
			String employeegender,String employeecity,String employeezone,String employeeaddress,
			int deptid,String employeetitle,int manager,String hiredate,String resigndate,
			int salary,String employeebirthday,String employeeintroduction);
	public Employee update(Employee eBean);
	public boolean deleteById(int employeeid);
}
