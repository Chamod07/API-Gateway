package com.chamod.order_service.controller;

import com.chamod.order_service.entity.Order;
import com.chamod.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/all")
    public Object getAllOrders() {
        return service.getAllOrders();
    }

    @PostMapping
    public Object createOrder(@RequestBody Order order) {
        return service.createOrder(order);
    }

}
