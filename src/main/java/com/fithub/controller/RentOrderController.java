package com.fithub.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.classroom.Classroom;
import com.fithub.model.classroom.IClassroomService;
import com.fithub.model.rentorder.IRentOrderService;
import com.fithub.model.rentorder.RentOrder;

//http://localhost:8080/fithub/rent/list
@RestController
@RequestMapping("/rent")
public class RentOrderController {
	
	@Autowired
	private IRentOrderService iRentOrderService;
	
	@Autowired
	private IClassroomService classroomService;
	
    // 儲存租借訂單
    @PostMapping("/insert")
    public void insertRentOrder(@RequestBody RentOrder rentOrder) {
    	List<Classroom> classroom= new ArrayList<Classroom>();
    	System.out.println(rentOrder.getClassroomid());
    	classroom.add(classroomService.findById(rentOrder.getClassroomid()));
    	rentOrder.setClassroom(classroom);
    	iRentOrderService.insert(rentOrder);
    }

    // 列出所有租借訂單
    @GetMapping("/list")
    public List<RentOrder> findAllRentOrders() {
        return iRentOrderService.findAll();
    }

    // 刪除租借訂單
    @GetMapping("/delete/{id}")
    public void deleteRentOrder(@PathVariable("id") int id) {
    	iRentOrderService.deleteById(id);
    }
  
}
