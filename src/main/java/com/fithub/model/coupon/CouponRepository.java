package com.fithub.model.coupon;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Coupon findByCouponcode(String couponcode);
	
	@Modifying
    @Transactional
    @Query("UPDATE Coupon c SET  c.couponused = :couponused WHERE c.couponid = :couponid")
	void updateConditionById(@Param("couponid") Integer couponid,@Param("couponused") String couponused);
}
