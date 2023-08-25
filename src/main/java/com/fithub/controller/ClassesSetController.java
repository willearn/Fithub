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

import com.fithub.model.classesset.ClassesSet;
import com.fithub.model.classesset.IClassesSetService;

@RestController
@RequestMapping("/classesset")
@CrossOrigin()
public class ClassesSetController {

	@Autowired
	private IClassesSetService cService;
		
	@GetMapping("/{cid}")
	public ClassesSet findClassesSet(@PathVariable("cid") int cid) {
		return cService.findById(cid); 
	}
	
	@GetMapping("/findAll")
	public List<ClassesSet> findAllClassesSets() {
		return cService.findAll();
	}
	
	@PostMapping
	public ClassesSet insertClassesSet(@RequestBody ClassesSet cBean) {	
		return cService.insert(cBean);
	}	
	
	@PutMapping
	public Boolean updateClassesSet(@RequestBody ClassesSet cBean) {	
		return cService.update(cBean);
	}	
	
	@DeleteMapping("/{cid}")
	public Boolean deleteClassesSet(@PathVariable("cid") int cid) {
		return cService.deleteById(cid);
						
	}
}
