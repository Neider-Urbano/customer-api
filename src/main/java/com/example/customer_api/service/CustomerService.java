package com.example.customer_api.service;

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
        return customerRepository.save(customer);
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

        extingCustomer.setName(customer.getName());
        extingCustomer.setEmail(customer.getEmail());
        extingCustomer.setPhoneNumber(customer.getPhoneNumber());

        return customerRepository.save(extingCustomer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found"));
        customerRepository.delete(customer);
    }
}
