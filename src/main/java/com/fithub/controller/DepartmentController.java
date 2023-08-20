package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fithub.model.department.Department;
import com.fithub.model.department.DepartmentService;

@RestController
public class DepartmentController {

//	@Autowired
//	private IDepartmentService dService;
	
	@Autowired
	private DepartmentService dService;
	
	@GetMapping("/departments/{did}")
	public ResponseEntity<Department> findById(@PathVariable("did") int did) throws JsonProcessingException {
		Department dept = dService.findById(did);

		if(dept != null) {
			System.out.println("dept not null");
			return new ResponseEntity<Department>(dept,HttpStatus.OK);
		}
		System.out.println("dept is null");
		
		return new ResponseEntity<Department>(dept,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> findAll() throws JsonProcessingException {
		List<Department> dept = dService.findAll();
		
		if (dept != null) {
			return new ResponseEntity<List<Department>>(dept,HttpStatus.OK);
		}
		
		return new ResponseEntity<List<Department>>(dept,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/departments")
	public ResponseEntity<String> insert(@RequestBody Department dBean) {
		Department resultBean = dService.insert(dBean);
		if(resultBean!=null) {
			return new ResponseEntity<String>("true",HttpStatus.OK);
		}
		return new ResponseEntity<String>("false",HttpStatus.OK);
	}
	
	@DeleteMapping("/departments/{did}")
	public ResponseEntity<String> delete(@PathVariable("did") int did){
		dService.deleteById(did);
			return new ResponseEntity<String>("true",HttpStatus.OK);
	}
	
	@PutMapping("/departments")
	public ResponseEntity<String> updateById(@RequestBody Department dBean) {
		Department resultBean = dService.update(dBean);
		if(resultBean!=null) {
			return new ResponseEntity<String>("true",HttpStatus.OK);
		}
		return new ResponseEntity<String>("false",HttpStatus.OK);
		
	}
}
