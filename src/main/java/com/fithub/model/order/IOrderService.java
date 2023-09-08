package com.fithub.model.order;

import java.util.List;

import com.fithub.model.orderitem.OrderItem;

public interface IOrderService {

	// 查詢全部
	public List<Order> findAll();

	// 新增單筆活動
	public Order insert(Order order);

	// 修改單筆
	public Boolean update(Order order);
	
	Boolean updateConditionById(Integer orderId, String orderCondition);

	// 刪除單筆
	public Boolean deleteById(Integer id);

	// 確認id存在
	public Boolean exitsById(Integer id);
	
	// 查詢單筆
	public Order findById(Integer id);
	
	void deleteAllById(Iterable<Integer> selectIds);

//	Order createOrder(Order order, OrderItem[] orderItems);

	Order createOrder(Order order);
}
