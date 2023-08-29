package com.fithub.model.orderitem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fithub.model.order.Order;

import jakarta.transaction.Transactional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	OrderItem findByOrderId(Integer orderId);

	@Transactional
	@Modifying
	@Query("from OrderItem where orderId = :orderid")
	List<OrderItem> getAllOrderItemByOrderId(@Param("orderid") Integer orderid);
}
