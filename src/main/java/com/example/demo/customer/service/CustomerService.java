package com.example.demo.customer.service;
import com.example.demo.customer.entities.Customer;
import com.example.demo.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findByPhone(String phoneNumber){
        List<Customer> result = new ArrayList<>();
        result.add(customerRepository.findById(Long.parseLong(phoneNumber)));
        return result;
    }
}
