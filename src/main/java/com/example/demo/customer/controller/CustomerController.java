package com.example.demo.customer.controller;

import com.example.demo.customer.entities.Customer;
import com.example.demo.customer.service.CustomerService;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class CustomerController {

    public static final String ONLY_DIGITS = "\\d+";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path="/customer")
    public ResponseEntity<Object> findCustomer(@RequestParam(required = true) String filter){
        if(!filter.matches(ONLY_DIGITS)){
            return ResponseEntity.badRequest().body("Filter is required and numeric");
        }
        return ResponseEntity.ok().body(this.customerService.findByPhone(filter));
    }
}
