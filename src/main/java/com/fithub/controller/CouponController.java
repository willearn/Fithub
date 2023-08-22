package com.fithub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.coupon.Coupon;
import com.fithub.model.coupon.CouponRepository;

@RestController
public class CouponController {
    
    @Autowired
    private CouponRepository couponDao;
    
//    @GetMapping("/coupons/{couponid}")
//    public ResponseEntity<?> getCouponById(@PathVariable Integer couponid) {
//        Optional<Coupon> couponOptional = couponDao.findById(couponid);
//        
//        if (couponOptional.isPresent()) {
//            Coupon coupon = couponOptional.get();
//            return ResponseEntity.ok(coupon);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//        
//    }
    
//    @GetMapping("/coupons")
//    public ResponseEntity<List<Coupon>> getAllCoupons() {
//        List<Coupon> coupons = couponDao.findAll();
//        System.out.println("-------------------------------------------");
//        if (coupons.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.ok(coupons);
//        }
//    }
}

