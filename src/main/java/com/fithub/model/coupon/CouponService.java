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
    
}