package com.fithub.model.employee;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService implements IEmployeeService{
	
	@Autowired
	private EmployeeRepository eRepo;

	public boolean insert(Employee eBean) {
		Employee resultBean = eRepo.save(eBean);
		if(resultBean != null) {
			return true;
		}
		return false;
	}
	
	public boolean update(Employee eBean) {
		Employee resultBean = eRepo.save(eBean);
		if(resultBean != null) {
			return true;
		}
		return false;
	}
	
	public void deleteById(Integer id) {
		eRepo.deleteById(id);
	}
	
	public Employee findById(Integer id) {
		Optional<Employee> optinoal = eRepo.findById(id);
		
		if(optinoal.isPresent()) {
			return optinoal.get();
		}
		
		return null;
	}
	
	public List<Employee> findAll(){
		List<Employee> resultBeans = eRepo.findAll();
		return resultBeans;
	}
	
	public List<Employee> findManagerByJobTitleId(Integer jobtitleid){
		List<Employee> resultBeans = eRepo.findManagersByJobTitleId(jobtitleid.toString());

		if(resultBeans.isEmpty()) {
			return null;
		}	
		return resultBeans;
	}

	// Author: ChrislaFolia
	@Override
	public List<Object[]> findNameAndIdByEmployeeTitle(String employeeTitle) {
		List<Object[]> list = eDao.findNameAndIdByEmployeeTitle(employeeTitle);
		return list;
	}

}
