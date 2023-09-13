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
import com.fithub.model.backstageaccount.BackStageAccount;
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
		System.out.println(result);
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
	
	@PostMapping("/departments/findPageByName")
	public ResponseEntity<?> findPageByName(@RequestBody String json) {
		System.out.println("JSON");
		System.out.println(json.toString());
		try {
			JSONObject obj = new JSONObject(json);

			JSONObject responseJson = new JSONObject();
			JSONArray array = new JSONArray();

			String name = obj.isNull("name") ? null : obj.getString("name");

			long count;
			
			
			
			//有的話 依照name去搜尋有幾筆資料，沒有則搜尋全部
			if (name != null) {
				Page<Object[]> page;
				count = dService.count(obj.getString("name"));
				page = dService.findPageByName(obj.getInt("start"), obj.getInt("rows"),
						obj.getString("name"));
				
				responseJson.put("count", count);

				for (Object[] dept : page) {
					JSONObject item = new JSONObject().put("deptid", dept[0])
							.put("deptname",dept[1]);
					array = array.put(item);
				}
			}else {
				Page<Department> page;
				count = dService.count();
				page = dService.findByPage(obj.getInt("start"), obj.getInt("rows"));
				
				responseJson.put("count", count);

				for (Department dept : page) {
					JSONObject item = new JSONObject().put("deptid", dept.getDeptid())
							.put("deptname", dept.getDeptname());
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
