package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.rentorder.IRentOrderService;
import com.fithub.model.rentorder.RentOrder;

@CrossOrigin
@RestController
@RequestMapping("/rent")
public class RentOrderController {

	@Autowired
	private IRentOrderService iRentOrderService;

	// 列出所有租借訂單
	@GetMapping("/list")
	public ResponseEntity<?> findAllRentOrders() {
		try {
			List<RentOrder> rentOrders = iRentOrderService.findAll();
			return new ResponseEntity<>(rentOrders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list/{classroomId}")
	public ResponseEntity<?> findAllDateTimeFromRentOrderAndclass(@PathVariable("classroomId") Integer classroomId) {
		try {
			List<Object[]> findAllDateTimeFromRentOrderAndclass = iRentOrderService
					.findAllDateTimeFromRentOrderAndclass(classroomId);

			return new ResponseEntity<>(findAllDateTimeFromRentOrderAndclass, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 查詢場地是否被預訂或使用
	@PostMapping("/checkClassroomAvailability")
	public ResponseEntity<?> checkClassroomAvailability(@RequestBody RentOrder rentOrder) {
		
		Integer classroomid = rentOrder.getClassroomid();
		String rentdate = rentOrder.getRentdate();
		String renttime = rentOrder.getRenttime();
		
		//true為已被使用 false為未使用
		Boolean classroomAvailability = iRentOrderService.checkClassroomAvailability(classroomid, rentdate, renttime);
		
		try {
			return new ResponseEntity<>(classroomAvailability, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 新增租借訂單
	@PostMapping("/insert")
	public ResponseEntity<?> insertRentOrder(@RequestBody RentOrder rentOrder) {
		try {
			RentOrder rentOrder2 =   iRentOrderService.insert(rentOrder);
			return new ResponseEntity<>(rentOrder2.getRentorderid(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 更新租借訂單
//	@PutMapping("/update")
//	public ResponseEntity<?> updateRentOrder(@RequestBody RentOrder rentOrder) {
//		try {
//			iRentOrderService.updateById(rentOrder);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	// 刪除單筆訂單
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRentOrder(@PathVariable("id") int id) {

		try {
			iRentOrderService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 刪除多筆訂單
	@DeleteMapping("/delete/multiple")
	public ResponseEntity<?> deleteMultipleRentOrders(@RequestBody List<Integer> ids) {
		try {
			iRentOrderService.deleteAllById(ids);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
