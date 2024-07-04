package com.example.customer_api.service;


import com.example.customer_api.exception.CustomerNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer(null, "Neider Urbano", "neider@example.com", "3204524545");
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.addCustomer(customer);

        assertEquals(customer, result);
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "Neider Urbano", "neider@example.com", "3204524545"),
                new Customer(2L, "Julian Bastilla", "julian@example.com", "+57 3204524545")
        );
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        assertEquals(customers, result);
    }

    @Test
    public void testGetCustomerById() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "Neider Urbano", "neider@example.com", "3204524545");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(customerId);

        assertEquals(customer, result);
    }

    @Test
    public void testGetCustomerById_NotFound() {
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(customerId));
    }

    @Test
    public void testUpdateCustomer() {
        Long customerId = 1L;
        Customer existingCustomer = new Customer(customerId, "Neider Urbano", "neider@example.com", "3204524545");
        Customer updatedCustomer = new Customer(customerId, "Julian Bastilla", "julian@example.com", "+57 3204524545");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        Customer result = customerService.updateCustomer(customerId, updatedCustomer);

        assertEquals(updatedCustomer.getId(), result.getId());
        assertEquals(updatedCustomer.getName(), result.getName());
        assertEquals(updatedCustomer.getEmail(), result.getEmail());
        assertEquals(updatedCustomer.getPhoneNumber(), result.getPhoneNumber());

        verify(customerRepository).findById(customerId);
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void testUpdateCustomer_NotFound() {
        Long customerId = 1L;
        Customer customerDetails = new Customer(customerId, "Neider Urbano", "neider@example.com", "3204524545");
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(customerId, customerDetails));
    }

    @Test
    public void testDeleteCustomer() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "Neider Urbano", "neider@example.com", "3204524545");

        when(customerRepository.save(customer)).thenReturn(customer);
        customerService.addCustomer(customer);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        customerService.deleteCustomer(customerId);

        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    public void testDeleteCustomer_NotFound() {
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomer(customerId));
    }
}
