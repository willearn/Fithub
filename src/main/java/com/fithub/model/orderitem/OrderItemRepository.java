package com.fithub.model.orderitem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	OrderItem findByOrderId(Integer orderId);

}
