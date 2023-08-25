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

import com.fithub.model.classes.Classes;
import com.fithub.model.classes.IClassesService;

@RestController
@RequestMapping("/classes")
@CrossOrigin()
public class ClassesController {

	@Autowired
	private IClassesService cService;
		
	@GetMapping("/{cid}")
	public ResponseEntity<?> findClasses(@PathVariable("cid") int cid) {
        try {
        	Classes resultBean = cService.findById(cid); 
            return new ResponseEntity<>(resultBean, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllClasses() {
        try {
            List<Classes> resultList = cService.findAll();
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@PostMapping
	public ResponseEntity<?> insertClasses(@RequestBody Classes cBean) {	
        try {
        	Classes resultBean=cService.insert(cBean);
            return new ResponseEntity<>(resultBean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@PutMapping
	public ResponseEntity<?> updateClasses(@RequestBody Classes cBean) {	
        try {
        	Boolean resultBoolean=cService.update(cBean);
            return new ResponseEntity<>(resultBoolean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}	
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<?> deleteClasses(@PathVariable("cid") int cid) {
        try {
        	Boolean resultBoolean=cService.deleteById(cid);
            return new ResponseEntity<>(resultBoolean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
						
	}
}
