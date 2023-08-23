package com.fithub.model.couponcategories;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fithub.model.coupon.Coupon;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "couponcategories")
public class CouponCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couponcategoriesid")
    private Integer couponcategoriesid;

    private String couponcategoriesname;
    
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "CouponCategories")
    private List<Coupon> coupons = new ArrayList<>(); 

    public CouponCategories() {
        
    }
}

