package com.fithub.model.activity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface IActivityService {

	List<Activity> findAll();

	Activity insert(Activity activity);

	void updateById(Activity activity);

	void deleteById(Integer id);

	Boolean exitsById(Integer id);

	void deleteAllById(Iterable<Integer> selectIds);

	List<Map<String, Object>> filteredAndSortedActivities(Date sqlDate);

	Map<String, Object> findDescriptionDateNameById(String activityid);
}