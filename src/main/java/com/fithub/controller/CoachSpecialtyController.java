package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fithub.model.coachspecialty.CoachSpecialty;
import com.fithub.model.coachspecialty.ICoachSpecialtyService;

@RestController
public class CoachSpecialtyController {

	@Autowired
	private ICoachSpecialtyService cService;
	
	@GetMapping("/coachspecialtys/{cid}")
	public ResponseEntity<CoachSpecialty> findById(@PathVariable("cid") int cid) throws JsonProcessingException {
		CoachSpecialty spec = cService.findById(cid);

		if(spec != null) {
			return new ResponseEntity<CoachSpecialty>(spec,HttpStatus.OK);
		}
		
		return new ResponseEntity<CoachSpecialty>(spec,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/coachspecialtys")
	public ResponseEntity<List<CoachSpecialty>> findAll() throws JsonProcessingException {
		List<CoachSpecialty> spec = cService.findAll();
		
		if (spec != null) {
			return new ResponseEntity<List<CoachSpecialty>>(spec,HttpStatus.OK);
		}
		
		return new ResponseEntity<List<CoachSpecialty>>(spec,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/coachspecialtys")
	public ResponseEntity<Object> insert(@RequestBody CoachSpecialty cBean) {
		boolean result = cService.insert(cBean);
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/coachspecialtys/{cid}")
	public ResponseEntity<Object> delete(@PathVariable("cid") int cid){
		cService.deleteById(cid);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/coachspecialtys/{cid}")
	public ResponseEntity<Object> updateById(@PathVariable("cid") int cid,@RequestBody CoachSpecialty cBean) {
		if(cService.findById(cid) != null) {
			boolean result = cService.update(cBean);
			if(result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
