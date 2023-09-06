package com.fithub.model.jobtitle;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JobTitleService implements IJobTitleService {

	@Autowired
	private JobTitleRepository jRepo;
	
	@Override
	public boolean insert(JobTitle jBean) {
		Integer result = jRepo.findJobTitleByName(jBean.getJobtitlename());
		System.out.println("Integer:" + result);
		if(result == null) {
			JobTitle resultBean = jRepo.save(jBean);
			if(resultBean != null) {
				return true;
			}
		}
		return false;

	}
	
	@Override
	public boolean update(JobTitle jBean) {
		Integer result = jRepo.findJobTitleByName(jBean.getJobtitlename());
		System.out.println("update-result:" + result);
		if(result == null) {
			JobTitle resultBean = jRepo.save(jBean);
			System.out.println("update-result2:" + resultBean);
			if(resultBean!=null) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void deleteById(Integer id) {
		jRepo.deleteById(id);
	}
	
	@Override
	public JobTitle findById(Integer id) {
		Optional<JobTitle> optional = jRepo.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
	    return null;
	}
	
	@Override
	public List<JobTitle> findAll() {
		List<JobTitle> list = jRepo.findAll();
		return list;
	}
	
	public Integer findJobTitleByName(String name) {
		Integer result = jRepo.findJobTitleByName(name);
		
		if(result != null) {
			return result;
		}
		return result;
	}
	
	
}
