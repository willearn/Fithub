package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fithub.model.classroom.Classroom;
import com.fithub.model.classroom.IClassroomService;

@CrossOrigin
@Controller
@RequestMapping("/classroom")
public class ClassroomController {

	@Autowired
	private IClassroomService iclassroomService;

	// 列出所有教室
	@GetMapping("/list")
	@ResponseBody
	public List<Classroom> findAllClassrooms() {
		return iclassroomService.findAll();
	}

	   @PostMapping("/insert")
	    public void insertClassroom(
	            @RequestPart("classroomName") String classroomName,
	            @RequestPart("classroomCapacity") int classroomCapacity,
	            @RequestPart("classroomDescription") String classroomDescription,
	            @RequestPart("classroomPrice") double classroomPrice,
	            @RequestPart("classroomStatus") String classroomStatus
	            ) {
	        
	        System.out.println("Classroom Name: " + classroomName);
	        System.out.println("Classroom Capacity: " + classroomCapacity);
	        System.out.println("Classroom Description: " + classroomDescription);
	        System.out.println("Classroom Price: " + classroomPrice);
	        System.out.println("Classroom Status: " + classroomStatus);
	    
	        
	        // iclassroomService.insert(classroom);
	    }

	    
	
	
	// 修改教室
	@PutMapping("/update")
	@ResponseBody
	public void updateClassroom(@RequestBody Classroom classroom) {
		iclassroomService.updateById(classroom);
	}

	// 刪除教室
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void deleteClassroom(@PathVariable("id") int id) {
		iclassroomService.deleteById(id);
	}

	// 刪除多間教室
	@DeleteMapping("/delete/multiple")
	@ResponseBody
	public void deleteMultipleRentOrders(@RequestBody List<Integer> ids) {
		iclassroomService.deleteAllById(ids);
	}

}
