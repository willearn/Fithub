package com.fithub.model.rentorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fithub.model.classes.Classes;
import com.fithub.model.classes.IClassesService;
import com.fithub.model.department.Department;

@Service
public class RentOrderService implements IRentOrderService {

	@Autowired
	private RentOrderRepository rentOrderRepo;

	@Autowired
	private IClassesService iClassesService;

	// 查詢全部
	@Override
	public List<RentOrder> findAll() {
		List<RentOrder> result = rentOrderRepo.findAll();
		return result;
	}
	
	// 查詢單筆
	@Override
	public Optional<RentOrder> findById(int Id) {
		Optional<RentOrder> result = rentOrderRepo.findById(Id);
		return result;
	}

	// 查詢全部並分頁
	@Override
	public Page<RentOrder> findAllPage(String date, int number, int size) {
		// 回傳第幾頁 每頁10筆
		Pageable pageable = PageRequest.of(number, size);
		return rentOrderRepo.findAllPage(date,pageable);
	}

	// 新增單筆
	@Override
	public RentOrder insert(RentOrder rentOrder) {
		RentOrder result = rentOrderRepo.save(rentOrder);
		return result;
	}

	// 修改單筆狀態
	@Override
	public Boolean updateRentstatusById(Integer rentorderid, String rentamount) {
		try {
			rentOrderRepo.updateRentstatusById(rentorderid, rentamount);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 刪除單筆
	@Override
	public Boolean deleteById(Integer id) {
		Boolean result = rentOrderRepo.existsById(id);

		if (result) {
			rentOrderRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	// 刪除多筆
	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		rentOrderRepo.deleteAllById(selectIds);
	}
	
	// 取得租借場地總金額
	@Override
	public int findRentAmount() {
		int total = 0;
		List<Map<String, Object>> result = rentOrderRepo.findRentAmount();
		for (Map<String, Object> map : result) {
			if (map != null) {
				int rentamount = (int) map.get("rentamount");
				total = total + rentamount;
			}
		}
		return total;
	}

	// 查詢指定教室是否被預訂或使用
	@Override
	public List<Object[]> findAllDateTimeFromRentOrderAndclass(Integer classroomId) {

		List<Object[]> allrentDateAndrentTimeAndrentStatus = rentOrderRepo
				.findAllrentDateAndrentTimeAndrentStatusByClassroomId(classroomId);
		List<Object[]> allclassDateAndclassTime = iClassesService
				.findAllclassDateAndclassTimeByClassroomId(classroomId);

		// 建立儲存篩選的集合
		List<Object[]> filteredRentData = new ArrayList<>();

		// 篩選狀態不為取消的
		for (Object[] rentData : allrentDateAndrentTimeAndrentStatus) {
			String rentStatus = (String) rentData[2];
			if (!"取消".equals(rentStatus)) {
				filteredRentData.add(rentData);
			}
		}

		// 建立儲存只包含 date 和 time 刪除status欄位
		List<Object[]> allrentDateAndTime = new ArrayList<>();

		// 建立新的數據結構
		for (Object[] rentData : filteredRentData) {
			Object[] newData = new Object[] { rentData[0], rentData[1] }; // 只包含 date 和 time
			allrentDateAndTime.add(newData);
		}

		// 合併兩個集合
		List<Object[]> mergedResult = new ArrayList<>();
		mergedResult.addAll(allrentDateAndTime);
		mergedResult.addAll(allclassDateAndclassTime);

		return mergedResult;
	}

	@Override
	public Boolean checkClassroomAvailability(Integer classroomId, String rentdate, String renttime) {

		// 預設未被使用
		Boolean usedClassroom = false;
		RentOrder resultRentOrder = rentOrderRepo.checkClassroomAvailability(classroomId, rentdate, renttime);

		// 檢查租借場地訂單是否有預定該場地 如果已被使用就直接返回不再檢查課堂
		if (resultRentOrder != null && !resultRentOrder.getRentstatus().equals("取消")) {
			usedClassroom = true;
			return usedClassroom;
		}

		Classes resultClasses = iClassesService.checkClass(classroomId, rentdate, renttime);

		// 檢查課堂是否有使用該場地
		if (resultClasses != null) {
			usedClassroom = true;
		}

		return usedClassroom;
	}

	@Override
	public List<RentOrder> findByMemberId(Integer id) {
		List<RentOrder> resultBeans = rentOrderRepo.findByMemberId(id);
		return resultBeans;
	}

	@Override
	public long count(Integer memberId , String date) {
		long count = rentOrderRepo.count(memberId , date);
		return count;
	}

	@Override
	public long count(Integer memberId) {
		long count = rentOrderRepo.count(memberId);
		return count;
	}

	@Override
	public Page<RentOrder> findPageByDate(Integer pageNumber, Integer rows , Integer memberId , String date) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "rentorderid");
		
		Page<RentOrder> page = rentOrderRepo.searchByMemberIdAndRentDate(pgb,memberId,date);
		
		return page;
	}

	@Override
	public Page<RentOrder> findByPage(Integer pageNumber, Integer rows , Integer memberId) {
		Pageable pgb = PageRequest.of(pageNumber, rows, Sort.DEFAULT_DIRECTION, "rentorderid");
		
		Page<RentOrder> page = rentOrderRepo.findAllByMemberId(pgb , memberId);
		
		return page;
	}
}
