package com.sfg.course.spring6restmvc.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sfg.course.spring6restmvc.model.Customer;

public interface CustomerService {

    Optional<Customer> getCustomerById(UUID uuid);

    List<Customer> getAllCustomers();

    Customer saveNewCustomer(Customer customer);

    Customer updateCustomerById(UUID customerId, Customer customer);

    Customer deleteCustomerById(UUID customerId);

    void patchCustomerById(UUID customerId, Customer customer);
}
