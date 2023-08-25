package com.fithub.model.course;

import java.util.List;

public interface ICourseService {

	// 查詢全部
	public List<Course> findAll();

	// 新增單筆活動
	public Course insert(Course course);

	// 修改單筆
	public Boolean update(Course course);

	// 刪除單筆
	public Boolean deleteById(Integer id);

	// 刪除多筆
	public void deleteAllById(Iterable<Integer> selectIds);
	
	// 確認id存在
	public Boolean exitsById(Integer id);
	
	// 查詢單筆
	public Course findById(Integer id);
	
}
