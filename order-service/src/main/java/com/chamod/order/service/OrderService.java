package com.chamod.order.service;

import com.chamod.order.entity.Order;
import com.chamod.order.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepo.findById(id);
    }

    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    @Transactional
    public Order updateOrder(Long id, Order orderDetails) {
        Order existingOrder = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        // Update the fields
        existingOrder.setCustomerId(orderDetails.getCustomerId());
        existingOrder.setOrderDate(orderDetails.getOrderDate());
        existingOrder.setTotalAmount(orderDetails.getTotalAmount());
        existingOrder.setStatus(orderDetails.getStatus());
        // Update other fields as needed

        return orderRepo.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        orderRepo.delete(order);
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepo.findByCustomerId(customerId);
    }

    public List<Order> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        return orderRepo.findByOrderDateBetween(startDate, endDate);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepo.findByStatus(status);
    }

    public Map<String, Object> getOrderStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // Total number of orders
        stats.put("totalOrders", orderRepo.count());

        // Orders by status count
        Map<String, Long> ordersByStatus = new HashMap<>();
        List<String> statuses = Arrays.asList("PENDING", "PROCESSING", "SHIPPED", "DELIVERED", "CANCELLED");
        for (String status : statuses) {
            ordersByStatus.put(status, orderRepo.countByStatus(status));
        }
        stats.put("ordersByStatus", ordersByStatus);

        // Orders created today
        LocalDate today = LocalDate.now();
        stats.put("ordersToday", orderRepo.countByOrderDate(today));

        return stats;
    }
}
