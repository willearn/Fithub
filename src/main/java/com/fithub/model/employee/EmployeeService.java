package com.fithub.model.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService implements IEmployeeService{
	
	@Autowired
	private EmployeeDAO eDao;

	public boolean insert(Employee eBean) {
		Employee resultBean = eDao.save(eBean);
		if(resultBean != null) {
			return true;
		}
		return false;
	}
	
	public boolean update(Employee eBean) {
		Employee result = eDao.save(eBean);
		if(result != null) {
			return true;
		}
		return false;
	}
	
	public void deleteById(Integer id) {
		eDao.deleteById(id);
	}
	
	public Employee findById(Integer id) {
		Optional<Employee> optinoal = eDao.findById(id);
		
		if(optinoal.isPresent()) {
			return optinoal.get();
		}
		
		return null;
	}
	
	public List<Employee> findAll(){
		List<Employee> list = eDao.findAll();
		return list;
	}

	// Author: ChrislaFolia
	@Override
	public List<Object[]> findNameAndIdByEmployeeTitle(String employeeTitle) {
		List<Object[]> list = eDao.findNameAndIdByEmployeeTitle(employeeTitle);
		return list;
	}

}
