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
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    public void testUpdateCustomer() {
        Long customerId = 1L;
        Customer existingCustomer = new Customer(customerId, "John Doe", "john@example.com", "123456789");
        Customer updatedCustomer = new Customer(customerId, "John Smith", "john.smith@example.com", "987654321");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        Customer result = customerService.updateCustomer(updatedCustomer);

        assertEquals(updatedCustomer.getId(), result.getId());
        assertEquals(updatedCustomer.getName(), result.getName());
        assertEquals(updatedCustomer.getEmail(), result.getEmail());
        assertEquals(updatedCustomer.getPhoneNumber(), result.getPhoneNumber());

        verify(customerRepository).findById(customerId);
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void testDeleteCustomer() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "John Doe", "john@example.com", "123456789");

        lenient().when(customerRepository.save(customer)).thenReturn(customer);
        customerService.addCustomer(customer);

        lenient().when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        customerService.deleteCustomer(customerId);

        verify(customerRepository, times(1)).deleteById(customerId);
    }
}
