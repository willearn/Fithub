package com.fithub.model.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fithub.model.employee.Employee;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	// XiaoQing
	@Query("from Order where memberId = :memberid")
	List<Order> getOrdersByMemberId(@Param("memberid") Integer memberid);

}
