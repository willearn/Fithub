package com.fithub.model.classes;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

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

	// Chrislafolia，返回指定course在指定時間内的所有classes資訊
	public List<ClassesDto> findAllByCourseIdAndDateRange(Integer courseId, String startDate, String endDate);

	// Chrislafolia，返回在指定時間内的所有classes資訊
	public List<ClassesDto> findAllByDateRange(String startDate, String endDate);

	// Chrislafolia，返回在指定memberId的在wishlist上面的classes資訊
	public List<ClassesDto> findClassesByClassesId(List<Integer> classesIds);

	// Chrislafolia，返回在指定memberId的在wishlist上面的classes資訊
	public List<Map<String, Object>> findWishlistClassesByMemberId(int memberId);

}
