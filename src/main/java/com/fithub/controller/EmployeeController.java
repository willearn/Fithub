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
import com.fithub.model.employee.Employee;
import com.fithub.model.employee.IEmployeeService;

@RestController
@CrossOrigin()
public class EmployeeController {

	@Autowired
	private IEmployeeService eService;
	
	@GetMapping("/employees/{eid}")
	public ResponseEntity<Employee> findById(@PathVariable("eid") int eid) throws JsonProcessingException {
		Employee emp = eService.findById(eid);
		
		if (emp != null) {
			
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		}
		
		return new ResponseEntity<Employee>(emp,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> findAll() throws JsonProcessingException {
		List<Employee> emps = eService.findAll();
		
		if (emps != null) {
			return new ResponseEntity<List<Employee>>(emps,HttpStatus.OK);
		}
		
		return new ResponseEntity<List<Employee>>(emps,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Object> insert(@RequestBody Employee eBean) {
		boolean result = eService.insert(eBean);
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/employees/{eid}")
	public ResponseEntity<Object> updateById(@PathVariable("eid") int eid,@RequestBody Employee eBean) {
		System.out.println("eBean : " + eBean.getDeptid());
		if(eService.findById(eid) != null) {
			boolean result = eService.update(eBean);
			if(result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/employees/{eid}")
	public ResponseEntity<Object> delete(@PathVariable("eid") int eid){
		eService.deleteById(eid);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
