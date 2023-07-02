package com.sfg.course.spring6restmvc.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.sfg.course.spring6restmvc.model.CustomerDto;
import com.sfg.course.spring6restmvc.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDto> customerMap;

    public CustomerServiceImpl() {
        CustomerDto customer1 = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("CustomerDto 1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDto customer2 = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("CustomerDto 2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDto customer3 = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("CustomerDto 3")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap = new HashMap<>();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
    }

    @Override
    public Optional<CustomerDto> getCustomerById(UUID uuid) {
        return Optional.of(customerMap.get(uuid));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customer) {

        CustomerDto savedCustomer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .version(1)
                .updateDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .name(customer.getName())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public CustomerDto updateCustomerById(UUID customerId, CustomerDto customer) {
        CustomerDto existingCustomer = customerMap.get(customerId);
        existingCustomer.setName(customer.getName());

        return existingCustomer;
    }

    @Override
    public CustomerDto deleteCustomerById(UUID customerId) {
        return customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDto customer) {
        CustomerDto existing = customerMap.get(customerId);

        if (StringUtils.hasText(customer.getName())) {
            existing.setName(customer.getName());
        }
    }
}











