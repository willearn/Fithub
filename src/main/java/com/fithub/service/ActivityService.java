package com.fithub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fithub.model.activity.Activity;
import com.fithub.model.activity.ActivityRepository;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository activityRepo;

	//新增單筆活動
	public Activity insert(Activity activity) {
		Activity result = activityRepo.save(activity);
		return result;
	}

	//新增多筆活動
	public List<Activity> insertAll(List<Activity> activityList) {
		List<Activity> results = activityRepo.saveAll(activityList);
		return results;
	}
	
	//刪除單筆
	public void delete(Integer id) {
		Boolean result =  activityRepo.existsById(id);
		
		if(result) {
			activityRepo.deleteById(id);
		}
	}
	
	//刪除多筆
	
	
	//修改單筆
	public void update(Activity activity) {
		
	}

	
	//查詢分頁
	public Page<Activity> findByPage(Integer pageNumber) {
		PageRequest pgrequest = PageRequest.of(pageNumber - 1, 10, Sort.Direction.DESC, "activityid");

		Page<Activity> page = activityRepo.findAll(pgrequest);
		return page;
	}
}
