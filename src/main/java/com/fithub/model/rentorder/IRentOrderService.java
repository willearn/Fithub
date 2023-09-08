package com.fithub.model.rentorder;

import java.util.List;

public interface IRentOrderService {

	// 查詢全部
	List<RentOrder> findAll();

	// 新增單筆活動
	RentOrder insert(RentOrder rentOrder);

	// 刪除單筆
	Boolean deleteById(Integer id);
	
	// 刪除多筆
	void deleteAllById(Iterable<Integer> selectIds);

	
	List<Object[]> findAllDateTimeFromRentOrderAndclass(Integer classroomId);

	Boolean checkClassroomAvailability(Integer classroomId, String rentdate, String renttime);

	//修改單筆狀態
	Boolean updateRentstatusById(Integer rentorderid, String rentamount);

}