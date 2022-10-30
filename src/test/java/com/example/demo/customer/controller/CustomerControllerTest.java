package com.example.demo.customer.controller;

import com.example.demo.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private CustomerController controller;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        this.controller = new CustomerController(this.customerService);
    }

    @Test
    void  shouldReturnBadRequestWithEmptyFilter(){
        final ResponseEntity expected = ResponseEntity.badRequest()
                .body("Filter is required and numeric");
        final var response = this.controller.findCustomer("not_number");
        assertEquals(expected, response);
    }

    @Test
    void  shouldReturnSuccessResponseWithFilterNumber(){
        final ResponseEntity expected = ResponseEntity.ok().body(new ArrayList<>());
        final ResponseEntity response = this.controller.findCustomer("1");
        assertEquals(expected, response);
    }

}