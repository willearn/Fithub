package com.fithub.model.order;

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
	
	@Override
    @Transactional
    public Order createOrder(Order order) {
        // 在这里进行订单的创建逻辑
        // 可以设置其他字段的值，然后保存订单
        Order savedOrder = orderRepo.save(order);

        // 遍历订单项，为每个订单项设置关联订单并保存
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(savedOrder);
            orderItemRepo.save(orderItem);
        }

        return savedOrder;
    }

}
