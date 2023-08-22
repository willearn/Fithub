package com.fithub.model.couponcatagories;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "couponcatagories")
public class CouponCatagories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couponcatagoriesid")
    private Integer couponcatagoriesid;

    private String couponcatagoriesname;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "couponCatagories")
    private List<Coupon> coupons = new ArrayList<>(); 

    public CouponCatagories() {
        
    }
}

