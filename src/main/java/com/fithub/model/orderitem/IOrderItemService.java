package com.fithub.model.orderitem;

import java.util.List;

public interface IOrderItemService {

	// 查詢全部
	public List<OrderItem> findAll();

	// 新增單筆活動
	public OrderItem insert(OrderItem orderItem);

	// 修改單筆
	public Boolean update(OrderItem orderItem);

	// 刪除單筆
	public Boolean deleteById(Integer id);

	// 確認id存在
	public Boolean exitsById(Integer id);
	
	// 查詢單筆
	public OrderItem findById(Integer id);
}
