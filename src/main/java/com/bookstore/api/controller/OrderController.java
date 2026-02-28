package com.bookstore.api.controller;

import com.bookstore.api.entity.Order;
import com.bookstore.api.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place an order from cart
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(Authentication authentication) {
        String username = authentication.getName();
        Order order = orderService.placeOrder(username);
        return ResponseEntity.ok(order);
    }

    // Get logged in user's orders
    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getMyOrders(Authentication authentication) {
        String username = authentication.getName();
        List<Order> orders = orderService.getMyOrders(username);
        return ResponseEntity.ok(orders);
    }

    // Admin: get all orders
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}