package com.chamod.customer.controller;

import com.chamod.customer.entity.Customer;
import com.chamod.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer API", description = "Operations related to customer management")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    @Operation(summary = "Get all customers", description = "Retrieves a list of all customers in the system")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Retrieves a specific customer by their unique identifier")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public ResponseEntity<Customer> getCustomerById(
            @Parameter(description = "ID of the customer to retrieve") @PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a customer", description = "Adds a new customer to the system")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a customer", description = "Updates an existing customer's information")
    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public ResponseEntity<Customer> updateCustomer(
            @Parameter(description = "ID of the customer to update") @PathVariable Long id,
            @RequestBody Customer customer) {
        Optional<Customer> updatedCustomer = customerService.updateCustomer(id, customer);
        return updatedCustomer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a customer", description = "Removes a customer from the system")
    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "ID of the customer to delete") @PathVariable Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search customers", description = "Search customers based on various criteria")
    public List<Customer> searchCustomers(
            @Parameter(description = "Name to search for") @RequestParam(required = false) String name,
            @Parameter(description = "Email to search for") @RequestParam(required = false) String email) {
        return customerService.searchCustomers(name, email);
    }

    @GetMapping("/count")
    @Operation(summary = "Get customer count", description = "Returns the total number of customers")
    public ResponseEntity<Long> getCustomerCount() {
        return ResponseEntity.ok(customerService.getCustomerCount());
    }

    @GetMapping("/stats")
    @Operation(summary = "Get customer statistics", description = "Returns various customer statistics for dashboard")
    public ResponseEntity<Map<String, Object>> getCustomerStatistics() {
        return ResponseEntity.ok(customerService.getCustomerStatistics());
    }
}
