package com.fithub.controller;

import com.fithub.model.orderitem.OrderItem;
import com.fithub.model.orderitem.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderItem> getOrderItemByOrderId(@PathVariable Integer orderId) {
        OrderItem orderItem = orderItemService.getOrderItemByOrderId(orderId);
        if (orderItem != null) {
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllOrderItems() {
        try {
            List<OrderItem> orderItems = orderItemService.findAll();
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOrderItemById(@PathVariable Integer id) {
//        try {
//            OrderItem orderItem = orderItemService.findById(id);
//            return new ResponseEntity<>(orderItem, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    

    @PostMapping
    public ResponseEntity<?> addOrderItem(@RequestBody OrderItem orderItem) {
        try {
            OrderItem addedOrderItem = orderItemService.insert(orderItem);
            return new ResponseEntity<>(addedOrderItem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem) {
        try {
            orderItem.setOrderId(id);
            Boolean updated = orderItemService.update(orderItem);
            if (updated) {
                return new ResponseEntity<>("Order item updated successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order item with ID " + id + " not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Integer id) {
        try {
            Boolean deleted = orderItemService.deleteById(id);
            if (deleted) {
                return new ResponseEntity<>("Order item deleted successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order item with ID " + id + " not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 刪除多筆訂單
  	@DeleteMapping("/delete/multiple")
  	public ResponseEntity<?> deleteMultipleRentOrders(@RequestBody List<Integer> ids) {
  		try {
  			orderItemService.deleteAllById(ids);
  			return new ResponseEntity<>(HttpStatus.OK);
  		} catch (Exception e) {
  			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  		}
  	}
}
