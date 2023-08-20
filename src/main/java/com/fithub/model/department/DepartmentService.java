package com.fithub.model.department;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDAO dDao;
	
	public Department insert(Department dept) {
		List<Department> result = dDao.findDepartmentByName(dept.getDeptname());
		
		if(result.isEmpty()) {
			dDao.save(dept);
			return dept;
		}
		
		return null;

	}
	
	public Department update(Department dept) {
		List<Department> result = dDao.findDepartmentByName(dept.getDeptname());
		
		if(result.isEmpty()) {
			dDao.save(dept);
			return dept;
		}
		
		return null;
	}
	
	public Department findById(Integer id) {
		Optional<Department> optional = dDao.findById(id);
	    return optional.orElse(null); // 如果存在值，返回值，否则返回 null
	}
	
	public List<Department> findAll() {
		List<Department> list = dDao.findAll();
		
		return list;
	}
	
	public void deleteById(Integer id) {
		dDao.deleteById(id);
	}
	
	
}
