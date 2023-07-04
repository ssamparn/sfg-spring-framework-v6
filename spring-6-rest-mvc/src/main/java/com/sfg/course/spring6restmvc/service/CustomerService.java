package com.sfg.course.spring6restmvc.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sfg.course.spring6restmvc.model.CustomerDto;

public interface CustomerService {

    Optional<CustomerDto> getCustomerById(UUID uuid);

    List<CustomerDto> getAllCustomers();

    CustomerDto saveNewCustomer(CustomerDto customer);

    Optional<CustomerDto> updateCustomerById(UUID customerId, CustomerDto customer);

    Boolean deleteCustomerById(UUID customerId);

    Optional<CustomerDto> patchCustomerById(UUID customerId, CustomerDto customer);
}
