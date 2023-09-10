package com.fithub.model.employee;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

public interface IEmployeeService {
	public boolean insert(Employee eBean);

	public boolean update(Employee eBean);

	public void deleteById(Integer id);

	public Employee findById(Integer id);

	public List<Employee> findAll();

	public List<Object[]> findAllemployeenameAndemployeeid();

	public List<Employee> findManagerByJobTitleId(Integer jobtitleid);

	public long count();

	public long count(String name);
	public long countByJobTitleId(Integer jobtitleid);
	public long countByJobTitleIdAndName(Integer jobtitleid, String name);
	public Page<Employee> findByPage(Integer pageNumber,Integer rows);
	public Page<Employee> findPageByName(Integer pageNumber, Integer rows, String name);
	public Page<Employee> findCoachByPage(Integer pageNumber,Integer rows , Integer jobtitleid);
	public Page<Employee> findCoachPageByName(Integer pageNumber, Integer rows,Integer jobtitleid ,String name);
	public List<Map<String,Object>> findCoachDataPicSpecialty();
}
