package com.fithub.model.jobtitle;

import java.util.List;

public interface IJobTitleService {
	public boolean insert(JobTitle dBean);
	public boolean update(JobTitle dBean);
	public void deleteById(Integer id);
	public JobTitle findById(Integer id);
	public List<JobTitle> findAll();
	public Integer findJobTitleByName(String name);
}
