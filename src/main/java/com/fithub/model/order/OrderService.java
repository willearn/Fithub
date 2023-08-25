package com.fithub.model.order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
	public Boolean update(Order order) {
		Boolean result = exitsById(order.getOrderId());
		if (result) {
			orderRepo.saveAndFlush(order);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(Integer id) {
		Boolean result = orderRepo.existsById(id);

		if (result) {
			orderRepo.deleteById(id);
			return true;
		}
		return false;
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

	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		orderRepo.deleteAllById(selectIds);
		
	}
	

}
