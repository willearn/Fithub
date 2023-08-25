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

import com.fithub.model.course.Course;
import com.fithub.model.course.ICourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin()
public class CourseController {

	@Autowired
	private ICourseService cService;
		
	@GetMapping("/{cid}")
	public Course findCourse(@PathVariable("cid") int cid) {
		return cService.findById(cid); 
	}
	
	@GetMapping("/findAll")
	public List<Course> findAllCourses() {
		return cService.findAll();
	}
	
	@PostMapping
	public Course insertCourse(@RequestBody Course cBean) {	
		return cService.insert(cBean);
	}	
	
	@PutMapping
	public Boolean updateCourse(@RequestBody Course cBean) {	
		return cService.update(cBean);
	}	
	
	@DeleteMapping("/{cid}")
	public Boolean deleteCourse(@PathVariable("cid") int cid) {
		return cService.deleteById(cid);
						
	}
	
	
}
