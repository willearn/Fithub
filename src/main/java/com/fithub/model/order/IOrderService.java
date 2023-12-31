package com.fithub.model.order;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.fithub.model.course.Course;
import com.fithub.model.orderitem.OrderItem;
import com.fithub.model.rentorder.RentOrder;

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
	
	List<Order> getOrdersByMemberId(Integer memberid);


	int findOrdertotalAmount();

	
	// 全部訂單分頁功能
	Page<Order> findAllPage(String date, int number, int size);

	Page<Order> findPageByMemberId(String date, Integer memberid, int number, int size);
	
	// ChrisLafolia 返回在指定classId及memberId的的課程購買數量
	Map<String, Object> getAlreadyBuyAmountByClassIdANDMemberid(int classId, int memberId);

}
