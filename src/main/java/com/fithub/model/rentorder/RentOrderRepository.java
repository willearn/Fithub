package com.fithub.model.rentorder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentOrderRepository extends JpaRepository<RentOrder, Integer> {
	 
	//william 自定查詢，返回 rentdate 和 renttime
    @Query("SELECT r.rentdate, r.renttime FROM RentOrder r")
    List<Object[]> findAllrentdateAndrenttime();
}
