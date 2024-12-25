package com.chamod.customer.service;

import com.chamod.customer.entity.Customer;
import com.chamod.customer.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

}
