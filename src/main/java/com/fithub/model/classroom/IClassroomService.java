package com.fithub.model.classroom;

import java.util.List;

public interface IClassroomService {

	// 查詢全部
	public List<Classroom> findAll();

	// 新增單筆活動
	public Classroom insert(Classroom classroom);

	// 修改單筆
	public void updateById(Classroom classroom);

	// 刪除單筆
	public void deleteById(Integer id);

	// 確認id存在
	public Boolean exitsById(Integer id);
	
	// 查詢單筆
	public Classroom findById(Integer id);
	
}
