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

import com.fithub.model.wishlist.IWishlistService;
import com.fithub.model.wishlist.Wishlist;


@RestController
@RequestMapping("/wishlist")
@CrossOrigin()
public class WishlistController {

	@Autowired
	private IWishlistService wService;
		
	@GetMapping("/{cid}")
	public ResponseEntity<?> findWishlist(@PathVariable("cid") int cid) {
        try {
            Wishlist resultBean = wService.findById(cid); 
            return new ResponseEntity<>(resultBean, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllWishlists() {
        try {
            List<Wishlist> resultList = wService.findAll();
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@PostMapping
	public ResponseEntity<?> insertWishlist(@RequestBody Wishlist wBean) {	
        try {
        	Wishlist resultBean=wService.insert(wBean);
            return new ResponseEntity<>(resultBean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}	
	
	@PutMapping
	public ResponseEntity<?> updateWishlist(@RequestBody Wishlist wBean) {	
        try {
        	Boolean resultBoolean=wService.update(wBean);
            return new ResponseEntity<>(resultBoolean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}	
	
	@DeleteMapping("/{wid}")
	public ResponseEntity<?> deleteWishlist(@PathVariable("wid") int wid) {
        try {
        	Boolean resultBoolean=wService.deleteById(wid);
            return new ResponseEntity<>(resultBoolean,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
						
	}
	
}
