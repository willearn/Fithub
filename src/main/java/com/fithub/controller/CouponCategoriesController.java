package com.fithub.controller;

import com.fithub.model.couponcategories.CouponCategories;
import com.fithub.model.couponcategories.CouponCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/coupon-categories")
public class CouponCategoriesController {

    private final CouponCategoriesService couponCategoriesService;

    @Autowired
    public CouponCategoriesController(CouponCategoriesService couponCategoriesService) {
        this.couponCategoriesService = couponCategoriesService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCouponCategories() {
        try {
            List<CouponCategories> couponCategoriesList = couponCategoriesService.getAllCouponCategories();
            return new ResponseEntity<>(couponCategoriesList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCouponCategoriesById(@PathVariable Integer id) {
        try {
            CouponCategories couponCategories = couponCategoriesService.getCouponCategoriesById(id);
            return new ResponseEntity<>(couponCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> saveCouponCategories(@RequestBody CouponCategories couponCategories) {
        try {
            CouponCategories savedCouponCategories = couponCategoriesService.saveCouponCategories(couponCategories);
            return new ResponseEntity<>(savedCouponCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  
    
	// 修改優惠券
	@PutMapping("/update")
	public ResponseEntity<?> updateCouponCategories(@RequestBody CouponCategories couponCategories) {
		try {
			couponCategoriesService.updateCouponCategories(couponCategories);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCouponCategories(@PathVariable Integer id) {
        try {
            couponCategoriesService.deleteCouponCategories(id);
            return new ResponseEntity<>("Coupon categories deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 // 刪除多筆訂單
  	@DeleteMapping("/delete/multiple")
  	public ResponseEntity<?> deleteMultipleRentOrders(@RequestBody List<Integer> ids) {
  		try {
  			couponCategoriesService.deleteAllById(ids);
  			return new ResponseEntity<>(HttpStatus.OK);
  		} catch (Exception e) {
  			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  		}
  	}
}
