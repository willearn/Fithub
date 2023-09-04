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

import com.fithub.model.classroom.Classroom;
import com.fithub.model.classroom.ClassroomDTO;
import com.fithub.model.classroom.IClassroomService;

@CrossOrigin
@RestController
@RequestMapping("/classroom")
public class ClassroomController {

	@Autowired
	private IClassroomService iclassroomService;

	// 列出所有教室
	@GetMapping("/list")
	public ResponseEntity<?> findAllClassrooms() {
		try {
			List<Classroom> classrooms = iclassroomService.findAll();
			return new ResponseEntity<>(classrooms, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 列出所有教室名稱和ID
	@GetMapping("/listName")
	public ResponseEntity<?> findAllClassroomNameAndId() {
		try {
			List<Object[]> classroomNamesAndIds = iclassroomService.findAllClassroomNameAndId();
			return new ResponseEntity<>(classroomNamesAndIds, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 列出所有教室名稱,狀態,ID
	@GetMapping("/getClassroomInfo")
	public ResponseEntity<?> getClassroomInfo() {
		try {
			List<ClassroomDTO> classroomInfo = iclassroomService.getClassroomInfo();
			return new ResponseEntity<>(classroomInfo, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 新增教室
	@PostMapping("/insert")
	public ResponseEntity<?> insertClassroom(@RequestBody Classroom classroom) {
		try {
			iclassroomService.insert(classroom);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 修改教室
	@PutMapping("/update")
	public ResponseEntity<?> updateClassroom(@RequestBody Classroom classroom) {
		try {
			iclassroomService.updateById(classroom);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 刪除教室
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteClassroom(@PathVariable("id") int id) {
		try {
			iclassroomService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 刪除多間教室
	@DeleteMapping("/delete/multiple")
	public ResponseEntity<?> deleteMultipleRentOrders(@RequestBody List<Integer> ids) {
		try {
			iclassroomService.deleteAllById(ids);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
