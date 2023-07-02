package com.sfg.course.spring6restmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

import com.sfg.course.spring6restmvc.model.Customer;
import com.sfg.course.spring6restmvc.service.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

        log.debug("Creating New Customer - in controller");

        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", CUSTOMER_PATH + "/" + savedCustomer.getId().toString());

        return new ResponseEntity(savedCustomer, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = CUSTOMER_PATH)
    public List<Customer> listAllCustomers() {

        log.debug("Get all Customer(s) - in controller");

        return customerService.getAllCustomers();
    }

    @GetMapping(value = CUSTOMER_PATH_ID)
    public Customer getCustomerById(@PathVariable("customerId") UUID id) {

        log.debug("Get a Customer byId - in controller");

        return customerService.getCustomerById(id);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> updateCustomerByID(@PathVariable("customerId") UUID customerId,
            @RequestBody Customer customer) {

        log.debug("Updating a Customer byId - in controller");

        Customer updatedCustomer = customerService.updateCustomerById(customerId, customer);

        return new ResponseEntity(updatedCustomer, HttpStatus.OK);
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchCustomerById(@PathVariable("customerId") UUID customerId,
            @RequestBody Customer customer){

        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable("customerId") UUID customerId) {

        Customer removedCustomer = customerService.deleteCustomerById(customerId);

        return new ResponseEntity(removedCustomer, HttpStatus.NO_CONTENT);
    }

}
