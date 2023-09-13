package com.fithub.model.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fithub.model.employee.Employee;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
//	@Modifying 是一個注解，通常用於 Spring Data JPA 中用於標識數據庫修改操作的方法上。它告訴 Spring Data JPA
//	，這個方法將執行一個修改（INSERT、UPDATE、DELETE）操作，而不僅僅是一個查詢操作。具體解釋如下：
//
//	用途：@Modifying 注解用於標識一個方法是用於修改數據庫記錄的，而不僅僅是查詢數據庫。這是在進行寫操作（如更新或刪除記錄）時非常重要的。
//
//	與事務一起使用：通常與 @Transactional 注解一起使用，以確保這個修改操作在一個事務中執行。
//	這是因為寫操作應該在事務中執行，以保證數據的一致性和完整性。
	
	// Archer 更新訂單已付款
    @Modifying
    @Transactional
    @Query("UPDATE Order r SET  r.orderCondition = :orderCondition WHERE r.orderId = :orderId")
	void updateConditionById(@Param("orderId") Integer orderId,@Param("orderCondition") String orderCondition);

	// XiaoQing
	@Query("from Order where memberId = :memberid")
	List<Order> getOrdersByMemberId(@Param("memberid") Integer memberid);

}
