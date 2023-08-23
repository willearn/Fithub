package com.fithub.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
import com.fithub.model.coupon.CouponRepository;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    @Autowired
    private CouponRepository couponRepository;

    @GetMapping
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable Integer id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        if (coupon.isPresent()) {
            return ResponseEntity.ok(coupon.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable Integer id, @RequestBody Coupon updatedCoupon) {
        Optional<Coupon> existingCoupon = couponRepository.findById(id);
        if (existingCoupon.isPresent()) {
            Coupon coupon = existingCoupon.get();
            coupon.setCouponname(updatedCoupon.getCouponname());
            coupon.setCouponstate(updatedCoupon.getCouponstate());
            coupon.setCoupongeneratedate(updatedCoupon.getCoupongeneratedate());
            coupon.setCouponenddate(updatedCoupon.getCouponenddate());
            coupon.setCouponcode(updatedCoupon.getCouponcode());
            coupon.setCouponamount(updatedCoupon.getCouponamount());
            coupon.setCoupondiscount(updatedCoupon.getCoupondiscount());
            coupon.setCouponceil(updatedCoupon.getCouponceil());
            coupon.setCouponused(updatedCoupon.getCouponused());
            coupon.setCouponthreshold(updatedCoupon.getCouponthreshold());
            // Set other fields here as needed
            couponRepository.save(coupon);
            return ResponseEntity.ok(coupon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Integer id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        if (coupon.isPresent()) {
            couponRepository.delete(coupon.get());
            System.out.println("OK");
            return ResponseEntity.noContent().build();
          
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
//	// 搜尋優惠券並分頁
//    @GetMapping("/page")
//    public ResponseEntity<Map<String, Object>> showCoupons(
//        @RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
//        @RequestParam(name = "selectedValue",defaultValue="3") String selected) {
//        Page<Coupon> page = couponRepository.findByPage(pageNumber, Integer.parseInt(selected));
//        Long countData = couponRepository.count(); // 使用 count 方法獲取總數據量
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("page", page);
//        response.put("countData", countData);
//
//        return ResponseEntity.ok(response);
//    }


}


    


