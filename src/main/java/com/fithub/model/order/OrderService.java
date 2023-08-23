package com.fithub.model.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Override
	public List<Order> findAll() {
		return orderRepo.findAll();

	}

	@Override
	public Order insert(Order order) {
		return orderRepo.save(order);

	}

	@Override
	public void updateById(Order order) {
		Boolean result = exitsById(order.getOrderId());
		if (result) {
			orderRepo.saveAndFlush(order);
		}
	}

	@Override
	public void deleteById(Integer id) {
		Boolean result = orderRepo.existsById(id);

		if (result) {
			orderRepo.deleteById(id);
		}
	}

	@Override
	public Boolean exitsById(Integer id) {
		return orderRepo.existsById(id);

	}

	@Override
	public Order findById(Integer id) {
		Optional<Order> result = orderRepo.findById(id);
		return result.get();
	}

}
