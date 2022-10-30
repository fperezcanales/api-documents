package com.example.demo.customer.controller;

import com.example.demo.customer.entities.Customer;
import com.example.demo.customer.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/customer")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public Customer findCustomer(){
        return this.customerService.findByPhone();
    }
}
