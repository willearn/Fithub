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
import com.fithub.model.backstageaccount.IBackStageAccountService;
import com.fithub.model.employee.Employee;

@RestController
@CrossOrigin()
public class BackStageAccountController {

	@Autowired
	private IBackStageAccountService bService;
	
	@GetMapping("/backstageaccounts/{account}")
	public ResponseEntity<BackStageAccount> findById(@PathVariable("account") String account) throws JsonProcessingException {
		BackStageAccount resultBean = bService.findBackStageAccountByAccount(account);
		
		if (resultBean != null) {
			
			return new ResponseEntity<BackStageAccount>(resultBean,HttpStatus.OK);
		}
		
		return new ResponseEntity<BackStageAccount>(resultBean,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/backstageaccounts")
	public ResponseEntity<List<BackStageAccount>> findAll() throws JsonProcessingException {
		List<BackStageAccount> accounts = bService.findAll();
		
		if (accounts != null) {
			return new ResponseEntity<List<BackStageAccount>>(accounts,HttpStatus.OK);
		}
		
		return new ResponseEntity<List<BackStageAccount>>(accounts,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/backstageaccounts")
	public ResponseEntity<Object> insert(@RequestBody BackStageAccount bBean) {
		boolean result = bService.insert(bBean);
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/backstageaccounts/{account}")
	public ResponseEntity<Object> updateByAccount(@PathVariable("account") String account,@RequestBody BackStageAccount bBean) {
		
		if(bService.findBackStageAccountByAccount(account) != null) {
			boolean result = bService.update(bBean);
			if(result) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/backstageaccounts/{account}")
	public ResponseEntity<Object> delete(@PathVariable("account") String account){
		try {
			bService.deleteBackStageAccountByAccount(account);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
	}
	
	@PostMapping("/backstageaccounts/findPageByName")
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
				count = bService.count(obj.getString("name"));
				page = bService.findPageByName(obj.getInt("start"), obj.getInt("rows"),
						obj.getString("name"));
				
				responseJson.put("count", count);

				for (Object[] ba : page) {
					JSONObject item = new JSONObject().put("employeeid", ba[0])
							.put("employeeaccount",ba[1])
							.put("employeename",ba[2])
							.put("loa", ba[3]);
					array = array.put(item);
				}
			}else {
				Page<BackStageAccount> page;
				count = bService.count();
				page = bService.findByPage(obj.getInt("start"), obj.getInt("rows"));
				
				responseJson.put("count", count);

				for (BackStageAccount ba : page) {
					JSONObject item = new JSONObject().put("employeeid", ba.getEmployeeid())
							.put("employeeaccount", ba.getEmployeeaccount())
							.put("employeename", ba.getEmployee().getEmployeename())
							.put("loa", ba.getLoa());
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
	
	
	public BackStageAccount checkLogin(BackStageAccount bBean) {
		return bService.checkLogin(bBean);
	}
	
	public Integer checkLoa(String account) {
		return bService.checkLoa(account);
	}
}
