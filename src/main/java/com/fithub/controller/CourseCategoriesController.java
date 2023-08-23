package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.coursecategories.CourseCategories;
import com.fithub.model.coursecategories.ICourseCategoriesService;

@CrossOrigin
@RestController
@RequestMapping("/coursecategories")
public class CourseCategoriesController {

	
	@Autowired
	private ICourseCategoriesService cService;
	
	@GetMapping("/cousecategories.controller/{cid}")
	public CourseCategories processQueryAction(@PathVariable("cid") int cid) {
		CourseCategories resultBean = cService.findById(cid); 
		return resultBean;
	}
	
	@GetMapping("/cousecategories.controller")
	public List<CourseCategories> processQueryAllAction() {
		return cService.findAll();
	}
}
