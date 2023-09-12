package com.fithub.model.coupon;

import java.util.List;

public interface ICouponService {
    
    Coupon getCouponById(Integer couponid);

    List<Coupon> getAllCoupons();

    boolean saveCoupon(Coupon coupon);

    boolean updateCoupon(Coupon coupon);

    boolean deleteCouponById(Integer couponid);

    boolean existsById(Integer couponid);
    
    void deleteAllById(Iterable<Integer> selectIds);
    
    String getCouponDiscount(String couponcode);
}
