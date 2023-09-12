package com.fithub.model.coupon;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findByCouponcode(String couponcode);
}
