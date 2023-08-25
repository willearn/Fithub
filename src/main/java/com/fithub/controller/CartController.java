package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Cart findCart(@PathVariable("cid") int cid) {
		return cService.findById(cid); 
	}
	
	@GetMapping("/findAll")
	public List<Cart> findAllCarts() {
		return cService.findAll();
	}
	
	@PostMapping
	public Cart insertCart(@RequestBody Cart cBean) {	
		return cService.insert(cBean);
	}	
	
	@PutMapping
	public Boolean updateCart(@RequestBody Cart cBean) {	
		return cService.update(cBean);
	}	
	
	@DeleteMapping("/{cid}")
	public Boolean deleteCart(@PathVariable("cid") int cid) {
		return cService.deleteById(cid);
						
	}
}
