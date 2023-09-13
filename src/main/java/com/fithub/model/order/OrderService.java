package com.fithub.model.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fithub.model.course.Course;
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
	public Boolean updateConditionById(Integer orderId, String orderCondition) {
		try {
			orderRepo.updateConditionById(orderId, orderCondition);
			return true;
		} catch (Exception e) {
			return false;
		}
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
//	@Transactional
//	public Order createOrder(Order order) {
//
//        Order savedOrder = orderRepo.save(order);
//        
//       
//        List<OrderItem> orderItems = new ArrayList<>();
//        
//        for (OrderItem item : order.getOrderItem()) {
//            item.setOrderId(savedOrder.getOrderId()); 
//            item.setClassId(item.getClassId()); 
//            item.setCouponId(item.getCouponId()); 
//
//            orderItems.add(orderItemRepo.save(item));            
//        }
//        
//        savedOrder.setOrderItem(orderItems);
//        
//        return savedOrder;
//    }
	
	@Override
    @Transactional
    public Order createOrder(Order order) {

        try {
        // 取得複製orderitem集合
        int i = 0;
        int[] classids = new int[order.getOrderItem().size()];
        int[] couponids = new int[order.getOrderItem().size()];


        for (OrderItem orderitem : order.getOrderItem()) {
            classids[i] = orderitem.getClassId();
            couponids[i] = orderitem.getCouponId();
            i++;
        }


        // 清空OrderItem
        order.setOrderItem(null);

        // 新增訂單取得訂單編號
        Order savedOrder = orderRepo.save(order);
        List<OrderItem> orderItems = new ArrayList<>();

        for (int x = 0;x<classids.length;x++) {
            //建立訂單項目 塞入新集合
            OrderItem orderItem = new  OrderItem();
            orderItem.setOrderId(savedOrder.getOrderId());
            orderItem.setClassId(classids[x]);
            orderItem.setCouponId(couponids[x]);
            orderItems.add(orderItem);
        }


        order.setOrderItem(orderItems);
        Order resultOrder = orderRepo.save(order);

        return resultOrder;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	@Override
	public Page<Order> findByPage(Integer pageNumber, Integer dataSize) {
		// 按照orderId降冪排序
		PageRequest pgb =PageRequest.of(pageNumber-1, dataSize, Sort.Direction.DESC, "orderId");
		
		Page<Order> page = orderRepo.findAll(pgb);
		return page;
	}

	@Override
	public List<Order> getOrdersByMemberId(Integer memberid) {
		try {
			List<Order> resultBeans = orderRepo.getOrdersByMemberId(memberid);
			return resultBeans;
		} catch (Exception e) {
			return null;
		}
	}

		

}
