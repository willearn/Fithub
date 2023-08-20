package com.fithub.model.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {
	@Autowired
	private IEmployeeDAO eDao;

	@Override
	public Employee insert(Employee eBean) {
		return eDao.insert(eBean);
	}

	@Override
	public Employee selectById(int employeeid) {
//		Employee emp = eDao.selectById(employeeid);
//		emp.setDeptname(emp.getDepartment().getDeptname());
		return eDao.selectById(employeeid);
	}

	@Override
	public List<Employee> selectAll() {
		return eDao.selectAll();
	}

	@Override
	public Employee update(int employeeid, String employeename, String employeeemail, String employeephone,
			String employeegender, String employeecity, String employeezone, String employeeaddress, int deptid,
			String employeetitle, int manager, String hiredate, String resigndate, int salary, String employeebirthday,
			String employeeintroduction) {
		return eDao.update(employeeid, employeename, employeeemail, employeephone, employeegender, employeecity,
				employeezone, employeeaddress, deptid, employeetitle, manager, hiredate, resigndate, salary,
				employeebirthday, employeeintroduction);
	}

	@Override
	public Employee update(Employee eBean) {
		return eDao.update(eBean);
	}

	@Override
	public boolean deleteById(int employeeid) {
		return eDao.deleteById(employeeid);
	}

//	@Override
//    public List<Employee> selectAll() {
//        List<Employee> emps = eDao.selectAll();
//        for (Employee emp : emps) {
//            emp.setDeptname(emp.getDepartment().getDeptname());
//        }
//        return emps;
//    }

}
