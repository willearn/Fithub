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

import com.fithub.model.course.Course;
import com.fithub.model.course.ICourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin()
public class CourseController {

	@Autowired
	private ICourseService cService;

	@GetMapping("/{cid}")
	public ResponseEntity<?> findCourse(@PathVariable("cid") int cid) {
		try {
			Course resultBean = cService.findById(cid);
			return new ResponseEntity<>(resultBean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAllCourses() {
		try {
			List<Course> resultList = cService.findAll();
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> insertCourse(@RequestBody Course cBean) {
		try {
			Course resultBean = cService.insert(cBean);
			return new ResponseEntity<>(resultBean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{cid}")
	public ResponseEntity<?> updateCourse(@RequestBody Course cBean) {
		try {
			Boolean resultBoolean = cService.update(cBean);
			return new ResponseEntity<>(resultBoolean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{cid}")
	public ResponseEntity<?> deleteCourse(@PathVariable("cid") int cid) {
		try {
			Boolean resultBoolean = cService.deleteById(cid);
			return new ResponseEntity<>(resultBoolean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/deleteMultiple")
	public ResponseEntity<?> deleteMultipleCourses(@RequestBody List<Integer> cids) {
		try {
			cService.deleteAllById(cids);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
