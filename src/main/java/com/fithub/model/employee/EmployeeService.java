package com.fithub.model.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository eRepo;

	@Override
	public boolean insert(Employee eBean) {
		try {
			Employee resultBean = eRepo.save(eBean);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean update(Employee eBean) {
		Employee resultBean = eRepo.save(eBean);
		if (resultBean != null) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteById(Integer id) {
		eRepo.deleteById(id);
	}

	@Override
	public Employee findById(Integer id) {
		Optional<Employee> optinoal = eRepo.findById(id);

		if (optinoal.isPresent()) {
			return optinoal.get();
		}

		return null;
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> resultBeans = eRepo.findAll();
		return resultBeans;
	}

	@Override
	public List<Employee> findManagerByJobTitleId(Integer jobtitleid) {
		List<Employee> resultBeans = eRepo.findManagersByJobTitleId(jobtitleid.toString());

		if (resultBeans.isEmpty()) {
			return null;
		}
		return resultBeans;
	}
	
	public List<Object[]> findAllemployeenameAndemployeeid(){
		List<Object[]> list = eRepo.findAllemployeenameAndemployeeid();
		return list;
	}

	// 找所有的count
	@Override
	public long count() {
		long count = eRepo.count();
		return count;
	}

	// 找所有的count
	@Override
	public long count(String name) {
		long count = eRepo.count(name);
		return count;
	}

	@Override
	public Page<Employee> findByPage(Integer pageNumber, Integer rows) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "employeeid");

		Page<Employee> page = eRepo.findAll(pgb);

		return page;
	}

	@Override
	public Page<Employee> findPageByName(Integer pageNumber, Integer rows, String name) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "employeeid");
		System.out.println("pageNumber " + pageNumber);
		System.out.println("rows " + rows);
		System.out.println("name " + name);
		Page<Employee> page = eRepo.searchByName(pgb, name);
		System.out.println(page);
		return page;
	}

//	public List<Employee> find(JSONObject obj) throws JSONException{
//		int start = obj.isNull("start") ? 0 : obj.getInt("start");
//		int rows = obj.isNull("rows") ? 0 : obj.getInt("rows");
//		
//		String sortType = obj.isNull("sortType") ? null : obj.getString("sortType");
//		String sortOrder = obj.isNull("sortOrder") ? null : obj.getString("sortOrder");
//		
//		
//	}

	
	
}
