package com.chamod.customer.service;

import com.chamod.customer.entity.Customer;
import com.chamod.customer.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {

        this.customerRepo = customerRepo;

    }

    @Override
    public List<Customer> getAllCustomers() {

        return customerRepo.findAll();

    }

    @Override
    public Customer getCustomerById(Long id) {

        return customerRepo.findById(id).orElse(null);

    }

    @Override
    public Customer saveCustomer(Customer customer) {

        return customerRepo.save(customer);

    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        Customer existingCustomer = customerRepo.findById(id).orElse(null);
        assert existingCustomer != null;

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setAddress(customer.getAddress());

        return customerRepo.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }

}
