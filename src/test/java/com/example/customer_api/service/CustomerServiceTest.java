package com.example.customer_api.service;


import com.example.customer_api.model.Customer;
import com.example.customer_api.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer(null, "John Doe", "john@example.com", "123456789");
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.addCustomer(customer);

        assertEquals(customer, result);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "John Doe", "john@example.com", "123456789"),
                new Customer(2L, "Jane Smith", "jane@example.com", "987654321")
        );
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        assertEquals(customers, result);
    }

    @Test
    public void testGetCustomerById() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "John Doe", "john@example.com", "123456789");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomerById(customerId);

        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
    }
}
