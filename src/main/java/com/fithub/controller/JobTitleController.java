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
import com.fithub.model.jobtitle.JobTitle;
import com.fithub.model.jobtitle.IJobTitleService;

@RestController
@CrossOrigin()
public class JobTitleController {

	@Autowired
	private IJobTitleService jService;
	
	@GetMapping("/jobtitles/{jid}")
	public ResponseEntity<JobTitle> findById(@PathVariable("jid") int jid) throws JsonProcessingException {
		JobTitle jobtitle = jService.findById(jid);

		if(jobtitle != null) {
			return new ResponseEntity<JobTitle>(jobtitle,HttpStatus.OK);
		}
		
		return new ResponseEntity<JobTitle>(jobtitle,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/jobtitles")
	public ResponseEntity<List<JobTitle>> findAll() throws JsonProcessingException {
		List<JobTitle> jobtitle = jService.findAll();
		
		if (jobtitle != null) {
			return new ResponseEntity<List<JobTitle>>(jobtitle,HttpStatus.OK);
		}
		
		return new ResponseEntity<List<JobTitle>>(jobtitle,HttpStatus.NOT_FOUND);
	}
	

	
	@PostMapping("/jobtitles")
	public ResponseEntity<Object> insert(@RequestBody JobTitle jBean) {
		boolean result = jService.insert(jBean);
		System.out.println(result);
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/jobtitles/{jid}")
	public ResponseEntity<Object> delete(@PathVariable("jid") int jid){
		jService.deleteById(jid);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/jobtitles/{jid}")
	public ResponseEntity<Object> updateById(@PathVariable("jid") int jid,@RequestBody JobTitle jBean) {
		if(jService.findById(jid) != null) {
			boolean result = jService.update(jBean);
			if(result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	public Integer findJobTitleIdByName(String jobtitlename) {
		Integer result = jService.findJobTitleByName(jobtitlename);
		System.out.println("findJobTitleIdByName--------------" + result);
		return result;
	}
}
