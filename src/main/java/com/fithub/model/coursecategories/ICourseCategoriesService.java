package com.fithub.model.coursecategories;

import java.util.List;

public interface ICourseCategoriesService {

	// 查詢全部
	public List<CourseCategories> findAll();

	// 新增單筆活動
	public CourseCategories insert(CourseCategories courseCategories);

	// 修改單筆
	public void updateById(CourseCategories courseCategories);

	// 刪除單筆
	public void deleteById(Integer id);

	// 確認id存在
	public Boolean exitsById(Integer id);
	
	// 查詢單筆
	public CourseCategories findById(Integer id);
	
	
}
