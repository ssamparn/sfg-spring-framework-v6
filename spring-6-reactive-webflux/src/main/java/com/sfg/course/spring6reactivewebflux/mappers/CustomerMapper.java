package com.sfg.course.spring6reactivewebflux.mappers;

import com.sfg.course.spring6reactivewebflux.domains.Customer;
import com.sfg.course.spring6reactivewebflux.models.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDto dto);

    CustomerDto customerToCustomerDto(Customer customer);
}
