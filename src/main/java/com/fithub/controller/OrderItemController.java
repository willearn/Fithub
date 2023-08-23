package com.fithub.controller;

import com.fithub.model.orderitem.OrderItem;
import com.fithub.model.orderitem.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.findAll();
    }

    @PostMapping
    public OrderItem addOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.insert(orderItem);
    }

    @PutMapping("/{id}")
    public Boolean updateOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem) {
        orderItem.setOrderId(id);
        return orderItemService.update(orderItem);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteOrderItem(@PathVariable Integer id) {
        return orderItemService.deleteById(id);
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Integer id) {
        return orderItemService.findById(id);
    }
}
