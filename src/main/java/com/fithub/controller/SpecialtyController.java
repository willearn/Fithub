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
import com.fithub.model.specialty.ISpecialtyService;
import com.fithub.model.specialty.Specialty;

@RestController
@CrossOrigin()
public class SpecialtyController {

	@Autowired
	private ISpecialtyService sService;
	
	@GetMapping("/specialtys/{sid}")
	public ResponseEntity<Specialty> findById(@PathVariable("sid") int sid) throws JsonProcessingException {
		Specialty spec = sService.findById(sid);

		if(spec != null) {
			return new ResponseEntity<Specialty>(spec,HttpStatus.OK);
		}
		
		return new ResponseEntity<Specialty>(spec,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/specialtys")
	public ResponseEntity<List<Specialty>> findAll() throws JsonProcessingException {
		List<Specialty> spec = sService.findAll();
		
		if (spec != null) {
			return new ResponseEntity<List<Specialty>>(spec,HttpStatus.OK);
		}
		
		return new ResponseEntity<List<Specialty>>(spec,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/specialtys")
	public ResponseEntity<Object> insert(@RequestBody Specialty dBean) {
		boolean result = sService.insert(dBean);
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/specialtys/{sid}")
	public ResponseEntity<Object> delete(@PathVariable("sid") int sid){
		sService.deleteById(sid);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/specialtys/{sid}")
	public ResponseEntity<Object> updateById(@PathVariable("sid") int sid,@RequestBody Specialty dBean) {
		if(sService.findById(sid) != null) {
			boolean result = sService.update(dBean);
			if(result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
