package com.fithub.model.rentorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fithub.model.classroom.Classroom;
import com.fithub.model.classroom.ClassroomRepository;
import com.fithub.model.rentorderclassroom.RentOrderClassroom;
import com.fithub.model.rentorderclassroom.RentOrderClassroomService;


@Service
public class RentOrderService implements IRentOrderService {

	@Autowired
	private RentOrderRespository rentOrderRespo;
	
	@Autowired
	private ClassroomRepository classroomRepository;

	@Autowired 
	private RentOrderClassroomService rentOrderClassroomService;
	
	// 查詢全部
	@Override
	public List<RentOrder> findAll() {
		List<RentOrder> result = rentOrderRespo.findAll();
		return result;
	}

	//新增訂單並且加中間表
	@Override
	public RentOrder createRentOrderWithClassroom(RentOrder rentOrder) {
		// 獲取教室
		Classroom classroom = classroomRepository.findById(rentOrder.getClassroomid()).orElse(null);

		// 新增租借訂單
		RentOrder savedRentOrder = rentOrderRespo.save(rentOrder);

		// 建立新的 RentOrderClassroom 
		RentOrderClassroom rentOrderClassroom = new RentOrderClassroom();
		rentOrderClassroom.setRentorderid(rentOrder.getRentorderid());
		rentOrderClassroom.setClassroomid(classroom.getClassroomId());
		// 新增 RentOrderClassroom 
		rentOrderClassroomService.insert(rentOrderClassroom);
		return savedRentOrder;
	}

	// 新增單筆
	@Override
	public RentOrder insert(RentOrder rentOrder) {
		RentOrder result = rentOrderRespo.save(rentOrder);
		return result;
	}

	// 修改單筆
	@Override
	public void updateById(RentOrder rentOrder) {
		Boolean result = findById(rentOrder.getRentorderid());
		if (result) {
			rentOrderRespo.saveAndFlush(rentOrder);
		}
	}

	// 刪除單筆
	@Override
	public void deleteById(Integer id) {
		Boolean result = rentOrderRespo.existsById(id);

		if (result) {
			rentOrderRespo.deleteById(id);
		}
	}

	// 確認id存在
	@Override
	public Boolean findById(Integer id) {
		Boolean result = rentOrderRespo.existsById(id);
		return result;
	}
}
