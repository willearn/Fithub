package com.fithub.model.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ActivityService implements IActivityService {

	@Autowired
	private ActivityRepository activityRepo;

	// 新增單筆活動
	@Override
	public Activity insert(Activity activity) {
		Activity result = activityRepo.save(activity);
		return result;
	}

	// 新增多筆活動
	@Override
	public List<Activity> insertAll(List<Activity> activityList) {
		List<Activity> results = activityRepo.saveAll(activityList);
		return results;
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = activityRepo.existsById(id);

		if (result) {
			activityRepo.deleteById(id);
		}
	}

	// 刪除多筆
	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		activityRepo.deleteAllById(selectIds);
	}

	// 修改單筆
	@Override
	public void updateById(Activity activity) {
		Boolean result = findById(activity.getActivityid());
		if (result) {
			activityRepo.saveAndFlush(activity);
		}
	}

	// 確認id存在
	@Override
	public Boolean findById(Integer id) {
		Boolean result = activityRepo.existsById(id);
		return result;
	}

	@Override
	public Long countData() {
		Long result = activityRepo.count();
		return result;
	}

	// 查詢分頁
	@Override
	public Page<Activity> findByPage(Integer pageNumber, Integer choiceShowValue) {
		PageRequest pgrequest = PageRequest.of(pageNumber - 1, choiceShowValue, Sort.Direction.DESC, "activityid");
		Page<Activity> page = activityRepo.findAll(pgrequest);
		return page;
	}
}
