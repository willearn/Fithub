package com.fithub.model.orderitem;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	OrderItem findByOrderId(Integer orderId);

	@Transactional
	@Modifying
	@Query("from OrderItem where orderId = :orderid")
	List<OrderItem> getAllOrderItemByOrderId(@Param("orderid") Integer orderid);

	// Chrislafolia，返回在指定classId的課程購買人數
	@Query("SELECT oi.classId classId, count(oi.orderId) orderAmount "
			+ "FROM OrderItem oi WHERE  oi.classId = :classId GROUP BY oi.classId")
	Map<String, Object> getOrderItemAmountByClassId(@Param("classId") int classId);
}
