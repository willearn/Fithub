package com.fithub.controller;

import java.util.ArrayList;
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
import com.fithub.model.classroom.IClassroomService;
import com.fithub.model.employee.Employee;

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
	public ResponseEntity<?> findAllClassroomNamesAndIds() {
		try {
			List<Object[]> classroomNamesAndIds = iclassroomService.findAllClassroomNamesAndIds();
			return new ResponseEntity<>(classroomNamesAndIds, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Author Chrislafolia
	// 查詢全部教室 不含 description 和 pic
	@GetMapping("/listWithoutDescriptionsAndPics")
	public ResponseEntity<?> findAllClassroomsWithoutDescriptionsAndPics() {
		try {
			List<Object[]> resultList = iclassroomService.findAllClassroomsWithoutDescriptionsAndPics();
			if (resultList != null) {
				List<Classroom> classroomsList = new ArrayList<>();
				for (Object[] result : resultList) {
				    // Assuming the first element of the result array is the employee title and the second is the employee ID
					int classroomId = (int) result[0];
				    String classroomName = (String) result[1];
				    int classroomCapacity =(int) result[2];
				    int classroomPrice =(int) result[3];
				    String classroomStatus =(String) result[4];
				    

				    Classroom classroom = new Classroom();
				    classroom.setClassroomId(classroomId);
				    classroom.setClassroomName(classroomName);
				    classroom.setClassroomCapacity(classroomCapacity);
				    classroom.setClassroomPrice(classroomPrice);
				    classroom.setClassroomStatus(classroomStatus);

				    classroomsList.add(classroom);
				}
				return new ResponseEntity<>(classroomsList, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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
