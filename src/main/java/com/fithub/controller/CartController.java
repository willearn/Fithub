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

import com.fithub.model.cart.Cart;
import com.fithub.model.cart.ICartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin()
public class CartController {

	@Autowired
	private ICartService cService;
		
	@GetMapping("/{cid}")
	public ResponseEntity<?> findCart(@PathVariable("cid") int cid) {
        try {
        	Cart resultBean = cService.findById(cid); 
            return new ResponseEntity<>(resultBean, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllCarts() {
        try {
            List<Cart> resultList = cService.findAll();
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@PostMapping
	public ResponseEntity<?> insertCart(@RequestBody Cart cBean) {	
        try {
        	Cart resultBean=cService.insert(cBean);
            return new ResponseEntity<>(resultBean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}	
	
	@PutMapping("/{cid}")
	public ResponseEntity<?> updateCart(@RequestBody Cart cBean) {	
        try {
        	Boolean resultBoolean=cService.update(cBean);
            return new ResponseEntity<>(resultBoolean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}	
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<?> deleteCart(@PathVariable("cid") int cid) {
        try {
        	Boolean resultBoolean=cService.deleteById(cid);
            return new ResponseEntity<>(resultBoolean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
						
	}
	
	@DeleteMapping("/deleteMultiple")
	public ResponseEntity<?> deleteMultipleCarts(@RequestBody List<Integer> cids) {
		try {
			cService.deleteAllById(cids);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
