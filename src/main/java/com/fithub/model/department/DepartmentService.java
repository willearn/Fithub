package com.fithub.model.department;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fithub.model.backstageaccount.BackStageAccount;

@Service
public class DepartmentService implements IDepartmentService{

	@Autowired
	private DepartmentRepository dRepo;
	
	@Override
	public boolean insert(Department dBean) {
		Department result = dRepo.findDepartmentByName(dBean.getDeptname());
		
		if(result == null) {
			Department resultBean = dRepo.save(dBean);
			if(resultBean != null) {
				return true;
			}
		}
		return false;

	}
	
	@Override
	public boolean update(Department dBean) {
		Department result = dRepo.findDepartmentByName(dBean.getDeptname());
		System.out.println("update-result:" + result);
		if(result == null) {
			Department resultBean = dRepo.save(dBean);
			System.out.println("update-result2:" + resultBean);
			if(resultBean!=null) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void deleteById(Integer id) {
		dRepo.deleteById(id);
	}
	
	@Override
	public Department findById(Integer id) {
		Optional<Department> optional = dRepo.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
	    return null;
	}
	
	@Override
	public List<Department> findAll() {
		List<Department> list = dRepo.findAll();
		return list;
	}
	
	public boolean findDepartmentByName(String name) {
		Department result = dRepo.findDepartmentByName(name);
		
		if(result != null) {
			return true;
		}
		return false;
	}

	@Override
	public long count(String name) {
		long count = dRepo.count(name);
		return count;
	}

	@Override
	public long count() {
		long count = dRepo.count();
		return count;
	}

	@Override
	public Page<Object[]> findPageByName(Integer pageNumber, Integer rows, String name) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "deptid");
		Page<Object[]> page = dRepo.searchByName(pgb, name);
		return page;
	}

	@Override
	public Page<Department> findByPage(Integer pageNumber, Integer rows) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "deptid");

		Page<Department> page = dRepo.findAll(pgb);

		return page;
	}
	

	
	
}
