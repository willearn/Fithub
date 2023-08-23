package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.coupon.Coupon;
import com.fithub.model.coupon.CouponService;

@RestController
@RequestMapping("/coupons2")
public class CouponController2 {

//    @Autowired
//	private CouponService couponService;
//
//    @GetMapping
//    public ResponseEntity<List<Coupon>> getAllCoupons() {
//        List<Coupon> coupons = couponService.getAllCoupons();
//        return ResponseEntity.ok(coupons);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Coupon> getCouponById(@PathVariable Integer id) {
//        Coupon coupon = couponService.getCouponById(id);
//        if (coupon != null) {
//            return ResponseEntity.ok(coupon);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> createCoupon(@RequestBody Coupon coupon) {
//        couponService.saveCoupon(coupon);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateCoupon(@PathVariable Integer id, @RequestBody Coupon coupon) {
//        if (couponService.getCouponById(id) != null) {
//            coupon.setCouponid(id);
//            couponService.saveCoupon(coupon);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCoupon(@PathVariable Integer id) {
//        if (couponService.getCouponById(id) != null) {
//            couponService.deleteCouponById(id);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
