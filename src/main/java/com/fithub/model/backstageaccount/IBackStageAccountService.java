package com.fithub.model.backstageaccount;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fithub.model.employee.Employee;


public interface IBackStageAccountService {
	public boolean insert(BackStageAccount dBean);
	public boolean update(BackStageAccount dBean);
	public void deleteBackStageAccountByAccount(String account);
	public BackStageAccount findBackStageAccountByAccount(String account);
	public List<BackStageAccount> findAll();
	public BackStageAccount checkLogin(BackStageAccount bBean);
	public Integer checkLoa(String account);
	long count();
	public long count(String name);
	Page<BackStageAccount> findByPage(Integer pageNumber, Integer rows);
	Page<Object[]> findPageByName(Integer pageNumber, Integer rows, String name);
}
