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

import com.fithub.model.wishlist.IWishlistService;
import com.fithub.model.wishlist.Wishlist;


@RestController
@RequestMapping("/wishlist")
@CrossOrigin()
public class WishlistController {

	@Autowired
	private IWishlistService wService;
		
	@GetMapping("/{cid}")
	public Wishlist findWishlist(@PathVariable("cid") int cid) {
		return wService.findById(cid); 
	}
	
	@GetMapping("/findAll")
	public List<Wishlist> findAllWishlists() {
		return wService.findAll();
	}
	
	@PostMapping
	public Wishlist insertWishlist(@RequestBody Wishlist wBean) {	
		return wService.insert(wBean);
	}	
	
	@PutMapping
	public Boolean updateWishlist(@RequestBody Wishlist wBean) {	
		return wService.update(wBean);
	}	
	
	@DeleteMapping("/{cid}")
	public Boolean deleteWishlist(@PathVariable("cid") int cid) {
		return wService.deleteById(cid);
						
	}
	
}
