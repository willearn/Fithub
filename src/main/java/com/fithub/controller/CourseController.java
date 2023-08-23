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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.course.Course;
import com.fithub.model.course.ICourseService;

@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private ICourseService cService;
	
//	@GetMapping("/course")
//	public String showCourse() {
//		return "course/indexCourse";
//	}
	
	@PostMapping
	public String processIncertAction(@RequestBody Course cBean) {
		cService.insert(cBean);
		return "course/indexCourse";
		
	} 
	
	@GetMapping("/{cid}")
	@ResponseBody
	public Course processQueryAction(@PathVariable("cid") int cid) {
		Course resultBean = cService.findById(cid); 
		return resultBean;
	}
	
	@GetMapping
	@ResponseBody
	public List<Course> processQueryAllAction() {
		return cService.findAll();
	}
	
	@PutMapping
	public String processUpdateAction(@RequestBody Course cBean) {
		cService.update(cBean);
		return "course";
	}	
	
	@DeleteMapping(value = "/{cid}", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public Boolean processDeleteAction(@PathVariable("cid") int cid) {
		boolean msg=cService.deleteById(cid);
		if (msg) {
			return true;
		}
			return false;			
	}
	
	
}
