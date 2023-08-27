package com.fithub.model.couponcategories;

import java.util.List;

public interface ICouponCategoriesService {
	
    List<CouponCategories> getAllCouponCategories();
    
    CouponCategories getCouponCategoriesById(Integer id);
    
    CouponCategories saveCouponCategories(CouponCategories couponCategories);
    
    boolean updateCouponCategories(CouponCategories couponCategories);
    
    void deleteCouponCategories(Integer id);
    
    void deleteAllById(Iterable<Integer> selectIds);
}

