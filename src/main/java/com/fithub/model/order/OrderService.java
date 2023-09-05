package com.fithub.model.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fithub.model.orderitem.OrderItem;
import com.fithub.model.orderitem.OrderItemRepository;



@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	
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
	@Transactional
	public Order createOrder(Order order) {
        // 这里可以进行一些订单属性的验证和处理
        // ...
        
        // 保存订单
        Order savedOrder = orderRepo.save(order);
        
        // 创建订单项
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItem item : order.getOrderItem()) {
            item.setOrderId(savedOrder.getOrderId());
            orderItems.add(orderItemRepo.save(item));
        }
        
        savedOrder.setOrderItem(orderItems);
        
        return savedOrder;
    }

}
