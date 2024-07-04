package com.example.customer_api.controller;

import com.example.customer_api.model.Customer;
import com.example.customer_api.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Add new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.addCustomer((customer)));
    }

    @Operation(summary = "Get all Customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully obtained Customers")
    })
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Operation(summary = "Get a customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully obtained Customer"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @Operation(summary = "Update a Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully updated"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @Operation(summary = "Delete a customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        String result = customerService.deleteCustomer(id);
        return ResponseEntity.ok(result);
    }
}
