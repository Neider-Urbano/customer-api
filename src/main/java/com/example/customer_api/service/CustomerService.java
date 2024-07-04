package com.example.customer_api.service;

import com.example.customer_api.exception.CustomerCreationException;
import com.example.customer_api.exception.CustomerNotFoundException;
import com.example.customer_api.model.Customer;
import com.example.customer_api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer){
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new CustomerCreationException("An error occurred while creating the customer: " + e.getMessage(), e);
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found"));
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Customer extingCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found"));

        try {
            extingCustomer.setName(customer.getName());
            extingCustomer.setEmail(customer.getEmail());
            extingCustomer.setPhoneNumber(customer.getPhoneNumber());

            return customerRepository.save(extingCustomer);
        } catch (Exception e) {
            throw new CustomerCreationException("An error occurred while creating the customer: " + e.getMessage(), e);
        }
    }

    public String deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found"));
        try {
            customerRepository.delete(customer);
            return "Customer with id " + id + " has been deleted";
        } catch (Exception e) {
            throw new CustomerCreationException("An error occurred while creating the customer: " + e.getMessage(), e);
        }
    }
}
