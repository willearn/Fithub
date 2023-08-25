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

import com.fithub.model.classesset.ClassesSet;
import com.fithub.model.classesset.IClassesSetService;

@RestController
@RequestMapping("/classesset")
@CrossOrigin()
public class ClassesSetController {

	@Autowired
	private IClassesSetService cService;
		
	@GetMapping("/{cid}")
	public ResponseEntity<?> findClassesSet(@PathVariable("cid") int cid) {
        try {
        	ClassesSet resultBean = cService.findById(cid); 
            return new ResponseEntity<>(resultBean, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllClassesSets() {
        try {
            List<ClassesSet> resultList = cService.findAll();
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@PostMapping
	public ResponseEntity<?> insertClassesSet(@RequestBody ClassesSet cBean) {	
        try {
        	ClassesSet resultBean=cService.insert(cBean);
            return new ResponseEntity<>(resultBean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}	
	
	@PutMapping
	public ResponseEntity<?> updateClassesSet(@RequestBody ClassesSet cBean) {	
        try {
        	Boolean resultBoolean=cService.update(cBean);
            return new ResponseEntity<>(resultBoolean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}	
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<?> deleteClassesSet(@PathVariable("cid") int cid) {
        try {
        	Boolean resultBoolean=cService.deleteById(cid);
            return new ResponseEntity<>(resultBoolean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
						
	}
}
