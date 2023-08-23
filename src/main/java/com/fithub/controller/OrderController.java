package com.fithub.controller;

import com.fithub.model.order.Order;
import com.fithub.model.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.findById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.insert(order);
    }

    @PutMapping("/{id}")
    public Boolean updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        order.setOrderId(id); 
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteOrder(@PathVariable Integer id) {
        return orderService.deleteById(id);
    }
}
