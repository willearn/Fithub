package com.fithub.service;

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

	public void insert(Activity activity) {
		activityRepo.save(activity);
	}

	public Page<Activity> findByPage(Integer pageNumber) {
		PageRequest pgrequest = PageRequest.of(pageNumber - 1, 5, Sort.Direction.DESC, "activityid");

		Page<Activity> page = activityRepo.findAll(pgrequest);
		return page;
	}
}
