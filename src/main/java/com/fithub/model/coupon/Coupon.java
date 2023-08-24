package com.fithub.model.coupon;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fithub.model.couponcategories.CouponCategories;
import com.fithub.model.orderitem.OrderItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "coupon")
public class Coupon {
    @Id
    @Column(name = "couponid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couponid;
    
    @Column(name = "couponcategoriesid")
    private Integer couponcategoriesid;
    
    private String couponname;
    private String couponstate;
    private String coupongeneratedate;
    private String couponenddate;
    private String couponcode;
    private String couponamount;
    private String coupondiscount;
    private String couponceil;
    private String couponused;
    private String couponthreshold;
    
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couponcategoriesid", insertable = false, updatable = false)
    private CouponCategories CouponCategories;
    
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coupon")
//	@JsonIgnore
//	private Set<OrderItem> orderItem = new HashSet<OrderItem>();

    public Coupon() {
    }

}


