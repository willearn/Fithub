package com.fithub.model.activity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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

	@Override
	public List<Map<String, Object>> filteredAndSortedActivities(Date currentDate) {

		try {
			List<Map<String, Object>> result = activityRepo.filteredAndSortedActivities(currentDate);

			// 將SQL日期轉成LocalDate並使用方法比較
			LocalDate localCurrentDate = currentDate.toLocalDate();

			for (Map<String, Object> map : result) {
				int activityid =  (int)map.get("activityid");
				Date activityoff = (Date) map.get("activityoff");
				LocalDate activityOffDate = activityoff.toLocalDate();
				
				// 當天日期>下架日期將顯示改為否
				int checkOff = activityOffDate.compareTo(localCurrentDate);
				if (checkOff < 0) {
					activityRepo.updateDisplayById(activityid,"否");
				}
			}
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
