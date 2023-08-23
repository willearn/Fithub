package com.fithub.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.classroom.Classroom;
import com.fithub.model.classroom.IClassroomService;
import com.fithub.model.rentorder.IRentOrderService;
import com.fithub.model.rentorder.RentOrder;

//http://localhost:8080/fithub/rent/list
@CrossOrigin
@RestController
@RequestMapping("/rent")
public class RentOrderController {

	@Autowired
	private IRentOrderService iRentOrderService;

	@Autowired
	private IClassroomService iclassroomService;

	// 列出所有租借訂單
	@GetMapping("/list")
	public List<RentOrder> findAllRentOrders() {
		return iRentOrderService.findAll();
	}

	// 儲存租借訂單
	@PostMapping("/insert")
	public void insertRentOrder(@RequestBody RentOrder rentOrder) {

		// 建立教室類的空集合
		List<Classroom> classrooms = new ArrayList<Classroom>();

		// 取得所有教室編號
		List<Integer> classroomids = rentOrder.getClassroomids();
		for (Integer classroomid : classroomids) {
			classrooms.add(iclassroomService.findById(classroomid));
		}

		// 將所有教室加到同一筆訂單
		rentOrder.setClassrooms(classrooms);
		// 新增訂單
		iRentOrderService.insert(rentOrder);
	}

	// 儲存租借訂單(多對多更新會自動刪除中間表,直接重新新增中間表)
	@PutMapping("/update")
	public void updateRentOrder(@RequestBody RentOrder rentOrder) {

		// 建立教室類的空集合
		List<Classroom> classrooms = new ArrayList<Classroom>();

		// 取得所有教室編號
		List<Integer> classroomids = rentOrder.getClassroomids();
		for (Integer classroomid : classroomids) {
			classrooms.add(iclassroomService.findById(classroomid));
		}

		// 將所有教室加到同一筆訂單
		rentOrder.setClassrooms(classrooms);

		// 修改訂單
		iRentOrderService.updateById(rentOrder);
	}

	// 刪除單筆訂單
	@DeleteMapping("/delete/{id}")
	public void deleteRentOrder(@PathVariable("id") int id) {
		iRentOrderService.deleteById(id);
	}

	// 刪除多筆訂單
	@DeleteMapping("/delete/multiple")
	public void deleteMultipleRentOrders(@RequestBody List<Integer> ids) {
		iRentOrderService.deleteAllById(ids);
	}

}
