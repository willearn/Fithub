package com.fithub.model.couponcategories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponCategoriesService implements ICouponCategoriesService {

    private final CouponCategoriesRepository couponCategoriesRepository;

    @Autowired
    public CouponCategoriesService(CouponCategoriesRepository couponCategoriesRepository) {
        this.couponCategoriesRepository = couponCategoriesRepository;
    }

    @Override
    public List<CouponCategories> getAllCouponCategories() {
        return couponCategoriesRepository.findAll();
    }

    @Override
    public CouponCategories getCouponCategoriesById(Integer id) {
        return couponCategoriesRepository.findById(id).orElse(null);
    }

    @Override
    public CouponCategories saveCouponCategories(CouponCategories couponCategories) {
        return couponCategoriesRepository.save(couponCategories);
    }

//    @Override
//    public CouponCategories updateCouponCategories(Integer id, CouponCategories couponCategories) {
//        CouponCategories existingCouponCategories = couponCategoriesRepository.findById(id).orElse(null);
//        if (existingCouponCategories != null) {
//            couponCategories.setCouponcategoriesid(id);
//            return couponCategoriesRepository.save(couponCategories);
//        }
//        return null; // Or throw an exception indicating not found
//    }

    @Override
    public void deleteCouponCategories(Integer id) {
        couponCategoriesRepository.deleteById(id);
    }

	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		couponCategoriesRepository.deleteAllById(selectIds);
		
	}

    public boolean updateCouponCategories(CouponCategories updatedCouponCategories) {
        if (updatedCouponCategories.getCouponcategoriesid() != null && couponCategoriesRepository.existsById(updatedCouponCategories.getCouponcategoriesid())) {
            couponCategoriesRepository.save(updatedCouponCategories);
            return true;
        }
        return false;
    }
    
}
