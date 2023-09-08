package com.fithub.model.activity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService implements IActivityService {

	@Autowired
	private ActivityRepository activityRepo;

	@Override
	public List<Activity> findAll() {
		List<Activity> result = activityRepo.findAll();
		return result;
	}
	
	// 篩選活動是否顯示並依照排序(越大越優先)
	@Override
	public List<Map<String, Object>> filteredAndSortedActivities(Date currentDate) {
		try {
			List<Map<String, Object>> result = activityRepo.filteredAndSortedActivities(currentDate);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public Map<String, Object> findDescriptionDateNameById(String activityid) {
		try {
			Map<String, Object> result = activityRepo.findDescriptionDateNameById(activityid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Activity insert(Activity activity) {
		Activity result = activityRepo.save(activity);
		return result;
	}

	@Override
	public void updateById(Activity activity) {
		Boolean result = exitsById(activity.getActivityid());
		if (result) {
			activityRepo.saveAndFlush(activity);
		}
	}

	@Override
	public void deleteById(Integer id) {
		Boolean result = activityRepo.existsById(id);

		if (result) {
			activityRepo.deleteById(id);
		}
	}

	// 確認id存在
	@Override
	public Boolean exitsById(Integer id) {
		Boolean result = activityRepo.existsById(id);
		return result;
	}

	// 刪除多筆
	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		activityRepo.deleteAllById(selectIds);
	}
}
