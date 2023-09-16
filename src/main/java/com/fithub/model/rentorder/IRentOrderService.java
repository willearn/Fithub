package com.fithub.model.rentorder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.fithub.model.department.Department;

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

	Page<RentOrder> findAllPage(String date, int number, int size);
	
	List<RentOrder> findByMemberId(Integer id);

	long count(Integer memberId , String string);

	long count(Integer memberId);
	
	Page<RentOrder> findPageByDate(Integer pageNumber, Integer rows , Integer memberId, String date);

	Page<RentOrder> findByPage(Integer pageNumber, Integer rows , Integer memberId);

	Optional<RentOrder> findById(int Id);

}