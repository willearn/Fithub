package com.fithub.model.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService implements ICouponService {

    private final CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public Coupon getCouponById(Integer id) {
        return couponRepository.findById(id).orElse(null);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public boolean saveCoupon(Coupon coupon) {
        couponRepository.save(coupon);
        return true;
    }

    @Override
    public boolean updateCoupon(Coupon coupon) {
        couponRepository.save(coupon);
        return true;
    }

    @Override
    public boolean deleteCouponById(Integer id) {
        couponRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean existsById(Integer id) {
        return couponRepository.existsById(id);
    }

	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		couponRepository.deleteAllById(selectIds);		
	}
	
	@Override
    public String getCouponDiscount(String couponcode) {
        Coupon coupon = couponRepository.findByCouponcode(couponcode);
        if (coupon != null) {
            return coupon.getCoupondiscount();
        } else {
            // 優惠券不存在或者未找到
            return "優惠券無效";
        }
    }
	
	 @Override
    public Coupon getCoupon(String couponcode) {
        return couponRepository.findByCouponcode(couponcode);
    }
	
	 @Override
	 public void updateCouponUsedStatus(Integer couponid, String couponused) {
	        couponRepository.updateConditionById(couponid, couponused);
	}
    
}