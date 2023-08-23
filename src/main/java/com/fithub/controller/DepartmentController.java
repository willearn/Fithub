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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fithub.model.department.Department;
import com.fithub.model.department.IDepartmentService;

@RestController
@CrossOrigin()
public class DepartmentController {

//	@Autowired
//	private IDepartmentService dService;
	
	@Autowired
	private IDepartmentService dService;
	
	@GetMapping("/departments/{did}")
	public ResponseEntity<Department> findById(@PathVariable("did") int did) throws JsonProcessingException {
		Department dept = dService.findById(did);

		if(dept != null) {
			return new ResponseEntity<Department>(dept,HttpStatus.OK);
		}
		
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
	public ResponseEntity<Object> insert(@RequestBody Department dBean) {
		boolean result = dService.insert(dBean);
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/departments/{did}")
	public ResponseEntity<Object> delete(@PathVariable("did") int did){
		dService.deleteById(did);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/departments/{did}")
	public ResponseEntity<Object> updateById(@PathVariable("did") int did,@RequestBody Department dBean) {
		if(dService.findById(did) != null) {
			boolean result = dService.update(dBean);
			if(result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
