package com.fithub.model.orderitem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderItemService implements IOrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepo;
	
	@Override
	public List<OrderItem> findAll() {
		return orderItemRepo.findAll();

	}

	@Override
	public OrderItem insert(OrderItem orderItem) {
		return orderItemRepo.save(orderItem);

	}

	@Override
	public void updateById(OrderItem orderItem) {
		Boolean result = exitsById(orderItem.getOrderId());
		if (result) {
			orderItemRepo.saveAndFlush(orderItem);
		}
	}

	@Override
	public void deleteById(Integer id) {
		Boolean result = orderItemRepo.existsById(id);

		if (result) {
			orderItemRepo.deleteById(id);
		}
	}

	@Override
	public Boolean exitsById(Integer id) {
		return orderItemRepo.existsById(id);

	}

	@Override
	public OrderItem findById(Integer id) {
		Optional<OrderItem> result = orderItemRepo.findById(id);
		return result.get();
	}

}
