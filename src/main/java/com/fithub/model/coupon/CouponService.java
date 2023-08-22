package com.fithub.model.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    private final CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon getCouponById(Integer id) {
        return couponRepository.findById(id).orElse(null);
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public void saveCoupon(Coupon coupon) {
        couponRepository.save(coupon);
    }

    public void deleteCouponById(Integer id) {
        couponRepository.deleteById(id);
    }
}

