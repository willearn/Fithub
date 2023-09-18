package com.fithub.model.classroom;

import java.util.List;
import java.util.Map;

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
	List<Map<String, Object>> findAllClassroomNamesAndIds();

	// 查詢全部教室 不含 description 和 pic
	List<Object[]> findAllClassroomsWithoutDescriptionsAndPics();

	List<ClassroomDTO> getClassroomInfo();
	
	// chrislafolia 查詢特定時間 classes
	List<Map<String, Object>> findClassesByDateAndTime(int classroomId,
    		 String classDate, String classTime);
	
	// chrislafolia 查詢特定時間 rentOrder
	List<Map<String, Object>> findRentOrdersByDateAndTimeAndStatus(int classroomId,
    		 String rentdate, String renttime, String rentstatus);

}
