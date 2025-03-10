package com.chamod.order.controller;

import com.chamod.order.entity.Order;
import com.chamod.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order API", description = "Operations related to order management")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/all")
    @Operation(summary = "Get all orders", description = "Retrieves a list of all orders in the system")
    public Object getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Retrieves a specific order by its unique identifier")
    @ApiResponse(responseCode = "200", description = "Order found")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Order> getOrderById(
            @Parameter(description = "ID of the order to retrieve") @PathVariable Long id) {
        return service.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create an order", description = "Creates a new order in the system")
    @ApiResponse(responseCode = "201", description = "Order created successfully")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOrder(order));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an order", description = "Updates an existing order's information")
    @ApiResponse(responseCode = "200", description = "Order updated successfully")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Order> updateOrder(
            @Parameter(description = "ID of the order to update") @PathVariable Long id,
            @RequestBody Order order) {
        return ResponseEntity.ok(service.updateOrder(id, order));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order", description = "Removes an order from the system")
    @ApiResponse(responseCode = "204", description = "Order deleted successfully")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Void> deleteOrder(
            @Parameter(description = "ID of the order to delete") @PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get orders by customer", description = "Retrieves all orders for a specific customer")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    public List<Order> getOrdersByCustomer(
            @Parameter(description = "ID of the customer") @PathVariable Long customerId) {
        return service.getOrdersByCustomer(customerId);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get orders by date range", description = "Retrieves all orders within a specific date range")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    public List<Order> getOrdersByDateRange(
            @Parameter(description = "Start date of the range") @RequestParam LocalDate startDate,
            @Parameter(description = "End date of the range") @RequestParam LocalDate endDate) {
        return service.getOrdersByDateRange(startDate, endDate);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get orders by status", description = "Retrieves all orders with a specific status")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    public List<Order> getOrdersByStatus(
            @Parameter(description = "Status of the orders to retrieve") @PathVariable String status) {
        return service.getOrdersByStatus(status);
    }

    @GetMapping("/statistics")
    @Operation(summary = "Get order statistics", description = "Retrieves statistics about orders")
    @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully")
    public Map<String, Object> getOrderStatistics() {
        return service.getOrderStatistics();
    }
}
