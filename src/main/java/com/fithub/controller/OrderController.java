package com.fithub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fithub.model.order.Order;
import com.fithub.model.order.OrderService;
import com.fithub.model.orderitem.OrderItem;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        try {
            List<Order> orders = orderService.findAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        try {
            Order order = orderService.findById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
  	@PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            Order createOrder =  orderService.createOrder(order);
            return new ResponseEntity<>(createOrder,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    



    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
        try {
            // Assuming you have a way to identify the order, possibly using order.getOrderId()
            Boolean updated = orderService.update(order);
            
            if (updated) {
                return ResponseEntity.ok("Order updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Order not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        try {
            Boolean deleted = orderService.deleteById(id);
            if (deleted) {
                return new ResponseEntity<>("Order deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order with ID " + id + " not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // 刪除多筆訂單
  	@DeleteMapping("/delete/multiple")
  	public ResponseEntity<?> deleteMultipleRentOrders(@RequestBody List<Integer> ids) {
  		try {
  			orderService.deleteAllById(ids);
  			return new ResponseEntity<>(HttpStatus.OK);
  		} catch (Exception e) {
  			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  		}
  	} 	

  	
}
