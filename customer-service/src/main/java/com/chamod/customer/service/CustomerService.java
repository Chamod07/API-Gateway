package com.chamod.customer.service;

import com.chamod.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    void deleteCustomer(Long id);
}
