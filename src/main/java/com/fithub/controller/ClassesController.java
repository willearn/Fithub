package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.classes.Classes;
import com.fithub.model.classes.IClassesService;

@RestController
@RequestMapping("/classes")
@CrossOrigin()
public class ClassesController {

	@Autowired
	private IClassesService cService;
		
	@GetMapping("/{cid}")
	public Classes findClasses(@PathVariable("cid") int cid) {
		return cService.findById(cid); 
	}
	
	@GetMapping("/findAll")
	public List<Classes> findAllClasses() {
		return cService.findAll();
	}
	
	@PutMapping
	public Boolean updateClasses(@RequestBody Classes cBean) {	
		return cService.update(cBean);
	}	
	
	@DeleteMapping("/{cid}")
	public Boolean deleteClasses(@PathVariable("cid") int cid) {
		return cService.deleteById(cid);
						
	}
}
