package com.example.demo.customer.service;

import com.example.demo.customer.entities.Customer;
import com.example.demo.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        this.customerService = new CustomerService(this.repository);
    }

    @Test
    void shouldReturnCustomerWhomMatchesFilter(){
        final Customer customer = new Customer("fernando", "1");
        final List<Customer> expectedList = new ArrayList<>();
        expectedList.add(customer);

        when(this.repository.findById(1L)).thenReturn(customer);

        final List<Customer> actual = this.customerService.findByPhone("1");
        assertEquals(expectedList, actual);
    }

    /*@Test
    void shouldReturnEmptyListWhenNotMatchesFilter(){
        final List<Customer> expectEmptyList = new ArrayList<>();
        when(this.repository.findById(0)).thenReturn([]);

        final List<Customer> actual = this.customerService.findByPhone("0");
        assertEquals(expectEmptyList, actual);
    }*/
}