package com.fithub.model.rentorder;

import java.util.List;

public interface IRentOrderService {

	// 查詢全部
	List<RentOrder> findAll();

	// 新增單筆活動
	RentOrder insert(RentOrder rentOrder);

	// 修改單筆
	void updateById(RentOrder rentOrder);

	// 刪除單筆
	void deleteById(Integer id);
	
	// 刪除多筆
	void deleteAllById(Iterable<Integer> selectIds);

	
	List<Object[]> findAllDateTimeFromRentOrderAndclass(Integer classroomId);

	RentOrder checkRentOrder(Integer classroomId, String rentdate, String renttime);

}