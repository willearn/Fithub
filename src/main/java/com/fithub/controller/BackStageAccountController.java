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
import com.fithub.model.backstageaccount.BackStageAccount;
import com.fithub.model.backstageaccount.IBackStageAccountService;

@RestController
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
		bService.deleteBackStageAccountByAccount(account);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	public BackStageAccount checkLogin(BackStageAccount bBean) {
		return bService.checkLogin(bBean);
	}
	
	public Integer checkLoa(String account) {
		return bService.checkLoa(account);
	}
}
