package com.fithub.model.orderitem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService implements IOrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepo;
	
	@Override
	public List<OrderItem> findAll() {
		return orderItemRepo.findAll();

	}
	
	@Override
    public List<OrderItem> findAllWithDetails() {
        List<OrderItem> orderItems = orderItemRepo.findAll();
        for (OrderItem orderItem : orderItems) {
            // 访问关联属性以触发延迟加载
            orderItem.getClasses().getClassDate(); // 这里假设Classes类中有一个获取类名的方法
            orderItem.getCoupon().getCoupondiscount(); // 这里假设Coupon类中有一个获取优惠码的方法
        }
        return orderItems;
    }

	@Override
	public OrderItem insert(OrderItem orderItem) {
		return orderItemRepo.save(orderItem);

	}

	@Override
	public Boolean update(OrderItem orderItem) {
		Boolean result = exitsById(orderItem.getOrderId());
		if (result) {
			orderItemRepo.saveAndFlush(orderItem);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(Integer id) {
		Boolean result = orderItemRepo.existsById(id);

		if (result) {
			orderItemRepo.deleteById(id);
			return true;
		}
		return false;
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

	@Override
	public void deleteAllById(Iterable<Integer> selectIds) {
		orderItemRepo.deleteAllById(selectIds);
		
	}
	

}
