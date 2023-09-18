package com.fithub.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
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
    
	// 修改優惠券
	@PutMapping("/update")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon) {
		try {
			couponService.updateCoupon(coupon);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{couponid}")
    public ResponseEntity<String> updateCouponUsedStatus(
            @PathVariable("couponid") Integer couponid,
            @RequestParam("couponused") String couponused) {
        try {
            couponService.updateCouponUsedStatus(couponid, couponused);
            return ResponseEntity.ok("Coupon updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating coupon: " + e.getMessage());
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
 	
 	@PostMapping("/api/coupon")
    public ResponseEntity<String> getCouponDiscount(@RequestBody Map<String, String> request) {
        String couponcode = request.get("couponcode");
        if (couponcode != null && !couponcode.isEmpty()) {
            String coupondiscount = couponService.getCouponDiscount(couponcode);
            return ResponseEntity.ok(coupondiscount);
        } else {
            // 優惠碼無效或未提供
            return ResponseEntity.badRequest().body("請提供有效的優惠碼");
        }
    }
 	
// 	@GetMapping("/api/{couponcode}")
//    public Coupon getCoupon(@PathVariable String couponcode) {
//        return couponService.getCoupon(couponcode);
//    }

 	@GetMapping("/api/{couponcode}")
 	public ResponseEntity<Coupon> getCoupon(@PathVariable String couponcode) {
 	    Coupon coupon = couponService.getCoupon(couponcode);
 	    if (coupon != null) {
 	        return ResponseEntity.ok(coupon); // 返回优惠码
 	    } else {
 	        return ResponseEntity.notFound().build(); // 返回 404 状态码
 	    }
 	}

}


    


