package com.fithub.model.classes;

import java.util.List;

public interface IClassesService {

	// 查詢全部
	public List<Classes> findAll();

	// 新增單筆活動
	public Classes insert(Classes classes);

	// 修改單筆
	public Boolean update(Classes classes);

	// 刪除單筆
	public Boolean deleteById(Integer id);

	// 刪除多筆
	public void deleteAllById(Iterable<Integer> selectIds);

	// 確認id存在
	public Boolean exitsById(Integer id);

	// 查詢單筆
	public Classes findById(Integer id);

	public List<Object[]> findAllclassDateAndclassTimeByClassroomId(Integer classroomId);

	public Classes checkClass(Integer classroomId, String classDate, String classTime);

	public List<ClassesDto> findAllByCourseIdAndDateRange(Integer classroomId, String startDate, String endDate);

}
