package com.fithub.controller;

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

@CrossOrigin
@RestController
@RequestMapping("/classroom")
public class ClassroomController {

	@Autowired
	private IClassroomService iclassroomService;

	// 列出所有教室
	@GetMapping("/list")
	public List<Classroom> findAllClassrooms() {
		return iclassroomService.findAll();
	}

	// 新增教室
	@PostMapping("/insert")
	public void insertClassroom(@RequestBody Classroom classroom) {
		iclassroomService.insert(classroom);
	}

	// 修改教室
	@PutMapping("/update")
	public void updateClassroom(@RequestBody Classroom classroom) {
		iclassroomService.updateById(classroom);
	}

	// 刪除教室
	@DeleteMapping("/delete/{id}")
	public void deleteClassroom(@PathVariable("id") int id) {
		iclassroomService.deleteById(id);
	}

	// 刪除多間教室
	@DeleteMapping("/delete/multiple")
	public void deleteMultipleRentOrders(@RequestBody List<Integer> ids) {
		iclassroomService.deleteAllById(ids);
	}

}
