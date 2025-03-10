package com.chamod.customer.service;

import com.chamod.customer.entity.Customer;
import com.chamod.customer.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepo.findById(id);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public Optional<Customer> updateCustomer(Long id, Customer customerDetails) {
        Optional<Customer> existingCustomer = customerRepo.findById(id);

        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            // Update only non-null fields
            if (customerDetails.getName() != null) {
                customer.setName(customerDetails.getName());
            }
            if (customerDetails.getEmail() != null) {
                customer.setEmail(customerDetails.getEmail());
            }
            if (customerDetails.getPhone() != null) {
                customer.setPhone(customerDetails.getPhone());
            }
            if (customerDetails.getAddress() != null) {
                customer.setAddress(customerDetails.getAddress());
            }
            return Optional.of(customerRepo.save(customer));
        }

        return Optional.empty();
    }

    public boolean deleteCustomer(Long id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Customer> searchCustomers(String name, String email) {
        List<Customer> customers = customerRepo.findAll();

        return customers.stream()
                .filter(customer -> (name == null
                        || customer.getName() != null && customer.getName().toLowerCase().contains(name.toLowerCase()))
                        &&
                        (email == null || customer.getEmail() != null
                                && customer.getEmail().toLowerCase().contains(email.toLowerCase())))
                .collect(Collectors.toList());
    }

    public Long getCustomerCount() {
        return customerRepo.count();
    }

    public Map<String, Object> getCustomerStatistics() {
        Map<String, Object> stats = new HashMap<>();
        List<Customer> customers = customerRepo.findAll();

        stats.put("totalCustomers", customers.size());

        // Calculate more statistics for the dashboard
        // Example: Domain distribution
        Map<String, Long> emailDomainStats = customers.stream()
                .filter(c -> c.getEmail() != null && c.getEmail().contains("@"))
                .collect(Collectors.groupingBy(
                        c -> c.getEmail().substring(c.getEmail().indexOf("@") + 1),
                        Collectors.counting()));
        stats.put("emailDomainDistribution", emailDomainStats);

        // Example: Recently added customers (assuming you have a creation date field)
        stats.put("lastUpdated", LocalDateTime.now());

        return stats;
    }
}
