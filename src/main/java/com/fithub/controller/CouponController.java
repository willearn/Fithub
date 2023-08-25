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

import com.fithub.model.coupon.Coupon;
import com.fithub.model.coupon.ICouponService;

@CrossOrigin
@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final ICouponService couponService;

    @Autowired
    public CouponController(ICouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCoupons() {
        try {
            List<Coupon> coupons = couponService.getAllCoupons();
            return new ResponseEntity<>(coupons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCouponById(@PathVariable Integer id) {
       try {
    	   Coupon coupon = couponService.getCouponById(id);
           return new ResponseEntity<>(coupon, HttpStatus.OK);
       } catch(Exception e) {
    	   return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PostMapping
    public ResponseEntity<?> saveCoupon(@RequestBody Coupon coupon) {
       try {    
    	   couponService.saveCoupon(coupon);
    	   return new ResponseEntity<>("Coupon inserted successfully.",HttpStatus.OK);
       } catch(Exception e) {
    	   return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }    
    

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCoupon(@PathVariable Integer id, @RequestBody Coupon coupon) {
        try {
            if (!couponService.existsById(id)) {
                return new ResponseEntity<>("Coupon with ID " + id + " not found.", HttpStatus.NOT_FOUND);
            }            
            coupon.setCouponid(id);
            couponService.updateCoupon(coupon);
            
            return new ResponseEntity<>("Coupon updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable Integer id) {
        try {
            couponService.deleteCouponById(id);
            return new ResponseEntity<>("Coupon deleted successfully.",HttpStatus.OK);
        } catch (Exception e) {
        	return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
 // 刪除多筆訂單
 	@DeleteMapping("/delete/multiple")
 	public ResponseEntity<?> deleteMultipleRentOrders(@RequestBody List<Integer> ids) {
 		try {
 			couponService.deleteAllById(ids);
 			return new ResponseEntity<>(HttpStatus.OK);
 		} catch (Exception e) {
 			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
 		}
 	}

}


    


