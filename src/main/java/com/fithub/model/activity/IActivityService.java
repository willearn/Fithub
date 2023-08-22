package com.fithub.model.activity;

import java.util.List;

import org.springframework.data.domain.Page;

public interface IActivityService {

	// 新增單筆活動
	Activity insert(Activity activity);

	// 新增多筆活動
	List<Activity> insertAll(List<Activity> activityList);

	// 刪除單筆
	void deleteById(Integer id);

	// 刪除多筆
	void deleteAllById(Iterable<Integer> selectIds);

	// 修改單筆
	void updateById(Activity activity);

	Long countData();

	// 查詢分頁
	Page<Activity> findByPage(Integer pageNumber, Integer choiceShowValue);

}