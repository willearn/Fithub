package com.fithub.controller;

import java.util.Base64;
import java.util.List;
import java.util.Map;

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
import com.fithub.model.coachpic.CoachPic;
import com.fithub.model.coachpic.ICoachPicService;

@RestController
@CrossOrigin()
public class CoachPicController {

	@Autowired
	private ICoachPicService cService;

	@GetMapping("/coachpics/{cid}")
	public ResponseEntity<CoachPic> findById(@PathVariable("cid") int cid) throws JsonProcessingException {
		CoachPic spec = cService.findById(cid);

		if (spec != null) {
			return new ResponseEntity<CoachPic>(spec, HttpStatus.OK);
		}

		return new ResponseEntity<CoachPic>(spec, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/coachpics")
	public ResponseEntity<List<CoachPic>> findAll() throws JsonProcessingException {
		List<CoachPic> spec = cService.findAll();

		if (spec != null) {
			return new ResponseEntity<List<CoachPic>>(spec, HttpStatus.OK);
		}

		return new ResponseEntity<List<CoachPic>>(spec, HttpStatus.NOT_FOUND);
	}

//	@PostMapping("/coachpics")
//	public ResponseEntity<Object> insert(@RequestBody CoachPic cBean) {
//		boolean result = cService.insert(cBean);
//		if(result){
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}

	@PostMapping("/coachpics")
	public ResponseEntity<Object> insert(@RequestBody Map<String, String> request) {
		Integer employeeid = Integer.parseInt(request.get("employeeid"));
		String base64Image = request.get("cpicfile");
		byte[] imageBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);
		
		CoachPic cBean = new CoachPic();
		cBean.setEmployeeid(employeeid);
		cBean.setCpicfile(imageBytes);
		
		
		boolean result = cService.insert(cBean);
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/coachpics/{cid}")
	public ResponseEntity<Object> delete(@PathVariable("cid") int cid) {
		cService.deleteById(cid);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/coachpics/{cid}")
	public ResponseEntity<Object> updateById(@PathVariable("cid") int cid, @RequestBody CoachPic cBean) {
		if (cService.findById(cid) != null) {
			boolean result = cService.update(cBean);
			if (result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
