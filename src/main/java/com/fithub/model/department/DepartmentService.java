package com.fithub.model.department;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService implements IDepartmentService{

	@Autowired
	private DepartmentDAO dDao;
	
	@Override
	public boolean insert(Department dBean) {
		Department result = dDao.findDepartmentByName(dBean.getDeptname());
		
		if(result == null) {
			Department resultBean = dDao.save(dBean);
			if(resultBean != null) {
				return true;
			}
		}
		return false;

	}
	
	@Override
	public boolean update(Department dBean) {
		Department result = dDao.findDepartmentByName(dBean.getDeptname());
		System.out.println("update-result:" + result);
		if(result == null) {
			Department resultBean = dDao.save(dBean);
			System.out.println("update-result2:" + resultBean);
			if(resultBean!=null) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void deleteById(Integer id) {
		dDao.deleteById(id);
	}
	
	@Override
	public Department findById(Integer id) {
		Optional<Department> optional = dDao.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
	    return null;
	}
	
	@Override
	public List<Department> findAll() {
		List<Department> list = dDao.findAll();
		return list;
	}
	
	public boolean findDepartmentByName(String name) {
		Department result = dDao.findDepartmentByName(name);
		
		if(result != null) {
			return true;
		}
		return false;
	}
	

	
	
}
