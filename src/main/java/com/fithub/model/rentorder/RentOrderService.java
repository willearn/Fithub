package com.fithub.model.rentorder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fithub.model.classes.IClassesService;

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

	// 新增單筆
	@Override
	public RentOrder insert(RentOrder rentOrder) {
		RentOrder result = rentOrderRepo.save(rentOrder);
		return result;
	}

	// 修改單筆
	@Override
	public void updateById(RentOrder rentOrder) {
		Boolean result = rentOrderRepo.existsById(rentOrder.getRentorderid());
		if (result) {
			rentOrderRepo.saveAndFlush(rentOrder);
		}
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = rentOrderRepo.existsById(id);

		if (result) {
			rentOrderRepo.deleteById(id);
		}
	}

	// 刪除多筆
	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		rentOrderRepo.deleteAllById(selectIds);
	}

	// 查詢指定教室 rentdate,renttime,rentstatus
	@Override
	public List<Object[]> findAllDateTimeFromRentOrderAndclass(Integer classroomId) {

		List<Object[]> allrentDateAndrentTimeAndrentStatus = rentOrderRepo.findAllrentDateAndrentTimeAndrentStatusByClassroomId(classroomId);
		List<Object[]> allclassDateAndclassTime = iClassesService.findAllclassDateAndclassTimeByClassroomId(classroomId);
		
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

		for (Object[] objects : mergedResult) {
			System.out.println(objects[0]+" "+objects[1]);
		}
		
		return mergedResult;
	}
}
