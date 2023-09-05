package com.fithub.model.classroom;

import java.util.List;

public interface IClassroomService {

	// 查詢全部
	List<Classroom> findAll();

	// 新增單筆活動
	Classroom insert(Classroom classroom);

	// 修改單筆
	void updateById(Classroom classroom);

	// 刪除單筆
	void deleteById(Integer id);

	// 確認id存在
	public Boolean exitsById(Integer id);

	// 查詢單筆
	Classroom findById(Integer id);

	// 刪除多筆
	void deleteAllById(Iterable<Integer> selectIds);

	// 查詢全部教室名稱和ID
	List<Object[]> findAllClassroomNameAndId();


	List<ClassroomDTO> getClassroomInfo();

}
