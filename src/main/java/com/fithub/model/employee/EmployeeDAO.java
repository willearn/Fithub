package com.fithub.model.employee;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class EmployeeDAO implements IEmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Employee insert(Employee eBean) {
		Session session = sessionFactory.openSession();
		Query<Employee> query = session
				.createQuery("from Employee where employeeemail = '" + eBean.getEmployeeemail() + "'", Employee.class);
		System.out.println("test1");
		if (query.list().isEmpty()) {
			session.persist(eBean);
			session.flush();
			session.close();
			System.out.println("test2");
			return eBean;
		}
		System.out.println("test3");
		session.close();
		return null;
	}

	@Override
	public Employee selectById(int employeeid) {
		Session session = sessionFactory.openSession();
		Employee resultBean = session.get(Employee.class, employeeid);
		session.close();
		return resultBean;
	}

	@Override
	public List<Employee> selectAll() {
		Session session = sessionFactory.openSession();
		try {
			Query<Employee> query = session.createQuery("from Employee",Employee.class);
//			Query<Employee> query = session.createQuery("SELECT e FROM Employee e JOIN FETCH e.department",Employee.class);
			List<Employee> lists = query.list();
			session.flush();
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Employee update(int employeeid, String employeename, String employeeemail, String employeephone,
			String employeegender, String employeecity, String employeezone, String employeeaddress, int deptid,
			String employeetitle, int manager, String hiredate, String resigndate, int salary, String employeebirthday,
			String employeeintroduction) {
		Session session = sessionFactory.openSession();
		Employee resultBean = session.get(Employee.class, employeeid);
		if (resultBean != null) {
			resultBean.setEmployeename(employeename);
			resultBean.setEmployeeemail(employeeemail);
			resultBean.setEmployeephone(employeephone);
			resultBean.setEmployeegender(employeegender);
			resultBean.setEmployeecity(employeecity);
			resultBean.setEmployeezone(employeezone);
			resultBean.setEmployeeaddress(employeeaddress);
			resultBean.setDeptid(deptid);
			resultBean.setEmployeetitle(employeetitle);
			resultBean.setManager(manager);
			resultBean.setHiredate(hiredate);
			resultBean.setResigndate(resigndate);
			resultBean.setSalary(salary);
			resultBean.setEmployeebirthday(employeebirthday);
			resultBean.setEmployeeintroduction(employeeintroduction);
		}

		return resultBean;
	}

	@Override
	public boolean deleteById(int employeeid) {
		Session session = sessionFactory.openSession();
		Employee resultBean = session.get(Employee.class, employeeid);

		if (resultBean != null) {
			try {
				session.remove(resultBean);
				return true;
			} catch (ConstraintViolationException e) {

				return true;
			} finally {
				session.close();
			}

		}

		return false;
	}
	
	@Override
	public Employee update(Employee eBean) {
		Session session = sessionFactory.openSession();
		if(eBean!=null) {
			session.merge(eBean);
			session.flush();
		}
		
		session.close();
		return eBean;
	}
}
