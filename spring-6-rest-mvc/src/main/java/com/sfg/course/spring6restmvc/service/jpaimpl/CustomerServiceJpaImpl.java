package com.sfg.course.spring6restmvc.service.jpaimpl;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.sfg.course.spring6restmvc.mappers.CustomerMapper;
import com.sfg.course.spring6restmvc.model.CustomerDto;
import com.sfg.course.spring6restmvc.repositories.CustomerRepository;
import com.sfg.course.spring6restmvc.service.CustomerService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJpaImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public Optional<CustomerDto> getCustomerById(UUID uuid) {
        return Optional.ofNullable(customerMapper
                .customerToCustomerDto(customerRepository.findById(uuid).orElse(null)));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customer) {
        return null;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDto customer) {

    }

    @Override
    public void deleteCustomerById(UUID customerId) {

    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDto customer) {

    }

}
