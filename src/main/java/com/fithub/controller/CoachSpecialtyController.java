package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
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
import com.fithub.model.coachspecialty.CoachSpecialty;
import com.fithub.model.coachspecialty.ICoachSpecialtyService;
import com.fithub.model.department.Department;

@RestController
@CrossOrigin()
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
		System.out.println(cBean.getCoachspecialtyid());
		System.out.println(cBean.getEmployeeid());
		System.out.println(cBean.getSpecialtyid());
		if(cService.findById(cid) != null) {
			boolean result = cService.update(cBean);
			if(result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/coachspecialtys/findPageByName")
	public ResponseEntity<?> findPageByName(@RequestBody String json) {
		System.out.println("JSON");
		System.out.println(json.toString());
		try {
			JSONObject obj = new JSONObject(json);

			JSONObject responseJson = new JSONObject();
			JSONArray array = new JSONArray();

			String name = obj.isNull("name") ? null : obj.getString("name");

			long count;
			
			
			
			//有的話 依照name去搜尋有幾筆資
//			料，沒有則搜尋全部
			if (name != null) {
				Page<CoachSpecialty> page;
				count = cService.count(obj.getString("name"));
				System.out.println("count1");
				System.out.println(count);
				page = cService.findPageByName(obj.getInt("start"), obj.getInt("rows"),
						obj.getString("name"));
				
				responseJson.put("count", count);

				for (CoachSpecialty coachSpecialty : page) {
					JSONObject item = new JSONObject()
							.put("coachspecialtyid", coachSpecialty.getCoachspecialtyid())
							.put("employeeid", coachSpecialty.getEmployee().getEmployeeid())
							.put("employeename", coachSpecialty.getEmployee().getEmployeename())
							.put("specialtyid", coachSpecialty.getSpecialtyid())
							.put("specialtyname", coachSpecialty.getSpecialty().getSpecialtyname());
					array = array.put(item);
				}
			}else {
				Page<CoachSpecialty> page;
				count = cService.count();
				page = cService.findByPage(obj.getInt("start"), obj.getInt("rows"));

				responseJson.put("count", count);

				for (CoachSpecialty coachSpecialty : page) {
					JSONObject item = new JSONObject()
							.put("coachspecialtyid", coachSpecialty.getCoachspecialtyid())
							.put("employeeid", coachSpecialty.getEmployee().getEmployeeid())
							.put("employeename", coachSpecialty.getEmployee().getEmployeename())
							.put("specialtyid", coachSpecialty.getSpecialtyid())
							.put("specialtyname", coachSpecialty.getSpecialty().getSpecialtyname());
					array = array.put(item);
				}
			}
			
		

			responseJson.put("list", array);
			return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
