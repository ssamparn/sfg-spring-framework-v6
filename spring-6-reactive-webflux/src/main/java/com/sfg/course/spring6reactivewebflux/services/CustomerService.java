package com.sfg.course.spring6reactivewebflux.services;

import com.sfg.course.spring6reactivewebflux.models.CustomerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Flux<CustomerDto> listCustomers();

    Mono<CustomerDto> getCustomerById(Integer customerId);

    Mono<CustomerDto> saveNewCustomer(CustomerDto customerDTO);

    Mono<CustomerDto> updateCustomer(Integer customerId, CustomerDto customerDTO);

    Mono<CustomerDto> patchCustomer(Integer customerId, CustomerDto customerDTO);

    Mono<Void> deleteCustomerById(Integer customerId);
}
